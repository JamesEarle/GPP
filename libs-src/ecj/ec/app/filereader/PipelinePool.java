package ec.app.filereader;

import java.util.HashMap;

/**
 *
 * @author James Earle
 */
public class PipelinePool {

    HashMap<String, Pipeline> pipelines;
    
    public PipelinePool() {
        pipelines = new HashMap<>();
    }
}
