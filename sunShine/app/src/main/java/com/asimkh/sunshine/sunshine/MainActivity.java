package com.asimkh.sunshine.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //// Set the color
       // root.setBackgroundColor(getResources().getDrawable(R.id.b)



        Button WeatherBtn = (Button) findViewById(R.id.weatherButton);

        WeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this, weatherList.class));
            }
        });

        Button PayPalBtn = (Button) findViewById(R.id.payPalButton);

        PayPalBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this, payment.class));
            }
        });

        Button FeedbeackBtn = (Button) findViewById(R.id.feedbackButton);
        FeedbeackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // startActivity( new Intent(MainActivity.this, feedback.class));
                sendEmail();

            }
        });


        /*
        ListView listView1 = (ListView) findViewById(R.id.list_View_forecast);

        String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        listView1.setAdapter(adapter);


    }*/
}

    protected void sendEmail(){



        Log.i("Send email", "");

        String[] TO = {"contact@asimkh.com"};
        String[] CC = {"asimkh@hotmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        //emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        //emailIntent.putExtra(Intent.EXTRA_TEXT   , "body of email");

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
