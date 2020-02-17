/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klijent;

import controler.Controller;
import domain.Klijent;
import form.termin.FormKlijentTableIzbor;
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
public class TableModelKlijent extends AbstractTableModel implements Runnable {

    String[] index = new String[]{"ID", "Ime", "Prezime", "Broj telefona", "Email"};
    private List<Klijent> klijenti;
    private List<Klijent> klijentiPretraga;
    private String kriterijum;
    private JDialog jDialog;

    public TableModelKlijent(JDialog jDialog) {
        try {
            this.jDialog = jDialog;
            klijenti = Controller.getInstance().getAllKlijenti();
            kriterijum = "";
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                endProgram();
            else
                ex.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    public void refreshTable() {
        try {
            klijenti = Controller.getInstance().getAllKlijenti();
            if (!kriterijum.equals("")) {
                klijentiPretraga = new LinkedList<>();
                for (Klijent klijent : klijenti) {
                    if (klijent.getPrezime().toLowerCase().contains(kriterijum.toLowerCase())) {
                        klijentiPretraga.add(klijent);
                    }
                }
                klijenti = klijentiPretraga;
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
        if (jDialog instanceof FormKlijentTable) {
            ((FormKlijentTable) jDialog).endProgram();
        } else {
            ((FormKlijentTableIzbor) jDialog).endProgram();
        }
    }

    public List<Klijent> getAllKlijenti() {
        return klijenti;
    }

    public Klijent getKlijent(int rowIndex) {
        return klijenti.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return klijenti.get(rowIndex).getKlijentId();
            case 1:
                return klijenti.get(rowIndex).getIme();
            case 2:
                return klijenti.get(rowIndex).getPrezime();
            case 3:
                return klijenti.get(rowIndex).getBrojTelefona();
            case 4:
                return klijenti.get(rowIndex).getEmail();
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

    public Klijent getSelectedPregled(int rowIndex) {
        return klijenti.get(rowIndex);
    }

    @Override
    public void run() {
        try{
        while (true) {
       
                Thread.sleep(10000);
                refreshTable();
            } 
        }catch(Exception e){
            if(e instanceof SocketException)
                endProgram();
        }
    }
}
