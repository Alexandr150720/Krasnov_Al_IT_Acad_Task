package by.it_academy.jd2.mk_jd2_111_25_7.service;

import by.it_academy.jd2.mk_jd2_111_25_7.model.Playlist;
import by.it_academy.jd2.mk_jd2_111_25_7.model.Song;

public interface IPlaylistService {

    void setUserEmail(String email);
    void addSong(Song song);
    void removeSong(String email, String songId);
    Playlist getPlaylist(String email);
}
