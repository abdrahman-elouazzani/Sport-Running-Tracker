package ma.elouazzani.elouazzani.running.history;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ma.elouazzani.elouazzani.running.Database.Data;
import ma.elouazzani.elouazzani.running.Database.DataBase;
import ma.elouazzani.elouazzani.running.R;

/**
 * Created by elouazzani on 10/12/2016.
 */
public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {
    private List<Data> datas;
    private Context context;
    //
    public Recycler_Adapter(Context context,List<Data> datas){
        this.datas=datas;
        this.context=context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,distance,time;
        ImageView trajet,setting;

        public ViewHolder(View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date);
            distance=(TextView)itemView.findViewById(R.id.distance);
            time=(TextView)itemView.findViewById(R.id.time);
            trajet=(ImageView)itemView.findViewById(R.id.trajet);
            setting=(ImageView)itemView.findViewById(R.id.setting);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_history_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"AlteHaasGroteskBold.ttf");
        //
        holder.date.setTypeface(typeface);
        holder.date.setText(datas.get(position).getDate());
        holder.distance.setTypeface(typeface);
        holder.distance.setText("" + String.format("%.02f", datas.get(position).getDistance()) + " /km");
        holder.time.setTypeface(typeface);
        holder.time.setText(datas.get(position).getTime());

        holder.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("are you sure you want to delete?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBase dataBase = new DataBase(context);
                        dataBase.deleData(datas.get(position));
                        removeData(datas.get(position));
                        Toast.makeText(context, "Item" + (position + 1) + " removed", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

    }

    private void removeData(Data data) {
        int position = datas.lastIndexOf(data);
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


}
