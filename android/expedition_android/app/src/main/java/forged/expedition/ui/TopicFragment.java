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

import java.util.Arrays;
import java.util.List;

import forged.expedition.Expedition;
import forged.expedition.R;
import forged.expedition.controllers.DataCallback;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
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

        initGridView();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        gridAdapter = new GridAdapter(Arrays.asList(getResources().getStringArray(R.array.default_topics)));
        topicGrid.setAdapter(gridAdapter);
        topicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                khanController.getTopicsByTopicName(((GridAdapter) topicGrid.getAdapter()).getItem(position).toString(), new DataCallback() {
                    @Override
                    public void receiveResults(List results) {
                        Toast.makeText(Expedition.getReference(), "Got " + ((GridAdapter) topicGrid.getAdapter()).getItem(position) + " topic: " + results.size(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        gridAdapter.notifyDataSetChanged();
    }

    private class GridAdapter extends GenericListAdapter<String> {

        public GridAdapter(List<String> dataList) {
            super(dataList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.topic_item_card, null);
            }

            ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position));

            return convertView;
        }
    }
}
