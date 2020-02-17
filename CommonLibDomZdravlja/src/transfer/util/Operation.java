/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author student1
 */
public interface Operation {
    public static final int OPERATION_LOGIN = 1;
    //get ALL
    public static final int OPERATION_GET_ALL_KLIJENTI = 2;
    public static final int OPERATION_GET_ALL_DOKTORI = 3;
    public static final int OPERATION_GET_ALL_PREGLEDI = 4;
    public static final int OPERATION_GET_ALL_USLUGE = 5;
    public static final int OPERATION_GET_ALL_TERMINI = 6;
    public static final int OPERATION_GET_ALL_VRSTE_SPECIJALISTE = 7;
    public static final int OPERATION_GET_ALL_USERS = 8;
    
    //ADD
    
    public static final int OPERATION_ADD_KLIJENT = 9;
    public static final int OPERATION_ADD_DOKTOR = 10;
    public static final int OPERATION_ADD_PREGLED =11;
    public static final int OPERATION_ADD_USLUGA = 12;
    public static final int OPERATION_ADD_TERMIN = 13;
    public static final int OPERATION_ADD_VRSTA_SPECIJALISTE = 14;
    public static final int OPERATION_ADD_USER = 15;
    
    //DELETE
    
    public static final int OPERATION_DELETE_KLIJENT = 16;
    public static final int OPERATION_DELETE_DOKTOR = 17;
    public static final int OPERATION_DELETE_PREGLED = 18;
    public static final int OPERATION_DELETE_USLUGA = 19;
    public static final int OPERATION_DELETE_TERMIN = 20;


    
    //EDIT
    
    public static final int OPERATION_EDIT_KLIJENT = 21;
    public static final int OPERATION_EDIT_DOKTOR = 22;
    public static final int OPERATION_EDIT_PREGLED = 23;
    public static final int OPERATION_EDIT_USLUGA = 24;

    
    //FIND_BY_ID
    
    public static final int OPERATION_FIND_BY_ID_KLIJENT = 25;
    public static final int OPERATION_FIND_BY_ID_DOKTOR = 26;
    public static final int OPERATION_FIND_BY_ID_PREGLED = 27;
    public static final int OPERATION_FIND_BY_ID_USLUGA =28;

    public static final int OPERATION_LOGOUT=29;
    public static final int OPERATION_PING=30;
}
