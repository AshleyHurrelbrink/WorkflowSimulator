package publishing.workflow;

import publishing.state.StateA;

public class Workflow implements Runnable {
    Thread t;
    public Workflow() {
        t = new Thread(this, "Workflow");
        System.out.println("in constructor la workflow " + t);
        t.start();
    }
    public void run() {
        StateA stateA = new StateA();
        stateA.execute();
        System.out.println("Exiting the thread");
    }
}
