package ma.elouazzani.elouazzani.running.run_activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.programmes.Programme_activity;
import ma.elouazzani.elouazzani.running.programmes.Programme_activity_2;
import ma.elouazzani.elouazzani.running.programmes.Programme_activity_3;
import ma.elouazzani.elouazzani.running.programmes.Programme_activity_4;

/**
 * Created by elouazzani on 29/11/2016.
 */
public class Programme_Adapter extends RecyclerView.Adapter<Programme_Adapter.ViewHolder> {
    private Context context;
    private List<ProgrammeData> data;
    private int index;

    public Programme_Adapter(Context context,List<ProgrammeData> data)
    {
        this.context=context;
        this.data=data;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title,text,details;
        public ViewHolder(View itemView) {
            super(itemView);

            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
            title=(TextView)itemView.findViewById(R.id.title);
            text=(TextView)itemView.findViewById(R.id.description);
            details=(TextView)itemView.findViewById(R.id.details);


        }
    }
    @Override
    public Programme_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Programme_Adapter.ViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.text.setText(data.get(position).getText());
        Glide.with(context).load(data.get(position).getUrl()).into(holder.thumbnail);
        holder.details.setOnClickListener(new View.OnClickListener() {
            Intent[] intents=new Intent[]
                    {
                            new Intent(context,Programme_activity.class),
                            new Intent(context,Programme_activity_2.class),
                            new Intent(context,Programme_activity_3.class),
                            new Intent(context,Programme_activity_4.class)
                    };
            @Override
            public void onClick(View v) {
                context.startActivity(intents[position]);
            }
        });

    }




    @Override
    public int getItemCount() {
        return data.size();
    }


}
