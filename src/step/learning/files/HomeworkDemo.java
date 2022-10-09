package step.learning.files;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Реалізувати файловий навігатор
//При старті виводиться вміст поточної директорії
//програма сприймає команди двох типів:
// cd  dir  - перейти у директорію "dir" (та вивести її зміст)
// cat file - вивести вміст файлу "file"
//Інші команди не обробляються з повідомленням "Command unknown"
//У разі неправильного значення для  dir  або  file  виводиться
// повідомлення "Resource not found"

@DemoClass
public class HomeworkDemo {

    StringBuilder currentPath;

    public HomeworkDemo() {
        currentPath = new StringBuilder();
    }

    public StringBuilder getCurrentPath() {
        return currentPath;
    }

    void cdCommand(String dir) {
        if (!currentPath.isEmpty()) {
            currentPath.append("/");
        }
        currentPath.append(dir);

        File file = new File(String.valueOf(currentPath));

        if (file.isDirectory()) {
            String[] items = file.list();

            if (items != null) {
                for (String itemName : items) {
                    System.out.print(ConsoleColors.GREEN + itemName + "\t");
                }
                System.out.println(ConsoleColors.RESET);
            }
        } else {
            System.out.println(dir + " is not a directory. Resource not found");
        }
    }

    void catCommand(String path) {
        File file = new File(path);

        if (file.isFile()) {
            String fileContent;
            StringBuilder sb = new StringBuilder();

            try (InputStream reader = new FileInputStream(path)) {
                int symbol;
                while ((symbol = reader.read()) != -1) {
                    sb.append((char) symbol);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            fileContent = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            System.out.println(fileContent);
        } else {
            System.out.println(path + " is not a file. Resource not found");
        }
    }


    @EntryPoint
    public void run() {
        cdCommand(".");

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println(" > " + getCurrentPath());
            System.out.println("Type down command : 'cd' or 'cat'");
            Scanner kbScanner = new Scanner(System.in);

            try {
                String[] input = (kbScanner.nextLine()).split(" ");
                switch (input[0]) {
                    case "cd" -> cdCommand(input[1]);
                    case "cat" -> catCommand(input[1]);
                    default -> {
                        System.out.println("Command unknown");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Command unknown");
            }
        }

    }

}
