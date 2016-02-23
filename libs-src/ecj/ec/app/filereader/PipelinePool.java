package ec.app.filereader;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author James Earle
 */
public class PipelinePool {

//    Pipeline[] pipelines;
//    ArrayList<Pipeline> pipelines2;
    HashMap<String, Pipeline> pipelines;
    
    public PipelinePool(int size) {
//        pipelines = new Pipeline[size];
//        pipelines2 = new ArrayList<>();
        pipelines = new HashMap<>();
    }
    
    public void setValue(int index) {
//        pipelines[0].setValue(pipelines[0].getValueAt(index));
//        for(Pipeline p : pipelines2) {
//            p.setValue(p.getValueAt(index));
//        }
       
        for(Entry<String, Pipeline> p : pipelines.entrySet()) {
            Pipeline pipe = p.getValue();
            pipe.setValue(pipe.getValueAt(index));
        }
        
    }
    
}
