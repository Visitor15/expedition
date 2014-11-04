package forged.expedition;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import forged.expedition.ui.RootTopicNavigatorFragment;
import forged.expedition.ui.TopicFragment;
import forged.expedition.ui.YouTubePlayer;

/**
 * Created by nchampagne on 11/4/14.
 */
public class TopicExplorerActivity extends FragmentActivity {

    private TopicFragment topicFragment;

    private YouTubePlayer youTubePlayerFragment;

    private RootTopicNavigatorFragment rootTopicNavigatorFragment;

    @Override
    protected void onResume() {
        super.onResume();
        if(getSupportFragmentManager().findFragmentById(R.id.top_fragment) == null) {
            rootTopicNavigatorFragment = new RootTopicNavigatorFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.top_fragment, rootTopicNavigatorFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }
        if(getSupportFragmentManager().findFragmentById(R.id.bottom_fragment) == null) {
            topicFragment = new TopicFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.bottom_fragment, topicFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    @Override
//    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        return super.onCreateView(name, context, attrs);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_explorer_activity);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
