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

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Util class with static fields and methods to be accessed
 * with out initialising this class
 */
public class MarginesUtils {


    /**
     * ethio tel card price variables
     */
    private static double teleFive = 4.65;
    private static double teleTen = 9.30;
    private static double teleFifteen = 13.95;
    private static double teleTwenty = 0;
    private static double teleTwentyfive = 23.25;
    private static double teleFifty = 46.50;
    private static double teleHundred = 93;
    private static double teleTwohundred = 0;


    /**
     * Safaricom card price variables
     */
    private static double safariFive = 4.65;
    private static double safariTen = 9.30;
    private static double safariFifteen = 13.95;
    private static double safariTwenty = 20;
    private static double safariTwentyfive = 23.25;
    private static double safariFifty = 46.50;
    private static double safariHundred = 93;
    private static double safariTwohundred = 186;


    //a double representing sum of the selected card
    private static double finalAmount;


    //actual price sum for the selected card
    public static double trueVal;

    //total sum of all selected cards
    public static double sumOfAll;

    //a boolean to make sure an item from the recycler view in MainActivity is pressed
    static boolean itemPressed = false;

    //operator string assigned to ethio tel by default
    public static String operator = "ethio tel";

    //private constructor for the util class
    private MarginesUtils(){

    }


    /**
     * a method for handling switching between operators
     * @param operatorPassed: a string representing an operator
     */
    public static void switchOperator(String operatorPassed){
        if(operatorPassed.equals("safari")) {
            operator = operatorPassed;
        }
        else if(operatorPassed.equals("ethio tel")){
            operator = operatorPassed;
        }
    }


