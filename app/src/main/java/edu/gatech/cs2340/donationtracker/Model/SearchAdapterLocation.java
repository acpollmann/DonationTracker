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
        this.arraylist = new ArrayList<>();
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

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        View view1 = view;
        final ViewHolder holder;
        if (view1 == null) {
            holder = new ViewHolder();
            view1 = inflater.inflate(R.layout.search_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = view1.findViewById(R.id.name);
            view1.setTag(holder);
        } else {
            holder = (ViewHolder) view1.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(locationList.get(position).getLocationName());
        return view1;
    }

    // Filter Class
    @SuppressWarnings("AssignmentToMethodParameter")
    public void filter(String charText) {
        String text = charText.toLowerCase(Locale.getDefault());
        locationList.clear();
        if (text.isEmpty()) {
            locationList.addAll(arraylist);
        } else {
            for (Location wp : arraylist) {
                if (wp.getLocationName().contains(text)) {
                    locationList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

