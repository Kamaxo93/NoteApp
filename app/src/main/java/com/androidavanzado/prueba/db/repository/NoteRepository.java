package com.androidavanzado.prueba.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidavanzado.prueba.db.dao.NoteDao;
import com.androidavanzado.prueba.db.database.NoteRoomDataBase;
import com.androidavanzado.prueba.db.entity.NoteEntity;
import com.androidavanzado.prueba.utils.Constants;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteRoomDataBase dataBase = NoteRoomDataBase.getDataBase(application);
        noteDao = dataBase.noteDao();
    }

    public LiveData<List<NoteEntity>> getAll() {
        return noteDao.getAll();
    }

    public LiveData<List<NoteEntity>> getAllFavorite() {
        return  noteDao.getAllFavorite();
    }

    public void insertNote(NoteEntity note) {
        new insertAsyncTask(noteDao).execute(note);
    }

    public void updateNote(NoteEntity note) {
        new updateAsyncTask(noteDao).execute(note);
    }

    public void deleteNote(NoteEntity noteEntity) { new deleteAsyncTask(noteDao).execute(noteEntity);}

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        insertAsyncTask(NoteDao noteDao) {
            noteDaoAsyncTask = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsyncTask.insert(noteEntities[Constants.POSITION_ZERO]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        updateAsyncTask(NoteDao noteDao) {
            noteDaoAsyncTask = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsyncTask.update(noteEntities[Constants.POSITION_ZERO]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        deleteAsyncTask(NoteDao noteDao) {
            noteDaoAsyncTask = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsyncTask.deleteById(noteEntities[Constants.POSITION_ZERO].id);
            return null;
        }
    }
}
