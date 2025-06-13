package by.it_academy.jd2.mk_jd2_111_25_7.repository;

import by.it_academy.jd2.mk_jd2_111_25_7.model.Playlist;
import by.it_academy.jd2.mk_jd2_111_25_7.model.Song;

public interface IPlaylistRepository {

    Playlist getPlaylist(String email);
    void createPlaylist(String email);
    void addSong(String email, Song song);
    void removeSong(String email, String songId);
    boolean playlistExists(String email);

}
