/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.pregled;

import controler.Controller;
import domain.Pregled;
import form.usluga.FormPregledTableIzbor;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rastko
 */
public class TableModelPregled extends AbstractTableModel implements Runnable {

    String[] index = new String[]{"ID", "Naziv pregleda", "Opis", "Vrsta specijaliste za pregled"};
    private List<Pregled> pregledi;
    private List<Pregled> preglediPretraga;
    private String kriterijum;
    private JDialog jDialog;

    public TableModelPregled(JDialog jDialog) {
        try {
            this.jDialog = jDialog;
            pregledi = Controller.getInstance().getAllPregledi();
            kriterijum = "";
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                endProgram();
            System.out.println("Greska u TableModelpregled konstruktoru");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return pregledi.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    public void refreshTable() {
        try {
            pregledi = Controller.getInstance().getAllPregledi();
            if (!kriterijum.equals("")) {
                preglediPretraga = new LinkedList<>();
                for (Pregled pregled : pregledi) {
                    if (pregled.getnazivPregleda().toLowerCase().contains(kriterijum.toLowerCase())) {
                        preglediPretraga.add(pregled);
                    }
                }
                pregledi = preglediPretraga;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                endProgram();
            }else
                ex.printStackTrace();
        }

    }

    private void endProgram() {
        if (jDialog instanceof FormPregledTable) {
            ((FormPregledTable) jDialog).endProgram();
        } else {
            ((FormPregledTableIzbor) jDialog).endProgram();
        }
    }

    public void addPregled(Pregled p) {
        pregledi.add(p);
        fireTableDataChanged();
    }

    public void removePregled(int rowIndex) {
        pregledi.remove(rowIndex);
        fireTableDataChanged();
    }

    public List<Pregled> getAllPregleds() {
        return pregledi;
    }

    public Pregled getPregled(int rowIndex) {
        return pregledi.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return pregledi.get(rowIndex).getpregledId();
            case 1:
                return pregledi.get(rowIndex).getnazivPregleda();
            case 2:
                return pregledi.get(rowIndex).getOpis();
            case 3:
                return pregledi.get(rowIndex).getVrstaSpecijalisteZaPregled();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void pretraga(String kriterijum) throws Exception {
        this.kriterijum = kriterijum;
        refreshTable();
    }

    public Pregled getSelectedPregled(int rowIndex) {
        return pregledi.get(rowIndex);
    }

    @Override
    public void run() {
        try {
            while (true) {

                Thread.sleep(10000);
                refreshTable();
            }
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                endProgram();
            }
        }
    }
}
