package operators;

import android.util.Log;

import rx.Subscriber;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by riven_chris on 2017/2/10.
 */

public class Subjects {

    public static final String TAG = "Subjects";

    public static void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(subscriber1());//1,2,3,4,5
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.subscribe(subscriber2());//4,5
        subject.onNext("4");
        subject.onNext("5");
    }

    public static void publishSubject2() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe();
    }

    public static void behaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create("1");
        subject.subscribe(subscriber1());//1,1,2,3,4,5,
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.subscribe(subscriber2());//3,3,4,5
        subject.onNext("4");
        subject.onNext("5");
    }

    public static void replaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(subscriber1());//1,2,3,4,5
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.subscribe(subscriber2());//1,2,3,4,5
        subject.onNext("4");
        subject.onNext("5");
    }

    public static void asyncSubject(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(subscriber1());//1,2,3,4,5
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.onCompleted();
        subject.subscribe(subscriber2());//1,2,3,4,5
        subject.onNext("4");
        subject.onNext("5");
//        subject.onCompleted();
    }

    public static Subscriber subscriber1() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Subscriber1 s: " + s);
            }
        };
    }

    public static Subscriber subscriber2() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Subscriber2 s: " + s);
            }
        };
    }

}
