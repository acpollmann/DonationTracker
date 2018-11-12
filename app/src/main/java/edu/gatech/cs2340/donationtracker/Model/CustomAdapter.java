package edu.gatech.cs2340.donationtracker.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Adapter that implements expandable list view for view location
 * @author Group 71B
 * @version 1.0
 */
public class CustomAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<GroupInfo> filterTypeList;

    /**
     * creating the adapter
     * @param context what based on and what goes to
     * @param filterTypeList what based on and what goes to
     */
    public CustomAdapter(Context context, List<GroupInfo> filterTypeList) {
        this.context = context;
        this.filterTypeList = filterTypeList;
    }

    /**
     * Gets the child information on the view location activity
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ChildInfo> productList = filterTypeList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    /**
     * Gets the child information id on the view location activity
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Gets the child information view on the view location activity
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildInfo detailInfo = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater detailInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert detailInflater != null;
            view = detailInflater.inflate(R.layout.locationsearch_child, null);
        }

        TextView childItem = view.findViewById(R.id.childItem);
        childItem.setText(detailInfo.getName().trim());

        return view;
    }

    /**
     * Gets the child information count on the view location activity
     */
    @Override
    public int getChildrenCount(int groupPosition) {

        List<ChildInfo> productList = filterTypeList.get(groupPosition).getProductList();
        return productList.size();

    }

    /**
     * Gets the group information on the view location activity
     */
    @Override
    public Object getGroup(int groupPosition) {
        return filterTypeList.get(groupPosition);
    }

    /**
     * Gets the group count information on the view location activity
     */
    @Override
    public int getGroupCount() {
        return filterTypeList.size();
    }

    /**
     * Gets the group id information on the view location activity
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets the group view information on the view location activity
     */
    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        GroupInfo headerInfo = (GroupInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inf != null;
            view = inf.inflate(R.layout.locationsearch_subject, null);
        }

        TextView heading = view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());

        return view;
    }

    /**
     * Check if there is a stable id
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Check if child is selectable
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
