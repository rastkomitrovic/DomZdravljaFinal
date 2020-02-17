/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Rastko
 */
public class ThreadClock extends Thread {
    private JLabel lblClock;
    public ThreadClock(JLabel lblClock){
        this.lblClock=lblClock;
    }

    @Override
    public void run() {
        SimpleDateFormat sfd=new SimpleDateFormat("hh:mm:ss");
        while(!isInterrupted()){
            lblClock.setText(sfd.format(new Date()));
        }
    }
    
}
