package forged.expedition.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.R;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.topics.GenericTopic;
import forged.expedition.topics.Topic;
import forged.expedition.util.Utils;

/**
 * Created by nchampagne on 10/28/14.
 */
public class TopicFragment extends Fragment {

//    private GridView topicGrid;

    private View rootView;

    private ViewPager mPager;

    private GenericViewPagerAdapter viewPagerAdapter;

    private GridAdapter gridAdapter;

    private KhanAcademyController khanController;

    public TopicFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        khanController = new KhanAcademyController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.topic_fragment, container, false);

        mPager = (ViewPager) rootView.findViewById(R.id.pager);

//        topicGrid = (GridView) rootView.findViewById(R.id.gridView);



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

//        initGridView();

        initViewPager();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        switch(getResources().getConfiguration().orientation) {
//            case Configuration.ORIENTATION_LANDSCAPE: {
//                topicGrid.setNumColumns(2);
//                break;
//            }
//            case Configuration.ORIENTATION_PORTRAIT: {
//                topicGrid.setNumColumns(1);
//                break;
//            }
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        this.getView().invalidate();
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

    private void initViewPager() {
        if(viewPagerAdapter == null) {
            viewPagerAdapter = new GenericViewPagerAdapter(getChildFragmentManager());
            mPager.setAdapter(viewPagerAdapter);
            createDefaultView();
        }
    }

    private void createDefaultView() {
        List<Topic> rootTopicList = new ArrayList<Topic>();
        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.default_topics));

        for (String s : stringList) {
            String[] splitStr = s.split("#");
            GenericTopic genTopic = new GenericTopic(splitStr[1]);
            genTopic.setDisplayTitle(splitStr[0]);
            rootTopicList.add(genTopic);
        }

        viewPagerAdapter.resetFramentList(new TopicGridFragment(rootTopicList, khanController, mPager));
    }

//    private void initGridView(GridView gridView) {
//        if(gridAdapter == null) {
//
//            List<GenericTopic> rootTopicList = new ArrayList<GenericTopic>();
//            List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.default_topics));
//
//            for (String s : stringList) {
//                String[] splitStr = s.split("#");
//                GenericTopic genTopic = new GenericTopic(splitStr[1]);
//                genTopic.setDisplayTitle(splitStr[0]);
//                rootTopicList.add(genTopic);
//            }
//
//            gridAdapter = new GridAdapter(rootTopicList);
//        }
//        gridView.setAdapter(gridAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                final Topic currentTopic = (Topic) gridView.getAdapter().getItem(position);
//
//                if(currentTopic.isVideoTopic()) {
//                    khanController.getVideoTopicsForTopic(currentTopic.getTopicId(), new DataCallback<VideoTopic>() {
//                        @Override
//                        public void receiveResults(List<VideoTopic> results) {
//                            getActivity().findViewById(R.id.banner_fragment).setVisibility(View.VISIBLE);
//                            YouTubePlayer youTubeFragment = new YouTubePlayer();
//                            youTubeFragment.addTopic(results.get(0));
//                            FragmentTransaction ft = getFragmentManager().beginTransaction();
//                            ft.replace(R.id.banner_fragment, youTubeFragment); // f1_container is your FrameLayout container
//                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                            ft.addToBackStack(null);
//                            ft.commit();
//                        }
//                    });
//                }
//                else {
//                    khanController.getTopicsByTopicName(currentTopic.getTopicId(), new DataCallback() {
//                        @Override
//                        public void receiveResults(List results) {
//                            if (results.size() > 0) {
//
//
//                                ((GenericViewPagerAdapter) mPager.getAdapter()).addFragment(gridFragment, true);
//
//                                mPager.setCurrentItem();
//
//                                try {
//                                    TopicFragment topicFragment = new TopicFragment();
//                                    topicFragment.addTopics(results);
//                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                                    ft.replace(R.id.topic_fragment, topicFragment); // f1_container is your FrameLayout container
//                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                                    ft.addToBackStack(null);
//                                    ft.commit();
//                                } catch (NullPointerException e) {
//
//                                }
//                            }
//                        }
//                    });
//                }
//            }
//        });
//
//        switch(getResources().getConfiguration().orientation) {
//            case Configuration.ORIENTATION_LANDSCAPE: {
//                gridView.setNumColumns(2);
//                break;
//            }
//            case Configuration.ORIENTATION_PORTRAIT: {
//                gridView.setNumColumns(1);
//                break;
//            }
//        }
//    }

    public void addTopics(List<Topic> topicList) {
        if(gridAdapter != null) {
            gridAdapter.addToList(topicList);
            gridAdapter.notifyDataSetChanged();
        }
        else {
            gridAdapter = new GridAdapter(topicList);
        }
    }

    public static class GridAdapter extends GenericListAdapter {

        int pixels = 0;

        public GridAdapter(List dataList) {
            super(dataList);


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            pixels = Utils.dipsToPixels(Expedition.getReference(), 4);

            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.subject_item, parent, false);
            }

            ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position).toString());
            ((CircularImageView) convertView.findViewById(R.id.circularImageView_icon)).setColor(((Topic) getItemList().get(position)).getDefaultColorId());


            return convertView;
        }
    }



}
