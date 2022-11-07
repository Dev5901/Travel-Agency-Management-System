package com.example.toursandtravelfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public  class TourNamesArrayAdapter extends ArrayAdapter{

    public TourNamesArrayAdapter(Context context, List<Tourlistclass> resource) {
        super(context, 0, resource);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent)
    {

        View listItemView=convertview;
        if (listItemView == null){
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.tourname_list_adapter,parent,false);
        }
        Tourlistclass fc= (Tourlistclass) getItem(position);
        Log.d("TAG", "getView: "+position);

        TextView textView=listItemView.findViewById(R.id.TournameListAdapter);

        Log.d("FoodNamesadapter", fc.location);
        if(fc.location.equalsIgnoreCase("place")){}
        else
        textView.setText(fc.location);

        return listItemView;

    }
}