package zlc.season.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.InterruptedIOException;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import zlc.season.rxjava2demo.demo.ChapterNine;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof InterruptedIOException) {
                    Log.d(TAG, "Io interrupted");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChapterNine.demo4();
            }
        });

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterNine.request(96);
            }
        });
    }

}
