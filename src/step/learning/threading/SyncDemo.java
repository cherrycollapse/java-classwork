package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

@DemoClass
public class SyncDemo {

    @EntryPoint
    public void demo(){
        System.out.println(ConsoleColors.BLUE+"Synchronization demo"+ConsoleColors.RESET);
        for(int i = 0;i<12;++i){
            new Thread(plus10percent).start();
        }
    }


    private double sum ;
    private Runnable plus10percent = () -> {
        double tmp = sum ;
        tmp *= 1.10 ; // + 10 %
        System.out.println("Current sum = "+tmp);
        sum = tmp;
    };



}


/*
    Синхронизация. Фоновые потоки - задачи



 */