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

public class BillNamesArrayAdapter extends ArrayAdapter{

    public BillNamesArrayAdapter(Context context, List<allbillclass> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent)
    {
        Log.d("inadapter","yes");

        View listItemView=convertview;
        if (listItemView == null){
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.billname_list_adapter,parent,false);
        }
        allbillclass fc= (allbillclass) getItem(position);
        Log.d("TAG", "getView: "+position);
        TextView name=listItemView.findViewById(R.id.name);
        TextView place=listItemView.findViewById(R.id.loc);
        TextView cost=listItemView.findViewById(R.id.costt);
        TextView phoneno=listItemView.findViewById(R.id.phno);
            name.setText(fc.name);
            place.setText(fc.place);
            cost.setText(fc.cost);
            phoneno.setText(fc.phoneno);

        Log.d("FoodNamesadapter", fc.name);

        return listItemView;

    }
}
