package ec.app.filereader;

/**
 * ENUM to allow for easier access to many different input files. There is
 * no setter function in this instance, because the enumerated type attributes
 * will never be written to, as they are only for cleaner file access.
 * 
 * Naming conventions for file types are as follows.
 *      - Any file without a "_#" suffix is raw, unnormalized data.
 *      - _1 Normalized data without any alteration in sampling intervals
 *      - _2 Normalized data, sampled every second data point
 *      - _5 Normalized data, sampled every 5th data point.
 * 
 * @author James Earle
 */
public enum InputFileEnum {
    
    SP_ORIG("/input/sp500.txt"),
    SP_NORM_1("/input/sp500_1.txt"),
    SP_NORM_2("/input/sp500_2.txt"),
    SP_NORM_5("/input/sp500_5.txt"),
    DJ_ORIG("/input/dowjones_05-07.txt"),
    DJ_NORM_1("/input/dowjones_05-07_1.txt"),
    DJ_NORM_2("/input/dowjones_05-07_2.txt"),
    DJ_NORM_5("/input/dowjones_05-07_5.txt"),
    MSFT_OPEN_1("/input/msft_open_14-16_1.txt"),
    MSFT_HIGH_1("/input/msft_high_14-16_1.txt"),
    MSFT_LOW_1("/input/msft_low_14-16_1.txt"),
    MSFT_CLOSE_1("/input/msft_close_14-16_1.txt"),
    MSFT_VOLUME_1("/input/msft_volume_14-16_1.txt"),
    MSFT_OPEN_2("/input/msft_open_14-16_2.txt"),
    MSFT_HIGH_2("/input/msft_high_14-16_2.txt"),
    MSFT_LOW_2("/input/msft_low_14-16_2.txt"),
    MSFT_CLOSE_2("/input/msft_close_14-16_2.txt"),
    MSFT_VOLUME_2("/input/msft_volume_14-16_2.txt");

    
    /* Class attributes */
    private final String fileName;
    
    /**
     * 
     * @param fileName 
     */
    private InputFileEnum(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * equivalent to a getValue function for a given enum.
     * 
     * @return the value of the chosen enum.
     */
    public String v() {
        return this.fileName;
    }
}
