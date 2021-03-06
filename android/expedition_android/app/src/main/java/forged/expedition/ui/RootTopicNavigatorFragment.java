package forged.expedition.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.R;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.events.TopicSelectedEvent;
import forged.expedition.topics.Topic;
import forged.expedition.topics.TopicKeys;
import forged.expedition.util.Utils;

/**
 * Created by nchampagne on 11/4/14.
 */
public class RootTopicNavigatorFragment extends Fragment {

    private View rootView;

    private GridView gridView;

    private GridAdapter gridAdapter;

    private KhanAcademyController khanController;

    public RootTopicNavigatorFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.root_topic_fragment, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);

        khanController = new KhanAcademyController();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        initGridView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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

    private void initGridView() {
        if(gridAdapter == null) {
            List<Topic> rootTopicList = new ArrayList<Topic>();
            List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.default_topics));

            for (String s : stringList) {
                String[] splitStr = s.split("#");

                try {
                    Topic t = (Topic) Class.forName(splitStr[1]).newInstance();
                    t.setDisplayTitle(splitStr[0]);
                    rootTopicList.add(t);
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            gridAdapter = new GridAdapter(rootTopicList);
        }

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Topic currentTopic = (Topic) gridView.getAdapter().getItem(position);
                Bundle b = new Bundle();
                b.putSerializable(TopicKeys.TOPIC_KEY, currentTopic);
                TopicSelectedEvent.broadcast(Expedition.getReference(), b);

            }
        });

        gridAdapter.notifyDataSetChanged();
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
                convertView = mInflater.inflate(R.layout.root_topic_item, parent, false);
            }

            ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position).toString());
            ((CircularImageView) convertView.findViewById(R.id.circularImageView_icon)).setColor(((Topic) getItemList().get(position)).getDefaultColorId());


            return convertView;
        }
    }
}
