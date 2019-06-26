package com.example.riven_chris.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import operators.Subjects;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    private static final String TAG = RxActivity.class.getSimpleName();

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        initView();
    }

    public void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        Subjects.asyncSubject();
    }


    public void apiTest() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void setProducer(Producer p) {
                super.setProducer(p);
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

//        Observable.just("1", "2");
//        Observable.from(new String[]{"1", "2"});

//        observable.subscribe(observer);
        observable.subscribe(subscriber);//这个有点怪

        Action1 next1 = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };
        Action1 actionError = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };
        Action0 action0 = new Action0() {
            @Override
            public void call() {

            }
        };

        observable.subscribe(next1);
        observable.subscribe(next1, actionError);
        observable.subscribe(next1, actionError, action0);

        Action2 action2;
        Action3 action3;//...

        Schedulers.io();
        Schedulers.newThread();
    }

    public void lift() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
            }
        });
        observable.lift(new Observable.Operator<Integer, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        subscriber.onNext(Integer.valueOf(s));
                    }
                };
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "lift: " + integer);
            }
        });
    }

    //compose()
    // 是唯一一个能从流中获取原生Observable 的方法，因此，
    // 影响整个流的操作符（像subscribeOn()和observeOn()）需要使用compose()，
    // 相对的，如果你在flatMap()中使用subscribeOn()/observeOn()，
    // 它只影响你创建的flatMap()中的Observable,而不是整个流。
    public void compose() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
            }
        });

        observable.compose(new LiftAll()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer i) {

            }
        });
    }

    public class LiftAll implements Observable.Transformer<String, Integer> {
        @Override
        public Observable<Integer> call(Observable<String> integerObservable) {
            return integerObservable.lift(new Observable.Operator<Integer, String>() {
                @Override
                public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
                    return new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String integer) {
                            subscriber.onNext(Integer.valueOf(integer));
                        }
                    };
                }
            });
        }
    }

    public void observerOn(Observable<String> observable, Func1<String, Integer> func1, Func1<Integer, String> func2, Subscriber<String> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(func1)
                .observeOn(Schedulers.newThread())
                .map(func2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
