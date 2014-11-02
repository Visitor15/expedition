package forged.expedition.ui;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerFragment;

import forged.expedition.R;
import forged.expedition.topics.Topic;
import forged.expedition.util.Utils;

/**
 * Created by nchampagne on 10/28/14.
 */
public class YouTubePlayer extends Fragment{

    Topic topic;

    View rootView;

    YouTubePlayerFragment youTubePlayerFragment;

    public com.google.android.youtube.player.YouTubePlayer.Provider provider;
    public com.google.android.youtube.player.YouTubePlayer youTubePlayer;

    public YouTubePlayer() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        initialize("", new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener() {
//
//
//            @Override
//            public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean b) {
//                YouTubePlayer.this.provider = provider;
//                YouTubePlayer.this.youTubePlayer = youTubePlayer;
//            }
//
//            @Override
//            public void onInitializationFailure(com.google.android.youtube.player.YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.youtube_fragment, container, false);

        return rootView;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//    }

    @Override
    public void onResume() {
        super.onResume();

//        youTubePlayer.cueVideo(topic.getYouTubeId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        switch(getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE: {
                rootView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                break;
            }
            case Configuration.ORIENTATION_PORTRAIT: {
                rootView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, Utils.dipsToPixels(getActivity(), 192)));
                break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void addTopic(Topic t) {
        this.topic = t;
    }
}
