package com.example.user.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver{
    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (ACTION.equals(intent.getAction())) {
            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SmsMessage[] msgs = getMessageFromIntent(intent);

            StringBuilder sBuilder = new StringBuilder();
            if (msgs != null && msgs.length > 0 ) {
                for (SmsMessage msg : msgs) {
                    sBuilder.append("receive text：\nsender：");
                    sBuilder.append(msg.getDisplayOriginatingAddress());
                    sBuilder.append("\n------content-------\n");
                    sBuilder.append(msg.getDisplayMessageBody());
                    i.putExtra("sms_address", msg.getDisplayOriginatingAddress());
                    i.putExtra("sms_body", msg.getDisplayMessageBody());
                }
            }
            Toast.makeText(context, sBuilder.toString(), 1000).show();
            context.startActivity(i);
        }

    }
    public static SmsMessage[] getMessageFromIntent(Intent intent) {
        SmsMessage retmeMessage[] = null;
        Bundle bundle = intent.getExtras();
        Object pdus[] = (Object[]) bundle.get("pdus");
        retmeMessage = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            byte[] bytedata = (byte[]) pdus[i];
            retmeMessage[i]  = SmsMessage.createFromPdu(bytedata);
        }
        return retmeMessage;
    }
}