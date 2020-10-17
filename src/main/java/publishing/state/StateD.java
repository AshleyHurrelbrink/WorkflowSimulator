package publishing.state;

import publishing.model.Document;
import publishing.service.PostOnGitHubService;
import publishing.service.Service;

public class StateD extends State{

    public StateD(Document document){
       this.document = document;
    }

    public static void nextState(Document document) {
        StateD stateD = new StateD(document);
        stateD.execute();
    }

    @Override
    public void execute() {
//        System.out.println("salut din D");

        PostOnGitHubService postOnGitHubService = new PostOnGitHubService(this.document);
        postOnGitHubService.runService();
    }
}
