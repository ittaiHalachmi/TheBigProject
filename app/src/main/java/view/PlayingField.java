package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class PlayingField extends AppCompatActivity implements view {
    BordTile[] bordTiles = new BordTile[25];
    Pcontroller playController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);
        playController = new PlayController();
        setUpField();


        LinearLayout linearLayout = findViewById(R.id.Hand);

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.blob_king));
        Card card = new Card(5,4,"00", imageView);
        for (int i = 0; i < bordTiles.length; i++) {
            if (bordTiles[i].getPosition().equals(card.getPosition())) {
                ((ImageView) bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(card.getCardImage().getDrawable());
                ((TextView) bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + card.getPower());

            }
        }
        Card card2 = new Card(5,4,"01", imageView);
        for (int i = 0; i < bordTiles.length; i++) {
            if (bordTiles[i].getPosition().equals(card2.getPosition())) {
                ((ImageView) bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(card2.getCardImage().getDrawable());
                ((TextView) bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("\n\n\n         " + card2.getPower());

            }
        }
        checkBordTils();
        checkBordTils();
    }

    private void checkBordTils() {
        Card[] cards = new Card[25];
        int i2 = 0;
        for (int i = 0; i < bordTiles.length; i++) {
            if (bordTiles[i].getCard() != null) {
                Card card = bordTiles[i].getCard();
                bordTiles[i].setCard(null);
                ((ImageView) bordTiles[i].getLook().findViewById(R.id.cardImage)).setImageDrawable(null);
                ((TextView) bordTiles[i].getLook().findViewById(R.id.cardPow)).setText("");

                bordTiles[i].getLook().setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
                card.setPosition(playController.returnNewPosition(card.getPosition()));
                cards[i2] = card;
                i2++;
            }
        }
        playController.moveCardsUp(bordTiles, cards, PlayingField.this);

    }

    private void setUpField() {
        int l = 0;
        int i2 = 0;
        TableLayout field = findViewById(R.id.tableLayout);
        int n = field.getMeasuredWidth();
        TableRow r1 = findViewById(R.id.row1);
        TableRow r2 = findViewById(R.id.row2);
        TableRow r3 = findViewById(R.id.row3);
        TableRow r4 = findViewById(R.id.row4);
        TableRow r5 = findViewById(R.id.row5);
        for (int i = 0; i < 5; i++){
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            view.setBackgroundColor(PlayingField.this.getResources().getColor(R.color.green));
            bordTiles[i] = new BordTile("" + l+i2,view);
            r1.addView(view, 206, 250);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 5; i < 10; i++){
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            bordTiles[i] = new BordTile("" + l+i2,view);
            r2.addView(view, 206, 250);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 10; i < 15; i++){
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            bordTiles[i] = new BordTile("" + l+i2,view);
            r3.addView(view, 206, 250);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 15; i < 20; i++){
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            bordTiles[i] = new BordTile("" + l+i2,view);
            r4.addView(view, 206, 250);
            i2++;
        }
        l++;
        i2 =0;
        for (int i = 20; i < 25; i++){
            View view = LayoutInflater.from(PlayingField.this).inflate(R.layout.custom_card, null);
            view.setBackgroundColor(PlayingField.this.getResources().getColor(R.color.red));
            bordTiles[i] = new BordTile("" + l+i2,view);
            r5.addView(view, 206, 250);
            i2++;
        }


    }



}
