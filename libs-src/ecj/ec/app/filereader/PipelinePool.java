package ec.app.filereader;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author James Earle
 */
public class PipelinePool {

    private final HashMap<String, Pipeline> pipelines;
    
    public PipelinePool() {
        pipelines = new HashMap<>();
    }
    
    public void setValue(int index) {
        for(Entry<String, Pipeline> p : pipelines.entrySet()) {
            Pipeline pipe = p.getValue();
            pipe.setValue(pipe.getValueAt(index));
        }
    }
    
    public HashMap getPipelines() {
        return this.pipelines;
    }
    
    public Pipeline get(String key) {
        return this.pipelines.get(key);
    }
    
    public void add(String key, Pipeline value) {
        this.pipelines.put(key, value);
    }
    
}
