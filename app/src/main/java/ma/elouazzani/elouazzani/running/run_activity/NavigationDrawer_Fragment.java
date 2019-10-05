package ma.elouazzani.elouazzani.running.run_activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ma.elouazzani.elouazzani.running.Database.DataBase;
import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.history.History;
import ma.elouazzani.elouazzani.running.history.ListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_Fragment extends Fragment {

RelativeLayout history;
    public NavigationDrawer_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_navigation_drawer_, container, false);
        history=(RelativeLayout)view.findViewById(R.id.history_layout);
        onClik_History(history);
        ListView listView=(ListView)view.findViewById(R.id.list_Nv);
        DataBase dataBase=new DataBase(getContext());
        ListAdapter listAdapter=new ListAdapter(getContext(),dataBase.getAllData());
        listView.setAdapter(listAdapter);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        //
        if(listAdapter.isEmpty()){ textView.setVisibility(View.VISIBLE);}
        listAdapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(),History.class));
               getActivity(). overridePendingTransition(R.animator.slide_left, R.animator.no_change);
            }
        });
        return view;
    }

 private void onClik_History(View v){
     v.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(getActivity(),History.class));
             getActivity(). overridePendingTransition(R.animator.slide_left, R.animator.no_change);
         }
     });
 }


}
