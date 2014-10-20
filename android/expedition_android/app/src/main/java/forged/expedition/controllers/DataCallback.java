package forged.expedition.controllers;

import java.util.List;

/**
 * Created by visitor15 on 10/19/14.
 */
public abstract class DataCallback<T> {

    public abstract void receiveResults(List<T> results);
}
