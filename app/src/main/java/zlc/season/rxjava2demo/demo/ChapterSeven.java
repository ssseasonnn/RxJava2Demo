package zlc.season.rxjava2demo.demo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/15
 * Time: 09:42
 * FIXME
 */
public class ChapterSeven {
    public static void demo1() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("ChapterSeven", "integer:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w("Error", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ChapterSeven", "complete");
                    }
                });
    }
}
