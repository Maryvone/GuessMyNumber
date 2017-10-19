package com.example.mcueff.guessmynumber;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //init des variables;
    private EditText txtNumber = null;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar pgbScore;
    private TextView lblOutput;
    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init des variables avec les élements Views
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        btnCompare = (Button) findViewById(R.id.btnCompare);
        lblResult = (TextView) findViewById(R.id.lblResult);
        pgbScore = (ProgressBar) findViewById(R.id.pgbScore);
        lblOutput = (TextView) findViewById(R.id.lblOutput);




        //ajout evenement sur le bouton
        btnCompare.setOnClickListener(btnCompareListener);

        init();
    }

    private void init(){
        score = 0;
        searchedValue = (int) (Math.random()*100);
        Log.i("DEBUG", "Valeur recherchée :" + searchedValue);

        //init pour vider les infos
        txtNumber.setText("");
        pgbScore.setProgress(score);
        lblResult.setText("");
        lblOutput.setText("");

        //mettre le focus sur la zone de saisie
        txtNumber.requestFocus();
    }

    private void congratulations(){
        lblResult.setText(R.string.strCongratulations);

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strMessage,score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE,getString(R.string.StrYes), new AlertDialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE,getString(R.string.strNo), new AlertDialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        retryAlert.show();
    }

    //création de l'évènement
    private View.OnClickListener btnCompareListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Bouton cliqué");

            //récupération des données saisies
            String strNumber = txtNumber.getText().toString();

            //si zone de saisie vide, on recommence
            if (strNumber.equals("")) return;

            int enteredValue = Integer.parseInt(strNumber);
            lblOutput.append(strNumber + "\r\n");
            pgbScore.incrementProgressBy(1);
            score++;


            if(enteredValue == searchedValue){
                congratulations();
            }else if (enteredValue < searchedValue){
                lblResult.setText(R.string.strGreater);
            } else {
                lblResult.setText(R.string.strLower);
            }

            //init pour vider les infos
            txtNumber.setText("");

            //mettre le focus sur la zone de saisie
            txtNumber.requestFocus();
        }
    };
}
