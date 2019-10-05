package ma.elouazzani.elouazzani.running.run_activity;

/**
 * Created by elouazzani on 29/11/2016.
 */
public class ProgrammeData {
    private int url;
    private String title;
    private String text;

    public ProgrammeData(int url,String title,String text){
        this.url=url;
        this.title=title;
        this.text=text;
    }
    // get
    int getUrl(){return this.url;}
    String getTitle(){return this.title;}
    String getText(){return this.text;}
}
