package com.notes;

import com.notes.entity.Note;
import com.notes.entity.User;
import com.notes.services.NoteService;
import com.notes.services.UserService;
import com.notes.store.NoteStore;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void displayNotes(List<Note> notes){
        int n = notes.size();
        if(n == 0) System.out.println("No notes created yet");
        for(int i=1;i<=n;++i){
            System.out.print(i + ": ");
            System.out.println(notes.get(i-1));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        NoteService noteService = new NoteService();
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);
        if(userService.getLoggedInUser() != null) userService.logout();

        while(true){

            System.out.println("Enter 1 to register");
            System.out.println("Enter 2 to login");
            System.out.println("Enter 3 to close");

            int choice = sc.nextInt(); sc.nextLine();

            if(choice == 1){
                System.out.println("Enter UserName");
                String userName = sc.nextLine();
                System.out.println("Enter Password");
                String password = sc.nextLine();
                if(!userService.addUser(userName,password)){
                    System.out.println("User Already Exists \n");
                }else{
                    System.out.println("Registered successfully");
                }
            }else if(choice == 2){

                System.out.println("Enter UserName");
                String userName = sc.nextLine();
                System.out.println("Enter Password");
                String password = sc.nextLine();

                if(userService.login(userName,password)){
                    System.out.println("logged in Successfully :");

                    while(true){

                        System.out.println("Enter 1 to view all Notes");
                        System.out.println("Enter 2 to delete all Notes");
                        System.out.println("Enter 3 to add new note");
                        System.out.println("Enter 4 to logout");
                        int option = sc.nextInt(); sc.nextLine();

                        if(option == 1){
                            displayNotes(noteService.getAllNotesOfLoggedInUser(userService.getLoggedInUser()));

                            System.out.println("Enter 0 to delete note");
                            System.out.println("Enter any other to move back");

                            option = sc.nextInt(); sc.nextLine();
                            if(option == 0){

                                while(true){
                                    System.out.println("Enter Note number to delete that");
                                    System.out.println("Enter -1 to move back");
                                    option = sc.nextInt(); sc.nextLine();

                                    if(option == -1){
                                        break;
                                    }else if(option == 0 || option > noteService.getAllNotesOfLoggedInUser(userService.getLoggedInUser()).size()){
                                        System.out.println("Enter valid position");
                                    }else{
                                        noteService.removeNote(userService.getLoggedInUser(),noteService.getAllNotesOfLoggedInUser(userService.getLoggedInUser()).get(option-1));
                                        System.out.println("Removed Sucessfully");
                                        break;
                                    }
                                }

                            }else{
                                continue;
                            }


                        }else if(option == 2){
                            noteService.removeAllNote(userService.getLoggedInUser());
                        }else if(option == 3){
                            String title, content;
                            System.out.println("Enter Title");
                            title = sc.nextLine();
                            System.out.println("Enter Content");
                            content = sc.nextLine();
                            Note note = new Note(userService.getLoggedInUser().getUserName(),title,content);
                            noteService.addNote(userService.getLoggedInUser(),note);
                        }else if(option == 4){
                            userService.logout();
                            break;
                        }else{
                            System.out.println("Choose correct option");
                        }

                    }


                }else{
                    System.out.println("Wrong Credentials");
                }

            }else if(choice  == 3){
                System.out.println("Closing the system ....");
                System.out.println();
                break;
            }else {
                System.out.println("Choose correct option");
            }
        }
    }
}
