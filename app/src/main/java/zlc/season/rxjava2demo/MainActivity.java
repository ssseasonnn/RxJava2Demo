package zlc.season.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zlc.season.rxjava2demo.demo.ChapterFive;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChapterFive.demo3();
            }
        });
    }

}
