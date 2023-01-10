package com.example.tirfe;

import static com.example.tirfe.RecyclerAdapter.fifteen;
import static com.example.tirfe.RecyclerAdapter.fifty;
import static com.example.tirfe.RecyclerAdapter.five;
import static com.example.tirfe.RecyclerAdapter.hundred;


import static com.example.tirfe.RecyclerAdapter.ten;
import static com.example.tirfe.RecyclerAdapter.trueFifteen;
import static com.example.tirfe.RecyclerAdapter.trueFifty;
import static com.example.tirfe.RecyclerAdapter.trueFive;
import static com.example.tirfe.RecyclerAdapter.trueHundred;

import static com.example.tirfe.RecyclerAdapter.trueTen;
import static com.example.tirfe.RecyclerAdapter.trueTwenty;
import static com.example.tirfe.RecyclerAdapter.trueTwenty_five;
import static com.example.tirfe.RecyclerAdapter.trueTwo_hundred;
import static com.example.tirfe.RecyclerAdapter.twenty;
import static com.example.tirfe.RecyclerAdapter.twenty_five;
import static com.example.tirfe.RecyclerAdapter.two_hundred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * Main class
 */
public class MainActivity extends AppCompatActivity{
    //Float variables for saving card margine values in the shared preference
    private float tele5, tele10, tele15, tele20, tele25, tele50, tele100, tele200;
    private float safari5, safari10, safari15, safari20, safari25, safari50, safari100, safari200;


    //Total sum variable of the actual price(sum) and resell price(trueSum)
    //of selected cards
    double sum;
    double trueSum;


    //A decimalFormat object for formatting doubles and floats to 2 decimal points
    final DecimalFormat df = new DecimalFormat("0.00");


    //A boolean for preventing the splash activity when returning from onPause
    public static boolean splashShown = false;


    //An ArrayList of cardData objects to store all the cards
    List<cardData> cardDataList;


    //Buttons on the Activity
    Button operatorButton;
    Button calc;


    //TextViews on the dialog
    TextView resulttv, profittv;


    //FrameLayout and adview for AdMob
    private FrameLayout adContainerView;
    private AdView adView;


    //The recycler view containing all the cards
    RecyclerView recyclerView;


    //Adapter for the recycler view
    RecyclerAdapter  adapter;

