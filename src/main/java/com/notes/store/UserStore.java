package com.notes.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.entity.Note;
import com.notes.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStore {

    private static final File FILE = new File("src/main/resources/users.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<User> loadUser() throws IOException {
        if(!FILE.exists()) return new ArrayList<>();
        else return mapper.readValue(FILE, new TypeReference<List<User>>() {});
    }

    public static void saveUsers(List<User> users){
        try{
            mapper.writeValue(FILE,users);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
