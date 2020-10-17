package publishing.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class GetUserDataService implements Service{

    protected String filePath;
    private Hashtable<String, String> userData;


    public GetUserDataService(String filePath){
        this.filePath = filePath;
        this.userData = new Hashtable<String, String>();
    }


    @Override
    public  Object runService(){
        this.processFile();
        return userData;
    };


    private void processFile(){
        BufferedReader reader;
        String[] split;

        try {
            reader = new BufferedReader(new FileReader(this.filePath));
            for(String line; (line = reader.readLine()) != null; ){
                split = line.split(":",2);
                this.userData.put(split[0], split[1].trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
