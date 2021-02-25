package com.example.responsivedesing;

public interface NoteListenerContract {
    void editNoteClick(Note note);
    void deleteNoteClick(Note note);
    void favoriteNoteClick(Note note);
}
