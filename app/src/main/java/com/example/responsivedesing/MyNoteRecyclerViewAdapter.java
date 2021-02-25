package com.example.responsivedesing;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final NoteListenerContract noteListener;

    public MyNoteRecyclerViewAdapter(List<Note> items, NoteListenerContract noteListener) {
        mValues = items;
        this.noteListener = noteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titleLabel.setText(holder.mItem.getTitle());
        holder.contentLabel.setText(holder.mItem.getContent());
        if (holder.mItem.isFavorite()) {
            holder.startFavoriteImg.setImageResource(R.drawable.ic_baseline_star);
        }

        holder.mView.setOnClickListener(v -> {
            if (noteListener != null) {
                noteListener.favoriteNoteClick(holder.mItem);
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
        public final ImageView startFavoriteImg;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleLabel = view.findViewById(R.id.fragment_item__label__title);
            contentLabel = view.findViewById(R.id.fragment_item__label__title);
            startFavoriteImg = view.findViewById(R.id.fragment_item__img__start_favorite);
        }
    }
}