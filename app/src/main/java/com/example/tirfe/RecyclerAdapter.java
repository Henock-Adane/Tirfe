package com.example.tirfe;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * a recyclerview adapter class handling all operation done with an item click
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    //the context where the recyclerview is placed
    private Context context;


    //list of the cards
    private List<cardData> list;


    //variable to store the numberpicker value
    int pickedVal = 0;


    //card sell price and operators true price
    static double five, trueFive = 0;
    static double ten, trueTen = 0;
    static double fifteen, trueFifteen = 0;
    static double twenty, trueTwenty = 0;
    static double twenty_five, trueTwenty_five = 0;
    static double fifty, trueFifty = 0;
    static double hundred, trueHundred = 0;
    static double two_hundred, trueTwo_hundred = 0;
    static double two_hundred_fifty, trueTwo_hundred_fifty = 0;
    static double five_hundred, trueFive_hundred = 0;
    static double one_thousand, trueOne_thousand = 0;


    /**
     * the recycleradapter constructor accepting context and a list of cardData
     * @param context: context of the activity with the recycler view
     * @param list: ArrayList of cardData objects
     */
    public RecyclerAdapter(Context context, List<cardData> list) {
        this.context = context;
        this.list = list;
    }


    //Overridden method for inflating the resource layout
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item,
                parent,false));
    }


    //Overridden method for handling item click and related operations
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
       //setting each item's text from the list of cardData objects
        holder.cardButton.setText(String.valueOf(list.get(position).getCardAmount()));


        //a String to store button text of the active(clicked) item
        String buttonText = String.valueOf(list.get(position).getCardAmount());

        //dialog object shown on item click letting user set card amount from a number picker
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.pickerdialog);
        dialog.setTitle(R.string.setamount);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        //Textviews on the dialog
        TextView okay_text;
        TextView cancel_text;
        okay_text = (TextView) dialog.findViewById(R.id.okay_text);
        cancel_text = (TextView) dialog.findViewById(R.id.cancel_text);


        //numberpicker for choosing card amount
        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(1000000000);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.clearFocus();


        //item click listener
        holder.cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //setting itemPressed variable of the util class true, as an item is already clicked
            MarginesUtils.itemPressed = true;


            //listener for okay textview of the dialog
            okay_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    String buttonNewText = pickedVal  + " " + buttonText;
                    holder.cardButton.setText(buttonNewText);


                    //making the picked value 0 to avoid repetition on other picker clicks
                    pickedVal = 0;
                }
            });


            //listener for cancel textview of the dialog
            cancel_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            dialog.show();
            }

        });


        //numberpicker listener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //saving picked value
                pickedVal = numberPicker.getValue();


                //switching items on the recyclerview to calculate sum of the respective card
                switch (holder.getAdapterPosition()){

                    case 0 :
                        if(MarginesUtils.operator.equals("safari") &&
                        MarginesUtils.getSafariFive() == 0){
                            Toast.makeText(context, R.string.alert5 , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            five = pickedVal;
                            five = MarginesUtils.calculateSum(5, five);
                            trueFive = MarginesUtils.trueVal;
                        }
                        break;

                    case 1 :
                        ten = pickedVal;
                        ten = MarginesUtils.calculateSum(10, ten);
                        trueTen = MarginesUtils.trueVal;
                        break;

                    case 2 :
                        fifteen = pickedVal;
                        fifteen = MarginesUtils.calculateSum(15, fifteen);
                        trueFifteen = MarginesUtils.trueVal;
                        break;

                    case 3 :
                        if(MarginesUtils.operator.equals("ethio tel") &&
                                MarginesUtils.getTeleTwenty() == 0){
                            Toast.makeText(context, R.string.alert20 , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            twenty = pickedVal;
                            twenty = MarginesUtils.calculateSum(20, twenty);
                            trueTwenty = MarginesUtils.trueVal;
                        }
                        break;

                    case 4 :
                        twenty_five = pickedVal;
                        twenty_five = MarginesUtils.calculateSum(25, twenty_five);
                        trueTwenty_five = MarginesUtils.trueVal;

                        break;

                    case 5 :
                        fifty = pickedVal;
                        fifty = MarginesUtils.calculateSum(50, fifty);
                        trueFifty = MarginesUtils.trueVal;

                        break;
                    case 6 :
                        hundred = pickedVal;
                        hundred = MarginesUtils.calculateSum(100, hundred);
                        trueHundred = MarginesUtils.trueVal;

                        break;
                    case 7 :
                        if(MarginesUtils.operator.equals("ethio tel") &&
                                MarginesUtils.getTeleTwohundred() == 0){
                            Toast.makeText(context, R.string.alert200 ,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            two_hundred = pickedVal;
                            two_hundred = MarginesUtils.calculateSum(200, two_hundred);
                            trueTwo_hundred = MarginesUtils.trueVal;
                        }

                        break;
                    case 8 :
                        if(MarginesUtils.operator.equals("ethio tel") &&
                                MarginesUtils.getTeleTwohundredfifty() == 0){
                            Toast.makeText(context, R.string.alert250 , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            two_hundred_fifty = pickedVal;
                            two_hundred_fifty = MarginesUtils.calculateSum(250, two_hundred_fifty);
                            trueTwo_hundred_fifty = MarginesUtils.trueVal;
                        }

                        break;
                    case 9 :
                        if(MarginesUtils.operator.equals("ethio tel") &&
                                MarginesUtils.getTeleFivehundred() == 0){
                            Toast.makeText(context, R.string.alert500 , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            five_hundred = pickedVal;
                            five_hundred = MarginesUtils.calculateSum(500, five_hundred);
                            trueFive_hundred = MarginesUtils.trueVal;
                        }

                        break;
                    case 10 :
                        if(MarginesUtils.operator.equals("ethio tel") &&
                                MarginesUtils.getTeleOnethousand() == 0){
                            Toast.makeText(context, R.string.alert1000 , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            one_thousand = pickedVal;
                            one_thousand = MarginesUtils.calculateSum(1000, one_thousand);
                            trueOne_thousand = MarginesUtils.trueVal;
                        }

                        break;

                }


            }
        });


    }


    //Overridden method for accessing the list size
    @Override
    public int getItemCount() {
        return list.size();
    }
}
