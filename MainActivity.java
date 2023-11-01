package com.example.colortiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    int brightNum = 0;
    int darkNum = 0;
    View[][] tiles = new View[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTiles();
        randTiles();
    }
    public void onClick(View view){
        changeColor(view);
        int n = Character.getNumericValue(view.getTag().toString().charAt(0));
        int m = Character.getNumericValue(view.getTag().toString().charAt(1));
        for(int i = 0; i < 4; i++){
            changeColor(findViewById(getId(i, m)));
            changeColor(findViewById(getId(n, i)));
        }
        countTiles();
        isVictory();
    }
    public void randTiles(){
        brightNum = 0;
        darkNum = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) {
                tiles[i][j] = findViewById(getId(i, j));
                long rand = Math.round(Math.random());
                System.out.println(rand);
                if(rand == 0) {
                    tiles[i][j].setBackgroundResource(R.drawable.bright);
                    brightNum++;
                }
                else {
                    tiles[i][j].setBackgroundResource(R.drawable.dark);
                    darkNum++;
                }
            }
    }
    public void changeColor(View view){
        if (Objects.equals(view.getBackground().getConstantState(), this.getResources().getDrawable(R.drawable.dark).getConstantState())) {
            view.setBackgroundResource(R.drawable.bright);
            brightNum++;
            darkNum--;
        }
        else{
            view.setBackgroundResource(R.drawable.dark);
            darkNum++;
            brightNum--;
        }
    }
    public int getId(int i, int j){
        return getResources().getIdentifier("t"+i+j, "id", getPackageName());
    }
    public void countTiles(){
        TextView bv = findViewById(R.id.bright);
        TextView dv = findViewById(R.id.dark);
        bv.setText(String.format("синих: %d", brightNum));
        dv.setText(String.format("красных: %d", darkNum));
    }
    public void isVictory(){
        if(darkNum==16 || brightNum == 16){
            Toast t = Toast.makeText(getApplicationContext(), "Победа", Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void restart(View view){
        randTiles();
        countTiles();
    }
}