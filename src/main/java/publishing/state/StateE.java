package publishing.state;

import publishing.model.Document;

public class StateE extends State {

    public StateE(Document document){
        this.document = document;
    }

    public static void nextState(Document document) {
        StateE stateE = new StateE(document);
        stateE.execute();
    }

    @Override
    public void execute() {
        System.out.println("salut din E");
//        System.out.println(this.document.getDocument());
    }
}
