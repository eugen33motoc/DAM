package com.example.mathquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    Button btn_start,btn0,btn1, btn2,btn3;
    TextView score,question,timer,buttonmessage;
    ProgressBar progTimer;
    Game g=new Game();
    int secondReaming=30;

    CountDownTimer timersecond=new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondReaming--;
         timer.setText(Integer.toString(secondReaming)+"sec");
         progTimer.setProgress(30-secondReaming);
        }

        @Override
        public void onFinish() {
            btn0.setEnabled(false);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            buttonmessage.setText("Time is up!"+g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));

            final Handler handler =new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    secondReaming=30;
                    buttonmessage.setText(g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));
                    score.setText("0");
                    g=new Game();
                    btn_start.setVisibility(View.VISIBLE);

                }
            },4000);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(navView, navController);

       btn_start = findViewById(R.id.btn_start);
        btn0 = findViewById(R.id.btn_answear0);
        btn1 = findViewById(R.id.btn_answear1);
        btn2 = findViewById(R.id.btn_answear2);
        btn3 = findViewById(R.id.btn_answear3);
        score = findViewById(R.id.tv_score);
        timer = findViewById(R.id.tv_timer);
        question = findViewById(R.id.tv_questions);
        buttonmessage = findViewById(R.id.textView4);
        progTimer=findViewById(R.id.prog_timer);

        timer.setText("0sec");
       question.setText("");
       buttonmessage.setText("Press Go");
        score.setText("0pts");
        progTimer.setProgress(0);
       View.OnClickListener startButtonClickListener = (v)->{
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                nextTurn();
                timersecond.start();

        };
        View.OnClickListener answerButtonClickListener= new View.OnClickListener(){
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
               int answerSelected= Integer.parseInt(buttonClicked.getText().toString());
                //Toast.makeText(MainActivity.this,"AnswerSelected="+answerSelected,Toast.LENGTH_SHORT).show();
            g.checkAnswer(answerSelected);
            score.setText(Integer.toString(g.getScore()));
            nextTurn();
            }
        };
        btn_start.setOnClickListener(startButtonClickListener);

        btn0.setOnClickListener(answerButtonClickListener);
        btn1.setOnClickListener(answerButtonClickListener);
        btn2.setOnClickListener(answerButtonClickListener);
        btn3.setOnClickListener(answerButtonClickListener);
     }
    private void nextTurn(){
        g.makeNewQuestion();
        int [] answer=g.getCurrentQuestion().getAnswerArray();
        btn0.setText(Integer.toString(answer[0]));
        btn1.setText(Integer.toString(answer[1]));
        btn2.setText(Integer.toString(answer[2]));
        btn3.setText(Integer.toString(answer[3]));

        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);

        question.setText((g.getCurrentQuestion().getQuestionPhrase()));
        buttonmessage.setText(g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));

    }
}