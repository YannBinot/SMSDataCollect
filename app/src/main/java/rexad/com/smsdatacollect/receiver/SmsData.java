package rexad.com.smsdatacollect.receiver;

/**
 * Created by Yann Binot Local on 20/10/2016.
 */

public class SmsData {
    // Number from witch the sms was send
    private String number;
    // SMS text body
    private String body;

    private String date;

    private String id;


    private boolean selected;


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }


}
