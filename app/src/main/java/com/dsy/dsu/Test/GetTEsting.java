package com.dsy.dsu.Test;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

import androidx.core.app.ActivityCompat;



import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetTEsting {

    public String getImsiFromSubscriptionManager(@NotNull Context context) {
        String simSerialNo = "";
        try {
            SubscriptionManager subsManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                List<SubscriptionInfo> subsList = subsManager.getActiveSubscriptionInfoList();
                if (subsList != null) {
                    for (SubscriptionInfo subsInfo : subsList) {
                        if (subsInfo != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                simSerialNo = subsInfo.getMccString() + subsInfo.getMncString() + subsInfo.getIccId().substring(8, 18);
                            }

                        }
                    }
                } else {
                    simSerialNo = "WiFi";

                }


                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(simSerialNo.isEmpty())
            simSerialNo = "N/A";

        return simSerialNo;
    }
}
