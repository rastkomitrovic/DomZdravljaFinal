/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import thread.ThreadClient;

/**
 *
 * @author Rastko
 */
public class TableModelUser extends AbstractTableModel implements Runnable{

    private List<ThreadClient> users;
    String [] index=new String[]{"Id","Ime","Prezime"};

    public TableModelUser(List<ThreadClient> users) {
        this.users = users;
    }
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ThreadClient thc=users.get(rowIndex);
        if(columnIndex==0)
            return thc.getUserId();
        if(columnIndex==1)
            return thc.getIme();
        return thc.getPrezime();
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }
    public void refresh(){
        fireTableDataChanged();
    }
    public ThreadClient getSelectedClient(int i){
        return users.get(i);
    }
    
    public void addClient(ThreadClient thc){
        if(!users.contains(thc))
            users.add(thc);
        fireTableDataChanged();
    }
    
    public void removeClient(ThreadClient thc){
        users.remove(thc);
        fireTableDataChanged();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(500);
            fireTableDataChanged();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
