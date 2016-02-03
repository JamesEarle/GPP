package ec.app.filereader;

import ec.Evolve;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James Earle
 * 
 * This class handles the appropriate execution of all associated python
 * processing scripts with the GPP project
 */
public class PythonDelegate {
    
    public String userDirectory;
    public Runtime r;
    
    public PythonDelegate(String userDirectory) {
        this.userDirectory = userDirectory;
        r = Runtime.getRuntime();
    }
    
    public void run(String program, String ... params) throws InterruptedException {
        
        System.out.println("*********************************************");
        
        try {
            Process p;
            if(params.length > 0) {
                String numRuns = params[0];
                if(!isWindows()) {
                    p = r.exec("python " + System.getProperty("user.dir") + "/../py/" + program + " " + numRuns + " --linux");
                } else {
                    p = r.exec("python " + System.getProperty("user.dir") + "\\..\\py\\" + program + " " + numRuns);
                }
                p.waitFor();

                collectOutput(p);
            } else {
               if(!isWindows()) {
                    p = r.exec("python " + System.getProperty("user.dir") + "/../py/" + program + " " + " --linux");
                } else {
                    p = r.exec("python " + System.getProperty("user.dir") + "\\..\\py\\" + program);
                }
                p.waitFor();

                collectOutput(p);
            }
        } catch(IOException e) {
            Logger.getLogger(Evolve.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("*********************************************");
    }
    
    public boolean isWindows() {
        String operatingSystem = System.getProperty("os.name");
        return operatingSystem.startsWith("Windows");
    }
    
    public void collectOutput(Process p) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        
        printReader(stdin, "Output: ");
        printReader(stderr, "Errors: ");
    }
    
    public void printReader(BufferedReader br, String message) throws IOException {
        String s;
        System.out.println(message);
        while((s = br.readLine()) != null) {
            System.out.println(s);
        }
    }
}
