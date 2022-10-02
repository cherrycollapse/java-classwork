package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.*;

@DemoClass
public class Complex {
    private Random random;
    public Complex() {
        random = new Random();
    }

    private void arraysDemo(){
        int [] arr1 = new int[4]; // default init
        int [] arr2 = new int[]{5,4,3,2,1};
        int [] arr3 = {5,4,3,2,1};

        for (int i=0;i<4;++i){
            System.out.println(String.format("arr1[%d] =%d%n", i,arr1[i]));
        }
        System.out.println("----------------------------------");

        int j=0;
        for(;j<arr2.length;j++){
            System.out.println(arr2[j]);
        }
        System.out.println("----------------------------------");

        for(int a:arr3){
            System.out.println(a);
        }
        System.out.println("----------------------------------");

        // Jagged arrays
        int [][] arr4 ={ {1,2,3},{4,5,6,7},{8,9} };

        int [][] arr5= new int [3][4];
        for(int i=0;i<3;i++){
            for (int k = 0; k<4; k++){
                System.out.print(arr5[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");

//        for(int[] a:arr4){
//            for(int x :a){
//                System.out.print(x+" ") ;
//            }
//            System.out.println();
//        }

        printArr(arr4);
        System.out.println("----------------------------------");

        initRandArray(arr5);
        printArr(arr5);
        System.out.println("----------------------------------");


    }

    private void initRandArray(int[][]arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr[i].length ; j++) {
                arr[i][j] = random.nextInt(42);
            }
        }
    }

    private void printArr(int[][]arr){
        for(int[] a:arr){
            for(int x :a){
                System.out.print(x+" ") ;
            }
            System.out.println();
        }
    }

    private void collectionsDemo(){
        ArrayList<Integer>arr1 = new ArrayList<>(); // generic collection, using Integer
        List<Integer> arr2 = new ArrayList<>(); // diamond operand

        arr1.add(10);
        arr1.add(20);
        arr1.add(30);
        arr1.add(40);

        for(Integer x:arr1){
            System.out.println(x+" ");
        }
        System.out.println();
        System.out.println("----------------------------------");

        arr1.set(1,21);
        arr1.remove(3);

        for(int i=0;i<arr1.size();i++){
            System.out.printf("i=%d, x=%d%n",i,arr1.get(i)); // %n - символ разрыва
        }
        System.out.println("----------------------------------");

        Map<String, String> map = new HashMap<>(); // diamond
        map.put("Hello","Привет");
        map.put("Bye","Пока");
        map.put("Hi","Здравствуйте");

        for(String key : map.keySet()){
            System.out.printf("%s -- %s\n",key, map.get(key));
        }
        System.out.println("----------------------------------");

        Scanner kbScanner = new Scanner(System.in);
        String str = kbScanner.nextLine();
        /*
        while(System.in.available()){
            int c = System.in.read();
        }
*/

        String translate = map.get(str);
        System.out.println(str+ " - " + translate);

    }

    /*
    Задание: UI работы со словарем
    Англо-Украинский словарь
    1. Показать все
    2. Перевод англ. слова
    3. Перевод укр. слова
    *4. Добавить слово
    0. Выход
    Введите выбор:
     */
    private void dictionaryDemo() {
        Map<String, String> map = new HashMap<>() ; // diamond
        map.put( "hello","привiт" ) ;
        map.put( "bye","до побачення" ) ;
        map.put( "cat","кiт" ) ;

        boolean flag = true ;

        System.out.printf( "Англо-украинский словарь\n1.Показать все\n2.Перевод англ. слова\n3.Перевод укр. слова\n4.Добавить слово\n0.Выход\nВведите выбор:" ) ;
        Scanner kbScanner = new Scanner( System.in ) ;
        String choice = " " ;

        while( flag ) {
            choice = kbScanner.nextLine() ;
            switch ( choice ) {
                case "1" :
                    for( String key : map.keySet() ) {
                        System.out.printf( "%s -- %s\n",key, map.get(key) ) ;
                    }
                    break ;
                case "2" :
                    System.out.println( "Введите слово на анлийском языке :" ) ;
                    String str1 = kbScanner.nextLine().toLowerCase() ;
                    String translate = map.get( str1 ) ;
                    System.out.println( str1 + " - " + translate ) ;
                    break ;
                case "3" :
                    System.out.println( "Введите слово на украинском языке :" ) ;
                    String str2 = kbScanner.nextLine().toLowerCase() ;

                    String[] values = map.values().toArray( new String[0] ) ;
                    String[] keys = map.keySet().toArray( new String[0] ) ;

                    for( int i = 0 ; i < values.length ; i++ ) {
                        if(values[i].contentEquals( str2 ) ) {
                            System.out.println(values[i] + " - "+ keys[i] ) ;
                        }
                    }
                    break ;
                case "4" :
                    System.out.println( "Введите слово на английском :" ) ;
                    String word = kbScanner.nextLine().toLowerCase() ;
                    System.out.println( "Введите его перевод :" ) ;
                    String translation = kbScanner.nextLine().toLowerCase() ;

                    if ( word != null && translation != null ) {
                        map.put( word, translation ) ;
                        System.out.println( "Слово добавлено" ) ;

                    }
                    else {
                        System.out.println( "Неккоректный ввод" ) ;
                    }
                    break ;
                case "0" :
                    flag = false ;
                    System.out.println( "Выход" ) ;
                    break ;
                default :
                    break ;
            }
            System.out.println( "----------------------------------" ) ;
        }

    }

    @EntryPoint
    public void run() {
        dictionaryDemo() ;
//        arraysDemo();
//        collectionsDemo();
    }
}

/* Тема : Комплексные типы данных - массивы и коллекции
Массивы - ссылочные типы (скорость и компактность, сложно добавить новый элемент в уже заполненный, сложно удалять с середины)
Коллекции - "эластичные массивы" (гибкость, когда неизвестно количество элементов - массив с запасом, но повышенная трудоекмкость и нагрузка на память)
 = рекомендация - коллекции использовать как что-то промежуточное - накапливать элементы и сбрасывать в массив
 - Линейные (списки) : ArrayList (лучше работает индексирование), LinkedList (связанный список), Vector, стек, очередь
 - Множество (set) в математическом смысле - не допускают повторяющихся элементов, элемент либо есть либо нет
 - Map ("dictionary")

 */
