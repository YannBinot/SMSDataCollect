package rexad.com.smsdatacollect.ui;

/**
 * Created by Yann Binot Local on 25/10/2016.
 */

public class SMSDataCollectInput {


    private String shortName;
    private String name;
    private int inputType;

    int id;


    public SMSDataCollectInput(String shortName, String name, int inputType, int id){
        this.shortName = shortName;
        this.name = name;
        this.inputType = inputType;
        this.id = id;
    }


    public String getName(){
        return name;
    }

    public String getShortName(){
        return shortName;
    }

    public int getInputType() {
        return inputType;
    }

    public int getId(){
        return id;
    }
}
