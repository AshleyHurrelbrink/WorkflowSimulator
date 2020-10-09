package publishing;

import publishing.service.GetUserDataService;
import publishing.service.GetUserGitHubDataService;
import publishing.service.PostOnGitHubService;
import publishing.service.SendViaEmailService;

public class MainPublishing {

    public static void main(String[] args) {

        PostOnGitHubService postOnGitHubService = new PostOnGitHubService(null);
//        PostOnGitHubService postOnGitHubService1 = new PostOnGitHubService(null);
        postOnGitHubService.runService();
//        postOnGitHubService1.runService();
//        SendViaEmailService sendViaEmailService = new SendViaEmailService();
    }
}