    /**
     * a method for handling sum of selected card
     * @param card: a mobile card kind
     * @param cardAmount: the amount of the card
     * @return finalAmount: sum of the selected card
     */
    public static double calculateSum(int card, double cardAmount){
        switch (card){

            case 5:
                    if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariFive;
                }
                else{
                    finalAmount = cardAmount * teleFive;
                }
                trueVal = cardAmount * 5;
                break;
            case 10:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariTen;
                }
                else{
                    finalAmount = cardAmount * teleTen;
                }
                trueVal = cardAmount * 10;
                break;
            case 15:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariFifteen;
                }
                else{
                    finalAmount = cardAmount * teleFifteen;
                }
                trueVal = cardAmount * 15;
                break;
            case 20:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariTwenty;
                }
                else{
                    finalAmount = cardAmount * teleTwenty;
                }
                trueVal = cardAmount * 20;
                break;
            case 25:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariTwentyfive;
                }
                else{
                    finalAmount = cardAmount * teleTwentyfive;
                }
                trueVal = cardAmount * 25;
                break;
            case 50:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariFifty;
                }
                else{
                    finalAmount = cardAmount * teleFifty;
                }
                trueVal = cardAmount * 50;
                break;
            case 100:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariHundred;
                }
                else{
                    finalAmount = cardAmount * teleHundred;
                }
                trueVal = cardAmount * 100;
                break;
            case 200:
                if(operator.equals("safari"))
                {
                    finalAmount = cardAmount * safariTwohundred;
                }
                else{
                    finalAmount = cardAmount * teleTwohundred;
                }
                trueVal = cardAmount * 200;
                break;


        }
        return finalAmount;
    }


    /**
     * A method for summing cards
     * @param five: card 5 total
     * @param ten: card 10 total
     * @param fifteen: card 15 total
     * @param twenty: card 20 total
     * @param twentyfive: card 25 total
     * @param fifty: card 50 total
     * @param hundred: card 100 total
     * @param two_hundred: card 200 total
     * @return sumOfAll: sum of all the cards
     */
    public static double sumMyCard( double five, double ten, double fifteen, double twenty,
                                    double twentyfive, double fifty, double hundred,
                                    double two_hundred){
        sumOfAll = five + ten + fifteen + twenty + twentyfive + fifty + hundred + two_hundred;
        return sumOfAll;
    }


    /**
     * A method for loading defualt card prices
     * @param operatorPassed: a string of the operator name
     */
    public static void loadDefault(String operatorPassed){
        if (operatorPassed.equals("ethio tel")){
            teleFive = 4.65;
            teleTen = 9.30;
            teleFifteen = 13.95;
            teleTwenty = 0;
            teleTwentyfive = 23.25;
            teleFifty = 46.50;
            teleHundred = 93;
            teleTwohundred = 0;
        }
        else if (operatorPassed.equals("safari")){
            safariFive = 4.65;
            safariTen = 9.30;
            safariFifteen = 13.95;
            safariTwenty = 20;
            safariTwentyfive = 23.25;
            safariFifty = 46.50;
            safariHundred = 93;
            safariTwohundred = 186;
        }
    }


    /**
     * A method for resetting sum values of each card and a boolean to indicate
     * item from recyclerview in MainActivity are not pressed
     */
    public static void resetValues(){
        MarginesUtils.itemPressed = false;
        five = trueFive = 0;
        ten = trueTen = 0;
        fifteen = trueFifteen  = 0;

        twenty_five = trueTwenty_five = 0;
        fifty = trueFifty = 0;
        hundred = trueHundred = 0;
        two_hundred = trueTwo_hundred = 0;
    }


    /**
     * A method for setting passed double to the selected card as the operators price
     * @param i: an index for the selected card
     * @param newVal: new double value of the card price
     */
    public static void setSpecificCard(int i, double newVal){
        switch (i){
            case 0 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleFive(newVal);
                else
                    MarginesUtils.setSafariFive(newVal);
                break;
            case 1 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleTen(newVal);
                else
                    MarginesUtils.setSafariTen(newVal);
                break;
            case 2 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleFifteen(newVal);
                else
                    MarginesUtils.setSafariFifteen(newVal);
                break;

            case 3 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleTwenty(newVal);
                else
                    MarginesUtils.setSafariTwenty(newVal);
                break;

            case 4 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleTwentyfive(newVal);
                else
                    MarginesUtils.setSafariTwentyfive(newVal);
                break;
            case 5 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleFifty(newVal);
                else
                    MarginesUtils.setSafariFifty(newVal);
                break;
            case 6 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleHundred(newVal);
                else
                    MarginesUtils.setSafariHundred(newVal);
                break;
            case 7 :
                if(operator.equals("ethio tel"))
                    MarginesUtils.setTeleTwohundred(newVal);
                else
                    MarginesUtils.setSafariTwohundred(newVal);
                break;
        }
    }


    /**
     * safari getters ans setters
     *
     */
    public static double getSafariFive() {
        return safariFive;
    }

    public static void setSafariFive(double safariFive) {
        MarginesUtils.safariFive = safariFive;
    }

    public static double getSafariTen() {
        return safariTen;
    }

    public static void setSafariTen(double safariTen) {
        MarginesUtils.safariTen = safariTen;
    }

    public static double getSafariFifteen() {
        return safariFifteen;
    }

    public static void setSafariFifteen(double safariFifteen) {
        MarginesUtils.safariFifteen = safariFifteen;
    }

    public static double getSafariTwenty() {
        return safariTwenty;
    }

    public static void setSafariTwenty(double safariTwenty) {
        MarginesUtils.safariTwenty = safariTwenty;
    }

    public static double getSafariTwentyfive() {
        return safariTwentyfive;
    }

    public static void setSafariTwentyfive(double safariTwentyfive) {
        MarginesUtils.safariTwentyfive = safariTwentyfive;
    }

    public static double getSafariFifty() {
        return safariFifty;
    }

    public static void setSafariFifty(double safariFifty) {
        MarginesUtils.safariFifty = safariFifty;
    }

    public static double getSafariHundred() {
        return safariHundred;
    }

    public static void setSafariHundred(double safariHundred) {
        MarginesUtils.safariHundred = safariHundred;
    }

    public static double getSafariTwohundred() {
        return safariTwohundred;
    }

    public static void setSafariTwohundred(double safariTwohundred) {
        MarginesUtils.safariTwohundred = safariTwohundred;
    }


    /**
     * ethio tel getters and setters
     */
    public static double getTeleFive() {
        return teleFive;
    }

    public static void setTeleFive(double teleFive) {
        MarginesUtils.teleFive = teleFive;
    }

    public static double getTeleTen() {
        return teleTen;
    }

    public static void setTeleTen(double teleTen) {
        MarginesUtils.teleTen = teleTen;
    }

    public static double getTeleFifteen() {
        return teleFifteen;
    }

    public static void setTeleFifteen(double teleFifteen) {
        MarginesUtils.teleFifteen = teleFifteen;
    }

    public static double getTeleTwenty() {
        return teleTwenty;
    }

    public static void setTeleTwenty(double teleTwenty) {
        MarginesUtils.teleTwenty = teleTwenty;
    }

    public static double getTeleTwentyfive() {
        return teleTwentyfive;
    }

    public static void setTeleTwentyfive(double teleTwentyfive) {
        MarginesUtils.teleTwentyfive = teleTwentyfive;
    }

    public static double getTeleFifty() {
        return teleFifty;
    }

    public static void setTeleFifty(double teleFifty) {
        MarginesUtils.teleFifty = teleFifty;
    }

    public static double getTeleHundred() {
        return teleHundred;
    }

    public static void setTeleHundred(double teleHundred) {
        MarginesUtils.teleHundred = teleHundred;
    }

    public static double getTeleTwohundred() {
        return teleTwohundred;
    }

    public static void setTeleTwohundred(double teleTwohundred) {
        MarginesUtils.teleTwohundred = teleTwohundred;
    }


}
