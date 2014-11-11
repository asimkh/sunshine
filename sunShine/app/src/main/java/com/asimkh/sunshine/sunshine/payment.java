package com.asimkh.sunshine.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import static android.view.View.OnClickListener;


public class payment extends Activity {

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;
<<<<<<< HEAD
    private static final String CONFIG_CLIENT_ID = "AVgrmxBcJsBvrymRzZOGPNsKSQfD4-EhqB79h0ValhkQzw8M3AEqicl2yy6E";
    private static final String CONFIG_RECEIVER_EMAIL = "asimkh@hotmail.com";
=======
    private static final String CONFIG_CLIENT_ID = "ID";
>>>>>>> FETCH_HEAD
    private static final int REQUEST_PAYPAL_PAYMENT = 100;

    private static PayPalConfiguration configPayPyal = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID);
            //.merchantName("SunShine App");
            //.merchantPrivacyPolicyUri(Uri.parse("http://"))
            //.merchantUserAgreementUri()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);







         /* call PayPal Services */

        Intent initPayPal = new Intent(this, PayPalService.class);

        //initPayPal.putExtra(PaymentActivity.CONNECTIVITY_SERVICE)
        initPayPal.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configPayPyal);

        startService(initPayPal);

        /* Paypayment button */

        Button PaymentB1 = (Button) findViewById(R.id.PayPalButton1);
        PaymentB1.setOnClickListener(new OnClickListener(){

        @Override
        public void onClick(View v) {
            PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(1), "USD", "SunShine App", PayPalPayment.PAYMENT_INTENT_SALE);

            Intent intentPayPal = new Intent(payment.this, PaymentActivity.class);
            intentPayPal.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configPayPyal);
            intentPayPal.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);


            startActivityForResult(intentPayPal, REQUEST_PAYPAL_PAYMENT);
                }

            });

        /* ------- */



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PAYPAL_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data
                        .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        System.out.println("Responseeee"+confirm);
                        Log.i("paymentExample", confirm.toJSONObject().toString());


                        JSONObject jsonObj=new JSONObject(confirm.toJSONObject().toString());

                        String paymentId=jsonObj.getJSONObject("response").getString("id");
                        System.out.println("payment id:-=="+paymentId);
                        Toast.makeText(getApplicationContext(), paymentId, Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment was submitted. Please see the docs.");
            }
        }


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
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
