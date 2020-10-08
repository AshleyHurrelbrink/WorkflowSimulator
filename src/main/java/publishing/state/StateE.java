package publishing.state;

public class StateE implements State {
    private String currentState;
    private String nextState;

    public StateE(){
        this.currentState = "E";
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
