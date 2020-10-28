package project;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) throws InterruptedException
    {
        /*Test With config*/
        /*
        String stateConfigFile ="state.txt";
        Config config = new Config(stateConfigFile);
        ArrayList<State> statesConfig = config.getStatesConfig();
        */

        /* Test without config*/
        //A,0++
        //B,1-+
        //C,2+
        Semaphore sem = new Semaphore(1);
        State a = new State(sem,"A",0,"++");
        State b = new State(sem,"B",1,"-+");
        State c = new State(sem,"C",2,"+");
        ArrayList<State> statesConfig = new ArrayList<>();
        statesConfig.add(a);
        statesConfig.add(b);
        statesConfig.add(c);

        /*Start workflow*/
        WorkflowManager manager = new WorkflowManager(statesConfig);
        manager.startWorkflow();
        manager.stopWorkflow();

    }
}
