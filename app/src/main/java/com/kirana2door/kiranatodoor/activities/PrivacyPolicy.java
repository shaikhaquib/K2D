package com.kirana2door.kiranatodoor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.kirana2door.kiranatodoor.R;

public class PrivacyPolicy extends AppCompatActivity {

    TextView policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().hide();
        policy = findViewById(R.id.privacypolicy);

        String htmlstring = "<p><b>Privacy Policy : </b> <br><br> The Company respects your privacy and values the " +
                "trust you place in it. Set out below is the Company&rsquo;s &lsquo;Privacy Policy&rsquo; " +
                "which details the manner in which information relating to you is collected, used and disclosed. <br><br>" +
                "Customer are advised to read and understand our Privacy Policy carefully, as by accessing the " +
                "website/app you agree to be bound by the terms and conditions of the Privacy Policy and consent " +
                "to the collection, storage and use of information relating to you as provided herein.<br><br> " +
                "If you do not agree with the terms and conditions of our Privacy Policy, including in relation to " +
                "the manner of collection or use of your information, please do not use or access the website/app.<br><br> " +
                "Our Privacy Policy is incorporated into the Terms and Conditions of Use of the website/app, and is " +
                "subject to change from time to time without notice. It is strongly recommended that you periodically " +
                "review our Privacy Policy as posted on the App/Web.<br><br> Should you have any clarifications regarding this " +
                "Privacy Policy, please do not hesitate to contact us at <b>info@kirana2door.com</b>.<br><br> <b>The collection, Storage " +
                "and Use of Information Related to You </b> <br><br>We may automatically track certain information about you based " +
                "upon your behaviour on the website. We use this information to do internal research on our users&rsquo; " +
                "demographics, interests, and behaviour to better understand, protect and serve our users. This information " +
                "is compiled and analyzed on an aggregated basis. This information may include the URL that you just came " +
                "from (whether this URL is on the website or not), which URL you next go to (whether this URL is on the " +
                "website or not), your computer browser information, your IP address, and other information associated " +
                "with your interaction with the website.<br><br> We also collect and store personal information provided by you " +
                "from time to time on the website/app. We only collect and use such information from you that we consider " +
                "necessary for achieving a seamless, efficient and safe experience, customized to your needs including: <br>" +
                "1. To enable the provision of services opted for by you; <br>" +
                "2. To communicate necessary account and product/service related information from time to time;<br> " +
                "3. To allow you to receive quality customer care services;<br> " +
                "4. To undertake necessary fraud and money laundering prevention checks, and comply with the highest security standards; <br>" +
                "5. To comply with applicable laws, rules and regulations; and <br>" +
                "6. To provide you with information and offers on products and services, on updates, on promotions, on related, " +
                "affiliated or associated service providers and partners, that we believe would be of interest to you. <br><br>  " +
                "Where any service requested by you involves a third party, such information as is reasonably necessary by " +
                "the Company to carry out your service request may be shared with such third party. We also do use your " +
                "contact information to send you offers based on your interests and prior activity. The Company may also use " +
                "contact information internally to direct its efforts for product improvement, to contact you as a survey " +
                "respondent, to notify you if you win any contest; and to send you promotional materials from its contest " +
                "sponsors or advertisers. <br><br>Contacts Permissions: If you allow Kirana2door to access your contacts " +
                "(including contact number, email id etc.), it enables Kirana2door to subscribe you and your contacts to " +
                "Kirana2door promotional emails, messages, ongoing offers etc., and through this permission you and your " +
                "contacts will be able to access a variety of social features such as inviting your friends to try our app, " +
                "send across referral links to your friends, etc. We may also use this information to make recommendations of " +
                "grocery items you placed. This information will be synced from your phone and stored on our servers.<br><br> Further, " +
                "you may from time to time choose to provide payment related financial information (credit card, debit card, bank " +
                "account details, billing address etc.) on the website. We are committed to keeping all such sensitive " +
                "data/information safe at all times and ensure that such data/information is only transacted over secure " +
                "website [of approved payment gateways which are digitally encrypted], and provide the highest possible degree " +
                "of care available under the technology presently in use. The Company will not use your financial information for " +
                "any purpose other than to complete a transaction with you. <br><br>To the extent possible, we provide you the option of " +
                "not divulging any specific information that you wish for us not to collect, store or use. You may also choose not " +
                "to use a particular service or feature on the website/application, and opt out of any non-essential communications " +
                "from the Company. <br><br>Further, transacting over the internet has inherent risks which can only be avoided by you " +
                "following security practices yourself, such as not revealing account/login related information to any other " +
                "person and informing our customer care team about any suspicious activity or where your account has/may have " +
                "been compromised.<br><br> Company use data collection devices such as &ldquo;cookies&rdquo; on certain pages of the " +
                "website to help analyse our web page flow, measure promotional effectiveness, and promote trust and safety. &ldquo;" +
                "Cookies&rdquo; are small files placed on your hard drive that assist us in providing our services. Company offers " +
                "certain features that are only available through the use of a &ldquo;cookie&rdquo;.<br><br> The Company also use cookies " +
                "to allow you to enter your password less frequently during a session. Cookies can also help the Company provide " +
                "information that is targeted to your interests. Most cookies are &ldquo;session cookies,&rdquo; meaning that they " +
                "are automatically deleted from your hard drive at the end of a session. You are always free to decline our " +
                "cookies if your browser permits, although in that case you may not be able to use certain features on the website " +
                "and you may be required to re-enter your password more frequently during a session. <br><br>Additionally, you may " +
                "encounter &ldquo;cookies&rdquo; or other similar devices on certain pages of the website that are placed by " +
                "third parties. The Company does not control the use of cookies by third parties. If you send the Company " +
                "personal correspondence, such as emails or letters, or if other users or third parties send us correspondence " +
                "about your activities on the website, the Company may collect such information into a file specific to you.<br><br> The " +
                "Company does not retain any information collected for any longer than is reasonably considered necessary by us," +
                " or such period as may be required by applicable laws. The Company may be required to disclose any information " +
                "that is lawfully sought from it by a judicial or other competent body pursuant to applicable laws.<br><br> The website " +
                "may contain links to other websites. We are not responsible for the privacy practices of such websites which we " +
                "do not manage and control.<br><br><b> Choices Available Regarding Collection, Use and Distribution of Information </b> <br><br>To protect " +
                "against the loss, misuse and alteration of the information under its control, the Company has in place appropriate " +
                "physical, electronic and managerial procedures. For example, the Company servers are accessible only to authorized " +
                "personnel and your information is shared with employees and authorized personnel on a need to know basis to complete " +
                "the transaction and to provide the services requested by you. Although the Company endeavours to safeguard the " +
                "confidentiality of your personally identifiable information, transmissions made by means of the Internet cannot " +
                "be made absolutely secure. By using the website, you agree that the Company will have no liability for disclosure " +
                "of your information due to errors in transmission and/or unauthorized acts of third parties. Please note that the " +
                "Company will not ask you to share any sensitive data or information via email or telephone. If you receive any such " +
                "request by email or telephone, please do not respond/divulge any sensitive data or information and forward the " +
                "information relating to the same to <b>info@kirana2door.com</b> for necessary action.<br><br> <b> Communication with Company</b><br><br>" +
                " If you " +
                "wish to correct or update any information you have provided, you may do so online, on the website itself. " +
                "Alternatively, you may contact the Company to correct or update such information by sending an e-mail to: " +
                "<b>info@kirana2door.com</b>. In the event of loss of access to the website, you may contact the Company by sending " +
                "an e-mail to: <b>info@kirana2door.com</b>. <br><br>In the event you wish to report a breach of the Privacy Policy, you may " +
                "contact the designated Grievance Officer of the Company at:<br><br> <b>Gurusharnam Complex, Market Yard Rd, <br>  Old Panvel, " +
                "Panvel, Navi Mumbai,<br>   Maharashtra 410206.</b><br> Email address: <b>info@kirana2door.com</b> </p>";

        policy.setText(Html.fromHtml(htmlstring));
    }
}
