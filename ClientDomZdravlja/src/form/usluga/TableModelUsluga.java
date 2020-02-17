/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.usluga;

import controler.Controller;
import domain.Pregled;
import domain.Usluga;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rastko
 */
public class TableModelUsluga extends AbstractTableModel implements Runnable {

    private List<Usluga> usluge;
    private List<Usluga> uslugePretraga;
    private String[] index = {"Naziv usluge", "Opis usluge", "Pregled na koji se odnosi usluga"};
    private FormUsluga formUsluga;

    public TableModelUsluga(FormUsluga formUsluga) {
        this.formUsluga = formUsluga;
        refreshTable();
    }

    @Override
    public int getRowCount() {
        return usluge.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usluga u = usluge.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getNazivUsluge();
            case 1:
                return u.getOpisUsluge();
            case 2:
                return u.getPregled();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usluga u = usluge.get(rowIndex);
        switch (columnIndex) {
            case 0:
                u.setNazivUsluge((String) aValue);
                break;
            case 1:
                u.setOpisUsluge((String) aValue);
                break;
            case 2:
                u.setPregled((Pregled) aValue);
                break;
        }
        try {
            if (Controller.getInstance().editUsluga(u)) {
                fireTableDataChanged();
            }

        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formUsluga.endProgram();
            }else
                ex.printStackTrace();
        }
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return true;
            case 1:
                return true;
            case 2:
                return false;
        }
        return false;
    }

    public void refreshTable() {
        try {
            usluge = Controller.getInstance().getAllUsluge();
            fireTableDataChanged();
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formUsluga.endProgram();
            }else
                ex.printStackTrace();
        }

    }

    public void search(String k) throws Exception {
        uslugePretraga = new LinkedList<>();
        for (Usluga u : usluge) {
            if (u.getNazivUsluge().contains(k)) {
                uslugePretraga.add(u);
            }
        }
        if (uslugePretraga.isEmpty()) {
            throw new Exception("Nije pronadjena nijedan usluga u po zadatom kriterijumu");
        }
        usluge = uslugePretraga;
        fireTableDataChanged();
    }

    public Usluga getSelectedUsluga(int row) {
        return usluge.get(row);
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
                formUsluga.endProgram();
            }
        }

    }
}