    /**
     * onCreate Hook method
     * @param savedInstanceState: saved data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //method call for initiating language which is settled previously
        loadLocale();


        //load margins here or if this instance of the MainActivity class is from the
        // menu toast values are already refreshed once settled on operators price menu
          try{  if((getIntent().getExtras().getBoolean("from menu"))){
              Toast.makeText(this, getResources().getString(R.string.values), Toast.LENGTH_SHORT).show();
          }} catch (Exception e){
                  loadMargines();
                }
        setContentView(R.layout.activity_main);


        //changing the title for the actionbar according to the default chosen language
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        //setting the background for the action bar
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_bar));


        //Making the splashShown boolean true to prevent splash activity once
        // MainActivity is initiated
        splashShown = true;


        //Making ethio tel as the active operator on onCreate
        MarginesUtils.operator = "ethio tel";


        //buttons on the activity
        operatorButton = findViewById(R.id.operator);
        calc = findViewById(R.id.calc_button);


        //get the reference to your FrameLayout
        adContainerView = findViewById(R.id.adView_container);


        //initialize AdMob SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });


        //Create an AdView and put it into your FrameLayout
        adView = new AdView(this);
        adContainerView.addView(adView);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");


        //start requesting banner ads
        loadBanner();


        //dialog for showing the result on pressing the calculate button
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.resultdialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        //TextViews on the dialog
        resulttv = dialog.findViewById(R.id.resultText);
        profittv = dialog.findViewById(R.id.profitText);


        //Method call for displaying the recycler view and its card components
        displayItems();


        /**
         * onClick listener for the operator button for switching between
         * the two operators
         */
        operatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MarginesUtils.operator.equals("ethio tel")) {
                    operatorButton.setText(getResources().getString(R.string.operator2));
                    MarginesUtils.switchOperator("safari");


                    //resetting the view and sum values
                    cardDataList.clear();
                    displayItems();
                    adapter.notifyDataSetChanged();
                    MarginesUtils.resetValues();
                    sum = trueSum = 0;

                }

                else if (MarginesUtils.operator.equals("safari")){
                    operatorButton.setText(getResources().getString(R.string.operator1));
                    MarginesUtils.switchOperator("ethio tel");


                    //resetting the view and sum values
                    cardDataList.clear();
                    displayItems();
                    MarginesUtils.resetValues();
                    sum = trueSum = 0;
                }

            }
        });


        /**
         * onClick for the calculate button showing the dialog with payment &
         * profit values
         */
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking sum for settled card values, if new button is not pressed show previous
                //result
                if(MarginesUtils.itemPressed){
                    checkSum();
                    MarginesUtils.itemPressed = false;
                }


                //assigning the payment and profit values, then showing them on the dialog
                String alert = getResources().getString(R.string.pay) + " " +
                         df.format(sum) + getResources().getString(R.string.Birr);
                String profitAlert = getResources().getString(R.string.profit) + " " +
                        df.format(trueSum - sum) + getResources().getString(R.string.Birr);
                resulttv.setText(alert);
                profittv.setText(profitAlert);

                dialog.show();

            }
        });




    }


    /**
     * A method for filling recycleview with common card values
     */
    private void displayItems() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        cardDataList = new ArrayList<>();
        cardDataList.add(new cardData(getResources().getString(R.string.five)));
        cardDataList.add(new cardData(getResources().getString(R.string.ten)));
        cardDataList.add(new cardData(getResources().getString(R.string.fifteen)));
        cardDataList.add(new cardData(getResources().getString(R.string.twenty)));
        cardDataList.add(new cardData(getResources().getString(R.string.twenty_five)));
        cardDataList.add(new cardData(getResources().getString(R.string.fifty)));
        cardDataList.add(new cardData(getResources().getString(R.string.hundred)));
        cardDataList.add(new cardData(getResources().getString(R.string.two_hundred)));
        adapter = new RecyclerAdapter(this,cardDataList);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //switching between menu items
        switch (item.getItemId()){
            case R.id.margine:
                sum = 0;
                trueSum = 0;
                Intent intent = new Intent(MainActivity.this,margines.class);
                startActivity(intent);
                return true;
            case R.id.lang:
                //language change dialog
                showChangeLangDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    /**
     * A method for handling language change
     */
    private void showChangeLangDialog() {
        final String[] langitems = {"አማርኛ", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.dialog);
        builder.setSingleChoiceItems(langitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    //amharic
                    setLocale("am");
                    MarginesUtils.resetValues();
                    finish();
                    getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(getIntent());
                }
                else if(i == 1){
                    //English
                    setLocale("en");
                    MarginesUtils.resetValues();
                    finish();
                    getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(getIntent());
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = builder.create();
        mDialog.show();
    }

    /**
     * A method for setting passed language then putting it with other float card margin values
     * on shared preferences to be restored in every launch of the app
     * @param lang: a language parameter
     */
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources()
                .getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.putFloat("tele5m", (float)MarginesUtils.getTeleFive());
        editor.putFloat("tele10m", (float)MarginesUtils.getTeleTen());
        editor.putFloat("tele15m", (float)MarginesUtils.getTeleFifteen());
        editor.putFloat("tele20m", (float)MarginesUtils.getTeleTwenty());
        editor.putFloat("tele25m", (float)MarginesUtils.getTeleTwentyfive());
        editor.putFloat("tele50m", (float)MarginesUtils.getTeleFifty());
        editor.putFloat("tele100m", (float)MarginesUtils.getTeleHundred());
        editor.putFloat("tele200m", (float)MarginesUtils.getTeleTwohundred());

        editor.putFloat("safari5m", (float)MarginesUtils.getSafariFive());
        editor.putFloat("safari10m", (float)MarginesUtils.getSafariTen());
        editor.putFloat("safari15m", (float)MarginesUtils.getSafariFifteen());
        editor.putFloat("safari20m", (float)MarginesUtils.getSafariTwenty());
        editor.putFloat("safari25m", (float)MarginesUtils.getSafariTwentyfive());
        editor.putFloat("safari50m", (float)MarginesUtils.getSafariFifty());
        editor.putFloat("safari100m", (float)MarginesUtils.getSafariHundred());
        editor.putFloat("safari200m", (float)MarginesUtils.getSafariTwohundred());

        editor.apply();
    }

    /**
     * A method for loading language and float card margin values from shared preference
     */
    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");

        tele5 = prefs.getFloat("tele5m", (float)MarginesUtils.getTeleFive());
        tele10 = prefs.getFloat("tele10m", (float)MarginesUtils.getTeleTen());
        tele15 = prefs.getFloat("tele15m", (float)MarginesUtils.getTeleFifteen());
        tele20 = prefs.getFloat("tele20m", (float)MarginesUtils.getTeleTwenty());
        tele25 = prefs.getFloat("tele25m", (float)MarginesUtils.getTeleTwentyfive());
        tele50 = prefs.getFloat("tele50m", (float)MarginesUtils.getTeleFifty());
        tele100 = prefs.getFloat("tele100m", (float)MarginesUtils.getTeleHundred());
        tele200 = prefs.getFloat("tele200m", (float)MarginesUtils.getTeleTwohundred());

        safari5 = prefs.getFloat("safari5m", (float)MarginesUtils.getSafariFive());
        safari10 = prefs.getFloat("safari10m", (float)MarginesUtils.getSafariTen());
        safari15 = prefs.getFloat("safari15m", (float)MarginesUtils.getSafariFifteen());
        safari20 = prefs.getFloat("safari20m", (float)MarginesUtils.getSafariTwenty());
        safari25 = prefs.getFloat("safari25m", (float)MarginesUtils.getSafariTwentyfive());
        safari50 = prefs.getFloat("safari50m", (float)MarginesUtils.getSafariFifty());
        safari100 = prefs.getFloat("safari100m", (float)MarginesUtils.getSafariHundred());
        safari200 = prefs.getFloat("safari200m", (float)MarginesUtils.getSafariTwohundred());

        //method call to put the retrieved language and float values
        // for next time use unless they are changed
        setLocale(language);

    }

    /**
     * A method for setting margin card values which are obtained from shared preference on the
     * loadLocale method
     */
    public void loadMargines() {
        MarginesUtils.setTeleFive(tele5);
        MarginesUtils.setTeleTen(tele10);
        MarginesUtils.setTeleFifteen(tele15);
        MarginesUtils.setTeleTwenty(tele20);
        MarginesUtils.setTeleTwentyfive(tele25);
        MarginesUtils.setTeleFifty(tele50);
        MarginesUtils.setTeleHundred(tele100);
        MarginesUtils.setTeleTwohundred(tele200);

        MarginesUtils.setSafariFive(safari5);
        MarginesUtils.setSafariTen(safari10);
        MarginesUtils.setSafariFifteen(safari15);
        MarginesUtils.setSafariTwenty(safari20);
        MarginesUtils.setSafariTwentyfive(safari25);
        MarginesUtils.setSafariFifty(safari50);
        MarginesUtils.setSafariHundred(safari100);
        MarginesUtils.setSafariTwohundred(safari200);
    }

    /**
     * A method for calculating the actual price(sum) and resell prices(trueSum)
     */
    public void checkSum(){

            sum = MarginesUtils.sumMyCard(five,ten,fifteen,twenty,twenty_five,fifty,hundred,
                    two_hundred);
            trueSum = MarginesUtils.sumMyCard(trueFive, trueTen, trueFifteen, trueTwenty,
                    trueTwenty_five,
                    trueFifty,trueHundred, trueTwo_hundred);

    }

    /**
     * A method for sizing ads with orientation
     */
    private AdSize getAdSize() {
        //Determine the screen width to use for the ad width
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        //selected width in dp
        int adWidth = (int) (widthPixels / density);

        //return the optimal size on portrait orientation
        return AdSize.getPortraitAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    /**
     * A method to request banner Ad by calling LoadAds() method of
     * the AdView library
     */
    private void loadBanner() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        AdSize adSize = getAdSize();
        //Set the adaptive ad size to the ad view
        adView.setAdSize(adSize);

        //Start loading the ad in the background
        adView.loadAd(adRequest);
    }
}