package com.androidavanzado.prueba.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.androidavanzado.prueba.db.entity.NoteEntity;
import com.androidavanzado.prueba.db.repository.NoteRepository;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {
    private LiveData<List<NoteEntity>> allNotes;
    private NoteRepository noteRepository;

    public NewNoteDialogViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAll();
    }

    //El fragment que necesita recibir la nuena lista de notas
    public LiveData<List<NoteEntity>> getNoteAll() {return allNotes;}

    //El fragment que inserta una nueva nota, actualiza o elimina deberá comunicarlo al viewModel
    public void insertNote(NoteEntity noteEntity) {noteRepository.insertNote(noteEntity);}
    public void updateNote(NoteEntity noteEntity) {noteRepository.updateNote(noteEntity);}
    public void deleteNote(NoteEntity noteEntity) {noteRepository.deleteNote(noteEntity);}
}