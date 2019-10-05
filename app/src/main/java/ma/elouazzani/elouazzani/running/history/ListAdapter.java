package ma.elouazzani.elouazzani.running.history;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ma.elouazzani.elouazzani.running.Database.Data;
import ma.elouazzani.elouazzani.running.R;

/**
 * Created by elouazzani on 09/12/2016.
 */
public class ListAdapter extends ArrayAdapter<Data> {
  private   List<Data> dataList;
  private Context context;

    public ListAdapter(Context context, List<Data> dataList) {
        super(context, 0,dataList);
        this.dataList=dataList;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.custom_listview,parent,false);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"Arial Black.ttf");
        TextView titel=(TextView)convertView.findViewById(R.id.titel);
        titel.setTypeface(typeface);
        titel.setText("my run "+(position+1));
        TextView dist=(TextView)convertView.findViewById(R.id.dist);
        dist.setText(""+String.format("%.02f",dataList.get(position).getDistance())+" km");
        TextView time=(TextView)convertView.findViewById(R.id.time);
        time.setText(dataList.get(position).getTime());
        ImageView circleImageView=(ImageView)convertView.findViewById(R.id.circle_image);
        Glide.with(context).load(R.drawable.logo_).into(circleImageView);
        return convertView;
    }
}
