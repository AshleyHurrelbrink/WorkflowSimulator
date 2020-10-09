package publishing.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class GetUserGitHubDataService extends GetUserDataService{

    private Hashtable<String, String> userGitHubData;

    public GetUserGitHubDataService(String filePath) {
        super(filePath);
        this.userGitHubData = new Hashtable<String, String>();
    }


    @Override
    public Hashtable getData() {
        this.processFile();
        return this.userGitHubData;
    }


    private void processFile(){
        BufferedReader reader;
        String[] split;

        try {
            reader = new BufferedReader(new FileReader(super.filePath));
            for(String line; (line = reader.readLine()) != null; ){
                split = line.split(":",2);
                this.userGitHubData.put(split[0], split[1].trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
