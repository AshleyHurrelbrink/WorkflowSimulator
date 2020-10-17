package publishing.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import publishing.model.Document;


/**
    TO DO: implementation on threads
*/

public class PostOnGitHubService{

    private Document document;

    private String localRepositoryPath;
    private String gitHubRepositoryLink;
    private String authenticationToken;

    private TextProgressMonitor consoleProgressMonitor;
    private static boolean isCloned = false;
    private static Git git;


    public PostOnGitHubService(Document document){
        GetUserDataService getUserDataService =
                new GetUserDataService("C:\\Users\\Stefii\\Desktop\\Year_4\\CEBP\\CEBP-Project-1\\git_data.txt");
        Hashtable<String, String> data = (Hashtable<String, String>) getUserDataService.runService();

        this.document = document;
        this.localRepositoryPath = data.get("local_repository");
        this.gitHubRepositoryLink = data.get("github_repository");
        this.authenticationToken = data.get("authentication_token");
        this.consoleProgressMonitor = new TextProgressMonitor(new PrintWriter(System.out));
    }


    /**
     * TO DO: allow branch selection
    */
    public void runService() {
        try {
            if (!isCloned) {
                git = this.cloneGitHubRepository();
            }
            this.addDocumentToLocalRepository(git, document.getDocument(), "docs");
            this.commitChanges(git);
            this.pushToGitHub(git);

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }


    private Git cloneGitHubRepository() throws GitAPIException {
        System.out.println("Cloning repository...");
        File localRepository = new File(this.localRepositoryPath);

        Repository repo = Git.cloneRepository()
                .setProgressMonitor(this.consoleProgressMonitor)
                .setDirectory(localRepository)
                .setURI(this.gitHubRepositoryLink)
                .call()
                .getRepository();

        Git git = new Git(repo);
        isCloned = true;

        return git;
    }


    /**
     * TO DO: add an actual file to repo
    */
    private void addDocumentToLocalRepository(Git git, String documentContent, String documentName) throws GitAPIException {
        System.out.println("Adding document to local repository...");
        File documentFile = new File(this.localRepositoryPath + "/" + documentName + ".txt");

        try {
            FileWriter documentWriter = new FileWriter(documentFile);
            documentWriter.write(documentContent);
            documentWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        git.add().addFilepattern(".").call();
    }


    private void printLocalRepositoryStatus(Git git) throws GitAPIException {
        Status status = git.status().setProgressMonitor(this.consoleProgressMonitor).call();
        System.out.println("Modified file = " + status.getModified());
    }


    private void commitChanges(Git git) throws GitAPIException {
        System.out.println("Committing changes...");
        RevCommit revCommit = git.commit()
                .setAll(true)
                .setMessage("Adding commit from JGIT")
                .call();
    }


    private void pushToGitHub(Git git) throws GitAPIException {
        System.out.println("Pushing to GitHub...");
        Iterable<PushResult> resultIterable = git.push()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(this.authenticationToken, ""))
                .call();
    }
}
