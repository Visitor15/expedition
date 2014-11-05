package forged.expedition.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import forged.expedition.events.LocalBroadcastReceiver;
import forged.expedition.events.TopicSelectedEvent;
import forged.expedition.topics.GenericTopic;
import forged.expedition.topics.Topic;
import forged.expedition.topics.TopicKeys;
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

    private TopicSelectedEventListener topicSelectedEventListener;

    public TopicFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        khanController = new KhanAcademyController();
        topicSelectedEventListener = new TopicSelectedEventListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.topic_fragment, container, false);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewPager();
        topicSelectedEventListener.register();
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
        topicSelectedEventListener.unregister();
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

        viewPagerAdapter.resetFramentList(new TopicGridFragment(khanController, mPager));
    }

    public void addTopics(List<Topic> topicList) {
        if(gridAdapter != null) {
            gridAdapter.addToList(topicList);
            gridAdapter.notifyDataSetChanged();
        }
        else {
            gridAdapter = new GridAdapter(topicList);
        }
    }

    private void onHandleTopicSelectedEvent(Bundle b) {
        if(!b.containsKey(TopicSelectedEvent.ACTION_TOPIC_SELECTED) && !b.containsKey(TopicKeys.TOPIC_KEY)) {
            return;
        }

        Topic t = (Topic) b.getSerializable(TopicKeys.TOPIC_KEY);

        if(t != null) {
            viewPagerAdapter.addFragment(new TopicGridFragment(t, khanController, mPager));
        }
    }

    public class TopicSelectedEventListener extends LocalBroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            onHandleTopicSelectedEvent(intent.getExtras());
        }

        @Override
        public IntentFilter createFilter() {
            return new IntentFilter(TopicSelectedEvent.ACTION_TOPIC_SELECTED);
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
