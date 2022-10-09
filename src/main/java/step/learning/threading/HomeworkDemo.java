package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.Scanner;


// Пользователь вводит число N, программа запускает N потоков, каждый
// из которых выводит свой номер "Thread X works"

@DemoClass
public class HomeworkDemo {

    static class CustomThread extends Thread {
        int number;

        public CustomThread(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            System.out.println();
            System.out.println("Thread " + this.number + ConsoleColors.GREEN + " works" + ConsoleColors.RESET);
        }
    }

    @EntryPoint
    public void run() {
        Scanner kbScanner = new Scanner(System.in);

        System.out.println("Enter number of threads :");
        int numberOfThreads = kbScanner.nextInt();

        for (int i = 1; i <= numberOfThreads; i++) {
            new CustomThread(i).start();
        }

    }

}
