package sd3.com.src;

import java.util.ArrayList;
import java.util.List;
import sd3.com.io.FileIO;
import sd3.com.model.Record;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    //I'm going to read the data from the file ('census.txt') into a List<Record>
    static List<Record> list = new ArrayList();
    static String file = "census.txt";
    
<<<<<<< Updated upstream
    public static void main(String[] args) {
   
=======
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException{
       
        list = FileIO.readFile(file);
        
        Callable<Integer> task1 = () -> {
            int total = 0;
            for(Record r : list)
            {
                if("MF".equals(r.getCode()))
                    total++;
            }
            return total;
        };
        
        ExecutorService exe = Executors.newCachedThreadPool();
        
        
        Callable<Integer> task2 = () ->{
            int total = 0;
            for(Record r : list)
            {
                if("MO".equals(r.getCode()))
                    total++;
            }
            return total;
        };
        
        
        
        Runnable task3 = () ->{
          int total = 0;
            for(Record r : list)
            {
                if("FO".equals(r.getCode()))
                    total++;
            }
            System.out.println("Task 3 result : " + total);
 
        };
        
        Runnable task4 = () ->{
          int total = 0;
            for(Record r : list)
            {
                if("LY".equals(r.getIndicator()))
                    total++;
            }
            System.out.println("Task 4 result : " + total);
 
        };
        Runnable task5 = () ->{
          int total = 0;
            for(Record r : list)
            {
                if("LN".equals(r.getIndicator()))
                    total++;
            }
            System.out.println("Task 5 result : " + total);
 
        };
        Future<Integer> future1 = exe.submit(task1);
        Integer result = future1.get();
        
        Future<Integer> future2 = exe.submit(task2);
        Integer result2 = future2.get();
        System.out.println("Task 1 result : " + result);
        System.out.println("Task 2 Result : " + result2);
        exe.submit(task3);
        exe.submit(task4);
        exe.submit(task5);
>>>>>>> Stashed changes
    }//end main


}//end class
