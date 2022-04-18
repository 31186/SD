package client;

import edu.ufp.inf.sd.rmi._05_observer.server.State;
import edu.ufp.inf.sd.rmi._05_observer.server.SubjectRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {
    private String id;
    private State lastObserverState;
    public SubjectRI subjectRI;
    private ObserverGuiClient client;

    protected ObserverImpl(String id, ObserverGuiClient client, SubjectRI subject) throws RemoteException {
        super();
        this.id = id;
        this.client = client;
        this.subjectRI = subject;
        this.subjectRI.attach(this);
    }

    @Override
    public void update() throws RemoteException {
        this.lastObserverState = subjectRI.getState();
        this.client.updateTextArea();
    }

    public State getLastObserverState() {
        return lastObserverState;
    }
}
