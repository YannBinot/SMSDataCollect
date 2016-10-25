package rexad.com.smsdatacollect;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rexad.com.smsdatacollect.receiver.ListAdapter;
import rexad.com.smsdatacollect.receiver.SmsData;

public class ReceiveDataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);

        getSupportLoaderManager().initLoader(1, null, this);
        final ListView listView = (ListView) findViewById(R.id.receiveSMS);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SmsData smsData = (SmsData) adapterView.getItemAtPosition(i);

                Toast.makeText(ReceiveDataActivity.this, "Ouvrir message", Toast.LENGTH_SHORT).show();
                Intent readMessageIntent = new Intent(ReceiveDataActivity.this, ReadMessageActivity.class);
                readMessageIntent.putExtra("value", smsData.getBody());

                startActivity(readMessageIntent);

            }
        });

    }

    @Override
    public Loader onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Uri.parse("content://sms/inbox");
        CursorLoader cursorLoader = new CursorLoader(this, uri, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader arg0, Cursor cursor) {

        List<SmsData> listInput = new ArrayList<SmsData>();
        // Read the sms data and store it in the list
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {

                String phone = cursor.getString(cursor.getColumnIndexOrThrow("address")).toString();
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body")).toString();
                if(!body.contains("<smsdata>")){
                    cursor.moveToNext();
                    continue;
                }
                SmsData smsdata = new SmsData();
                smsdata.setNumber(phone);
                smsdata.setBody(body);

                listInput.add(smsdata);

                cursor.moveToNext();
            }
        }
        ArrayAdapter<SmsData> adapter = new ListAdapter(this, listInput);
        final ListView listView = (ListView) findViewById(R.id.receiveSMS);
        listView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader arg0) {

    }


}
