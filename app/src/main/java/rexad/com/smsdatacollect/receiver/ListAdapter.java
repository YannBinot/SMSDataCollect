package rexad.com.smsdatacollect.receiver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rexad.com.smsdatacollect.R;

/**
 * Created by Yann Binot Local on 20/10/2016.
 */

public class ListAdapter extends ArrayAdapter<SmsData> {
    // List context
    private final Context context;
    // List values
    private final List<SmsData> smsList;

    public ListAdapter(Context context, List<SmsData> smsList) {
        super(context, R.layout.activity_receive_data, smsList);
        this.context = context;
        this.smsList = smsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_receive_data, parent, false);

        ListView listView = (ListView) rowView.findViewById(R.id.receiveSMS);
        SmsData smsData = (SmsData)getItem(position);

        final Calendar cal = Calendar.getInstance();
        String date = smsData.getDate();
        cal.setTimeInMillis(Long.valueOf(date));
        Date time = cal.getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "yyyy/MM/dd - k:mm", Locale.getDefault());
        date = dateFormatter.format(time);
        String body  = smsData.getBody();
        String[] split = body.split("@");


        TextView tv = new TextView(context);
        tv.setText("DATE "+ date +" (yyyy/MM/dd - hh:mm)\n"+
                "TEL : "+smsData.getNumber()+ "\n" +
                   "EXP : " + split[5]+"\n"
                 + "VIL : "+ split[1]);
        tv.setHeight(300);


        return tv;
    }

}
