package net.albertogarrido.dawandalite.network;


import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class NetworkStatusLiveData extends LiveData<Boolean> {

    private final Context context;

    private BroadcastReceiver broadcastReceiver = null;

    public NetworkStatusLiveData(Context context) {
        this.context = context;
    }

    public static Boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onActive() {
        super.onActive();
        registerBroadCastReceiver();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        unRegisterBroadCastReceiver();
    }

    public void cancelSubscription() {
        unRegisterBroadCastReceiver();
    }

    private void registerBroadCastReceiver() {
        if (broadcastReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle extras = intent.getExtras();
                    NetworkInfo info = extras.getParcelable("networkInfo");
                    setValue(info != null && info.getState() == NetworkInfo.State.CONNECTED);
                }
            };
            context.registerReceiver(broadcastReceiver, filter);
        }
    }

    private void unRegisterBroadCastReceiver() {
        if (broadcastReceiver != null) {
            context.unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }
}
