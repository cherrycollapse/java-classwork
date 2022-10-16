package step.learning.threading;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Д.З. Реализовать задачу расчета годовой инфляции, используя
//пул задач

@DemoClass
public class InflationDemo {

    double targetYear;
    double baseYear;
    private final Object sumLocker = new Object();

    @EntryPoint
    public void run() {
        Scanner kbScanner = new Scanner(System.in);

        System.out.println("Enter base year value : ");
        this.baseYear = kbScanner.nextDouble();

        System.out.println("Enter target year value : ");
        double b = kbScanner.nextDouble();

        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.submit((new Runnable() {
            @Override
            public void run() {
                double res;
                synchronized (sumLocker) {
                    res = (targetYear - baseYear) / baseYear * 100  ;
                }
                System.out.println("inflation rate = " + res);
            }
        }));

        pool.shutdown();

    }

}
