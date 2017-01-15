package tk.faizalatwork.developer.crosszero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void HumanIntent(View v){
        Intent human = new Intent(this,Human.class);
        finish();
        startActivity(human);
    }

    public void BotIntent(View v){
        Intent bot = new Intent(this,bot.class);
        finish();
        startActivity(bot);
    }
}
