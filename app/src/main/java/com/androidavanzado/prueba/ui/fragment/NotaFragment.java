package com.androidavanzado.prueba.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidavanzado.prueba.R;
import com.androidavanzado.prueba.db.entity.NoteEntity;
import com.androidavanzado.prueba.ui.adapter.MyNotaRecyclerViewAdapter;
import com.androidavanzado.prueba.ui.dialogfragment.NewNoteDialogFragment;
import com.androidavanzado.prueba.viewmodel.NewNoteDialogViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NoteEntity> noteList;
    private MyNotaRecyclerViewAdapter adapterNotes;
    private NewNoteDialogViewModel newNoteDialogViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }

            noteList = new ArrayList<>();
            adapterNotes = new MyNotaRecyclerViewAdapter(noteList, getActivity());
            recyclerView.setAdapter(adapterNotes);

            launchViewModel();
        }
        return view;
    }

    private void launchViewModel() {
        if (getActivity() != null) {
            newNoteDialogViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
            newNoteDialogViewModel.getNoteAll().observe(getActivity(), new Observer<List<NoteEntity>>() {
                @Override
                public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                    adapterNotes.setNewNotes(noteEntities);
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_note:
                showDialogNewNote();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogNewNote() {
        if (getActivity() != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            NewNoteDialogFragment newNoteDialogFragment = new NewNoteDialogFragment();
            newNoteDialogFragment.show(fragmentManager, "New Note Dialog Fragment");

        }
    }
}
