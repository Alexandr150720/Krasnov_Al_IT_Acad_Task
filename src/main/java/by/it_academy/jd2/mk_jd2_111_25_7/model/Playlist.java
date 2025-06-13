package by.it_academy.jd2.mk_jd2_111_25_7.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String userEmail;
    private List<Song> songs;

    public Playlist(String userEmail) {
        this.userEmail = userEmail;
        this.songs = new ArrayList<>();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
