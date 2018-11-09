package edu.gatech.cs2340.donationtracker.Model;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import edu.gatech.cs2340.donationtracker.R;
public class SearchAdapterLocation extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private final List<Location> locationList;
    private final List<Location> arraylist;

    public SearchAdapterLocation(Context context, List<Location> namesList) {
        mContext = context;
        this.locationList = namesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Location>();
        this.arraylist.addAll(namesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Location getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(locationList.get(position).getLocationName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        locationList.clear();
        if (charText.isEmpty()) {
            locationList.addAll(arraylist);
        } else {
            for (Location wp : arraylist) {
                if (wp.getLocationName().contains(charText)) {
                    locationList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

