package publishing.state;

import publishing.model.Document;

public class StateC extends State{

   public StateC (Document document) {
       this.document = document;
   }

    public static void nextState(Document document) {
        StateC stateC = new StateC(document);
        stateC.execute();
    }


    @Override
    public void execute() {
        System.out.println("state C chem la datorie state D si E");
        StateD.nextState(this.document);
        StateE.nextState(this.document);
    }
}