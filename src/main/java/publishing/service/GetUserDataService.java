package publishing.service;

import java.util.Hashtable;

public abstract class GetUserDataService implements Service{

    protected String filePath;

    public GetUserDataService(String filePath){
        this.filePath = filePath;
    }

    public abstract Hashtable getData();

    @Override
    public void runService() {

    }
}
