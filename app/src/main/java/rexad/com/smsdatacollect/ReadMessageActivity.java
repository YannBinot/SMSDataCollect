package rexad.com.smsdatacollect;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import rexad.com.smsdatacollect.exporter.FileHelper;
import rexad.com.smsdatacollect.ui.SMSDataCollectInput;
import rexad.com.smsdatacollect.ui.UIDataCollectBuilder;

/**
 * This class read selected message.
 * Dynamically the field and the associate value are displayed.
 * @author Yann
 */
public class ReadMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);
        ActivityCompat.requestPermissions(ReadMessageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        TextView tv = (TextView) findViewById(R.id.readMessage);
        Button exporter = (Button) findViewById(R.id.ExporterButton);
        final String value = getIntent().getStringExtra("value");


        UIDataCollectBuilder builder = new UIDataCollectBuilder();

        final List<SMSDataCollectInput> datas =  builder.buid();

        String valueToConvert = value.replaceAll("<smsdata>", "");
        valueToConvert = valueToConvert.replaceAll("</smsdata>", "");
        final String[] split = valueToConvert.split("@");

        StringBuffer bufferForDisplay =  new StringBuffer();
        int i = 1;
        for(SMSDataCollectInput input:datas){
            bufferForDisplay.append((input.getName()+" : "+split[i] +"\n\n"));
            i++;
        }


        exporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                int i = 1;
                for(SMSDataCollectInput input :datas){
                    buffer.append(input.getShortName()+";");
                    buffer.append(split[i]+";\n");
                    i++;
                }
                buffer.append("\n");

                if (true){// TODO faire le bon test
                    String file  =  "data"+  Calendar.getInstance().getTimeInMillis()+".csv";

                    try {
                        String chemin =Environment.getExternalStorageDirectory().toString()+"/Download/";
                        File fichier = new File(chemin, file);
                        fichier.createNewFile();
                        FileWriter filewriter = new FileWriter(fichier,true);
                        filewriter.write(buffer.toString());
                        filewriter.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(ReadMessageActivity.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReadMessageActivity.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });


        tv.setText(bufferForDisplay.toString());
    }
}
