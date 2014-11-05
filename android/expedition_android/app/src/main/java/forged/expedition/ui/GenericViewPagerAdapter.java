package forged.expedition.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nchampagne on 11/2/14.
 */
public class GenericViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();

    public GenericViewPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment, boolean setCurrent) {
        fragmentList.add(fragment);
        notifyDataSetChanged();
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void resetFragmentList() {
        fragmentList = new ArrayList<Fragment>();
        notifyDataSetChanged();
    }

    public void resetFramentList(Fragment fragment) {
        resetFragmentList();
        addFragment(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public abstract class ScreenSlidePageFragment extends Fragment {
        public ScreenSlidePageFragment() {}

        @Override
        public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    }
}
