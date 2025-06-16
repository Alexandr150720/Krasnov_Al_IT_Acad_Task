package by.it_academy.jd2.mk_jd2_111_25_7.service;

import by.it_academy.jd2.mk_jd2_111_25_7.model.Playlist;
import by.it_academy.jd2.mk_jd2_111_25_7.model.Song;
import by.it_academy.jd2.mk_jd2_111_25_7.repository.IPlaylistRepository;
import by.it_academy.jd2.mk_jd2_111_25_7.repository.PlaylistRepository;

import java.util.UUID;

public class PlaylistService implements IPlaylistService{

    private final IPlaylistRepository repository = new PlaylistRepository();

    @Override
    public void setUserEmail(String email) {
        if (!repository.playlistExists(email)) {
            repository.createPlaylist(email);
        }
    }

    @Override
    public void addSong(String email, Song song) {
        if (song.getId() == null) {
            song.setId(UUID.randomUUID().toString());
        }
        repository.addSong(email, song);
    }

    @Override
    public void removeSong(String email, String songId) {
        repository.removeSong(email, songId);
    }

    @Override
    public Playlist getPlaylist(String email) {
        return repository.getPlaylist(email);
    }
}
