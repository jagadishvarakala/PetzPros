package com.petz.pros.root;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.petz.pros.R;
import com.petz.pros.data.BaseDataManager;
import com.petz.pros.data.prefs.PreferencesManager;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.main.ui.dashboard.DashBoardActivity;
import com.petz.pros.ui.main.ui.past.PastServicesActivity;
import com.petz.pros.ui.main.ui.pending.PendingServicesActivity;
import com.petz.pros.ui.main.upcoming.UpcomingServicesActivity;
import com.petz.pros.ui.splash.SplashActivity;

import java.util.Map;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

public class FirebaseMessaging extends FirebaseMessagingService {

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.e("My Token",token);
                    }
                });
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
     //   sendRegistrationToServer(token);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //int type=getSharedPreferences("login_info",MODE_PRIVATE).getInt("usertype",-1);

        Map<String, String> data = remoteMessage.getData();
        String body = data.get("body");
        String title = data.get("title");
        String className = data.get("click_action");
        Intent intent;
        if(className != null && className.equalsIgnoreCase("BOOKING_INITIATE")){
            intent = new Intent(getApplicationContext(), PendingServicesActivity.class);
        } else if(className != null && className.equalsIgnoreCase("BOOKING_STATUS")) {
            intent = new Intent(getApplicationContext(), NavigationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("data", title);
            intent.putExtra("KEY", true);
        }else if(className != null && className.equalsIgnoreCase("PAYMENT_STATUS")){
            intent = new Intent(getApplicationContext(), UpcomingServicesActivity.class);
            intent.putExtra("body",true);
        }else{
            intent = new Intent(getApplicationContext(), SplashActivity.class);
        }
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            channel = new NotificationChannel("222", "petzpros_channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bark),att);
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getApplicationContext(), "222")
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setLargeIcon(((BitmapDrawable)getDrawable(R.mipmap.ic_launcher)).getBitmap())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bark))
                        .setContentText(body)
                        .setContentIntent(pi);

        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        if (nm != null) {
            nm.notify(101, builder.build());
        }
    }

}
