package com.willowtreeapps.androidinstantappsdemo.data.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseTokenListenerService extends FirebaseInstanceIdService {

    public static final String TAG = FirebaseTokenListenerService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        // log updated InstanceID token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, refreshedToken);

    }

}
