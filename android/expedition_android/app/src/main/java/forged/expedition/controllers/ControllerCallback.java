package forged.expedition.controllers;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by visitor15 on 10/17/14.
 */
public abstract class ControllerCallback implements Serializable {

    public abstract void handleCallback(Bundle b);
}
