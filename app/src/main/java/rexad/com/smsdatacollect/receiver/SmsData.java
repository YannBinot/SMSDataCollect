package rexad.com.smsdatacollect.receiver;

/**
 * Created by Yann Binot Local on 20/10/2016.
 */

public class SmsData {
    // Number from witch the sms was send
    private String number;
    // SMS text body
    private String body;

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


}
