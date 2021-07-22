package pl.karkaminski.gen2wavetests;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnBarcodeScannedListener {

    private static final String TAG = "karkaminski";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");

        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);

        Intent directInputIntent = new Intent("com.amarula.barcodescanner.DIRECT_INPUT");
        directInputIntent.putExtra("direct_input_enable", false);
        startService(directInputIntent);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

        registerReceiver(
                new ScannerBroadcastReceiver(this),
                new IntentFilter("com.amarula.barcodescanner.DECODE_RESULTS")
        );
    }

    @Override
    public void onBarcodeScanned(String data) {
        Log.i(TAG, "onBarcodeScanned: ");
        String string = textView.getText().toString();
        textView.setText(data + "\n" + string);
    }

    public void scan() {
        startActivityForResult(new Intent("com.google.zxing.client.android.SCAN"), 0, null);
    }


}



