package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thebigproject.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button beckBuilder = findViewById(R.id.DeckBuilder);
        Button shop = findViewById(R.id.Shop);
        Button findGame = findViewById(R.id.FindGame);

        beckBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to deck building menu
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to shop menu
            }
        });

        findGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, PlayingField.class);
               // i.putExtra(Deck)
                startActivity(i);
            }
        });



    }
}