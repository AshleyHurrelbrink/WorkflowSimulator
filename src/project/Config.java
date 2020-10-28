package project;

import java.util.ArrayList;

public class Config {
    private String fileName;
    private ArrayList<State> statesConfig = new ArrayList();

    public Config(String filename){
        this.fileName=filename;
    }

    public ArrayList<State> getConfig(){
        return statesConfig;
    }

    public void readConfigFile() {
        //file open checks
        // for read file()
        //    statesConfig.add(new State("A",1,"++"))} --> Ex: A,1++
    }

}
