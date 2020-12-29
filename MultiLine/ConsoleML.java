//package p1.sad.P1.MultiLine;

import java.beans.*;
import java.io.*;

public class ConsoleML implements PropertyChangeListener{
    public int nCols;
    public int index;

    public ConsoleML(){

    }

    public static int getWidth(){
        int n = 0;
        try{
            final String[] cmd = {"bash","-c","tput cols </dev/tty"};
            Process child = Runtime.getRuntime().exec(cmd);
            BufferedReader r = new BufferedReader(new InputStreamReader(child.getInputStream()));
            n = Integer.parseInt(r.readLine());
        }catch(Exception e){
            e.printStackTrace();
        }
        return n;

    }

    public void propertyChange(PropertyChangeEvent e){
        nCols = getWidth();
        System.out.print(KeyNumber.doRESET);
        System.out.print(KeyNumber.doDELETE);
        System.out.print(e.getOldValue());
        System.out.print(KeyNumber.doRESET);
        index = (int) e.getNewValue();

        if((index%nCols)!=0){
            System.out.print("\u001b[" + index%nCols + "C");
        } 
        if((index/nCols)!=0){
            System.out.print("\u001b[" + index/nCols + "B");
        }
    }

}
