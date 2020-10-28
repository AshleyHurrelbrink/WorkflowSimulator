package project;
import java.util.concurrent.Semaphore;

public class State extends Thread{
    //next states
    //name
    //start
    //stop
    //conditii de next

    //print ("suntem in state "name" ")
    //set global variable:

    Semaphore sem;
    String stateName;
    int cond;
    String nextState;

    public State(Semaphore sem, String stateName, int cond, String nextState)
    {
        super(stateName);
        this.sem = sem;
        this.stateName = stateName;
        this.cond = cond;
        this.nextState = nextState;
    }

    @Override
    public void run() {

        System.out.println("Starting " + stateName);
        try {
            System.out.println(stateName + " is waiting for permit");

            sem.acquire();
            System.out.println(stateName + " gets permit");

            int n = stateName.length();

            for (int i = 0; i < n; i++) {
                Shared.count++;
                System.out.println(stateName + ": " + Shared.count);

                Thread.sleep(10);
            }

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(stateName + " releases the permit.");
        sem.release();
    }
}
