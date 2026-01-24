package com.notes.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.entity.Note;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteStore {
    private static final File FILE = new File("src/main/resources/notes.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Note> loadNotes(){
        try{
            if(!FILE.exists()) return new ArrayList<>();
            else return mapper.readValue(FILE, new TypeReference<List<Note>>() {});
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void saveNotes(List<Note> notes) throws IOException {
        try{
            mapper.writeValue(FILE,notes);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
