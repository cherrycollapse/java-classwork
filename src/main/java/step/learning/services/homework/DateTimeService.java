package step.learning.services.homework;

import java.text.SimpleDateFormat;
import java.util.Date;


// Реалізувати службу часу з методами
//getDate() - повертає рядок з поточною датою у форматі чч.мм.рррр
//getTime() - повертає рядок з поточним часом у форматі гг:хх:сс
//Ін'єктувати службу у App, вивести рез-ти роботи методів.

public class DateTimeService {

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format((new Date()));

    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format((new Date()));
    }

}
