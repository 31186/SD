package server;

import edu.ufp.inf.sd.rmi._05_observer.client.ObserverRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SubjectImpl extends UnicastRemoteObject implements SubjectRI {
    private State subjectState;
    private final ArrayList<ObserverRI> observers = new ArrayList<>();

    public SubjectImpl() throws RemoteException {
        super();
        this.subjectState = new State("", "");
    }

    @Override
    public void attach(ObserverRI observer) throws RemoteException {
        if (this.observers.contains(observer))
            throw new RemoteException("User already attached");

        this.observers.add(observer);
    }

    @Override
    public void detach(ObserverRI observer) throws RemoteException {
        if (!this.observers.contains(observer))
            throw new RemoteException("User not attached");

        this.observers.add(observer);
    }

    @Override
    public State getState() throws RemoteException {
        return this.subjectState;
    }

    @Override
    public void setState(State state) throws RemoteException {
        this.subjectState = state;
        this.notifyAllObservers();
    }

    public void notifyAllObservers() throws RemoteException {
        for (ObserverRI myObserver: this.observers) {
            myObserver.update();
        }
    }
}
