package tk.faizalatwork.developer.crosszero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class Human extends AppCompatActivity {
    final int playerValueZero = 0;
    final int playerValueCross = 1;
    boolean playerTurnZero=false;
    boolean playerTurnCross=false;
    int[][] arr = new int[3][3];
    Random rn;
    LinearLayout lay1,lay2,lay3,PlayAgain;
    TextView turnView;
    int turnIndex=0;
    boolean gameContinues=true;
    Button button;
    boolean firstclick=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human);
        rn = new Random();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                arr[i][j]=10;
            }
        }

        lay1 = (LinearLayout)findViewById(R.id.lay1);
        lay2 = (LinearLayout)findViewById(R.id.lay2);
        lay3 = (LinearLayout)findViewById(R.id.lay3);
        PlayAgain = (LinearLayout)findViewById(R.id.PlayAgain);
        button = (Button)findViewById(R.id.button_renew);
        turnView = (TextView)findViewById(R.id.turnView);

    }

    public void playerClick(View v){
        TextView view = (TextView) v;
        String getID =v.getResources().getResourceName(v.getId());

        String[] arrGetID = getID.split("_");
        int coordX = Integer.valueOf(arrGetID[1]);
        int coordY = Integer.valueOf(arrGetID[2]);

        if(gameContinues) {
            if (playerTurnCross) {
                changeData(playerValueCross, coordX - 1, coordY - 1);
                view.setText(R.string.crossCheck);
            } else {
                changeData(playerValueZero, coordX - 1, coordY - 1);
                view.setText(R.string.zeroCheck);
            }
            changePlayerTurn();
        }
    }

    public void FirstClick(View v){
        if(firstclick) {
            if (rn.nextInt(2) == 0) {
                playerTurnZero = true;
                playerTurnCross = false;
                turnView.setText(R.string.zeroPlays);
            } else {
                playerTurnCross = true;
                playerTurnZero = false;
                turnView.setText(R.string.crossPlays);
            }
            lay1.setVisibility(View.VISIBLE);
            lay2.setVisibility(View.VISIBLE);
            lay3.setVisibility(View.VISIBLE);
            PlayAgain.setVisibility(View.GONE);
            firstclick=!firstclick;
        }
        else{
            String temp = v.getResources().getResourceName(v.getId());
            String temp2[] = temp.split("_");
            try{
                if(temp2[1].equals("renew")){
                    renew(v);
                }
            }
            catch (Exception e){
                // do nothing
            }
        }
    }

    protected void changePlayerTurn(){
        playerTurnZero=!playerTurnZero;
        playerTurnCross=!playerTurnCross;
        if(playerTurnZero&&gameContinues) {
            turnView.setText(R.string.zeroPlays);
        }
        if(playerTurnCross&&gameContinues){
            turnView.setText(R.string.crossPlays);
        }
    }

    protected void changeData(int PlayerValue,int i,int j){
        arr[i][j]=PlayerValue;
        turnIndex++;
        checkWin(PlayerValue*3);
    }

    protected void checkWin(int checkValue){
        if((arr[0][0]+arr[0][1]+arr[0][2])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[1][0]+arr[1][1]+arr[1][2])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[2][0]+arr[2][1]+arr[2][2])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[0][0]+arr[1][0]+arr[2][0])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[0][1]+arr[1][1]+arr[2][1])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[0][2]+arr[1][2]+arr[2][2])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[0][0]+arr[1][1]+arr[2][2])==checkValue){
            reportWin(checkValue/3);
        }
        else if((arr[0][2]+arr[1][1]+arr[2][0])==checkValue){
            reportWin(checkValue/3);
        }
        else if(turnIndex==9){
            reportWin(10);
        }
    }

    protected void reportWin(int player){
        gameContinues=false;
        if(player==playerValueCross){
            turnView.setText(R.string.crossWins);
        }
        else if(player==playerValueZero){
            turnView.setText(R.string.zeroWins);
        }
        else{
            turnView.setText(R.string.tie);
        }
        turnView.setTextColor(getResources().getColor(R.color.win));
        PlayAgain.setVisibility(View.VISIBLE);
        button.setText(R.string.playAgain);

    }

    public void renew(View v){
        Intent intent = new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}
