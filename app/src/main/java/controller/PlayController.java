package controller;

import android.content.Context;

import model.BordTile;
import model.Card;


public class PlayController implements Pcontroller{


   public String returnNewPosition(String position){
       switch (position.indexOf(1)){
           case '5':
               //enemyTakesDamege()
           default:
               position = (Integer.parseInt(position) + 10) + "";

       }
       return position;
   }

    @Override
    public void moveCardsUp(BordTile[] bordTiles, Card[] cards, Context context) {

    }
}
