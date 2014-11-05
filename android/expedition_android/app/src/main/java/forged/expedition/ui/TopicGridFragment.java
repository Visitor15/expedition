package forged.expedition.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.R;
import forged.expedition.controllers.DataCallback;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.topics.Topic;
import forged.expedition.topics.VideoTopic;

public class TopicGridFragment extends Fragment {

    List<Topic> topicList = new ArrayList<Topic>();

    TopicFragment.GridAdapter gridAdapter;

    GridView gridView;

    KhanAcademyController khanController;

    private View rootView;

    private ViewPager mPager;

    public TopicGridFragment() {
    }

    public TopicGridFragment(KhanAcademyController khanController, ViewPager mPager) {
        this.khanController = khanController;
        this.mPager = mPager;
    }

    public TopicGridFragment(List<Topic> topicList, KhanAcademyController khanController, ViewPager mPager) {
        this.topicList = topicList;
        this.khanController = khanController;
        this.mPager = mPager;
    }

    public TopicGridFragment(Topic topic, KhanAcademyController khanController, ViewPager mPager) {
        this.khanController = khanController;
        this.mPager = mPager;

        getTopicsForTopic(topic);
    }

    public void addTopic(Topic t) {
        topicList.add(t);
    }

    public void addTopicList(List<Topic> list) {
        topicList.addAll(list);
    }

    private void getTopicsForTopic(Topic t) {
        khanController.getTopicsByTopicName(t.getTopicId(), new DataCallback() {
            @Override
            public void receiveResults(List results) {
                topicList = results;
                initGridView((GridView) rootView.findViewById(R.id.gridView), mPager);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.grid_fragment, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);

        initGridView(gridView, mPager);

        return rootView;
    }

    private void initGridView(final GridView gridView, final ViewPager mPager) {
        gridAdapter = new TopicFragment.GridAdapter(topicList);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Topic currentTopic = (Topic) gridView.getAdapter().getItem(position);

                if (currentTopic.isVideoTopic()) {
                    khanController.getVideoTopicsForTopic(currentTopic.getTopicId(), new DataCallback<VideoTopic>() {
                        @Override
                        public void receiveResults(List<VideoTopic> results) {
                            getActivity().findViewById(R.id.banner_fragment).setVisibility(View.VISIBLE);

                            Fragment frag = getChildFragmentManager().findFragmentById(R.id.banner_fragment);
                            if (frag != null) {
                                ((YouTubePlayer) frag).setCurrentTopic(results.get(0));
                                ((YouTubePlayer) frag).refreshYouTubePlayer();
                            } else {
                                YouTubePlayer youTubeFragment = new YouTubePlayer();
                                youTubeFragment.setCurrentTopic(results.get(0));
                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.banner_fragment, youTubeFragment); // f1_container is your FrameLayout container
                                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                ft.addToBackStack(null);
                                ft.commit();
                            }
                        }
                    });
                } else {
                    khanController.getTopicsByTopicName(currentTopic.getTopicId(), new DataCallback() {
                        @Override
                        public void receiveResults(List results) {
                            if (results.size() > 0) {
                                ((GenericViewPagerAdapter) mPager.getAdapter()).addFragment(new TopicGridFragment(results, khanController, mPager));
                                mPager.getAdapter().notifyDataSetChanged();
                                mPager.setCurrentItem(mPager.getAdapter().getCount() - 1, true);
                            }
                        }
                    });
                }
            }
        });

        gridAdapter.notifyDataSetChanged();
    }
}