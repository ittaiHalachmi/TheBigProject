package controller;

import model.Card;
import model.Deck;

public interface Deckcontrolle {
    public Deck getDeck();
    public Deck changeCard(Card card, int i);
}
