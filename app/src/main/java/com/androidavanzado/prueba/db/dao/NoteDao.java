package com.androidavanzado.prueba.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.androidavanzado.prueba.db.entity.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteEntity note);

    @Update
    void update(NoteEntity note);

    @Query("DELETE From notes")
    void deleteAll();

    @Query("DELETE FROM notes WHERE id = :idNote")
    void deleteById(int idNote);

    @Query("Select * From notes Order By title ASC")
    LiveData<List<NoteEntity>> getAll();

    @Query("Select * From notes Where favorite LIKE 1 Order By title ASC")
    LiveData<List<NoteEntity>> getAllFavorite();
}
