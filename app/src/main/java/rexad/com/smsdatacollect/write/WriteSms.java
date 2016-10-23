package rexad.com.smsdatacollect.write;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann Binot Local on 16/10/2016.
 */

public class WriteSms {

    private List<String> _parameterToWrite = new ArrayList<String>();


    public WriteSms(){
    }


    public boolean addParameterToWrite(String value){
        if(value == null || value.isEmpty()){
            return false;
        }
        _parameterToWrite.add(value);
        return true;
    }


    public String writeMessage(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("<smsdata>");
        for(String parameter : _parameterToWrite){
            buffer.append("@");
            buffer.append(parameter);
        }
        buffer.append("</smsdata>");

        return buffer.toString();
    }






}
