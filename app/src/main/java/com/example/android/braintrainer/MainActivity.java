package com.example.android.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button button;
Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playagain;
    TextView sumTextview;
    ArrayList<Integer> answers=new ArrayList<Integer>();
TextView result;
    TextView point;
    int locationCorrect;
    int score;
    int numberOfQuestion;
    TextView timer;
    RelativeLayout game;


    public void playAgain(View view)
    {
        score=0;
        numberOfQuestion=0;
        timer.setText("30s");
        point.setText("0/0");
        result.setText("");
        playagain.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        generateQuestion();
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your score :"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }
    public void generateQuestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextview.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationCorrect=rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0; i<4; i++)
        {
            if(i == locationCorrect)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer == locationCorrect)
                {
                    incorrectAnswer=rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationCorrect)))
        {
          score++;
            result.setText("correct");
        }
        else
        {
            result.setText("incorrect");
        }
        numberOfQuestion++;
        point.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();

    }

    public void start(View view)
    {
        button.setVisibility(View.INVISIBLE);
        game.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.button6));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
point=(TextView)findViewById(R.id.scoretextView);
        timer=(TextView)findViewById(R.id.TimetextView);
        playagain=(Button)findViewById(R.id.button6);
        button=(Button)findViewById(R.id.button);
        game=(RelativeLayout)findViewById(R.id.gamelayout);
        sumTextview=(TextView)findViewById(R.id.textView);
        button0=(Button)findViewById(R.id.button2);
        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button4);
        button3=(Button)findViewById(R.id.button5);
        result=(TextView)findViewById(R.id.resulttextview);



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
