package ec.app.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * The IOManager class is an all around Input / Output helper class to hold 
 * the logic associated with creating directories, writing to files, etc.
 * This keeps the GP implementation logic free of this task.
 * 
 * @author James Earle
 */
public class IOManager {
    
    private final String rootDir;
    private final String fileName, path;
    private final boolean subDir;
    
    /**
     * Receives the top level directory as a parameter and creates the file and subdirectory 
     * patterns to match. This is for file manipulation, such as output.txt files.
     * 
     * @param rootDir the top level directory for the IOManager to manipulate beneath. 
     * @param subDir if true, the fileName variable actually represents another directory level
     */
    public IOManager(String rootDir, boolean subDir) {
        this.rootDir = rootDir;
        this.subDir = subDir;
        
        // Instantiate a new Date object on construction of the IOManager.
        Date date = new Date();
        
        String format = subDir ? "HH-mm-ss" : "HH-mm-ss-" + Double.toString(Math.random());
        
        /* Hour, Minute, Second, Random (for sorted uniqueness) */
        this.fileName = new SimpleDateFormat(format).format(date);
        
        /* Year, Month, Day */
        String suffix = subDir ? "/" + this.fileName : "";
        this.path = this.rootDir + "/" + new SimpleDateFormat("yyyy-MM-dd").format(date) + suffix;
    }
    
    /**
     * Blank constructor builds with default assumed values.
     */
    public IOManager() {
        this.rootDir = "docs-img/";
        this.subDir = true;
        
        // Instantiate a new Date object on construction of the IOManager.
        Date date = new Date();
        
        String format = subDir ? "HH-mm-ss" : "HH-mm-ss-" + Double.toString(Math.random());
        
        /* Hour, Minute, Second, Random (for sorted uniqueness) */
        this.fileName = new SimpleDateFormat(format).format(date);
        
        /* Year, Month, Day */
        String suffix = subDir ? "/" + this.fileName : "";
        this.path = this.rootDir + "/" + new SimpleDateFormat("yyyy-MM-dd").format(date) + suffix;
    }
    
    /**
     * Sets permission of the given File object to be globally readable,
     * writable, and executable.
     * 
     * @param file the File object to receive permission updates
     * @throws IOException 
     */
    public void setAllPermissions(File file) throws IOException {
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        Runtime.getRuntime().exec("chmod 777 " + file.getAbsolutePath());
    }
    
    /**
     * Creates the appropriate directory structure and out.txt file for
     * this execution.
     * 
     * @throws IOException 
     */
    public void executionSetup() throws IOException {        
        makeDirectory(this.path);
        makeOutFile(this.path, this.fileName);
    }
    
    public void makeMultiDirStructure() {
        //FILL THIS IN
    }
    
    /**
     * Uses Java's File.mkdir() method to create the directory given 
     * by path.
     * 
     * @param path the directory structure to be created.
     * @return True if the path was created successfully, False otherwise.
     */
    public boolean makeDirectory(String path) {
        //TODO see if using mkdirs in both cases is fine...
        //return this.subdirectory ? new File(path).mkdirs() : new File(path).mkdir();
        return new File(path).mkdirs();
    }
    
    /**
     * Makes the out.txt file for this execution in the format
     * yyyy-MM-dd/hh-mm-ss_(random).txt, then sets permissions to be
     * usable globally for read, write, and execute.
     * 
     * @param path the path to this specific executions out files.
     * @param fileName the file name to be written to.
     * @throws IOException 
     */
    public void makeOutFile(String path, String fileName) throws IOException {
        File file = new File(this.path + "/" + this.fileName + "_out.txt");
        setAllPermissions(file);
    }
    
    public PrintWriter makePrintWriter(String suffix) throws FileNotFoundException {
        return subDir ? new PrintWriter(this.path + "/" + suffix) : new PrintWriter(this.path + "/" + this.fileName + suffix);
    }
    
    /**
     * Getter method for the base level directory which we are operating in. This 
     * was passed to the IOManager constructor and is used as the directory prefix 
     * for all file manipulation.
     * 
     * @return the top level directory which was passed into the IOManager constructor.
     */
    public String getRootDir() {
        return this.rootDir;
    }
    
    /**
     * Getter method for the subdirectory created for this specific execution, 
     * using the prefix of this.rootDir with the subdirectory folder the timestamped
     * directory holding out files. Timestamped as yyyy-MM-dd
     * 
     * @return the path to out files for this execution
     */
    public String getPath() {
        return this.path;
    }

}
