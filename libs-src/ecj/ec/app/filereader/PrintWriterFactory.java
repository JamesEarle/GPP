package ec.app.filereader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * An access manager for the multiple PrintWriters used throughout the project.
 * To ease access, you are able to create and destroy (close) PrintWriter 
 * objects through this helper class.
 * 
 * @author James Earle
 */
public class PrintWriterFactory extends IOManager {
    
    /*
    private final String rootDir;
    private final String fileName, path;
    private final boolean subdirectory;
    */
    
    /* Class attributes */
    public ArrayList<PrintWriter> printers;
    
    /**
     * Default blank constructor
     */
    public PrintWriterFactory() {
        printers = new ArrayList<>();
    }
    
    /**
     * 
     * @param parent 
     */
    public PrintWriterFactory(IOManager parent) {
        
    }
    
    /**
     * 
     * @param name
     * @return
     * @throws FileNotFoundException 
     */
    @Override
    public PrintWriter makePrintWriter(String name) throws FileNotFoundException {
        PrintWriter result = super.makePrintWriter(name);
        this.printers.add(result);
        return result;
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    public PrintWriter getPrinter(int index) {
        return this.printers.get(index);
    }
    
    /**
     * 
     */
    public void close() {
        for(PrintWriter p : this.printers) {
            p.close();
        }
    }
    
}
