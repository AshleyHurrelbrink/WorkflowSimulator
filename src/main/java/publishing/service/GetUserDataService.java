package publishing.service;

public abstract class GetUserDataService implements Service{

    protected String filePath;

    public GetUserDataService(String filePath){
        this.filePath = filePath;
    }

    @Override
    public abstract Object runService();
}
