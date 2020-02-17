/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.termin;

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
public class TableModelUslugaTermin extends AbstractTableModel implements Runnable {

    private List<Usluga> usluge;
    private List<Usluga> uslugePretraga;
    private String[] index = {"Naziv usluge", "Opis usluge", "Pregled na koji se odnosi usluga"};
    private Pregled pregled;
    private String k;
    private FormUslugaIzbor formUslugaIzbor;

    public TableModelUslugaTermin(FormUslugaIzbor formUslugaIzbor) {
        this.formUslugaIzbor = formUslugaIzbor;
        refreshTable(pregled);
        k = "";
    }

    TableModelUslugaTermin(Pregled p, FormUslugaIzbor formUslugaIzbor) {
        this.formUslugaIzbor = formUslugaIzbor;
        pregled = p;
        k = "";
        refreshTable(pregled);

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
    public String getColumnName(int column) {
        return index[column];
    }

    public void refreshTable(Pregled pregled) {
        try {
            if (!k.equals("")) {
                uslugePretraga = new LinkedList<>();
                for (Usluga u : usluge) {
                    if (u.getNazivUsluge().contains(k)) {
                        uslugePretraga.add(u);
                    }
                }
                usluge = uslugePretraga;
                fireTableDataChanged();
            } else {
                usluge = new LinkedList<>();
                for (Usluga u : Controller.getInstance().getAllUsluge()) {
                    if (u.getPregled().equals(pregled)) {
                        usluge.add(u);
                    }
                }
                fireTableDataChanged();
            }
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formUslugaIzbor.endProgram();
            }else
                ex.printStackTrace();
        }

    }

    public void search(String k) throws Exception {
        this.k = k;
        refreshTable(pregled);

    }

    public Usluga getSelectedUsluga(int row) {
        return usluge.get(row);
    }


    @Override
    public void run() {
        try {
            while (true) {

                Thread.sleep(10000);
                refreshTable(pregled);
            }
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formUslugaIzbor.endProgram();
            }

        }
    }
}
