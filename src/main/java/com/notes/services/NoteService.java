package com.notes.services;

import com.notes.entity.Note;
import com.notes.entity.User;
import com.notes.store.NoteStore;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NoteService {

    private List<Note> notes = NoteStore.loadNotes();

    public void addNote(User loggedInUser,Note note) throws IOException {
        notes.add(note);
        NoteStore.saveNotes(notes);
    }

    public void removeNote(User loggedInUser,Note note) throws IOException {
        notes.remove(note);
        NoteStore.saveNotes(notes);
    }

    public void removeAllNote(User loggedInUser) throws IOException {
        notes = notes.stream()
                .filter(note -> !note.getUserName().equals(loggedInUser.getUserName()))
                .collect(Collectors.toList());
        NoteStore.saveNotes(notes);
    }

    public List<Note> getAllNotesOfLoggedInUser(User loggedInUser){
       return notes.stream()
                .filter(note -> note.getUserName().equals(loggedInUser.getUserName()))
                .collect(Collectors.toList());
    }
}
