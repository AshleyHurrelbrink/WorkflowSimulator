package publishing.state;

import publishing.model.Document;

public class StateB extends State {

    public StateB (Document document) {
        this.document = document;
    }

    public static boolean checkDocument(Document document) {
        if (document.getDocument().length() < 550){
            return true;
        }
        return false;
    }

    public static void nextState(Document document) {
        StateB stateB = new StateB(document);
        stateB.execute();

    }

    @Override
    public void execute() {
        if(checkDocument(this.document) == true) {
            System.out.println("documentul e bun");
            StateC.nextState(this.document);
        }
        else {
            System.out.println("documentul nu e bun");
            StateA.nextState(this.document);
        }
    }
}