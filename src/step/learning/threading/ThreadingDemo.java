package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

@DemoClass
public class ThreadingDemo {
    @EntryPoint
    public void demo(){
     new PrinterThread().start(); // run - синхронный вызов // start - в новом потоке

        new ArgThread("arg1").start();
        ArgThread argThread = new ArgThread();
        argThread.setArg("arg2");
        argThread.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(ConsoleColors.YELLOW + "Anon thread" + ConsoleColors.RESET);;
            }
        }.start();

        Runnable runnable =()->{
            System.out.println("Arrow runnable");
        };
        new Thread(runnable).start();

        new Thread (()-> System.out.println("Arrow in Thread"));

        // new Thread (this::printDemo);


        System.out.println(ConsoleColors.RED + "Threading Demo" + ConsoleColors.RESET);
    }

    void printDemo(){
        System.out.println(ConsoleColors.GREEN+"printDemo method"+ConsoleColors.RESET);
    }

    static class PrinterThread extends Thread{

        @Override
        public void run() {
            System.out.println("PrinterThread  works");
        }
    }

    static class ArgThread extends Thread{

        private String arg;

        // ctrl + o
        @Override
        public void run() {
            System.out.println("Arg thread : "+this.arg);
        }

        // alt + insert
        public void setArg(String arg) {
            this.arg = arg;
        }

        public ArgThread() {
            this.arg = "";
        }

        public ArgThread(String arg) {
            this.arg = arg;
        }
    }



}
