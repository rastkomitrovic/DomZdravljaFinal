/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.termin;

import controler.Controller;
import domain.Termin;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rastko
 */
public class TableModelTermin extends AbstractTableModel implements Runnable {

    private String index[] = new String[]{"Datum i vreme pocetka termina", "Datum i vreme zavrsetka termina", "Klijent", "Doktor", "Pregled"};
    private List<Termin> termini;
    private List<Termin> terminiPretraga;
    private String kriterijum;
    private FormTermin formTermin;

    public TableModelTermin(FormTermin formTermin) {
        try {
            this.formTermin = formTermin;
            termini = Controller.getInstance().getAllTermini();
            kriterijum = "";
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formTermin.endProgram();
            }else
                ex.printStackTrace();
        }
    }

    public void refreshTable() {
        try {
            termini = Controller.getInstance().getAllTermini();
            if (kriterijum.equals("")) {
                fireTableDataChanged();
            } else {
                if (kriterijum.charAt(0) == 'd') {
                    searchDoktor(kriterijum.substring(1));
                } else if (kriterijum.charAt(0) == 'k') {
                    searchKlijent(kriterijum.substring(1));
                }
            }
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                formTermin.endProgram();
            }else
                ex.printStackTrace();
        }

    }

    @Override
    public int getRowCount() {
        return termini.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sft = new SimpleDateFormat("hh:mm");
        Termin t = termini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sfd.format(t.getVremeOd());
            case 1:
                return sfd.format(t.getVremeDo());
            case 2:
                return t.getKlijent().getIme() + " " + t.getKlijent().getPrezime();
            case 3:
                return t.getDoktor().getIme() + " " + t.getDoktor().getPrezime();
            case 4:
                return t.getPregled().getnazivPregleda();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }

    public Termin getSelectedTermin(int row) {
        return termini.get(row);
    }

    public void searchDoktor(String kriterijum) throws Exception {
        this.kriterijum = 'd' + kriterijum;
        terminiPretraga = new LinkedList<>();
        for (Termin t : termini) {
            if (t.getDoktor().getIme().toLowerCase().contains(kriterijum.toLowerCase())) {
                terminiPretraga.add(t);
            }
        }
        termini = terminiPretraga;
        fireTableDataChanged();
    }

    public void searchKlijent(String kriterijum) {
        this.kriterijum = 'k' + kriterijum;
        terminiPretraga = new LinkedList<>();
        for (Termin t : termini) {
            if (t.getKlijent().getIme().toLowerCase().contains(kriterijum.toLowerCase())) {
                terminiPretraga.add(t);
            }
        }
        termini = terminiPretraga;
        fireTableDataChanged();
    }

    @Override
    public void run() {
        try {
            while (true) {

                Thread.sleep(10000);
                refreshTable();
            }
        } catch (Exception e) {
            if (e instanceof SocketException) {
                formTermin.endProgram();
            }
        }
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
        refreshTable();
    }

}
