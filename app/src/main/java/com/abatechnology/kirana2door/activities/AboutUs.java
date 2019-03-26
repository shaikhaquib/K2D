package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abatechnology.kirana2door.R;

public class AboutUs extends AppCompatActivity {

    public String s1,s2,s3;
    public TextView intro,details,add;
    public ImageView twitter,facebook,instagram,gmail;
    public static String FACEBOOK_URL = "https://www.facebook.com/Kirana2door-244570766215219";
    public static String FACEBOOK_PAGE_ID = "Kirana2door-244570766215219";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();
        intro = findViewById(R.id.intro);
        details = findViewById(R.id.details);
        twitter = findViewById(R.id.twitter);
        facebook = findViewById(R.id.facebook);
        instagram = findViewById(R.id.instagram);
        gmail = findViewById(R.id.gmail);
        add = findViewById(R.id.add);

        s1 = "Save more with <b>Kirana2door!</b> We give you all the <br>grocery need.<br>" +
                "We are an <b>e-commerce service</b> that specialize in selling <br><b>fresh  groceries</b> and <br>deliver them to our customer." +
                "You can choose your <br><b>favourite groceries anytime</b> at <br>the comfort of your cozy home.<br>";

        intro.setText(Html.fromHtml(s1));

        s2 = "•&emsp;Choose your favouriteGrocery Shop.<br>" +
                "•&emsp;Just one click! You will get freshest, groceries your doorstep.<br>" +
                "•&emsp;No worry to carry all the heavy bags.<br>" +
                "•&emsp;We’ll transport your groceries over and bring it right to your doorstep!<br>" +
                "<br>The mobile apps for Android platforms can be <br>downloaded at <b>Kirana2door.com/download</b> <br>whereas " +
                "web orders can be placed at <b>Kirana2door.com</b>.<br>" +
                "<br>The friendly application provides easy modes of <br>payment. It also allows you <br>" +
                "to schedule the time of delivery.<br>";
        details.setText(Html.fromHtml(s2));

        s3 = "<b>Gurusharnam Complex, Market Yard Rd, <br>" +
                "Old Panvel, Panvel, Navi Mumbai, <br>" +
                "Maharashtra 410206.<br></b>";

        add.setText(Html.fromHtml(s3));

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=Kirana2D"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Kirana2D"));
                }
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL();
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                String[] recipients={"info@kirana2door.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Send mail via..."));
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://instagram.com/_u/" + "kirana2door"));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/" + "kirana2door")));
                }
            }
        });
    }

    public String getFacebookPageURL() {
        PackageManager packageManager = getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
