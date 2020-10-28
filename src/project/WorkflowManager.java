package project;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkflowManager {

        private ArrayList<State> statesConfig;

        public WorkflowManager(ArrayList<State> statesConfig){
            this.statesConfig=statesConfig;
        }

        public boolean isConditionValid(ArrayList<State> states){
            for(State state : states){
                if(state.getStartCondition() == Shared.count){
                    return true;
                }
            }
            return false;
        }

        public void resetStateThread(State state, ArrayList<State> stateConfig){
            State newState = new State (state.getSem(),state.getName(),state.getStartCondition(),state.getNextState());
            stateConfig.remove(state);
            stateConfig.add(newState);
        }

        public void startWorkflow() throws InterruptedException {
            while(isConditionValid(statesConfig)) {
                for (State state : statesConfig) {
                    if (state.getStartCondition() == Shared.count) {
                        //resetStateThread(state,statesConfig);
                        state.run();
                        System.out.println("EXITED (START) " + "["+ state.getName() + "]");
                        state.join();
                        System.out.println("EXITED (JOIN) " + "["+ state.getName() + "]");
                    }
                }
            }
        }

        public void stopWorkflow() throws InterruptedException {
            for (State state : statesConfig) {
                state.join();
                System.out.println("EXITED (JOIN) " + "["+ state.getName() + "]");
            }
        }


}

