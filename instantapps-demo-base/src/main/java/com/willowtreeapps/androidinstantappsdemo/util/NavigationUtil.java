package com.willowtreeapps.androidinstantappsdemo.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Locale;

import static com.willowtreeapps.androidinstantappsdemo.util.Constants.ROOT_ENDPOINT;

public class NavigationUtil {

    private NavigationUtil() {
    }

    public static void goToRoot(Context context) {
        invokeDeepLink(context, String.format(Locale.US, "%s/", ROOT_ENDPOINT));
    }

    public static void goToItem(Context context, long itemId) {
        invokeDeepLink(context, String.format(Locale.US, "%s/item/%d/", ROOT_ENDPOINT, itemId));
    }

    public static void goToCart(Context context, String cartId) {
        invokeDeepLink(context, String.format(Locale.US, "%s/cart/%s/", ROOT_ENDPOINT, cartId));
    }

    private static void invokeDeepLink(Context context, String deepLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        context.startActivity(intent);
    }

}
