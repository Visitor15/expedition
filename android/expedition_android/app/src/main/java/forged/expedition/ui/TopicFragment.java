package forged.expedition.ui;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.R;
import forged.expedition.controllers.DataCallback;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.topics.GenericTopic;
import forged.expedition.topics.Topic;

/**
 * Created by nchampagne on 10/28/14.
 */
public class TopicFragment extends Fragment {

    private GridView topicGrid;

    private View rootView;

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

        rootView = inflater.inflate(R.layout.topic_fragment, container);

        topicGrid = (GridView) rootView.findViewById(R.id.gridView);

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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initGridView() {
        if(gridAdapter == null) {

            List<GenericTopic> rootTopicList = new ArrayList<GenericTopic>();
            List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.default_topics));

            for(String s : stringList) {
                String[] splitStr = s.split("#");
                GenericTopic genTopic = new GenericTopic(splitStr[1]);
                genTopic.setDisplayTitle(splitStr[0]);
                rootTopicList.add(genTopic);
            }

            gridAdapter = new GridAdapter(rootTopicList);
            topicGrid.setAdapter(gridAdapter);
            topicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    khanController.getTopicsByTopicName(((GenericTopic) topicGrid.getAdapter().getItem(position)).getTopicId(), new DataCallback() {
                        @Override
                        public void receiveResults(List results) {
                            Toast.makeText(Expedition.getReference(), "Got " + ((GenericTopic)((GridAdapter) topicGrid.getAdapter()).getItem(position)).getDisplayName() + " topic: " + results.size(), Toast.LENGTH_LONG).show();
                            gridAdapter.setItemList(results);
                            gridAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    public class GridAdapter extends GenericListAdapter {

        public GridAdapter(List dataList) {
            super(dataList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.topic_item_card, parent, false);
            }

            ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position).toString());
            ((CircularImageView) convertView.findViewById(R.id.circularImageView_icon)).setColor(((GenericTopic) getItemList().get(position)).getDefaultColorId());

            return convertView;
        }
    }
}
