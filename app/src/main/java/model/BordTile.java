package model;

import android.view.View;
import android.widget.ImageView;

public class BordTile {
    private String position;
    private View look;
    private Card card;
    public BordTile(String position, View look){
        this.position = position;
        this.look = look;
    }

    public void setCard(Card card) { this.card = card; }

    public View getLook() {
        return look;
    }

    public void setLook(View look) {
        this.look = look;
    }

    public String getPosition() {
        return position;
    }

    public Card getCard() { return this.card; }
}
