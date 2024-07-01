package com.example.melodymix;

public class Playlist {
    private String name;
    private int likes;
    // private int imageResId; // nếu có ảnh minh họa

    public Playlist(String name, int likes) {
        this.name = name;
        this.likes = likes;
        // this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }
}
