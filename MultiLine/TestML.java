//package p1.sad.P1.MultiLine;

import java.io.InputStreamReader;

public class TestML {
    private LineML line;
    private ConsoleML console;
    public TestML(){
        line = new LineML();
        console = new ConsoleML();
        line.addPropertyChangeListener(console);
    }
    public LineML getLineML(){
        return line;
    }

    public static void main(String [] args){
        TestML t = new TestML();
        EditableBufferedReaderML in = new EditableBufferedReaderML(
                new InputStreamReader(System.in),t.getLineML());
        String str = null;
        try {
            System.out.print(KeyNumber.doDELETE);
            System.out.print(KeyNumber.doRESET);
            in.setRaw();
            str = in.readLine();
            in.unsetRaw();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + str);
        
    }
}
