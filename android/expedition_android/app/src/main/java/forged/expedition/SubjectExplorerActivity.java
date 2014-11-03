package forged.expedition;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridView;

import forged.expedition.controllers.data_controllers.KhanAcademyController;
import forged.expedition.ui.BannerFragment;
import forged.expedition.ui.TopicFragment;

/**
 * Created by visitor15 on 10/19/14.
 */
public class SubjectExplorerActivity extends FragmentActivity {

    private KhanAcademyController khanController;

    private BannerFragment bannerFragment;

    private GridView topicGrid;

    public SubjectExplorerActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_explorer);

//        topicGrid = (GridView) findViewById(R.id.gridView);
//
//        topicGrid.setAdapter(new GenericListAdapter<Topic>() {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if(convertView == null) {
//                    convertView = getLayoutInflater().inflate(R.layout.subject_item, null);
//                }
//
//                ((TextView) convertView.findViewById(R.id.textView_title)).setText(getItemList().get(position).getDisplayName());
//                ((CircularImageView) convertView.findViewById(R.id.circularImageView_icon)).setColor(getItemList().get(position).getDefaultColorId());
//
//                return convertView;
//            }
//        });

        khanController = new KhanAcademyController();

//        bannerFragment = ((BannerFragment) getFragmentManager().findFragmentById(R.id.fragment));

//        bannerFragment.getView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                khanController.getAllMathTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllScienceTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllArtsAndHumanitiesTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllComputingTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllTestPrepTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllPartnerContentTopics(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//
//                khanController.getAllTalksAndInterviews(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });

//                khanController.getAll(new DataCallback() {
//
//                    @Override
//                    public void receiveResults(final List results) {
//                        System.out.println("GOT FINAL RESULTS: " + results.size());
////                        ((GenericListAdapter) topicGrid.getAdapter()).addToList(results);
//                    }
//                });
//            }
//        });
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

        TopicFragment topicFragment = new TopicFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.topic_fragment, topicFragment); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.addToBackStack(null);
        ft.commit();
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
    }
}
