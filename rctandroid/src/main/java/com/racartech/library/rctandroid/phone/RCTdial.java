package com.racartech.library.rctandroid.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class RCTdial {

    public static void dial(Activity activity, String phone_number) {
        // This method will automatically dial the phone number once called
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +phone_number));
        activity.startActivity(intent);
    }

}
