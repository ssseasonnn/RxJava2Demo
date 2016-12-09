package zlc.season.rxjava2demo.demo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxjava2demo.Api;
import zlc.season.rxjava2demo.RetrofitProvider;
import zlc.season.rxjava2demo.entity.LoginRequest;
import zlc.season.rxjava2demo.entity.LoginResponse;
import zlc.season.rxjava2demo.entity.RegisterRequest;
import zlc.season.rxjava2demo.entity.RegisterResponse;

import static zlc.season.rxjava2demo.MainActivity.TAG;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/9
 * Time: 16:55
 * FIXME
 */
public class ChapterThree {
    public static void demo1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    public static void demo2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    public static void demo3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    public static void practice1(final Context context) {
        final Api api = RetrofitProvider.get().create(Api.class);
        api.register(new RegisterRequest())            //发起注册请求
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        //先根据注册的响应结果去做一些操作
                    }
                })
                .observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        return api.login(new LoginRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
