package com.example.przypominajkaopiciuwody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button generujButton;
Button szklankaButton;
ProgressBar progressBar;
ProgressBar vertical_progressbar;
TextView wodaLeftTextView;
SharedPreferences sharedPreferences;
int btnClickCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplication().getSharedPreferences("com.example.przypominajkaopiciuwody", Context.MODE_PRIVATE);
        wodaLeftTextView = findViewById(R.id.wodaLeftTextView);
        generujButton = findViewById(R.id.generujButton);
        szklankaButton = findViewById(R.id.szklankaButton);
        progressBar = findViewById(R.id.progressBar);
        vertical_progressbar = findViewById(R.id.vertical_progressbar);


        wodaLeftTextView.setVisibility(View.INVISIBLE);
        vertical_progressbar.setVisibility(View.INVISIBLE);
        szklankaButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        vertical_progressbar.setProgress(2500);

//Przycisk do uzupełniania progressbaru
        szklankaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getProgress()>=2250)
                {
                    Toast.makeText(getApplicationContext(),"Wypiłeś dzienne minimum wody!",Toast.LENGTH_LONG).show();
                }
                progressBar.setProgress(progressBar.getProgress() + 250);
                vertical_progressbar.setProgress(vertical_progressbar.getProgress()-250);
                btnClickCount+=1;
                wodaLeft(szklankaButton);
            }

        });

    }
//Przycisk do wygenerowania wody/pasków/etc
    public void wygenerujWode(View view){
        generujButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        szklankaButton.setVisibility(View.VISIBLE);
        vertical_progressbar.setVisibility(View.VISIBLE);
        wodaLeftTextView.setVisibility(View.VISIBLE);

    }
 public void wodaLeft(View view){
        int liczbaSzklanek=10-btnClickCount;
        if (liczbaSzklanek>=0) {
            wodaLeftTextView.setText("Pozostało: " + liczbaSzklanek + " szklanek do wypicia!");
        }else wodaLeftTextView.setText("Pozostało: 0 szklanek do wypicia!");
 }



    //MENU


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

//Obsluga menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.newDay){
            Intent intent = new Intent(getApplicationContext(),newDay.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}
