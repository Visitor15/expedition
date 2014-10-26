package forged.expedition.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import forged.expedition.Expedition;

/**
 * Created by visitor15 on 10/19/14.
 */
public abstract class GenericListAdapter<T> extends BaseAdapter {

    List<T> itemList;

    private LayoutInflater mInflater;

    public GenericListAdapter() {
        createLayoutInflater();
    }

    public GenericListAdapter(List<T> list) {
        this.itemList = list;
        createLayoutInflater();
    }

    private void createLayoutInflater() {
        mInflater = LayoutInflater.from(Expedition.getReference());
    }

    public LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void addToList(T item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addToList(List<T> items) {
        this.itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItemList(List<T> items) {
        if(itemList != null) {
            itemList.clear();;
        }
        else {
            itemList = new ArrayList<T>();
        }
        this.itemList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (itemList != null) ? itemList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
