package controller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebigproject.R;

import java.util.Random;

import model.BordTile;
import model.Card;
import model.Deck;
import view.PlayingField;


public class PlayController implements Pcontroller{


   public String returnNewPosition(String position, Card card, BordTile[] bordTiles){
       int MyPow, EnemyPow;
       if (position.indexOf(0) != '5'){
           position = (Integer.parseInt(position) + 10) + "";
           BordTile bordTile = getTile(position, bordTiles);
           if(bordTile != null && bordTile.getCard() != null && card != null){
               MyPow = card.getPower();
               EnemyPow = bordTile.getCard().getPower();
               card.setPower(MyPow - EnemyPow);
               bordTile.getCard().setPower(EnemyPow - MyPow);
               if (card.getPower() <= 0 || bordTile.getCard().getEffect().equals("dry")){
                   position = "666";
                   ((TextView)bordTile.getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + bordTile.getCard().getPower());
               }
               if(bordTile.getCard().getPower() <= 0 || card.getEffect().equals("dry")){
                   bordTile.setCard(null);
                   ((TextView)bordTile.getLook().findViewById(R.id.cardPow)).setText("");
                   ((ImageView)bordTile.getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
               }
           }
       }

       return position;
   }

    public String returnNewPosition2(String position, Card card, BordTile[] bordTiles){
        int MyPow, EnemyPow;
        if (position.indexOf(0) != '0'){
            position = "" + (Integer.parseInt(position) - 10);
            if(position.length() == 1)
                position = 0 + "" + position;
            BordTile bordTile = getTile(position, bordTiles);
            if(bordTile != null && bordTile.getCard() != null){
                MyPow = card.getPower();
                EnemyPow = bordTile.getCard().getPower();
                card.setPower(MyPow - EnemyPow + 1);
                bordTile.getCard().setPower(EnemyPow - MyPow);
                if (card.getPower() <= 0 || bordTile.getCard().getEffect().equals("dry")){
                    position = "666";
                    ((TextView)bordTile.getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + bordTile.getCard().getPower());
                }
                if(bordTile.getCard().getPower() <= 0 || card.getEffect().equals("dry")){
                    bordTile.setCard(null);
                    ((TextView)bordTile.getLook().findViewById(R.id.cardPow)).setText("");
                    ((ImageView)bordTile.getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
                }
            }
        }

        return position;
    }

    private BordTile getTile(String position, BordTile[] bordTiles) {
       BordTile bordTile = null;
        for (int i = 0; i < 25; i++) {
            if(bordTiles[i].getPosition().equals(position))
                bordTile = bordTiles[i];
        }
       return bordTile;
    }

    public void rocketGOBoom(Card[] rockets, BordTile[] bordTiles){
       String position;
       BordTile exploding;

        for (int i = 0; (i < 25 && rockets[i] != null); i++) {
            position = rockets[i].getPosition();
            for (int h = 0; h < 5; h++){
                exploding = getTile(h + "" + position.charAt(1),bordTiles);
                if(!exploding.getPosition().equals(rockets[i].getPosition()) && exploding.getCard() != null){
                    exploding.getCard().setPower(exploding.getCard().getPower()-rockets[i].getPower());
                    ((TextView)exploding.getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + exploding.getCard().getPower());
                    if(exploding.getCard().getPower() <= 0){
                        exploding.setCard(null);
                        ((TextView)exploding.getLook().findViewById(R.id.cardPow)).setText("");
                        ((ImageView)exploding.getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
                    }
                }

            }
            rockets[i] = null;
        }
    }

    @Override
    public void moveCardsUp(BordTile[] bordTiles, Card[] Allycards, Context context) {
        Card[] rockets = new Card[25];
        for (int i = 0; Allycards[i] != null && i < 25; i++){
            if(Allycards[i].getEffect().equals("rocket")){
                for (int h = 0; h < 25; h++) {
                    if (rockets[h] == null) {
                        rockets[h] = Allycards[i];
                        Allycards[i] = null;
                    }
                }
            } else {
                Allycards[i].setPosition(returnNewPosition(Allycards[i].getPosition(), Allycards[i], bordTiles));
            }
        }

        for (int i = 0; Allycards[i] != null; i++) {
            for (int h = 0; h < 25; h++) {
                if (bordTiles[h].getPosition().equals(Allycards[i].getPosition())){
                    Allycards[i].setPower(Allycards[i].getPower()-1);
                    bordTiles[h].setCard(Allycards[i]);
                    ((ImageView)bordTiles[h].getLook().findViewById(R.id.cardImage)).setBackgroundColor(context.getResources().getColor(R.color.green));
                    bordTiles[h].setColor("green");
                    if (Allycards[i].getPower() > 0){

                        ((ImageView)bordTiles[h].getLook().findViewById(R.id.cardImage)).setImageDrawable(Allycards[i].getCardImage().getDrawable());
                        ((TextView)bordTiles[h].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + Allycards[i].getPower());
                    }else {
                        bordTiles[h].setCard(null);
                    }
                }
            }
        }
        rocketGOBoom(rockets, bordTiles);

    }



    @Override
    public void moveCardsDown(BordTile[] bordTiles, Card[] cards, PlayingField context) {
        for (int i = 0; cards[i] != null; i++) {
            cards[i].setPosition(returnNewPosition2(cards[i].getPosition(), cards[i], bordTiles));
        }

        for (int i = 0; cards[i] != null; i++) {
            for (int h = 0; h < 25; h++) {
                if (bordTiles[h].getPosition().equals(cards[i].getPosition())) {
                    cards[i].setPower(cards[i].getPower() - 1);
                    bordTiles[h].setCard(cards[i]);
                    ((ImageView) bordTiles[h].getLook().findViewById(R.id.cardImage)).setBackgroundColor(context.getResources().getColor(R.color.red));
                    bordTiles[h].setColor("red");
                    if (cards[i].getPower() > 0) {

                        ((ImageView) bordTiles[h].getLook().findViewById(R.id.cardImage)).setImageDrawable(cards[i].getCardImage().getDrawable());
                        ((TextView) bordTiles[h].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + cards[i].getPower());
                    }

                }
            }
        }
    }



    public Deck shafelDeck(Deck deck) {
        Card[] cards = new Card[8];
        Random random = new Random();
        int n;
        for (int i = 0; i < 8 && !isEmpty(deck.getCards()); i++) {
            n = random.nextInt(8);
            while (deck.getCards()[n] == null){
                n = random.nextInt(8);
            }
            cards[i] = deck.getCards()[n];

            deck.getCards()[n] = null;
        }

        for (int i = 0; i < 8; i++) {
            deck.getCards()[i] = cards[i];
        }

        return deck;
    }


    public boolean isEmpty(Card[] cards){
        for (int i = 0; i < cards.length; i++) {
            if(cards[i] != null)
                return false;
        }
        return true;
    }

}
