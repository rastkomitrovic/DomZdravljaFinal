/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.termin;

import controler.Controller;
import domain.Doktor;
import domain.VrstaSpecijaliste;
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
public class TableModelDoktorIzbor extends AbstractTableModel implements Runnable{

    private List<Doktor> doktori;
    private List<Doktor> doktoriPretraga;
    private VrstaSpecijaliste vrsta;
    String []index=new String[]{"Ime","Prezime","Broj ordinacije","Broj telefona","Email"};
    private String kriterijum;
    private FormDoktorTableIzbor formDoktorTableIzbor;
    public TableModelDoktorIzbor(FormDoktorTableIzbor formDoktorTableIzbor) {
        try {
            this.formDoktorTableIzbor=formDoktorTableIzbor;
            doktori=Controller.getInstance().getAllDoctors();
            kriterijum="";
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                formDoktorTableIzbor.endProgram();
            else
                ex.printStackTrace();
        }
    }

    public TableModelDoktorIzbor(VrstaSpecijaliste vr, FormDoktorTableIzbor formDoktorTableIzbor) {
        try {
            this.formDoktorTableIzbor=formDoktorTableIzbor;
            kriterijum="";
            vrsta=vr;
            List<Doktor> pom=Controller.getInstance().getAllDoctors();
            doktori=new LinkedList<>();
            for(Doktor d:pom)
                if(d.getVrstaSpecijaliste().equals(vr))
                    doktori.add(d);
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                formDoktorTableIzbor.endProgram();
            else
                ex.printStackTrace();
        }
    }
    
    public void refreshTable(){
        try {
            if(!kriterijum.equals("")){
                doktoriPretraga=new LinkedList<>();
                for(Doktor d:doktori)
                    if(d.getPrezime().toLowerCase().contains(kriterijum.toLowerCase()) && d.getVrstaSpecijaliste().equals(vrsta))
                        doktoriPretraga.add(d);
                doktori=doktoriPretraga;
                fireTableDataChanged();
            }
            List<Doktor> pom=Controller.getInstance().getAllDoctors();
            doktori=new LinkedList<>();
            for(Doktor d:pom)
                if(d.getVrstaSpecijaliste().equals(vrsta))
                    doktori.add(d);
            fireTableDataChanged();
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                formDoktorTableIzbor.endProgram();
            else
                ex.printStackTrace();
        }
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
        Doktor d=doktori.get(rowIndex);
        switch(columnIndex){
            case 0:
                return d.getIme();
            case 1:
                return d.getPrezime();
            case 2:
                return d.getBrojOrdinacije();
            case 3:
                return d.getBrojTelefona();
            case 4:
                return d.getEmail();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }
    
    public Doktor getSelectedDoktor(int row){
        return doktori.get(row);
    }
    
    public void pretraga(String kriterijum) throws Exception{
        this.kriterijum=kriterijum;
        refreshTable();
    }

    @Override
    public void run() {
        try {
        while(true){
            
                Thread.sleep(10000);
                refreshTable();
            }
        }catch (Exception ex) {
                if(ex instanceof SocketException)
                    formDoktorTableIzbor.endProgram();
            }
            
        }
    }

  
    

