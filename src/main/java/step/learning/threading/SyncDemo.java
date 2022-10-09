package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.concurrent.*;
import java.util.function.Function;

@DemoClass
public class SyncDemo {
    @EntryPoint
    public void demo(){

        System.out.println(ConsoleColors.RESET + "Synchronization demo" );
        sum =  100;
        int months = 12;
        threads = months;
        for( int i = 0; i < months; ++i){
            new Thread(plus10percentSyncFin).start();
        }
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for(int i = 1; i <= 10; ++i){
            pool.submit( ( (Function<Integer,Runnable>) x ->  ()-> System.out.printf("pool %d works%n",x)).apply(i));
        }

        Future<String> res = pool.submit( () -> { return "Hello,world!"; });

        try{
            String str = res.get();
            System.out.println(str);
        }catch (Exception ex){
            System.out.println( ex.getMessage() );
        }
        res = pool.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                }
        );
        pool.shutdown();
    }

    private int threads;
    private double sum;

    private Runnable plus10percent = ()  ->{
        double tmp = sum;
        tmp *= 1.10; // + 10 %
        System.out.println( "current sum = " + tmp );
        sum = tmp;
    };

    private final Object sumLocker  = new Object();
    private final Object locker  = new Object();
    private Runnable plus10percentSync = ()  ->{
        synchronized (locker){
            double tmp = sum;
            tmp *= 1.10; // + 10 %
            System.out.println( "current sum = " + tmp );
            sum = tmp;
        }
    };

    private Runnable plus10percentSync2 = ()  ->{
        synchronized (locker){
            sum *= 1.10;
        }
        System.out.println( "current sum = " + sum );
    };

    private Runnable plus10percentSync3 = ()  ->{
        double tmp;
        synchronized (locker){
            tmp = sum = sum * 1.10;
        }
        System.out.println( "current sum = " + tmp );
    };

    private Runnable plus10percentSyncFin = ()  ->{

        double tmp;
        boolean isLast;
        synchronized (sumLocker){
            tmp = sum = sum * 1.10;
        }
        synchronized (sumLocker){
            threads--;
            isLast = threads == 0;
        }
        System.out.println( (isLast ? ConsoleColors.GREEN_BOLD : " ") + "current sum = " + tmp +  (isLast ? ConsoleColors.RED_BOLD : " "));
    };

}