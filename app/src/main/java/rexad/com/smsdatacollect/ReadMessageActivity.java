package rexad.com.smsdatacollect;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import rexad.com.smsdatacollect.exporter.FileHelper;

public class ReadMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);
        ActivityCompat.requestPermissions(ReadMessageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        TextView tv = (TextView) findViewById(R.id.readMessage);
        Button exporter = (Button) findViewById(R.id.ExporterButton);
        final String value = getIntent().getStringExtra("value");


        exporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueToConvert = value.replaceAll("<smsdata>", "");
                valueToConvert = valueToConvert.replaceAll("</smsdata>", "");
                String[] split = valueToConvert.split("@");
                StringBuffer buffer = new StringBuffer();
                buffer.append("Nom du village;");
                buffer.append(split[1]+";\n");
                buffer.append("Date;");
                buffer.append(split[2]+";\n");
                buffer.append("Mois de référence;");
                buffer.append(split[3]+";\n");
                buffer.append("Nom de l’expéditeur;");
                buffer.append(split[4]+";\n");
                buffer.append("M3 compteur forage du mois;");
                buffer.append(split[5]+";\n");
                buffer.append("M3 compteurs BF du mois;");
                buffer.append(split[6]+";\n");
                buffer.append("M3 compteurs BP du mois;");
                buffer.append(split[7]+";\n");
                buffer.append("Paiements eau de PMH dans le mois;");
                buffer.append(split[8]+";\n");
                buffer.append("Facturation BF et BP envoyée ce mois;");
                buffer.append(split[9]+";\n");
                buffer.append("Paiements BF et BP reçus ce mois;");
                buffer.append(split[10]+";\n");
                buffer.append("Montant mis en compte d’épargne ce mois;");
                buffer.append(split[11]+";\n");
                buffer.append("Solde compte d’épargne en fin de mois;");
                buffer.append(split[12]+";\n");
                buffer.append("Dépenses exceptionnelles du mois (nvx équipts..);");
                buffer.append(split[13]+";\n");
                buffer.append("Nature dépense exceptionnelle;");
                buffer.append(split[14]+";\n");
                buffer.append("\n");

              System.out.println(buffer.toString());
                if (true){
                    String file  =  "data"+  Calendar.getInstance().getTimeInMillis()+".csv";

                    try {


                        String chemin =Environment.getExternalStorageDirectory().toString()+"/Download/";//getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                        File fichier = new File(chemin, file);
                        System.out.println(fichier.getAbsolutePath().toString());
                        fichier.createNewFile();
                        FileWriter filewriter = new FileWriter(fichier,true);
                        filewriter.write(buffer.toString());
                        filewriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(ReadMessageActivity.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReadMessageActivity.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });


        tv.setText(value);
    }
}
