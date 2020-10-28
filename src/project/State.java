package project;

import java.util.concurrent.Semaphore;

public class State extends Thread{

    private Semaphore sem;
    private String stateName;
    private int startCondition;
    private String nextState;

    public State(Semaphore sem, String stateName, int startCondition, String nextState)
    {
        super(stateName);
        this.sem = sem;
        this.stateName = stateName;
        this.startCondition = startCondition;
        this.nextState = nextState;
    }


    @Override
    public void run() {

        System.out.println("STARTING " + "["+ stateName + "]");
        try {
            System.out.println("["+ stateName + "] -> " + "is waiting for permit");

            sem.acquire();
            System.out.println("["+ stateName + "] -> " + "gets permit");

            if(this.nextState.charAt(0)=='+'){
                Shared.count++;
                System.out.println("["+ stateName + "] -> " +"(action: +) changes count to" + ": " + Shared.count);
                updateNextState();
                Thread.sleep(10);
            }
            else if(this.nextState.charAt(0)=='-'){
                Shared.count--;
                System.out.println("["+ stateName + "] -> " +"(action: -) changes count to" + ": " + Shared.count);
                updateNextState();
                Thread.sleep(10);
            }

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println("["+ stateName + "] -> " + "releases the permit.");
        sem.release();
    }

    public int getStartCondition(){
        return this.startCondition;
    }

    public String getNextState(){
        return this.nextState;
    }

    public Semaphore getSem(){
        return this.sem;
    }

    public void updateNextState(){
        int len = this.nextState.length();
        this.nextState = this.nextState.substring(1,len);
        System.out.println("["+ stateName + "] -> nextState = " + this.nextState);
    }

}
