package com.willowtreeapps.androidinstantappsdemo.util;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by willowtree on 5/12/17.
 */

public class FirebaseRequestHandler extends RequestHandler {

    private static final String TAG = FirebaseRequestHandler.class.getSimpleName();
    private static final String SCHEME_FIREBASE_STORAGE = "gs";

    private final FirebaseStorage firebaseStorage;

    public FirebaseRequestHandler() {
        firebaseStorage = FirebaseStorage.getInstance();
    }

    @Override
    public boolean canHandleRequest(Request data) {
        String scheme = data.uri.getScheme();
        return SCHEME_FIREBASE_STORAGE.equals(scheme);
    }

    @Override
    public Result load(Request request, int networkPolicy) throws IOException {
        Log.i(TAG, "load " + request.uri.toString());
        StorageReference gsReference = firebaseStorage.getReferenceFromUrl(request.uri.toString());
        StreamDownloadTask mStreamTask = gsReference.getStream();

        InputStream inputStream;
        try {
            inputStream = Tasks.await(mStreamTask).getStream();
            return new Result(BitmapFactory.decodeStream(inputStream), Picasso.LoadedFrom.NETWORK);
        } catch (ExecutionException | InterruptedException e) {
            throw new IOException(e);
        }
    }

}
