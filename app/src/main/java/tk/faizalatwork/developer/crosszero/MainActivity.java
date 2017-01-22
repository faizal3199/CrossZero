package tk.faizalatwork.developer.crosszero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean firstTime=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void HumanIntent(View v){
        Intent human = new Intent(this,Human.class);
        if(!firstTime){
            finish();
        }
        Log.v("appVerboseFaizal","Value is:"+firstTime);
        firstTime=false;
        startActivity(human);
    }

    public void BotIntent(View v){
        Intent bot = new Intent(this,bot.class);
        if(!firstTime){
            finish();
        }
        Log.v("appVerboseFaizal","Value is:"+firstTime);
        firstTime=false;
        startActivity(bot);
    }
}
