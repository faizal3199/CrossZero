package tk.faizalatwork.developer.crosszero;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class bot extends AppCompatActivity {
    final int playerValueHuman = 1;
    final int playerValueBot = 0;
    int[][] arr = new int[3][3];
    int turnIndex = 0;
    int botTurnIndex=0;
    int[] botArr = new int[9];

    boolean playerTurnHuman = false;
    boolean playerTurnBot = false;
    boolean gameContinues=true;
    boolean botPlaysFirst = true;
    boolean firstClick = true;

    Random rn;

    LinearLayout lay1, lay2, lay3, PlayAgain;
    TextView turnView;
    TextView aa,ba,ca,ab,bb,cb,ac,bc,cc;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        rn = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = 10;
            }
        }

        lay1 = (LinearLayout) findViewById(R.id.Botlay1);
        lay2 = (LinearLayout) findViewById(R.id.Botlay2);
        lay3 = (LinearLayout) findViewById(R.id.Botlay3);
        PlayAgain = (LinearLayout) findViewById(R.id.BotPlayAgain);
        button = (Button) findViewById(R.id.Botbutton_renew);
        turnView = (TextView) findViewById(R.id.BotturnView);

        aa=(TextView)findViewById(R.id.Botid_1_1);
        ab=(TextView)findViewById(R.id.Botid_1_2);
        ac=(TextView)findViewById(R.id.Botid_1_3);
        ba=(TextView)findViewById(R.id.Botid_2_1);
        bb=(TextView)findViewById(R.id.Botid_2_2);
        bc=(TextView)findViewById(R.id.Botid_2_3);
        ca=(TextView)findViewById(R.id.Botid_3_1);
        cb=(TextView)findViewById(R.id.Botid_3_2);
        cc=(TextView)findViewById(R.id.Botid_3_3);

        turnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstClickMethod();
                firstClick=false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstClickMethod();
                if(!firstClick){
                    renew();
                }
                firstClick=false;
            }
        });

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(0,0);
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(0,1);
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(0,2);
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(1,0);
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(1,1);
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(1,2);
            }
        });

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(2,0);
            }
        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(2,1);
            }
        });
        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView)v;
                view.setText(R.string.crossCheck);
                HumanPlays(2,2);
            }
        });
    }

    //bot-0  human-1
    protected void firstClickMethod(){
        if(firstClick){
            lay1.setVisibility(View.VISIBLE);
            lay2.setVisibility(View.VISIBLE);
            lay3.setVisibility(View.VISIBLE);
            PlayAgain.setVisibility(View.GONE);

            if(rn.nextInt(2)==0){
                playerTurnBot=true;
                turnView.setText(R.string.botPlays);
                BotPlays();
            }else{
                playerTurnHuman=true;
                turnView.setText(R.string.humanPlays);
                botPlaysFirst=false;
            }
        }
    }

    protected void HumanPlays(int x,int y){
        arr[x][y]=playerValueHuman;
        checkWin(playerValueHuman*3);
        if(gameContinues) {
            turnView.setText(R.string.botPlays);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (turnIndex != 9) {
                        BotPlays();
                    }
                }
            }, 800);

        }
    }

    protected void BotPlays(){
        if((arr[0][0]+arr[1][1]+arr[2][2])==10){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
        }
        else if((arr[0][2]+arr[1][1]+arr[2][0])==10){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
        }
        else if((arr[1][0]+arr[1][1]+arr[1][2])==10){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if((arr[0][1]+arr[1][1]+arr[2][1])==10){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[0][0]+arr[0][1]+arr[0][2])==10){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[2][0]+arr[2][1]+arr[2][2])==10){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
        }
        else if((arr[0][0]+arr[1][0]+arr[2][0])==10){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
        }
        else if((arr[0][2]+arr[1][2]+arr[2][2])==10){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if((arr[0][0]+arr[1][1]+arr[2][2])==12){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
        }
        else if((arr[0][2]+arr[1][1]+arr[2][0])==12){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
        }
        else if((arr[1][0]+arr[1][1]+arr[1][2])==12){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if((arr[0][1]+arr[1][1]+arr[2][1])==12){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[0][0]+arr[0][1]+arr[0][2])==12){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[2][0]+arr[2][1]+arr[2][2])==12){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
        }
        else if((arr[0][0]+arr[1][0]+arr[2][0])==12){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
        }
        else if((arr[0][2]+arr[1][2]+arr[2][2])==12){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if((arr[0][0]+arr[1][1]+arr[2][2])==21){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
        }
        else if((arr[0][2]+arr[1][1]+arr[2][0])==21){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
        }
        else if((arr[1][0]+arr[1][1]+arr[1][2])==21){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if((arr[0][1]+arr[1][1]+arr[2][1])==21){
            if(arr[1][1]==10){
                arr[1][1]=playerValueBot;
                setZeroBot(4);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[0][0]+arr[0][1]+arr[0][2])==21){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[0][1]==10){
                arr[0][1]=playerValueBot;
                setZeroBot(3);
            }
        }
        else if((arr[2][0]+arr[2][1]+arr[2][2])==21){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[2][1]==10){
                arr[2][1]=playerValueBot;
                setZeroBot(5);
            }
        }
        else if((arr[0][0]+arr[1][0]+arr[2][0])==21){
            if(arr[0][0]==10){
                arr[0][0]=playerValueBot;
                setZeroBot(0);
            }
            else if(arr[2][0]==10){
                arr[2][0]=playerValueBot;
                setZeroBot(2);
            }
            else if(arr[1][0]==10){
                arr[1][0]=playerValueBot;
                setZeroBot(1);
            }
        }
        else if((arr[0][2]+arr[1][2]+arr[2][2])==21){
            if(arr[2][2]==10){
                arr[2][2]=playerValueBot;
                setZeroBot(8);
            }
            else if(arr[0][2]==10){
                arr[0][2]=playerValueBot;
                setZeroBot(6);
            }
            else if(arr[1][2]==10){
                arr[1][2]=playerValueBot;
                setZeroBot(7);
            }
        }
        else if(botPlaysFirst){
            arr[1][1]=playerValueBot;
            setZeroBot(4);
            botPlaysFirst=false;
        }
        else if(turnIndex==8){
            Log.v("appVerboseFaizal","It was accessed");
            label:
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(arr[i][j]==10){
                        arr[i][j]=playerValueBot;
                        setZeroBot(i+j*3);
                        break label;
                    }
                }
            }
        }

        checkWin(playerValueBot*3);
        if(gameContinues){
            turnView.setText(R.string.humanPlays);
        }
    }


    protected void setZeroBot(int i){
        if(i==0){
            aa.setText(R.string.zeroCheck);
        }
        else if(i==1){
            ba.setText(R.string.zeroCheck);
        }
        else if(i==2){
            ca.setText(R.string.zeroCheck);
        }
        else if(i==3){
            ab.setText(R.string.zeroCheck);
        }
        else if(i==4){
            bb.setText(R.string.zeroCheck);
        }
        else if(i==5){
            cb.setText(R.string.zeroCheck);
        }
        else if(i==6){
            ac.setText(R.string.zeroCheck);
        }
        else if(i==7){
            bc.setText(R.string.zeroCheck);
        }
        else if(i==8){
            cc.setText(R.string.zeroCheck);
        }
    }

    protected void generateList(){
        ArrayList<Integer> list = new ArrayList<>(9);
        for(int i =0;i<9;i++){
            list.add(i);
        }
        int indexTemp=0;
        while(list.size()>0){
            int index = rn.nextInt(list.size());
            botArr[indexTemp]=list.get(index);
            indexTemp++;
            list.remove(index);
        }
        for(int i=0;i<9;i++) {
            Log.v("List", "At "+i+": "+botArr[i]);
        }
    }

    protected void checkWin(int checkValue){
        turnIndex++;
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

    protected void reportWin(int winnerValue){
        if(winnerValue==playerValueHuman){
            turnView.setText(R.string.humanWins);
        }
        else if(winnerValue==playerValueBot){
            turnView.setText(R.string.botWins);
        }
        else{
            turnView.setText(R.string.tie);
        }
        turnView.setTextColor(getResources().getColor(R.color.win));
        PlayAgain.setVisibility(View.VISIBLE);
        button.setText(R.string.playAgain);
        gameContinues=false;
    }
    protected void renew(){
        Intent intent = new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}
