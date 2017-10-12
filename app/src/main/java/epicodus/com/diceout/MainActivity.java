package epicodus.com.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView rollResult;

    int score;

    Random rando;

    int dieUno;
    int dieDos;
    int dieTres;

    TextView scoreText;

    ArrayList<Integer> dice;
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        score = 0;

        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        Toast.makeText(getApplicationContext(), "Welcome to Diceout!", Toast.LENGTH_SHORT).show();

        rando = new Random();

        dice = new ArrayList<>();

        //Butterknife this shit!
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);


    }

    public void rollDice(View v) {
        rollResult.setText("Clicked!");

        dieUno = rando.nextInt(6)+1;
        dieDos = rando.nextInt(6)+1;
        dieTres = rando.nextInt(6)+1;

        dice.clear();
        dice.add(dieUno);
        dice.add(dieDos);
        dice.add(dieTres);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String msg;

        if (dieUno == dieDos && dieUno == dieTres) {
            int scoreDelta = dieUno * 100;
            msg = "You rolled a triple " + dieUno + "! You score " + scoreDelta + " points!";
        } else if (dieUno == dieDos || dieUno == dieTres || dieDos == dieTres) {
            msg = "You rolled doubles for 50 points";
            score += 50;
        } else {
            msg = "You didn't score this roll, try again.";
        }

        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
//        int num = rando.nextInt(6)+1;
//        String randomValue = "Number generated: " + num;
//        Toast.makeText(getApplicationContext(),randomValue,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
