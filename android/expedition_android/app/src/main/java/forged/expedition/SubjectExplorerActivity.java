package forged.expedition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.StackView;

/**
 * Created by visitor15 on 10/3/14.
 */
public class SubjectExplorerActivity extends Activity {

    LayoutInflater mInflater;

    StackView stackView;

    public SubjectExplorerActivity() {

    }

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.generic_expandable_card);
        stackView = (StackView)this.findViewById(
                R.id.stackView);

        mInflater = getLayoutInflater();
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p/>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @Override
    protected void onStart() {
        super.onStart();

        stackView.setAdapter(new ImageAdapter(this));
    }



    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     * <p/>
     * <p>For activities that are using raw {@link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be requeried (because you had deactivated it in
     * {@link #onStop}.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Called after {@link #onRestoreInstanceState}, {@link #onRestart}, or
     * {@link #onPause}, for your activity to start interacting with the user.
     * This is a good place to begin animations, open exclusive-access devices
     * (such as the camera), etc.
     * <p/>
     * <p>Keep in mind that onResume is not the best indicator that your activity
     * is visible to the user; a system window such as the keyguard may be in
     * front.  Use {@link #onWindowFocusChanged} to know for certain that your
     * activity is visible to the user (for example, to resume a game).
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestoreInstanceState
     * @see #onRestart
     * @see #onPostResume
     * @see #onPause
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Called when activity resume is complete (after {@link #onResume} has
     * been called). Applications will generally not implement this method;
     * it is intended for system classes to do final setup after application
     * resume code has run.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link android.os.Bundle} populated by this method
     * will be passed to both).
     * <p/>
     * <p>This method is called before an activity may be killed so that when it
     * comes back some time in the future it can restore its state.  For example,
     * if activity B is launched in front of activity A, and at some point activity
     * A is killed to reclaim resources, activity A will have a chance to save the
     * current state of its user interface via this method so that when the user
     * returns to activity A, the state of the user interface can be restored
     * via {@link #onCreate} or {@link #onRestoreInstanceState}.
     * <p/>
     * <p>Do not confuse this method with activity lifecycle callbacks such as
     * {@link #onPause}, which is always called when an activity is being placed
     * in the background or on its way to destruction, or {@link #onStop} which
     * is called before destruction.  One example of when {@link #onPause} and
     * {@link #onStop} is called and not this method is when a user navigates back
     * from activity B to activity A: there is no need to call {@link #onSaveInstanceState}
     * on B because that particular instance will never be restored, so the
     * system avoids calling it.  An example when {@link #onPause} is called and
     * not {@link #onSaveInstanceState} is when activity B is launched in front of activity A:
     * the system may avoid calling {@link #onSaveInstanceState} on activity A if it isn't
     * killed during the lifetime of B since the state of the user interface of
     * A will stay intact.
     * <p/>
     * <p>The default implementation takes care of most of the UI per-instance
     * state for you by calling {@link android.view.View#onSaveInstanceState()} on each
     * view in the hierarchy that has an id, and by saving the id of the currently
     * focused view (all of which is restored by the default implementation of
     * {@link #onRestoreInstanceState}).  If you override this method to save additional
     * information not captured by each individual view, you will likely want to
     * call through to the default implementation, otherwise be prepared to save
     * all of the state of each view yourself.
     * <p/>
     * <p>If called, this method will occur before {@link #onStop}.  There are
     * no guarantees about whether it will occur before or after {@link #onPause}.
     *
     * @param outState Bundle in which to place your saved state.
     * @see #onCreate
     * @see #onRestoreInstanceState
     * @see #onPause
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Called as part of the activity lifecycle when an activity is going into
     * the background, but has not (yet) been killed.  The counterpart to
     * {@link #onResume}.
     * <p/>
     * <p>When activity B is launched in front of activity A, this callback will
     * be invoked on A.  B will not be created until A's {@link #onPause} returns,
     * so be sure to not do anything lengthy here.
     * <p/>
     * <p>This callback is mostly used for saving any persistent state the
     * activity is editing, to present a "edit in place" model to the user and
     * making sure nothing is lost if there are not enough resources to start
     * the new activity without first killing this one.  This is also a good
     * place to do things like stop animations and other things that consume a
     * noticeable amount of CPU in order to make the switch to the next activity
     * as fast as possible, or to close resources that are exclusive access
     * such as the camera.
     * <p/>
     * <p>In situations where the system needs more memory it may kill paused
     * processes to reclaim resources.  Because of this, you should be sure
     * that all of your state is saved by the time you return from
     * this function.  In general {@link #onSaveInstanceState} is used to save
     * per-instance state in the activity and this method is used to store
     * global persistent data (in content providers, files, etc.)
     * <p/>
     * <p>After receiving this call you will usually receive a following call
     * to {@link #onStop} (after the next activity has been resumed and
     * displayed), however in some cases there will be a direct call back to
     * {@link #onResume} without going through the stopped state.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onStop
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * This is called for activities that set launchMode to "singleTop" in
     * their package, or if a client used the {@link android.content.Intent#FLAG_ACTIVITY_SINGLE_TOP}
     * flag when calling {@link #startActivity}.  In either case, when the
     * activity is re-launched while at the top of the activity stack instead
     * of a new instance of the activity being started, onNewIntent() will be
     * called on the existing instance with the Intent that was used to
     * re-launch it.
     * <p/>
     * <p>An activity will always be paused before receiving a new intent, so
     * you can count on {@link #onResume} being called after this method.
     * <p/>
     * <p>Note that {@link #getIntent} still returns the original Intent.  You
     * can use {@link #setIntent} to update it to this new Intent.
     *
     * @param intent The new intent that was started for the activity.
     * @see #getIntent
     * @see #setIntent
     * @see #onResume
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
     * depending on later user activity.
     * <p/>
     * <p>Note that this method may never be called, in low memory situations
     * where the system does not have enough memory to keep your activity's
     * process running after its {@link #onPause} method is called.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestart
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onDestroy
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Perform any final cleanup before an activity is destroyed.  This can
     * happen either because the activity is finishing (someone called
     * {@link #finish} on it, or because the system is temporarily destroying
     * this instance of the activity to save space.  You can distinguish
     * between these two scenarios with the {@link #isFinishing} method.
     * <p/>
     * <p><em>Note: do not count on this method being called as a place for
     * saving data! For example, if an activity is editing data in a content
     * provider, those edits should be committed in either {@link #onPause} or
     * {@link #onSaveInstanceState}, not here.</em> This method is usually implemented to
     * free resources like threads that are associated with an activity, so
     * that a destroyed activity does not leave such things around while the
     * rest of its application is still running.  There are situations where
     * the system will simply kill the activity's hosting process without
     * calling this method (or any others) in it, so it should not be used to
     * do things that are intended to remain around after the process goes
     * away.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onPause
     * @see #onStop
     * @see #finish
     * @see #isFinishing
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called by the system when the device configuration changes while your
     * activity is running.  Note that this will <em>only</em> be called if
     * you have selected configurations you would like to handle with the
     * {@link android.R.attr#configChanges} attribute in your manifest.  If
     * any configuration change occurs that is not selected to be reported
     * by that attribute, then instead of reporting it the system will stop
     * and restart the activity (to have it launched with the new
     * configuration).
     * <p/>
     * <p>At the time that this function has been called, your Resources
     * object will have been updated to return resource values matching the
     * new configuration.
     *
     * @param newConfig The new device configuration.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context contxt;
        Integer[] images = {
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher
        };

        public ImageAdapter(Context c) {
            contxt = c;
        }

        public int getCount() {
            return images.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View view, ViewGroup
                parent) {
            if (view == null) {
                view = mInflater.inflate(R.layout.item, parent, false);
//                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            ((ImageView) view).setImageResource(images[position]);
            return view;
        }
    }
}
