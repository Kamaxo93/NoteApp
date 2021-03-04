package com.androidavanzado.prueba.ui.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.androidavanzado.prueba.R;
import com.androidavanzado.prueba.db.entity.NoteEntity;
import com.androidavanzado.prueba.viewmodel.NewNoteDialogViewModel;

public class NewNoteDialogFragment extends DialogFragment {


    private View view;
    private EditText titleNewNoteInput;
    private EditText contentNewNoteInput;
    private RadioGroup selectionColorRg;
    private Switch NoteFavoriteSw;

    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.title_dialog_new_note))
                .setMessage(getString(R.string.message_dialog_new_note))
                .setPositiveButton(getString(R.string.label_bnt_positive_dialog_new_note), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (getActivity() != null) {
                            NewNoteDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
                            if (titleNewNoteInput != null &&
                                    titleNewNoteInput.getText() != null &&
                                    !titleNewNoteInput.getText().toString().isEmpty() &&
                                    contentNewNoteInput != null &&
                                    contentNewNoteInput.getText() != null &&
                                    !contentNewNoteInput.getText().toString().isEmpty()) {
                                NoteEntity noteEntity = new NoteEntity(titleNewNoteInput.getText().toString(), contentNewNoteInput.getText().toString(),NoteFavoriteSw.isChecked(), "azul");
                                mViewModel.insertNote(noteEntity);
                                dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Faltan campos por rellenar", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.label_bnt_negative_dialog_new_note, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.new_note_dialog_fragment, null);
        titleNewNoteInput = view.findViewById(R.id.new_note_dialog_fragment__input__title_note);
        contentNewNoteInput = view.findViewById(R.id.new_note_dialog_fragment__input__content_note);
        selectionColorRg = view.findViewById(R.id.new_note_dialog_fragment__rdg__group);
        NoteFavoriteSw = view.findViewById(R.id.new_note_dialog_fragment__switch__note_favorite);
        builder.setView(view);
        return builder.create();
    }
}