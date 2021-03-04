package com.androidavanzado.prueba.ui.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.androidavanzado.prueba.R;
import com.androidavanzado.prueba.db.entity.NoteEntity;
import com.androidavanzado.prueba.viewmodel.NewNoteDialogViewModel;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;
    private NewNoteDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NoteEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((AppCompatActivity) ctx).get(NewNoteDialogViewModel.class);
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
//        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                viewModel.deleteNote(holder.mItem);
//                notifyDataSetChanged();
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewNotes(List<NoteEntity> newNotes) {
        this.mValues = newNotes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleLabel;
        public final TextView contentLabel;
        public final ImageView favoriteImg;
        public final CardView cardView;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleLabel = view.findViewById(R.id.textViewTitulo);
            contentLabel = view.findViewById(R.id.textViewContenido);
            favoriteImg = view.findViewById(R.id.imageViewFavorita);
            cardView = view.findViewById(R.id.fragment_note__content__card_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleLabel.getText() + "'";
        }
    }
}
