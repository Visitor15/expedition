package forged.expedition;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import forged.expedition.controllers.DataCallback;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.controllers.data_controllers.RequestController;
import forged.expedition.networking.NetworkServiceConnection;
import forged.expedition.topics.MathTopic;
import forged.expedition.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    private NetworkServiceConnection mNetworkServiceConnection;

    private boolean mServiceActive;

    private KhanAcademyController khanController;

    private RequestController requestController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnClickListener(clickListener);

        khanController = new KhanAcademyController();
        requestController = new RequestController();
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


//        List<MathTopic> mathTopics = khanController.getAllMathTopics();

//        List<Topic> topics = khanController.getAllTopics();

//        System.out.println("SIZE: " + topics.size());

//        if(mNetworkServiceConnection == null) {
//            mNetworkServiceConnection = new NetworkServiceConnection(MainActivity.this);
//        }
//
//        boolean success = this.bindService(new Intent(MainActivity.this,
//                NetworkService.class),
//                mNetworkServiceConnection,
//                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    public void serviceConnected() {
//        mNetworkServiceConnection.sendRequest();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            khanController.getAllMathTopics(new DataCallback<MathTopic>() {

                @Override
                public void receiveResults(List<MathTopic> results) {
                    System.out.println("GOT MATH RESULT:" + results);
                }
            });

        }
    };



    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }

            khanController.getAllMathTopics(new DataCallback<MathTopic>() {

                @Override
                public void receiveResults(List<MathTopic> results) {
                    System.out.println("GOT MATH RESULT:" + results);
                }
            });

//            khanController.getAllComputingTopics(new DataCallback<ComputingTopic>() {
//
//                @Override
//                public void receiveResults(List<ComputingTopic> results) {
//                    System.out.println("GOT COMPUTING RESULT:" + results);
//                }
//            });
//
//            khanController.getAllTalksAndInterviews(new DataCallback<TalksAndInterviewsTopic>() {
//
//                @Override
//                public void receiveResults(List<TalksAndInterviewsTopic> results) {
//                    System.out.println("GOT TalksAndInterviewsTopic RESULT:" + results);
//                }
//            });
//
//            khanController.getAllScienceTopics(new DataCallback<ScienceTopic>() {
//
//                @Override
//                public void receiveResults(List<ScienceTopic> results) {
//                    System.out.println("GOT ScienceTopic RESULT:" + results);
//                }
//            });
//
//            khanController.getAllArtsAndHumanitiesTopics(new DataCallback<ArtsAndHumanitiesTopic>() {
//
//                @Override
//                public void receiveResults(List<ArtsAndHumanitiesTopic> results) {
//                    System.out.println("GOT ArtsAndHumanitiesTopic RESULT:" + results);
//                }
//            });
//
//            khanController.getAllPartnerContentTopics(new DataCallback<PartnerContentTopic>() {
//
//                @Override
//                public void receiveResults(List<PartnerContentTopic> results) {
//                    System.out.println("GOT PartnerContentTopic RESULT:" + results);
//                }
//            });
//
//            khanController.getAllEconomicsAndFinanceTopics(new DataCallback<EconomicsAndFinanceTopic>() {
//
//                @Override
//                public void receiveResults(List<EconomicsAndFinanceTopic> results) {
//                    System.out.println("GOT EconomicsAndFinanceTopic RESULT:" + results);
//                }
//            });
//
//            khanController.getAllTestPrepTopics(new DataCallback<TestPrepTopic>() {
//
//                @Override
//                public void receiveResults(List<TestPrepTopic> results) {
//                    System.out.println("GOT TestPrepTopic RESULT:" + results);
//                }
//            });

//            Intent intent = new Intent(MainActivity.this, SubjectExplorerActivity.class);
//            startActivity(intent);

            return true;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
