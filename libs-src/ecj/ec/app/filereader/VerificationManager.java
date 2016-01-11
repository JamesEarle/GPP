package ec.app.filereader;

/**
 *
 * @author James Earle
 */
public class VerificationManager {
    
    private double percentage;
    private int range;
    
    public VerificationManager(double boundary, int dataInputSize) {
        this.percentage = boundary;
        this.setRange(dataInputSize);
    }
    
    /**
     *
     * @param dataInputSize
     */
    public final void setRange(int dataInputSize) {
        this.range = (int)Math.floor(dataInputSize * this.percentage);
    }
    
    /**
     *
     * @return
     */
    public int getRange() {
        return range;
    }
    
    public boolean inVerificationRange(int i) {
        return i > this.range;
    }
    
    /**
     *
     * @param percentage
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    /**
     *
     * @return
     */
    public double getPercentage() {
        return percentage;
    }

}
