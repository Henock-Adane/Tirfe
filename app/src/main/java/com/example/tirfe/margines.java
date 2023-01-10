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
import static com.example.tirfe.RecyclerAdapter.trueTwenty_five;
import static com.example.tirfe.RecyclerAdapter.trueTwo_hundred;
import static com.example.tirfe.RecyclerAdapter.twenty_five;
import static com.example.tirfe.RecyclerAdapter.two_hundred;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A class for viewing and editing operators selling price
 */
public class margines extends AppCompatActivity implements View.OnClickListener {
    //DecimalFormat object for formatting doubles and floats in to 2 decimal points
    final DecimalFormat df = new DecimalFormat("0.00");


    //tag string for log
    public static final String TAG = "Unfilled data at: ";


    //A boolean for a state where an empty edittext is found
    boolean emptyfound = false;


    //buttons on the activity
    Button operator, defaultButton, setbutton;

    //all the edittexts to be filled with price values
    EditText Et5, Et10, Et15, Et20, Et25, Et50, Et100, Et200;


    /**
     * The onCreate hook method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margines);

        //setting the actionbars background
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_bar));


        //setting the operator as ethio tel
        MarginesUtils.operator = "ethio tel";


        //edittext linking from resource
        Et5 = findViewById(R.id.cardEditText);
        Et10 = findViewById(R.id.cardEditText1);
        Et15 = findViewById(R.id.cardEditText2);
        Et20 = findViewById(R.id.cardEditText3);
        Et25 = findViewById(R.id.cardEditText4);
        Et50 = findViewById(R.id.cardEditText5);
        Et100 = findViewById(R.id.cardEditText6);
        Et200 = findViewById(R.id.cardEditText7);


        //linking buttons from resource
        setbutton = findViewById(R.id.setButton);
        setbutton.setOnClickListener(this);
        operator = findViewById(R.id.operatorMargine);
        operator.setOnClickListener(this);
        defaultButton = findViewById(R.id.defaultButton);
        defaultButton.setOnClickListener(this);


        //setting hint for edittext with ethio tel card prices
        Et5.setHint(String.valueOf(df.format(MarginesUtils.getTeleFive()) + " : "
                + (getResources()
                .getString(R.string.cardNum5))));
        Et10.setHint(String.valueOf(df.format(MarginesUtils.getTeleTen()) + " : "
                + (getResources()
                .getString(R.string.cardNum10))));
        Et15.setHint(String.valueOf(df.format(MarginesUtils.getTeleFifteen()) + " : "
                + (getResources()
                .getString(R.string.cardNum15))));
        Et20.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwenty()) + " : "
                + (getResources()
                .getString(R.string.cardNum20))));
        Et25.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwentyfive()) + " : "
                + (getResources()
                .getString(R.string.cardNum25))));
        Et50.setHint(String.valueOf(df.format(MarginesUtils.getTeleFifty()) + " : "
                + (getResources()
                .getString(R.string.cardNum50))));
        Et100.setHint(String.valueOf(df.format(MarginesUtils.getTeleHundred()) + " : "
                + (getResources()
                .getString(R.string.cardNum100))));
        Et200.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwohundred()) + " : "
                + (getResources()
                .getString(R.string.cardNum200))));

    }

    /**
     * onClick method for handling click event of all the buttons in the activity
     * @param view: view component where the click is occurring in
     */
    @Override
    public void onClick(View view) {
        //ArrayList of edittexts to easily access and traverse each edittexts
        List<EditText> Ets = new ArrayList<EditText>(Arrays.asList(Et5, Et10, Et15, Et20, Et25, Et50,
                Et100, Et200));


        //Intent object to go back to the mainactivity once values are set
        Intent intent = new Intent(this, MainActivity.class);


        //switch statement assigning operations on each button click
        switch (view.getId()){
            case R.id.operatorMargine:
                if(operator.getText() == getResources().getString(R.string.operator1)){

                    operator.setText(getResources().getString(R.string.operator2));
                    Et5.setHint (String.valueOf(df.format(MarginesUtils.getSafariFive())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum5)));
                    Et10.setHint(String.valueOf(df.format(MarginesUtils.getSafariTen())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum10)));
                    Et15.setHint(String.valueOf(df.format(MarginesUtils.getSafariFifteen())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum15)));
                    Et20.setHint(String.valueOf(df.format(MarginesUtils.getSafariTwenty())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum20)));
                    Et25.setHint(String.valueOf(df.format(MarginesUtils.getSafariTwentyfive())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum25)));
                    Et50.setHint(String.valueOf(df.format(MarginesUtils.getSafariFifty())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum50)));
                    Et100.setHint(String.valueOf(df.format(MarginesUtils.getSafariHundred())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum100)));
                    Et200.setHint(String.valueOf(df.format(MarginesUtils.getSafariTwohundred())) + " : "
                            + (getResources()
                            .getString(R.string.cardNum200)));

                }
                else if (operator.getText() == getResources().getString(R.string.operator2)){

                    operator.setText(getResources().getString(R.string.operator1));
                    Et5.setHint(String.valueOf(df.format(MarginesUtils.getTeleFive()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum5))));
                    Et10.setHint(String.valueOf(df.format(MarginesUtils.getTeleTen()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum10))));
                    Et15.setHint(String.valueOf(df.format(MarginesUtils.getTeleFifteen()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum15))));
                    Et20.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwenty()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum20))));
                    Et25.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwentyfive()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum25))));
                    Et50.setHint(String.valueOf(df.format(MarginesUtils.getTeleFifty()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum50))));
                    Et100.setHint(String.valueOf(df.format(MarginesUtils.getTeleHundred()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum100))));
                    Et200.setHint(String.valueOf(df.format(MarginesUtils.getTeleTwohundred()) + " : "
                            + (getResources()
                            .getString(R.string.cardNum200))));


                }
                Toast.makeText(this, operator.getText() + " " + getResources()
                        .getString(R.string.setoperator), Toast.LENGTH_SHORT).show();
                break;

            case R.id.defaultButton:
                if(operator.getText() == getResources().getString(R.string.operator1)){
                    MarginesUtils.loadDefault("ethio tel");
                    Et5.setHint(String.valueOf(MarginesUtils.getTeleFive()) + " " + (getResources()
                            .getString(R.string.cardNum5)));
                    Et10.setHint(String.valueOf(MarginesUtils.getTeleTen()) + " " + (getResources()
                            .getString(R.string.cardNum10)));
                    Et15.setHint(String.valueOf(MarginesUtils.getTeleFifteen()) + " " + (getResources()
                            .getString(R.string.cardNum15)));
                    Et20.setHint(String.valueOf(MarginesUtils.getTeleTwenty()) + " " + (getResources()
                            .getString(R.string.cardNum20)));
                    Et25.setHint(String.valueOf(MarginesUtils.getTeleTwentyfive()) + " " + (getResources()
                            .getString(R.string.cardNum25)));
                    Et50.setHint(String.valueOf(MarginesUtils.getTeleFifty()) + " " + (getResources()
                            .getString(R.string.cardNum50)));
                    Et100.setHint(String.valueOf(MarginesUtils.getTeleHundred()) + " " + (getResources()
                            .getString(R.string.cardNum100)));
                    Et200.setHint(String.valueOf(MarginesUtils.getTeleTwohundred()) + " " + (getResources()
                            .getString(R.string.cardNum200)));

                }
                else{
                   MarginesUtils.loadDefault("safari");

                    Et5.setHint(String.valueOf(MarginesUtils.getSafariFive()));
                    Et10.setHint(String.valueOf(MarginesUtils.getSafariTen()));
                    Et15.setHint(String.valueOf(MarginesUtils.getSafariFifteen()));
                    Et20.setHint(String.valueOf(MarginesUtils.getSafariTwenty()));
                    Et25.setHint(String.valueOf(MarginesUtils.getSafariTwentyfive()));
                    Et50.setHint(String.valueOf(MarginesUtils.getSafariFifty()));
                    Et100.setHint(String.valueOf(MarginesUtils.getSafariHundred()));
                    Et200.setHint(String.valueOf(MarginesUtils.getSafariTwohundred()));
                }
                //resetting the each card util values used to get the sums on MinActivity
                MarginesUtils.resetValues();

                //finishing this activity with a toast and an intent which have a boolean to indicate
                //this intent is sent from the menu(used for not loading margins that are stored
                // in sharedPreference)
                Toast.makeText(this, getResources().getString(R.string.defaultset), Toast.LENGTH_SHORT).show();
                intent.putExtra("from menu", true);
                startActivity(intent);
                break;

            case R.id.setButton:
                    //iterating the edittext arraylist to find an empty one
                    for (int i = 0; i< Ets.size(); i++) {
                        if (TextUtils.isEmpty(Ets.get(i).getText().toString())){
                             emptyfound = true;
                            Log.i(TAG, i+"");

                        }
                        else{
                            //setting the passed input to the respective card
                                MarginesUtils.setSpecificCard(i, (Double.parseDouble(Ets.get(i).getText()
                                        .toString())));
                        }

                    }


                    //if empty edittext is found toast "some values are unchanged"
                    if(emptyfound)
                        Toast.makeText(this, getResources().getString(R.string.fillall),
                                Toast.LENGTH_SHORT).show();


                    //reset util values which are used for the sum in MainActivity and send the
                    //intent with the boolean indicating it is sent from the menu/margines class
                    MarginesUtils.resetValues();
                    intent.putExtra("from menu", true);
                    startActivity(intent);

                break;

        }
    }
}