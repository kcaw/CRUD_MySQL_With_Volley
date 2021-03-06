package com.example.user_pc.tbcrudsql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.tbcrudsql.Dosen.InsertDosen;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;

import java.util.List;

public class AdapterDosen extends RecyclerView.Adapter<AdapterDosen.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterDosen (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_dosen,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama_dosen.setText(md.getNama_dosen());
        holder.tvnip.setText(md.getNip());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama_dosen,tvnip;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnama_dosen = (TextView) view.findViewById(R.id.nama_dosen);
            tvnip = (TextView) view.findViewById(R.id.nip);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InsertDosen.class);
                    update.putExtra("update",1);
                    update.putExtra("nip", md.getNip());
                    update.putExtra("nama_dosen", md.getNama_dosen());
                    update.putExtra("prodi_dosen", md.getProdi_dosen());
                    update.putExtra("matkul_dosen", md.getMatkul_dosen());

                    context.startActivity(update);
                }
            });
        }
    }
}
