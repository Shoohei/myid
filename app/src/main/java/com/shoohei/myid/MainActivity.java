package com.shoohei.myid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView message = (TextView) findViewById(R.id.message_view);
        Button button = (Button) findViewById(R.id.button);

        // Ajout de l'ID Device au feedback
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        final String messageDisplayed = getString(R.string.message) + deviceId;
        message.setText(messageDisplayed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "rabbitbattery@gmail.com" });
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, messageDisplayed);

                startActivity(Intent.createChooser(emailIntent, getString(R.string.send)));
            }
        });
    }
}
