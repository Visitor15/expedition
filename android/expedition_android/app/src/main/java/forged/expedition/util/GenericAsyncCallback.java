package forged.expedition.util;

/**
 * Created by visitor15 on 10/6/14.
 */
public abstract class GenericAsyncCallback implements Runnable {

    public abstract void onHandleAsyncCallback();

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        onHandleAsyncCallback();
    }
}
