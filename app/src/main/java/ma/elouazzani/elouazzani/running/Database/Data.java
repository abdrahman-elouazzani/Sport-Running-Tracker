package ma.elouazzani.elouazzani.running.Database;

/**
 * Created by elouazzani on 09/12/2016.
 */
public class Data {

    private int id;
    private String date;
    private String time;
    private double distance;

   public Data(String date,double distance,String time)
   {
       this.date=date;
       this.time=time;
       this.distance=distance;


   }
    // id
    public void setId(int id){this.id=id;}
    public int getId(){return this.id;}

    // date
    public String getDate(){return this.date;}
    // time
    public String getTime(){return this.time;}
    // distance
    public double getDistance(){return this.distance;}

}
