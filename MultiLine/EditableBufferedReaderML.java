//package p1.sad.P1.MultiLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class EditableBufferedReaderML extends BufferedReader {
    private LineML line;

    public EditableBufferedReaderML(final Reader in, LineML line) {
        super(in);
        this.line = line;
    }

    public void setRaw() throws InterruptedException {
        final String[] cmd = new String[] { "/bin/sh", "-c", "stty -echo raw </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void unsetRaw() throws InterruptedException {
        final String[] cmd = new String[] { "/bin/sh", "-c", "stty -echo sane </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public int read(){
        try{
            int i = super.read();
            if(i==27){
                i = super.read();
                if(i==91){
                    i =super.read();
                    switch(i){
                        case 72:
                        return KeyNumber.BEGIN;
                        case 70:
                        return KeyNumber.END;
                        case 68:
                        return KeyNumber.LEFT;
                        case 67: 
                        return KeyNumber.RIGHT;
                        case 66: 
                        return KeyNumber.DOWN;
                        case 65: 
                        return KeyNumber.UP;
                        case 50:
                            i = super.read();
                            if(i==126){
                                return KeyNumber.INSERT;
                            }
                        break;
                        case 51:
                            i=super.read();
                            if(i==126){
                                return KeyNumber.DELETE;
                            }
                        break;
                        /*case 77:
                        return KeyNumber.MOUSE;*/

                    }
                }
            }
            return i;
        }catch(IOException e){
            e.printStackTrace();
        }
        return 0;
    }


    public String readLine(){
        int r = 0;
        while(true){
            r = read();
            switch (r){
                case KeyNumber.ENTER:
                    return line.getLine();
                case KeyNumber.LEFT:
                    line.moveLeft();
                    break;
                case KeyNumber.RIGHT:
                    line.moveRight();
                    break;
                case KeyNumber.BEGIN:
                    line.moveFirst();
                    break;
                case KeyNumber.END:
                    line.moveEnd();
                    break;
                case KeyNumber.INSERT:
                    line.toogleIns();
                    break;
                case KeyNumber.DELETE:
                    line.toogleBackspace();
                    line.delete();
                    line.toogleBackspace();
                case KeyNumber.BACKSPACE:
                    line.delete();
                    break;
                case KeyNumber.DOWN:
                    line.moveDown();
                    break;
                case KeyNumber.UP:
                    line.moveUp();
                    break;
                /*case KeyNumber.MOUSE:
                    try{
                        char cb = (char) super.read();
                        char cx = (char) super.read();
                        char cy = (char) super.read();
                        String s = "";
                        s = s + cb + cx + cy;
                        line.getPosition(s);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;*/
                default:
                    line.add(r);
                    break;
            }
        }
    }
}
