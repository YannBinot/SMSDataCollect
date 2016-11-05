package rexad.com.smsdatacollect;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rexad.com.smsdatacollect.receiver.ListAdapter;
import rexad.com.smsdatacollect.receiver.SmsData;

public class ReceiveDataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ArrayAdapter<SmsData> adapter = null;
    CursorLoader cursorLoader = null;

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
        cursorLoader = new CursorLoader(this, uri, null, null, null, null);
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
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date")).toString();
                String id = cursor.getString(0).toString();
                if (!body.contains("<smsdata>")) {
                    cursor.moveToNext();
                    continue;
                }
                SmsData smsdata = new SmsData();
                smsdata.setNumber(phone);
                smsdata.setBody(body);
                smsdata.setDate(date);
                smsdata.setId(id);
                smsdata.setSelected(false);
                listInput.add(smsdata);

                cursor.moveToNext();
            }
        }
        adapter = new ListAdapter(this, listInput);
        final ListView listView = (ListView) findViewById(R.id.receiveSMS);
        listView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader arg0) {

    }


    private int deleteMessage(Context context, String id) {

        //int count = 0;
        //  Cursor c = cursorLoader.loadInBackground();
        Uri deleteUri = Uri.parse("content://sms");
        int count = 0;
        Cursor c = context.getContentResolver().query(deleteUri, null, null,
                null, null);
        while (c.moveToNext()) {
            try {
                // Delete the SMS
                String pid = c.getString(0); // Get id;
                String uri = "content://sms/" + pid;
                if (pid.equals(id)) {
                    count = context.getContentResolver().delete(Uri.parse(uri),
                            null, null);
                    System.out.println("dqsdqsdqsdqsdqs + " + count);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getCause());
            }
        }
        return count;
    }
/*
    //Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
    public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menusuppression, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem ms = menu.getItem(1);
        MenuItem mi = menu.getItem(0);
        if(ms.getTitle().equals("Selectionner")){

            mi.setEnabled(false);
        }
        else{
            mi.setEnabled(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {

        final  ListView listView = (ListView) findViewById(R.id.receiveSMS);

        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.supprimer:
                Toast.makeText(ReceiveDataActivity.this, "Paramètres", Toast.LENGTH_SHORT).show();

                for(int i = 0; i < adapter.getCount();i++ ){
                        SmsData sms = adapter.getItem(i);
                    if (sms.isSelected()){
                        deleteMessage(ReceiveDataActivity.this,  sms.getId());
                    }
                }




                return true;
            case R.id.checkbox:


                 int visibility = View.VISIBLE;
                if(item.getTitle().equals("Selectionner")){
                    Toast.makeText(ReceiveDataActivity.this, "Selectionner", Toast.LENGTH_SHORT).show();
                    item.setTitle("Deselectionner");

                    visibility = View.VISIBLE;
                }
                else{
                    Toast.makeText(ReceiveDataActivity.this, "Deselectionner", Toast.LENGTH_SHORT).show();
                    item.setTitle("Selectionner");
                    visibility = View.GONE;
                }



                if(adapter!=null && adapter instanceof ListAdapter) {

                    for(int i = 0; i < listView.getChildCount();i++ ){
                        RelativeLayout o = (RelativeLayout) listView.getChildAt(i);
                        CheckBox cb = (CheckBox)o.findViewById(R.id.CheckBox01);
                        if(visibility == View.GONE){
                            cb.setChecked(false);
                        }
                        cb.setVisibility(visibility);
                    }

                    adapter.notifyDataSetChanged();
                }

                return true;

        }
        return false;
    }*/


}
