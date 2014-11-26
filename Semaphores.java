/**
 * This is an implementation of the one of the first examples in the little book of semaphores in section 3.1
 * The goal is that statement B occurs befor statement A. Both these statements occur in respective private static classes.
 * I would like to implement a version that does the same with lock and/or jobs.
 */

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class Semaphores
{
    //The semaphore that is used to guarantee order
    public static Semaphore sem = new Semaphore(0);
      
    //The class that contains statement A
    private static  class A implements Runnable{
        public void run(){
            try{
            sem.acquire();
           } catch (InterruptedException e){}
            System.out.println("I'm in class A"); //statement A
        }
    }
    
    //The class that contains statement B
    private static  class B implements Runnable{
        public void run(){
            System.out.println("I'm in class B");  // statement B
             
            sem.release();
          

        }
    }
    
    
    public static void main(String[] args){

    Thread a = new Thread(new A());
    Thread b = new Thread(new B());
    
    a.start();
    b.start();
    
    }
    
    
}
