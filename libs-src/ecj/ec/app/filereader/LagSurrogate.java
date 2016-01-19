package ec.app.filereader;

/**
 * A bridge between the X_Lag object and the implementation files main loop to 
 * pass indices used in the for loop as data is processed so that we can 
 * accurately assess the time lag's relationship.
 * @author James Earle
 */
public class LagSurrogate {
    
    /* Constants */
    private final int SHORT_LAG = 5; // 1 Week, minues 2-day weekened
    private final int LONG_LAG = 23; // 4 Weeks, minus 4x2-day weekends
    
    /* Class attributes */
    private int timeLag; 
    private double lagResult;
    
    /**
     * 
     * @param inputType 
     */
    public LagSurrogate(InputFileEnum inputType) {
        if(dataIsDowJones(inputType)) {
            this.timeLag = SHORT_LAG;
        } else {
            this.timeLag = LONG_LAG;
        }
        
        // If left uninitialized will throw error when XLag refers to it.
        this.lagResult = 0;
    }
    
    /**
     * 
     * @param inputType
     * @return 
     */
    public final boolean dataIsDowJones(InputFileEnum inputType) {
        switch(inputType) {
            case DJ_ORIG:
            case DJ_NORM:
            case DJ_NORM_2:
            case DJ_NORM_5:
                return true;
            default:
                return false;
        }
    }
    
    /**
     * 
     * @param lag 
     */
    public void setLag(int lag) {
        this.timeLag = lag;
    }
    
    /**
     * 
     * @return 
     */
    public int getLag() {
        return this.timeLag;
    }
    
    /**
     * 
     * @param lagResult 
     */
    public void setLagResult(double lagResult) {
        this.lagResult = lagResult;
    }
    
    /**
     * 
     * @return 
     */
    public double getLagResult() {
        return this.lagResult;
    }

}
