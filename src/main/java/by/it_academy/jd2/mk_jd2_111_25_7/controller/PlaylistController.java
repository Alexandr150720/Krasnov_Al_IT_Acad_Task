package by.it_academy.jd2.mk_jd2_111_25_7.controller;

import by.it_academy.jd2.mk_jd2_111_25_7.model.Playlist;
import by.it_academy.jd2.mk_jd2_111_25_7.model.Song;
import by.it_academy.jd2.mk_jd2_111_25_7.service.IPlaylistService;
import by.it_academy.jd2.mk_jd2_111_25_7.service.PlaylistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PlaylistController extends HttpServlet {

    private final IPlaylistService playlistService = new PlaylistService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if ("/user/email".equals(pathInfo)) {
            handleSetEmail(request, response);
        } else if ("/playlist/songs".equals(pathInfo)) {
            handleAddSong(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.startsWith("/playlist/songs/")) {
            String[] splittedPath = pathInfo.split("/");
            String songId = splittedPath[splittedPath.length - 1];
            handleRemoveSong(songId, request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/playlist/songs".equals(request.getPathInfo())) {
            handleGetPlaylist(request, response);
        }
    }

    private void handleSetEmail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = readBody(request);
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", email);
        playlistService.setUserEmail(email);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void handleAddSong(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String userEmail = getUserEmailFromSession(request);
        if (userEmail == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String body = readBody(request);
        String[] songData = body.split(",");
        Song song = new Song(UUID.randomUUID().toString(), songData[0].trim(), songData[1].trim());
        playlistService.addSong(userEmail, song);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    private void handleGetPlaylist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String userEmail = getUserEmailFromSession(request);
        if (userEmail == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setContentType("application/json");
        String jsonResponse = convertPlaylistToJson(playlistService.getPlaylist(userEmail));
        response.getWriter().write(jsonResponse);
    }

    private String getUserEmailFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (String) session.getAttribute("userEmail") : null;
    }

    private String readBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private String convertPlaylistToJson(Playlist playlist) {
        StringBuilder json = new StringBuilder();
        json.append("{\"userEmail\":\"").append(playlist.getUserEmail()).append("\",\"songs\":[");
        List<Song> songs = playlist.getSongs();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            json.append("{\"id\":\"").append(song.getId()).append("\",\"title\":\"")
                    .append(song.getTitle()).append("\",\"artist\":\"").append(song.getArtist()).append("\"}");
            if (i < songs.size() - 1) {
                json.append(",");
            }
        }
        json.append("]}");
        return json.toString();
    }

    private void handleRemoveSong(String songId, HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        String userEmail = getUserEmailFromSession(request);
        if (userEmail == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        playlistService.removeSong(userEmail, songId); // Передаем email
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
