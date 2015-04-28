package takehome;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
/**
 * Created by jaylee on 4/27/15.
 */
@RestController
public class THServiceController {
    private static int year;
    private static String make;
    private static String model;
    private static String enginType;
    private static int mpgCity;
    private static int mpgHighway;
    private static String editorReview;
    private static double avgRating;


    @RequestMapping("/takehome")
    public THService serviceHandler(@RequestParam(value="api_key", required=true) String apiKey) throws IOException {
        try {
            fetchByVin(apiKey);
            fetchEditorReview(apiKey);
            fetchAvgRating(apiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new THService(year,mpgCity,mpgHighway,enginType,editorReview,avgRating,make,model);
    }

    public static void fetchByVin(String apiKey) throws IOException {

        String s = "https://api.edmunds.com/api/vehicle/v2/vins/1N4AL3AP4DC295509?fmt=json&api_key="+apiKey;
        URL url = new URL(s);

        Scanner scan = new Scanner(url.openStream());
        String str = "";
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        JSONObject obj = new JSONObject(str);


        JSONObject res = obj.getJSONArray("years").getJSONObject(0);
        year=res.getInt("year");
        mpgCity=obj.getJSONObject("MPG").getInt("city");
        mpgHighway = obj.getJSONObject("MPG").getInt("highway");
        enginType= obj.getJSONObject("engine").getString("type");
        make = obj.getJSONObject("make").getString("name");
        model = obj.getJSONObject("model").getString("name");

        //System.out.println(res.getInt("year"));


    }

    public static void fetchEditorReview(String apiKey) throws IOException {

        String s = "https://api.edmunds.com/v1/content/editorreviews?make="+make.toLowerCase()+"&"+"model="+model.toLowerCase()+"&year="+year+"&fmt=json&api_key="+apiKey;
        URL url = new URL(s);

        Scanner scan = new Scanner(url.openStream());
        String str = "";
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();


        JSONObject obj = new JSONObject(str);



        editorReview = obj.getJSONObject("editorial").getString("edmundsSays");

        //System.out.println("Editor Summary:"+ editorReview);


    }

    public static void fetchAvgRating(String apiKey) throws  IOException {
        String s = "https://api.edmunds.com/api/vehiclereviews/v2/"+make.toLowerCase()+"/"+model.toLowerCase()+"/"+year+"?fmt=json&api_key="+apiKey;
        URL url = new URL(s);

        Scanner scan = new Scanner(url.openStream());
        String str = "";
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();


        JSONObject obj = new JSONObject(str);

        avgRating=obj.getDouble("averageRating");
    }

}
