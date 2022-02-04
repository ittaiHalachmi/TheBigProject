package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thebigproject.R;

import controller.Pcontroller;
import controller.PlayController;
import model.BordTile;
import model.Card;
import model.Deck;

public class PlayingField extends AppCompatActivity implements view {
    BordTile[] bordTiles = new BordTile[25];
    BordTile[] myCards = new BordTile[8];
    Pcontroller playController;
    Card selectedCard;
    int selectedIndex;
    int points;
    Boolean tileSelectModeOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);
        playController = new PlayController();

        Card[] cards = new Card[8];
        points = 30;

        for (int i = 0; i < 8; i++) {
            ImageView imageView = new ImageView(this);

            if (i == 0 || i == 1) {
                imageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.blob_king));
                cards[i] = new Card(0, "" + i, imageView, "green", "");
            }
            if( i == 3 || i == 2) {
                imageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.rocket_slime));
                cards[i] = new Card(0, "" + i, imageView, "green", "rocket");
            }
            if (i == 4 || i == 5){
                imageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.solid_slime));
                cards[i] = new Card(0, "" + i, imageView, "green", "solid");
            }

            if(i == 6 || i == 7) {
                imageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.send_buket));
                cards[i] = new Card(0, "" + i, imageView, "green", "dry");
            }
        }


        Deck deck = new Deck(cards);
        setUpField(deck);

        Button button = findViewById(R.id.PlusToCost);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView p = findViewById(R.id.PointCounter);
                if(tileSelectModeOn && points > 0){
                   points--;
                   p.setText("   " + points);
                   selectedCard.setPower(selectedCard.getPower()+1);
                }
            }
        });
        Button button2 = findViewById(R.id.MinusToCost);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView p = findViewById(R.id.PointCounter);
                if(tileSelectModeOn){
                    points++;
                    p.setText("   " + points);
                    selectedCard.setPower(selectedCard.getPower()-1);
                }
            }
        });

        Button button3 = findViewById(R.id.turnEnd);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTurnEnd();
            }
        });

        Button button4 = findViewById(R.id.turnEnd2);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnemyTurnEnd();
            }
        });
    }

    private void MyTurnEnd() {
        Card[] AllyCards = new Card[25];
        int i2 = 0;
        for (int i = 0; i < 25; i++) {
            if (((TextView)bordTiles[i].getLook().findViewById(R.id.cardPow)).getText() != "" && bordTiles[i].getCard() != null && bordTiles[i].getCard().getPower() > 0){
                if(bordTiles[i].getCard().getColor().equals("green") && !bordTiles[i].getCard().getEffect().equals("solid") && !SolidInFront(bordTiles[i].getCard())){
                    AllyCards[i2] = bordTiles[i].getCard();
                    i2++;
                    bordTiles[i].setCard(null);
                    ((TextView) bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("");
                    ((ImageView)bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
                }
            }
        }
        playController.moveCardsUp(bordTiles, AllyCards, PlayingField.this);
        int n =1;
    }

    public boolean SolidInFront(Card card){
        String position = card.getPosition();
        for (int i = 0; i < 5; i++) {
            position = playController.returnNewPosition(position,null,bordTiles);
            for (int h = 0; h < bordTiles.length; h++) {
                if (bordTiles[h].getPosition().equals(position)){
                    if(bordTiles[h].getCard() == null){
                        return false;
                    }
                    else if(bordTiles[h].getPosition().equals(position) && bordTiles[h].getCard().getEffect().equals("solid")){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private void EnemyTurnEnd() {
        Card[] EnemyCards = new Card[25];
        int i3 = 0;
        for (int i = 0; i < 25; i++) {
            if (((TextView)bordTiles[i].getLook().findViewById(R.id.cardPow)).getText() != "" && bordTiles[i].getCard() != null && bordTiles[i].getCard().getPower() > 0){
                if(bordTiles[i].getCard().getColor().equals("red")){
                    EnemyCards[i3] = bordTiles[i].getCard();
                    i3++;
                    bordTiles[i].setCard(null);
                    ((TextView) bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("");
                    ((ImageView)bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
                }
            }
        }
        playController.moveCardsDown(bordTiles, EnemyCards, PlayingField.this);
        int n =1;
    }



    private void setUpField(Deck deck) {
        int l = 0;
        int i2 = 0;
        TableLayout field = findViewById(R.id.tableLayout);
        int n = field.getMeasuredWidth();
        TableRow r1 = findViewById(R.id.row1);
        TableRow r2 = findViewById(R.id.row2);
        TableRow r3 = findViewById(R.id.row3);
        TableRow r4 = findViewById(R.id.row4);
        TableRow r5 = findViewById(R.id.row5);

        deck = playController.shafelDeck(deck);
        setUpHand(deck);


        for (int i = 0; i < 5; i++){
            View view2 = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            view2.setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
            bordTiles[i] = new BordTile("" + l + "" +i2,view2);
            bordTiles[i].setColor("green");
            int finalI = i;
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (tileSelectModeOn && bordTiles[finalI].getColor().equals("green") && bordTiles[finalI].getCard() == null){
                                bordTiles[finalI].setCard(selectedCard);
                        ((ImageView)bordTiles[finalI].getLook().findViewById(R.id.cardImage)).setImageDrawable(selectedCard.getCardImage().getDrawable());
                        ((TextView)bordTiles[finalI].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + selectedCard.getPower());
                        bordTiles[finalI].getLook().setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
                        bordTiles[finalI].getCard().setPosition(bordTiles[finalI].getPosition());
                        tileSelectModeOn = false;
                        drawCard(selectedIndex);
                    }
                }
            });
            r1.addView(view2, 206, 250);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 5; i < 10; i++){
            insertBordTile(l,i,i2,r2);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 10; i < 15; i++){
            insertBordTile(l,i,i2,r3);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 15; i < 20; i++){
            insertBordTile(l,i,i2,r4);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 20; i < 25; i++){
            View view2 = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            view2.setBackgroundColor(PlayingField.this.getResources().getColor(R.color.red));
            bordTiles[i] = new BordTile("" + l+i2,view2);
            bordTiles[i].setColor("red");

            //
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.blob_king));
            Card card = new Card(6, bordTiles[i].getPosition(), imageView, "red", "");
            bordTiles[i].setCard(card);
            ((ImageView)bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(card.getCardImage().getDrawable());
            //

            int finalI = i;
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (tileSelectModeOn && bordTiles[finalI].getColor().equals("green") && bordTiles[finalI].getCard() == null){
                        bordTiles[finalI].setCard(selectedCard);
                        ((ImageView)bordTiles[finalI].getLook().findViewById(R.id.cardImage)).setImageDrawable(selectedCard.getCardImage().getDrawable());
                        ((TextView)bordTiles[finalI].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + selectedCard.getPower());
                        bordTiles[finalI].getLook().setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
                        bordTiles[finalI].getCard().setPosition(bordTiles[finalI].getPosition());
                        tileSelectModeOn = false;
                        drawCard(selectedIndex);
                    }
                }
            });
            r5.addView(view2, 206, 250);
            i2++;
        }


    }

    private void setUpHand(Deck deck) {
        LinearLayout hand = findViewById(R.id.Hand);
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            ((ImageView)view.findViewById(R.id.cardImage)).setImageDrawable(deck.getCards()[i].getCardImage().getDrawable());
            myCards[i] = new BordTile("" + i, view);
            myCards[i].setCard(deck.getCards()[i]);
            int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tileSelectModeOn)
                        tileSelectModeOn = false;
                    else
                        tileSelectModeOn = true;

                    if (tileSelectModeOn){
                        selectedCard = myCards[finalI].getCard();
                        selectedIndex = finalI;
                    }
                }
            });
            hand.addView(view,210,280);
        }
        for (int i = 4; i < 8; i++) {
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            ((ImageView)view.findViewById(R.id.cardImage)).setImageDrawable(deck.getCards()[i].getCardImage().getDrawable());
            myCards[i] = new BordTile("" + i, view);
            myCards[i].setCard(deck.getCards()[i]);
        }
    }
    public void drawCard(int i){
        Card temp = new Card(0,"", myCards[i].getCard().getCardImage(),myCards[i].getCard().getColor(),myCards[i].getCard().getEffect());
        myCards[i].setCard(myCards[4].getCard());
        for (int h = 4; h < 7; h++) {
            myCards[h].setCard(myCards[h+1].getCard());
        }
        myCards[7].setCard(temp);

        for (int g = 0; g < 8; g++) {
            ((ImageView)myCards[g].getLook().findViewById(R.id.cardImage)).setImageDrawable(myCards[g].getCard().getCardImage().getDrawable());
        }
    }


    public void insertBordTile(int l, int i, int i2, LinearLayout row){

        View view2 = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
        bordTiles[i] = new BordTile("" + l+i2,view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tileSelectModeOn && bordTiles[i].getColor().equals("green") && bordTiles[i].getCard() == null){
                    bordTiles[i].setCard(selectedCard);
                    ((ImageView)bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(selectedCard.getCardImage().getDrawable());
                    ((TextView)bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + selectedCard.getPower());
                    bordTiles[i].getLook().setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
                    bordTiles[i].getCard().setPosition(bordTiles[i].getPosition());
                    tileSelectModeOn = false;
                    drawCard(selectedIndex);
                }
            }
        });
        row.addView(view2, 206, 250);

    }
    public void send(){
        
    }



}
