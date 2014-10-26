package forged.expedition;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import forged.expedition.controllers.DataCallback;
import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.topics.Topic;
import forged.expedition.ui.BannerFragment;
import forged.expedition.ui.GenericListAdapter;

/**
 * Created by visitor15 on 10/19/14.
 */
public class SubjectExplorerActivity extends Activity {

    private KhanAcademyController khanController;

    private BannerFragment bannerFragment;

    private GridView topicGrid;

    public SubjectExplorerActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_explorer);

        topicGrid = (GridView) findViewById(R.id.gridView);

        topicGrid.setAdapter(new GenericListAdapter<Topic>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.subject_item, null);
                }

                ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position).getDisplayName());

                return convertView;
            }
        });

        khanController = new KhanAcademyController();

        bannerFragment = ((BannerFragment) getFragmentManager().findFragmentById(R.id.fragment));

        bannerFragment.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khanController.getAllMathTopics(new DataCallback() {

                    @Override
                    public void receiveResults(final List results) {
                        ((GenericListAdapter) topicGrid.getAdapter()).setItemList(results);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public class SubjectListExpandableAdapter implements ExpandableListAdapter {

        private final LayoutInflater mInflater;
        List<Topic> topicList;


        public SubjectListExpandableAdapter(List<Topic> list) {
            mInflater = LayoutInflater.from(Expedition.getReference());
            topicList = list;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return topicList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        @Override
        public Topic getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Topic getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.subject_item, null);
            }

            ((TextView) convertView.findViewById(R.id.textView_title)).setText(topicList.get(groupPosition).getDisplayName());

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }
}
