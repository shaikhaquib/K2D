package com.kirana2door.kiranatodoor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.kirana2door.kiranatodoor.R;

public class AboutUs extends AppCompatActivity {

    public String s1,s2,s3;
    public TextView intro,details,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        intro = findViewById(R.id.intro);
        details = findViewById(R.id.details);
        add = findViewById(R.id.add);

        s1 = "Save more with <b>Kirana2door!</b> We give you all the \ngrocery need.\n" +
                "We are an <b>e-commerce service</b> that specialize in selling \n<b>fresh  groceries</b> and \ndeliver them to our customer." +
                "You can choose your \n<b>favourite groceries anytime</b> at \nthe comfort of your cozy home.\n";

        intro.setText(Html.fromHtml(s1));

        s2 = "•\tChoose your favouriteGrocery Shop.\n" +
                "•\tJust one click! You will get freshest, groceries your doorstep.\n" +
                "•\tNo worry to carry all the heavy bags.\n" +
                "•\tWe’ll transport your groceries over and bring it right to your doorstep!\n" +
                "The mobile apps for Android platforms can be \ndownloaded at <b>Kirana2door.com/download</b> \nwhereas web orders can be placed at <b>Kirana2door.com</b>.\n" +
                "\nThe friendly application provides easy modes of \npayment. It also allows you \nto schedule the time of delivery.\n\n";
        details.setText(Html.fromHtml(s2));

        s3 = "Gurusharnam Complex, Market Yard Rd, \n" +
                "Old Panvel, Panvel, Navi Mumbai, \n" +
                "Maharashtra 410206.\n";

        add.setText(Html.fromHtml(s3));
    }
}
