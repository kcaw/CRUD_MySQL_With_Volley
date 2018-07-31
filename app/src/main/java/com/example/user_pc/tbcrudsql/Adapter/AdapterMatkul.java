package com.example.user_pc.tbcrudsql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.tbcrudsql.Matakuliah.InsertMatkul;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;

import java.util.List;

public class AdapterMatkul extends RecyclerView.Adapter<AdapterMatkul.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterMatkul (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_matkul,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvmatkul.setText(md.getMatkul());
        holder.tvid_matkul.setText(md.getId_matkul());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvmatkul,tvid_matkul;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvmatkul = (TextView) view.findViewById(R.id.matkul);
            tvid_matkul = (TextView) view.findViewById(R.id.id_matkul);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InsertMatkul.class);
                    update.putExtra("update",1);
                    update.putExtra("id_matkul", md.getId_matkul());
                    update.putExtra("matkul", md.getMatkul());
                    update.putExtra("prodi_matkul", md.getProdi_matkul());
                    update.putExtra("dosen_matkul", md.getDosen_matkul());

                    context.startActivity(update);
                }
            });
        }
    }
}
