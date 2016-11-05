package rexad.com.smsdatacollect.receiver;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Yann Binot Local on 05/11/2016.
 */

public class SmsReceiveVeiwHolder {

    private CheckBox checkBox ;
    private TextView textView ;

    public SmsReceiveVeiwHolder(){

    }
    public SmsReceiveVeiwHolder(TextView textView, CheckBox checkBox){
        this.checkBox = checkBox;
        this.textView = textView;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
