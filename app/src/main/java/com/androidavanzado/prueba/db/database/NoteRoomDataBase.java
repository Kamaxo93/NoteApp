package com.androidavanzado.prueba.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidavanzado.prueba.db.dao.NoteDao;
import com.androidavanzado.prueba.db.entity.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteRoomDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
    private static volatile NoteRoomDataBase INSTANCE;

    public static NoteRoomDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDataBase.class, "notes_database").build();
                }
            }
        }
        return INSTANCE;
    }
}