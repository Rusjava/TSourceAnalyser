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

/**
 *
 * @author Ruslan Feshchenko
 * @version 0.1
 */
public class ElectronBunchRead {
    
    private File file=null;
    private Object stream=null;

    /**
     * Number of columns
     */
    public final int NCOL=6;
    
    private int nrays;
    private int rayCounter;
    
    /**
     * Constructor
     * @param nrays
     */
    public ElectronBunchRead (int nrays) throws FileNotOpenedException, 
            FileNotFoundException, IOException {
        this.nrays=nrays;
        
        if (openRead("Choose a text file with ray data")) {
            stream=new BufferedReader(new FileReader(file));
            String line=((BufferedReader)stream).readLine();
        }   else {
            throw new FileNotOpenedException();
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
