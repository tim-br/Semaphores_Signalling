
/**
 * This example is from section 3.4 Multiplex. The goal is that only X number of thread are allowed to access the critical code at a time.
 * In this case X is 6, but it can be changed easily.
 */
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class Multiplex
{
    private static final int WAITMIN = 3000;
    private static final int WAITMAX = 6000;
    private static  int tot = 0;
    private static Random rng = new Random();
    public static Semaphore multiplex = new Semaphore(6);//the multiplex that only allowd in 6 threads.

    private static class A implements Runnable{
        public void run(){

            try{
                multiplex.acquire();
            } catch (InterruptedException e){}
            tot += 1;
            System.out.println("Statement a " + tot);
            try{
                Thread.sleep(WAITMIN + rng.nextInt(WAITMAX - WAITMIN));
            } catch (InterruptedException e){}
            multiplex.release();
            tot -= 1;

        }
    }

    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        int i;
        for(i = 0; i< 10; i++){
            executor.execute(new A());
        }

    }
}
