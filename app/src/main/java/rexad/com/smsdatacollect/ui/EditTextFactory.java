package rexad.com.smsdatacollect.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import rexad.com.smsdatacollect.R;


/**
 * Created by Yann Binot Local on 25/10/2016.
 */

public class EditTextFactory {






    public static void createEditTextToLayout(final Context context, LinearLayout layout, final SMSDataCollectInput dataInput){



        TextView tv = new TextView(context);
        tv.setText(dataInput.getShortName());
        tv.setPadding(0,0,15,0);
        tv.setEms(10);

        LinearLayout.LayoutParams subparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT/*,19.0f*/);
        subparam.setMargins(0,dpToPx(15),0,0);
        tv.setLayoutParams(subparam);

        layout.addView(tv);

        final EditText t = new EditText(context);
        t.setId(dataInput.getId());
        t.setBackgroundResource(R.drawable.edit_text_style);
        t.setPadding(0,0,15,0);
        t.setEms(10);
        t.setInputType(dataInput.getInputType());
        t.setHint(dataInput.getName());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT/*, 20f*/);
        param.setMargins(0,dpToPx(15),0,0);
        t.setLayoutParams(param);

        SharedPreferences settings = context.getSharedPreferences("SmsDataCollect", 0);
        String key = dataInput.getShortName().trim();
        String value = settings.getString(key, "");
        t.setText(value);
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                SharedPreferences settings = context.getSharedPreferences("SmsDataCollect", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(dataInput.getShortName().trim(),t.getText().toString());

                // Commit the edits!
                editor.commit();



            }


        });


        layout.addView(t);


        View view = new View(context);

        LinearLayout.LayoutParams paramView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dpToPx(1)/*, 20f*/);

        paramView.setMargins(0,dpToPx(15),0,0);
        view.setBackgroundColor(Color.DKGRAY);
        view.setLayoutParams(paramView);

        layout.addView(view);




    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }



}
