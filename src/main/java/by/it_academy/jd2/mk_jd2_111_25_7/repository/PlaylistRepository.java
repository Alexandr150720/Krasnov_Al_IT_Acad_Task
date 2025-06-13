package by.it_academy.jd2.mk_jd2_111_25_7.repository;

import by.it_academy.jd2.mk_jd2_111_25_7.model.Playlist;
import by.it_academy.jd2.mk_jd2_111_25_7.model.Song;

import java.util.concurrent.ConcurrentHashMap;

public class PlaylistRepository implements IPlaylistRepository {

    private static final ConcurrentHashMap<String, Playlist> playlists = new ConcurrentHashMap<>();

    @Override
    public void createPlaylist(String email) {
        playlists.putIfAbsent(email, new Playlist(email));
    }

    @Override
    public void addSong(String email, Song song) {
        Playlist playlist = playlists.get(email);
        if (playlist != null) {
            playlist.getSongs().add(song);
        }
    }

    @Override
    public void removeSong(String email, String songId) {
        Playlist playlist = playlists.get(email);
        if (playlist != null) {
            playlist.getSongs().removeIf(song -> song.getId().equals(songId));
        }
    }

    @Override
    public boolean playlistExists(String email) {
        return playlists.containsKey(email);
    }

    @Override
    public Playlist getPlaylist(String email) {
        return playlists.get(email);
    }
}
