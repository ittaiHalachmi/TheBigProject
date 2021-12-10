package controller;

import android.content.Context;

import model.BordTile;
import model.Card;

public interface Pcontroller {
     String returnNewPosition(String position);

    void moveCardsUp(BordTile[] bordTiles, Card[] cards, Context context);
}
