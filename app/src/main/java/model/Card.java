package model;

import android.widget.ImageView;

public class Card {

    private int power;
    private final int cost;
    private String position;
    private final ImageView cardImage;


    public Card(int power, int cost, String position, ImageView cardImage) {
        this.power = power;
        this.cost = cost;
        this.position = position;
        this.cardImage = cardImage;
    }

    public ImageView getCardImage() {
        return cardImage;
    }
    public int getCost() {
        return cost;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
