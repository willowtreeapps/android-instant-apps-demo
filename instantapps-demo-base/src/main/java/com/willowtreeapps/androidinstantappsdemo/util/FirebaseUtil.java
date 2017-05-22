package com.willowtreeapps.androidinstantappsdemo.util;

import com.google.firebase.storage.FirebaseStorage;

/**
 * Created by willowtree on 5/12/17.
 */

public class FirebaseUtil {

    private FirebaseUtil() {
    }

    /**
     * translate a relative file path to a full uri
     */
    public static String storagePathToUri(String path) {
        return FirebaseStorage.getInstance().getReference(path).toString();
    }

}
