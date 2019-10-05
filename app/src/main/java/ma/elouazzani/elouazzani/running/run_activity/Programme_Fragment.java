package ma.elouazzani.elouazzani.running.run_activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ma.elouazzani.elouazzani.running.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Programme_Fragment extends Fragment {
List<ProgrammeData> data;

int[] titles=new int[]
        {      R.string.title1,
               R.string.title2,
               R.string.title3,
               R.string.title4
        };
 int[] texts=new int[]
         {
                R.string.description1,
                R.string.description2,
                R.string.description3,
                R.string.description4
         };

    int[] urls=new int[]
            {
                    R.drawable.runing1,
                    R.drawable.runing2,
                    R.drawable.runing3,
                    R.drawable.runing4
            };


    private void addData()
    {
        data=new ArrayList<>();
        int size=urls.length;
        for(int i=0;i<size;i++)
        data.add(new ProgrammeData(urls[i],getResources().getString(titles[i]).toString(),
                getResources().getString(texts[i]).toString()));
    }


    public Programme_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_programme_, container, false);
        addData();
        Programme_Adapter adapter=new Programme_Adapter(getContext(),data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }


}
