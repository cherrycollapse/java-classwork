package step.learning.serial;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@DemoClass
public class SerializationDemo {

    public void serializeList()
    {
        List<DataObject> list = new ArrayList<>();
        list.add(new DataObject(10,2.9f,"Hello","trans"));
        list.add(new DataObject(15,2.9f,"Hello","trans"));
        list.add(new DataObject(17,2.9f,"Hello","trans"));
        list.add(new DataObject(18,2.9f,"Hello","trans"));
        list.add(new DataObject(0,2.9f,"Hello","trans"));

        try(FileOutputStream file = new FileOutputStream("list.ser"))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.flush();
        } catch (IOException e) {
            System.out.println("error");
            return;
        }
        System.out.println("Serialized");
    }

    public void serialized()
    {
        DataObject data1 = new DataObject();
        DataObject data2 = new DataObject(10);
        DataObject data3 = new DataObject(10,2.5f);
        DataObject data4 = new DataObject(10,2.5f,"Hello","trans");

        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data4);

        System.out.println();
        System.out.println();

        //сериализация
        try(FileOutputStream file = new FileOutputStream("save.ser"))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(data1);
            oos.writeObject(data2);
            oos.writeObject(data3);
            oos.writeObject(data4);
            oos.flush();
        } catch (IOException e) {
            System.out.println("error");
            return;
        }
        System.out.println("Serialized");
    }

    public void deserializedList()
    {
        try(FileInputStream file = new FileInputStream("list.ser"))
        {
            ObjectInputStream ios = new ObjectInputStream(file);
//            List<DataObject>  list = (List<DataObject>)ios.readObject();
//            for(DataObject data : list){
//                System.out.println(data);
//            }
//
            List<?> list = (List<?>)ios.readObject();
            for(Object data:list)
            {
                if(data instanceof DataObject)
                {
                    System.out.println(data);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error");
            return;
        }
        System.out.println("Done");
    }

    public void deserialized()
    {
        try(FileInputStream file = new FileInputStream("save.ser"))
        {
            ObjectInputStream ios = new ObjectInputStream(file);
//            DataObject data = (DataObject)ios.readObject();
//            System.out.println(data);
            while(file.available()>0)
            {
                DataObject data = (DataObject)ios.readObject();
                System.out.println(data);
            }
//            data = (DataObject)ios.readObject();
//            System.out.println(data);
//            data = (DataObject)ios.readObject();
//            System.out.println(data);
//            data = (DataObject)ios.readObject();
//            System.out.println(data);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error");
            return;
        }
        System.out.println("Done");
    }

    @EntryPoint
    public void run()
    {
        serializeList();
        deserializedList();


    }

}