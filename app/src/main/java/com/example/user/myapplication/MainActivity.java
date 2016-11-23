package com.example.user.myapplication;

import android.content.SharedPreferences;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.SpannedString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.net.Uri;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.text.SpannableString;
import android.text.Spannable;
import android.text.Spanned;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebView;
import android.net.http.SslError;
import android.webkit.WebViewClient;
import android.webkit.SslErrorHandler;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;




public class MainActivity extends AppCompatActivity {


    Toolbar mToolbar;
    Button button1;
    Button button2;
    MyApp myApp;
     TextView textView;


     public void setLinkClickable(final SpannableStringBuilder clickableHtmlBuilder,
                                  final URLSpan urlSpan) {
        int start = clickableHtmlBuilder.getSpanStart(urlSpan);
        int end = clickableHtmlBuilder.getSpanEnd(urlSpan);
        int flags = clickableHtmlBuilder.getSpanFlags(urlSpan);
        clickableHtmlBuilder.removeSpan(urlSpan);
        clickableHtmlBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(urlSpan.getURL()));



            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }
    private CharSequence getClickableHtml(String html) {
        Spanned spannedHtml = Html.fromHtml(html);
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
        for(final URLSpan span : urls) {
            setLinkClickable(clickableHtmlBuilder, span);
        }
        return clickableHtmlBuilder;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setLogo(R.drawable.ic_action_name);
        mToolbar.setTitle("Traceur Sidonie");


        textView = (TextView) findViewById(R.id.textView1);
        
        Intent intent = getIntent();
        WebView View  = (WebView)findViewById(R.id.webView);
        View.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        View.getSettings().setJavaScriptEnabled(true);

        View.getSettings().setSaveFormData(true);
        View.getSettings().setDefaultTextEncodingName("UTF-8");
        View.getSettings().setUseWideViewPort(true);
        View.getSettings().setLoadWithOverviewMode(true);
        View.getSettings().setUserAgentString("Mozilla/5.0(fr-fr)");
        View.setFocusable(false);

        if (intent != null) {
            String address = intent.getStringExtra("sms_address");
            if (address != null) {
                textView.append("\n\nsender：\n" + address);
                String bodyString = intent.getStringExtra("sms_body");
                if (bodyString != null) {
                    textView.append("\ncontent：\n" + bodyString);
                    textView.setText(getClickableHtml(bodyString));
                    //textView.setMovementMethod(LinkMovementMethod.getInstance());




                    URLSpan[] urlSpans = textView.getUrls();
                    for ( URLSpan urlSpan : urlSpans )
                    {
                        View.loadUrl( urlSpan.getURL() );
                    }


                }

            }


        }

        class webViewClient extends WebViewClient {

            //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                //如果不需要其他对点击链接事件的处理返回true，否则返回false

                return true;
            }
        }










                setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        ConActivity1.class);
                startActivity(myIntent);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        myApp = (MyApp)getApplication();
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                SharedPreferences sharedPreferences= getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                String phoneNo = sharedPreferences.getString("phone", "");
                String sms ="0000,A00,";

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);

                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }

            }
        }
        );


    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






}
