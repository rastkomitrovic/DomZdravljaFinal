/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.termin;

import domain.Usluga;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;
import sesion.Session;

/**
 *
 * @author Rastko
 */
public class TableModelUslugaIzbor extends AbstractTableModel{
    private List<Usluga> usluge;
    private String [] index={"Naziv usluge","Opis usluge","Pregled na koji se odnosi usluga"};
    public TableModelUslugaIzbor() {
        if(Session.getUslugeLista()==null)
            usluge=new LinkedList<>();
        else
            usluge=Session.getUslugeLista();
        fireTableDataChanged();
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
        Usluga u=usluge.get(rowIndex);
        switch(columnIndex){
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

    
    
    public void refreshTable(){
        fireTableDataChanged();
    }
    
    
    public Usluga getSelectedUsluga(int row){
        return usluge.get(row);
    }
    
    public void add(Usluga u){
        usluge.add(u);
        fireTableDataChanged();
    }
    
    public void delte(Usluga u){
        usluge.remove(u);
        fireTableDataChanged();
    }
    
    
    public boolean contains(Usluga u){
        return usluge.contains(u);
    }
    
    public List<Usluga> getUsluge(){
        return usluge;
    }
    
    public void setUsluge(List<Usluga> u){
        usluge=u;
        fireTableDataChanged();
    }
    
}
