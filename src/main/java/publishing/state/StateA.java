package publishing.state;

import publishing.model.Document;
import publishing.service.RandomTextCreatorService;

public class StateA extends State{

    public StateA() {
        int n = 500;
        this.document = new Document();
        RandomTextCreatorService randomTextCreatorService =new RandomTextCreatorService();
        this.document.setDocument(randomTextCreatorService.getAlphaNumericString(n));
    }

    public static void nextState(Document document){
        StateA stateA = new StateA();
        stateA.execute();
    }

    public void execute(){
//        System.out.println(document.getDocument());
        StateB.nextState(this.document);
    }


}