package publishing.state;

public interface State {
    public String getState();
    public String nextState();
    public void run();
}
