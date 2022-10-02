package step.learning.files;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.io.*;
import java.nio.charset.StandardCharsets;

@DemoClass
public class FilesDemo {

    @EntryPoint
    public void run(){
        //fsDemo();
        ioDemo();
    }

    private void fsDemo()  //  работа с файловой системой
    {
        File file = new File("newItem");  //  текущая директория

        if(file.isDirectory())
        {
            System.out.println(file.getName() + " - is existing directory");

            String[] list = file.list();
            if(list != null)
            {
                for(String itemName: list)
                {
                    System.out.println(itemName);
                }
            }
            else
            {
                System.out.println("List request error");
            }

            //  предложим удалить директорию
            System.out.println("Delete directory? (y/...) " );
            int sym;
            try{
                sym = System.in.read();
            }
            catch(IOException ex)
            {
                System.out.println("System error: "+ ex.getMessage());
                return;
            }

            if(sym == 'y')
            {
                boolean res = file.delete();
                if(res)
                {
                    System.out.println("Directory deleted successfully");
                }
                else
                {
                    System.out.println("Deletion error");
                }
            }
            else {
                System.out.println("Delete cancelled");
            }
        }
        else if(file.isFile())
        {
            System.out.println(file.getName() + " - is existing file");

            if(file.canRead()){
                System.out.println("Readable");
            }
            else
            {
                System.out.println("Non-Readable");
            }

            if(file.canExecute()){
                System.out.println("Executable");
            }
            else
            {
                System.out.println("Non-Executable");
            }

            if(file.canWrite()){
                System.out.println("Writable");
            }
            else
            {
                System.out.println("Non-Writable");
            }

            System.out.println("File size: "+ file.length());
        }
        else
        {
            System.out.println(file.getName() + " - does not exist");

            boolean res = file.mkdir();  //  создать директорию

            if(res)
            {
                System.out.printf("Directory '%s' created %n",file.getName());
            }
            else
            {
                System.out.println("Directory creation error");
            }
        }
    }



    private void ioDemo()  //  работа с файлами для хранения данных
    {
        //  чтение из файла. низкоуровневый вариант

        String fileContent;
        StringBuilder sb = new StringBuilder();

        try(InputStream reader = new FileInputStream("hello.txt.txt"))
        {
            int symbol;
            while((symbol = reader.read())!=-1)
            {
                //fileContent += (char)symbol;

                sb.append((char)symbol);
            }
            // reader.read();
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            return;
        }
        fileContent = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        System.out.println(fileContent);

        //  запись в файл  --  более высокий уровень
        try(FileWriter writer = new FileWriter("result.txt"))
        {
            writer.write("Привет");
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Write finished");
    }
}