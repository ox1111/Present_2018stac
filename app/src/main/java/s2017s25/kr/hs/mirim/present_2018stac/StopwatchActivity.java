package s2017s25.kr.hs.mirim.stac2018_present;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    TextView nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

     
        nextBtn = (TextView) findViewById(R.id.stopwatch_next_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StopwatchActivity.this, ScriptInputActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
