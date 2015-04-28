package takehome;

/**
 * Created by jaylee on 4/27/15.
 */
public class THService {


    private final int year;
    private final int mpgCity;
    private final int mpgHighway;
    private final String engineType;
    private final String editorReview;
    private final double avgRating;
    private final String model;
    private final String make;



    public THService(int year, int mpgCity, int mpgHighway,String engineType,
                    String editorReview, double avgRating, String make,
                    String model) {
        this.year = year;
        this.mpgCity = mpgCity;
        this.mpgHighway = mpgHighway;
        this.engineType = engineType;
        this.editorReview = editorReview;
        this.avgRating = avgRating;
        this.make = make;
        this.model = model;


    }


    public int getYear() { return year;}

    public int getMpgCity() { return mpgCity;}

    public int getMpgHighway() { return mpgHighway;}

    public double getAvgRating() { return avgRating;}

    public String getEngineType() { return  engineType;}

    public String getEditorReview() { return  editorReview;}

    public String getModel() { return model;}

    public String getMake() { return make;}

}