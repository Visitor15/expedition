package forged.expedition.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerFragment;

import forged.expedition.R;
import forged.expedition.topics.Topic;

/**
 * Created by nchampagne on 10/28/14.
 */
public class YouTubePlayer extends Fragment {

    Topic topic;

    View rootView;

    YouTubePlayerFragment youTubePlayerFragment;

    public com.google.android.youtube.player.YouTubePlayer.Provider provider;
    public com.google.android.youtube.player.YouTubePlayer youTubePlayer;

    public YouTubePlayer() {

    }

    public void setCurrentTopic(Topic t) {
        this.topic = t;
    }

    public void refreshYouTubePlayer() {
        initYouTubePlayer();
    }

    private void initYouTubePlayer() {
        youTubePlayerFragment = (YouTubePlayerFragment) getActivity().getFragmentManager().findFragmentById(R.id.youtube_fragment);

        youTubePlayerFragment.initialize("AIzaSyBbIP4truKygm4nSWphhKEftv69glq3FDY", new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener() {


            @Override
            public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean wasRestored) {
                YouTubePlayer.this.provider = provider;
                YouTubePlayer.this.youTubePlayer = youTubePlayer;

                if (!wasRestored) {
                    youTubePlayer.cueVideo(topic.getYouTubeId());
                }
            }

            @Override
            public void onInitializationFailure(com.google.android.youtube.player.YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youTubeInitializationResult.getErrorDialog(getActivity(), 0);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.youtube_fragment, container, false);

//        switch(getResources().getConfiguration().orientation) {
//            case Configuration.ORIENTATION_LANDSCAPE: {
//                rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
//                break;
//            }
//            case Configuration.ORIENTATION_PORTRAIT: {
//                rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.2f));
//                break;
//            }
//        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        initYouTubePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        switch(getResources().getConfiguration().orientation) {
//            case Configuration.ORIENTATION_LANDSCAPE: {
//                rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
//                break;
//            }
//            case Configuration.ORIENTATION_PORTRAIT: {
//                rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.3f));
//                break;
//            }
//        }
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
        YouTubePlayerFragment f = (YouTubePlayerFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.youtube_fragment);
        if (f != null)
            getActivity().getFragmentManager().beginTransaction().remove(f).commit();

        getActivity().findViewById(R.id.banner_fragment).setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
