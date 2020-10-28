package project;

import java.util.ArrayList;

public class Config {
    private String fileName;
    private ArrayList<State> statesConfig = new ArrayList();

    public Config(String filename){
        this.fileName=filename;
    }

    public ArrayList<State> getStatesConfig(){
        return statesConfig;
    }

    public void readConfigFile() {
        //file open checks
        //Semaphore sem = new Semaphore(1);
        // for read file()
        //    statesConfig.add(new State(sem,"A",1,"++"))} --> Ex: A,1++
    }

}
