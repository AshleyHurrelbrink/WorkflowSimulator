package publishing.state;

import publishing.model.Document;

public abstract class State {
    protected Document document;

    public abstract void execute();

    public static void nextState(Document document) {}
}
