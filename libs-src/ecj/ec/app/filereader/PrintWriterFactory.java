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
    
    /* Class attributes */
    private final ArrayList<PrintWriter> printers;
    private final IOManager parent;
    
    /**
     * Create a new instance of the factory whose parent IOManager object
     * is provided, along with appropriate timestamped directories.
     * 
     * @param parent 
     */
    public PrintWriterFactory(IOManager parent) {
        this.parent = parent;
        printers = new ArrayList<>();
    }
    
    /**
     * Create a PrintWriter with the given fileName and return it
     * after instantiating it with the appropriate timestamped 
     * directory structure provided by IOManager parent;
     * 
     * @param fileName
     * @return the new PrintWriter
     * @throws FileNotFoundException 
     */
    @Override
    public PrintWriter makePrintWriter(String fileName) throws FileNotFoundException {
        // NOTE: Cannot call "super.makePrintWriter();" because we need to use 
        // the timestamp associated with the original IOManager Object instantiated
        // in the ec.Evolve main class.
        PrintWriter result = parent.makePrintWriter(fileName);
        this.printers.add(result);
        return result;
    }
    
    /**
     * @param index
     * @return the PrintWriter at the given index
     */
    public PrintWriter getPrinter(int index) {
        return this.printers.get(index);
    }
    
    /**
     * Close all PrintWriter object's created in this instance.
     */
    public void close() {
        for(PrintWriter p : this.printers) {
            p.close();
        }
    }
    
}
