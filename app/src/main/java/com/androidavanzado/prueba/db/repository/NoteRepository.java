package com.androidavanzado.prueba.db.repository;

import android.app.Application;

import com.androidavanzado.prueba.db.dao.NoteDao;
import com.androidavanzado.prueba.db.database.NoteRoomDataBase;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteRoomDataBase dataBase = NoteRoomDataBase.getDataBase(application);
        noteDao = dataBase.noteDao();
    }
}
