package com.androidavanzado.prueba;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private Context ctx;

    public MyNotaRecyclerViewAdapter(List<Note> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titleLabel.setText(holder.mItem.getTitle());
        holder.contentLabel.setText(holder.mItem.getContent());

        if(holder.mItem.isFavorite()) {
            holder.favoriteImg.setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleLabel;
        public final TextView contentLabel;
        public final ImageView favoriteImg;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleLabel = view.findViewById(R.id.textViewTitulo);
            contentLabel = view.findViewById(R.id.textViewContenido);
            favoriteImg = view.findViewById(R.id.imageViewFavorita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleLabel.getText() + "'";
        }
    }
}
