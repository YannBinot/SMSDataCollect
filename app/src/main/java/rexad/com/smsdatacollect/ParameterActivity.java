package rexad.com.smsdatacollect;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParameterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);
        // Restore preferences
        SharedPreferences settings = getSharedPreferences("ParameterPrefs", 0);
        String numero = settings.getString("PhoneNumber", "");
        final EditText phoneNumber = (EditText)findViewById(R.id.phoneNumberFromParameter);

        phoneNumber.setText(numero);


        Button valider = (Button)findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We need an Editor object to make preference changes.
                // All objects are from android.context.Context
                SharedPreferences settings = getSharedPreferences("ParameterPrefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("PhoneNumber", phoneNumber.getText().toString());

                // Commit the edits!
                editor.commit();

                Toast.makeText(ParameterActivity.this, "Numéro validé", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
