package publishing.state;

public class StateD implements State{

    private String currentState;
    private String nextState;

    public StateD(){
        this.currentState = "D";
        this.nextState = null;
    }

    @Override
    public String getState() {
        return this.currentState;
    }

    @Override
    public String nextState() {
        return this.nextState;
    }

    @Override
    public void run() {

    }
}
