package home.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Button allWorkflows_button, newWorkflow_button;
    public Pane allWorkflows_page, newWorkflow_page;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allWorkflows_page.setVisible(true);
        newWorkflow_page.setVisible(false);
    }

    public void onAllWorkflowsButtonClick(ActionEvent event) throws IOException
    {
        allWorkflows_page.setVisible(true);
        newWorkflow_page.setVisible(false);
    }

    public void onNewWorkflowButtonClick(ActionEvent event) throws IOException
    {
        allWorkflows_page.setVisible(false);
        newWorkflow_page.setVisible(true);
    }


    public void startBtnClick(ActionEvent event) throws IOException
    {
        Parent goToPage= FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene goToPageScene=new Scene(goToPage);
        Stage appStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(goToPageScene);
        appStage.show();
    }

    public void onCloseButtonClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void onMinimizeButtonClick(ActionEvent event) {
        Stage stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}

