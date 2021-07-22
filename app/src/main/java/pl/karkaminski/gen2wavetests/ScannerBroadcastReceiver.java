package pl.karkaminski.gen2wavetests;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScannerBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "karkaminski";
    private OnBarcodeScannedListener listener;
    public ScannerBroadcastReceiver(OnBarcodeScannedListener listener) {
        Log.i(TAG, "ScannerBroadcastReceiver: ");
        this.listener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        listener.onBarcodeScanned(intent.getStringExtra("SCAN_RESULT"));
    }
}
