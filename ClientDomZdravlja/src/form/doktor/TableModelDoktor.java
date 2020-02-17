/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.doktor;

import controler.Controller;
import domain.Doktor;
import domain.VrstaSpecijaliste;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rastko
 */
public class TableModelDoktor extends AbstractTableModel implements Runnable {

    private List<Doktor> doktori;
    private List<Doktor> doktoriPretraga;
    String[] index = new String[]{"Ime", "Prezime", "Broj ordinacije", "Vrsta specijaliste", "Broj telefona", "Email"};
    private String kriterijum;
    private FormDoktorTable formDoktorTable;
    public TableModelDoktor(FormDoktorTable formDoktorTable) throws Exception {
        this.formDoktorTable=formDoktorTable;
        doktori = Controller.getInstance().getAllDoctors();
        kriterijum = "";

    }

    public TableModelDoktor(VrstaSpecijaliste vr,FormDoktorTable formDoktorTable) throws Exception {
        this.formDoktorTable=formDoktorTable;
        List<Doktor> pom = Controller.getInstance().getAllDoctors();
        doktori = new LinkedList<>();
        for (Doktor d : pom) {
            if (d.getVrstaSpecijaliste().equals(vr)) {
                doktori.add(d);
            }
        }
    }

    public void refreshTable() throws Exception {
        doktori = Controller.getInstance().getAllDoctors();
        if (!kriterijum.equals("")) {
            doktoriPretraga = new LinkedList<>();
            for (Doktor d : doktori) {
                if (d.getPrezime().toLowerCase().contains(kriterijum.toLowerCase())) {
                    doktoriPretraga.add(d);
                }
            }
            doktori = doktoriPretraga;
        }
        fireTableDataChanged();

    }

    @Override
    public int getRowCount() {
        return doktori.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doktor d = doktori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return d.getIme();
            case 1:
                return d.getPrezime();
            case 2:
                return d.getBrojOrdinacije();
            case 3:
                return d.getVrstaSpecijaliste();
            case 4:
                return d.getBrojTelefona();
            case 5:
                return d.getEmail();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }

    public Doktor getSelectedDoktor(int row) {
        return doktori.get(row);
    }

    public void pretraga(String kriterijum) throws Exception {
        this.kriterijum = kriterijum;
        refreshTable();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return false;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Doktor dok = getSelectedDoktor(rowIndex);
            VrstaSpecijaliste vr = (VrstaSpecijaliste) aValue;
            VrstaSpecijaliste stara = dok.getVrstaSpecijaliste();
            dok.setVrstaSpecijaliste(vr);
            if (Controller.getInstance().updateDoktor(dok)) {
                dok.setVrstaSpecijaliste(vr);
                fireTableDataChanged();
            } else {
                dok.setVrstaSpecijaliste(stara);
            }

        } catch (Exception ex) {
            if(ex instanceof SocketException)
                formDoktorTable.endProgram();
            else
                ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {

                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {

        } catch (Exception ex) {
            if(ex instanceof SocketException)
                formDoktorTable.endProgram();
            else
                ex.printStackTrace();
        }
    }

}
