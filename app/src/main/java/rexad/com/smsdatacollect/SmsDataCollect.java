package rexad.com.smsdatacollect;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rexad.com.smsdatacollect.write.WriteSms;

public class SmsDataCollect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_data_collect);
        Button buttonOK = (Button)findViewById(R.id.okButton);

        final EditText dateEditText = (EditText)findViewById(R.id.DateEditText);
        dateEditText.setFreezesText(true);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SmsDataCollect.this, "Date", Toast.LENGTH_SHORT).show();
                final Calendar cal = Calendar.getInstance();

              new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    cal.set(year,month,dayOfMonth);
                      SimpleDateFormat dateFormatter = new SimpleDateFormat(
                              "dd/MM/yyyy");
                     dateEditText.setText(dateFormatter.format(cal.getTime()));
                  }
              }, cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH),  cal.get(Calendar.DAY_OF_MONTH)).show();
                // DatePickerDialog dialog = new DatePickerDialog();
            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String numero =  phoneNumber.getText().toString();
                SharedPreferences settings = getSharedPreferences("ParameterPrefs", 0);
                String numero = settings.getString("PhoneNumber", "");
                ActivityCompat.requestPermissions(SmsDataCollect.this,new String[]{Manifest.permission.SEND_SMS},1);
                ActivityCompat.requestPermissions(SmsDataCollect.this,new String[]{Manifest.permission.RECEIVE_SMS},1);
                ActivityCompat.requestPermissions(SmsDataCollect.this,new String[]{Manifest.permission.READ_SMS},1);
                ActivityCompat.requestPermissions(SmsDataCollect.this,new String[]{Manifest.permission.READ_CONTACTS},1);

                WriteSms writeSms = new WriteSms();
                boolean isSendable = true;
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.NomVillageEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.DateEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.MoisDeReferenceEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.NomExpediteurEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.M3CompteurForageDuMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.M3CompteursBFDuMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.M3CompteursBPDuMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.PaiementsEauDePMHDansLeMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.FacturationBFetBPEnvoyeeCeMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.PaiementsBFetBPrecusCeMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.MontantMisEnCompteEpargneCeMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.SoldeCompteEpargneEnFinDeMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.DepensesExceptionnellesDuMoisEditText));
                isSendable&=writeSms.addParameterToWrite(getValue(R.id.NatureDepenseExceptionnelleEditText));
                //String message = "Salut, Je t'envoie ce message pour tester l'application";

                String message = writeSms.writeMessage();

                if(numero.length() >=4 && message.length()>0 && isSendable){
                    System.out.println(message);
                    SmsManager.getDefault().sendTextMessage(numero, null, message,null,null);
                    Toast.makeText(SmsDataCollect.this, "Message envoyé", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SmsDataCollect.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String getValue(int id){

        View view = findViewById(id);
       if(view instanceof  EditText) {
           return ((EditText)view).getText().toString();
       }
        return "";
    }

    //Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
    public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.monmenu, menu);
        return true;
    }
    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.parameter:
                Toast.makeText(SmsDataCollect.this, "Paramètres", Toast.LENGTH_SHORT).show();
                Intent parametreIntent = new Intent(SmsDataCollect.this, ParameterActivity.class);
                startActivity(parametreIntent);
                return true;
            case R.id.quitter:
                //Pour fermer l'application il suffit de faire finish()
                finish();
                return true;
        }
        return false;}

}
