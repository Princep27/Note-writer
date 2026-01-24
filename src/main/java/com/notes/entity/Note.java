package com.notes.entity;

public class Note {
    String userName;
    String title;
    String content;

    public Note() {
    }

    public Note(String userName,String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title :" + title + "\n" + "Content :" + content;
    }
}
