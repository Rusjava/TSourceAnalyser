/*
 * Package for the class reading from the lectron data file
 */
package ElectronBunchRead;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.io.Closeable;

/**
 *
 * @author Ruslan Feshchenko
 * @version 0.1
 */
public class ElectronBunchRead implements Closeable {
    
    private File file=null;
    private Object stream=null;

    /**
     * Number of columns
     */
    public final int NCOL=6;
 
    private int electronCounter;
    
    /**
     * Constructor
     * @throws ElectronBunchRead.ElectronBunchRead.FileNotOpenedException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public ElectronBunchRead () throws FileNotOpenedException, 
            FileNotFoundException, IOException, InterruptedException, InvocationTargetException {
        if (openRead("Choose a text file with ray data")) {
            stream=new BufferedReader(new FileReader(file));
            String line=((BufferedReader)stream).readLine();
        }   else {
            throw new FileNotOpenedException();
        }   
    }
    
    /**
     * Closes I/O stream
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (stream!=null) {
            ((BufferedReader)stream).close();
        }
    }
    
    /**
     * Reads binary data of one ray or of the file heading
     * @param electronData double array of 18 numbers representing 18 columns of ray data
     * @throws java.io.EOFException
     * @throws java.io.IOException
     */
    public void read(double [] electronData) throws EOFException, IOException,
            InputMismatchException, NoSuchElementException {
        int nread=Math.min(electronData.length, NCOL);
        electronCounter++;
        Scanner header;
        String line=((BufferedReader)stream).readLine();
        if (line==null) {
            throw new EOFException ();
        }
        header=new Scanner(line);
        for (int i=0; i<nread; i++) { 
            electronData[i]=header.nextDouble();     
        }
    }
    
    private boolean openRead(String title) throws InterruptedException, InvocationTargetException {
        class Answer {
            public int ans;
        }
        final Answer ans=new Answer();
        final JFileChooser fo=new JFileChooser ();
        fo.setDialogTitle(title);
        if (SwingUtilities.isEventDispatchThread()) {
            ans.ans=fo.showOpenDialog(null);  
        } else {
            SwingUtilities.invokeAndWait(()->ans.ans=fo.showOpenDialog(null));
        }   
        if (ans.ans==JFileChooser.APPROVE_OPTION) {
            file=fo.getSelectedFile();
            return true;
        }
        return false;
    }
    
    /**
     * Returns the current value of electron counter
     * @return
     */
    public int getElectronCounter () {
        return electronCounter;
    }
    
    /**
     * Class for exception thrown when the user cancels file opening
     */
    public static class FileNotOpenedException extends Exception {

        /**
         * Empty constructor
         */
        public FileNotOpenedException () {
            super();
        }
    }
}
