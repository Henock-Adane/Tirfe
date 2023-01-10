package com.example.tirfe;


/**
 * card class for registering new cards
 */
public class cardData {
    String cardAmount;

    /**
     * card constructor
     * @param cardAmount: a string parameter representing card amount
     */
    public cardData(String cardAmount) {
        this.cardAmount = cardAmount;
    }

    /**
     * A method for accessing the card amount
     * @return cardAmount: a string parameter representing card amount
     */
    public String getCardAmount() {
        return cardAmount;
    }

    /**
     * A method for updating the cardAmount
     * @param cardAmount: a string parameter representing card amount
     */
    public void setCardAmount(String cardAmount) {
        this.cardAmount = cardAmount;
    }


}
