package operators;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.Course;
import model.Student;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static rx.Observable.just;

/**
 * Created by riven_chris on 2017/2/9.
 */

public class Operators {

    public static final String TAG = "Operators";


    public static void main(String[] args) {
        concat();
    }

    public static void map() {
        just("a", "b", "c").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s.toUpperCase();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                        Log.d(TAG, s + "\r");
                    }
                });
    }

    public static void flatMap() {
        Student s1 = new Student(new Course[]{new Course("m1"), new Course("m2")});
        s1.setTag("1");
        Student s2 = new Student(new Course[]{new Course("s1"), new Course("s2")});
        s2.setTag("2");
        Student s3 = new Student(new Course[]{new Course("t1"), new Course("t2")});
        s3.setTag("3");

        just(s1, s2, s3).subscribeOn(Schedulers.io()).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                Log.d(TAG, "student" + student.getTag());
                return Observable.from(student.getCourses())
                        .subscribeOn(Schedulers.io());//不同线程执行数据发射是乱序的
            }
        }).subscribe(new Subscriber<Course>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, course.getName());
            }
        });
    }

    public static void concatMap() {
        Student s1 = new Student(new Course[]{new Course("m1"), new Course("m2")});
        s1.setTag("1");
        Student s2 = new Student(new Course[]{new Course("s1"), new Course("s2")});
        s2.setTag("2");
        Student s3 = new Student(new Course[]{new Course("t1"), new Course("t2")});
        s3.setTag("3");

        just(s1, s2, s3).subscribeOn(Schedulers.io()).concatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                Log.d(TAG, "student" + student.getTag());
                return Observable.from(student.getCourses())
                        .subscribeOn(Schedulers.io());
            }
        }).subscribe(new Subscriber<Course>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, course.getName());
            }
        });
    }

    public static void concat() {
        Observable<String> observable1 = Observable.just("1,2").delay(3, TimeUnit.SECONDS);
        Observable<String> observable2 = Observable.just("3,4,5").delay(1, TimeUnit.SECONDS);
        Observable.concat(observable1, observable2).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("value: " + s);
            }
        });
    }

    private static void merge() {
        Observable<String> observable1 = just("1", "2", "3");
        Observable<String> observable2 = just("a", "b", "c");

        Observable.merge(observable2, observable1).subscribe(new Action1<String>() {
            @Override
            public void call(String o) {
                System.out.println(o);
            }
        });
    }

    //只发射最先发射数据的数据源的所有数据，其他全部丢弃
    private static void amb() {
        Observable<String> observable1 = just("1", "2", "3")
                .delay(1, TimeUnit.SECONDS).subscribeOn(Schedulers.computation());
        Observable<String> observable2 = just("a", "b", "c");

        Observable.amb(observable1, observable2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void zip() {
        Observable<String> observable1 = just("1", "2", "3", "4", "5");
        Observable<String> observable2 = just("a", "b", "c");
        Observable.zip(observable1, observable2, new Func2<String, String, String>() {
            @Override
            public String call(String s1, String s2) {
                return s1 + " ," + s2;
            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        }).subscribe();
    }

    private static void scan() {
        just("a", "b", "c", "d", "e", "f").scan(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {

                return s + " ," + s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void filter() {
        just("a", "b", "c", "d", "e", "f").filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.equals("b") || s.equals("e");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void take() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).take(4).takeLast(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println();

        //emits items before 2 seconds runs out
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).take(2, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {

                return integer > 4;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void skip() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).skip(2).skipLast(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void elementAt() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).elementAtOrDefault(10, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void startWith() {
        Observable.just("a", "b", "c").startWith("1", "2").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable.just(1, 2, 3, 4, 5).startWith(Observable.just(6, 7)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

            }
        });
    }

    private static void buffer() {
        Observable.from(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
                .buffer(2, 3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        System.out.println("----------group-----------");
                        Observable.from(integers).subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("data: " + integer);
                            }
                        });
                    }
                });
    }

    private static void defer() {
        Observable<Long> observable = Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return Observable.just(System.currentTimeMillis());
            }
        });
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long o) {
                System.out.println("mills: " + o);
            }
        });
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long o) {
                System.out.println("mills: " + o);
            }
        });
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long o) {
                System.out.println("mills: " + o);
            }
        });
    }


    private static void lift() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("l1: " + subscriber);
                subscriber.onNext("1");
            }
        }).lift(new Observable.Operator<String, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("l2: " + "this: " + this + " ," + subscriber);
                        subscriber.onNext(s);
                    }
                };
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("l3: " + this);
            }
        });
    }

    private static void doOnSubscribe() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribeOn(Schedulers.computation())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })//如果在 doOnSubscribe() 之后有 subscribeOn() 的话，
                // 它将执行在离它最近的 subscribeOn() 所指定的线程
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }

    private static void combineLatest() {
        //// TODO: 2017/3/14
        Observable<String> observable1 = Observable.just("1");
        Observable<Integer> observable2 = Observable.just(1);
        Observable.combineLatest(observable1, observable2, new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                System.out.println(s + "," + integer);
                return s + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void compose() {
        //// TODO: 2017/3/14
        Observable.just(1, 2, 3).compose(new Observable.Transformer<Integer, Integer>() {
            @Override
            public Observable<Integer> call(Observable<Integer> integerObservable) {
                return null;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {

            }
        });
    }
}
