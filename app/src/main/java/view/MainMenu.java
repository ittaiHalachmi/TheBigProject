package view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        Intent i = getIntent();
        String userName = i.getStringExtra("userName");
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);

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