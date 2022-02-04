package controller;

import android.content.Context;

import model.BordTile;
import model.Card;
import model.Deck;
import view.PlayingField;

public interface Pcontroller {


    void moveCardsUp(BordTile[] bordTiles, Card[] cards, Context context);
    Deck shafelDeck(Deck deck);
    String returnNewPosition(String position, Card card, BordTile[] bordTiles);
    void moveCardsDown(BordTile[] bordTiles, Card[] enemyCards, PlayingField playingField);
}
