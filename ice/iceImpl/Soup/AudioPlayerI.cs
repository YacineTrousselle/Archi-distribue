﻿using FirstServer.service;
using Ice;
using LibVLCSharp.Shared;
using Soup;
using Exception = System.Exception;

namespace FirstServer.iceImpl.Soup;

public class AudioPlayerI : AudioPlayerDisp_
{
    private Dictionary<String, MediaPlayer> mediaPlayers = new();
    private LibVLC _libVlc;
    private DatabaseService _databaseService;
    private int _id;

    public AudioPlayerI(DatabaseService databaseService)
    {
        _libVlc = new LibVLC();
        _databaseService = databaseService;
        _id = 0;
    }

    public override string initializeAudioPlayer(string songId, Current current = null)
    {
        ArgumentNullException.ThrowIfNull(_databaseService.FindSongById(songId));
        
        var rtspUrl = $"rtsp://127.0.0.1:8556/audio-{_id++}.sdp";
        MediaPlayer? mediaPlayer;
        if (!mediaPlayers.ContainsKey(rtspUrl))
        {
            mediaPlayer = new MediaPlayer(_libVlc);
            mediaPlayers.Add(rtspUrl, mediaPlayer);
        }
    
        mediaPlayer = mediaPlayers[rtspUrl];
    
        var media = new Media(_libVlc, Path.Join(Program.SongPath, $"{songId}.{Extensions.AudioExt}"));
        media.AddOption($"--sout '#rtp{{sdp={rtspUrl}}}'");
        mediaPlayer.Media = media;
        mediaPlayer.Play();
    
        Console.WriteLine("New player for " + songId + ", url: " + rtspUrl);

        return rtspUrl;
    }

    public override void play(string rtspUrl, Current current = null)
    {
        if (!mediaPlayers.ContainsKey(rtspUrl)) 
        {
            throw new Exception();
        }
        mediaPlayers[rtspUrl].Play();
        Console.WriteLine("play " + rtspUrl);
    }

    public override void pause(string rtspUrl, Current current = null)
    {
        if (!mediaPlayers.ContainsKey(rtspUrl)) 
        {
            throw new Exception();
        }
        mediaPlayers[rtspUrl].Pause();
        Console.WriteLine("pause " + rtspUrl);
    }

    public override void close(string rtspUrl, Current current = null)
    {
        if (!mediaPlayers.ContainsKey(rtspUrl)) 
        {
            throw new Exception();
        }
        Console.WriteLine("close " + rtspUrl);
        mediaPlayers[rtspUrl].Stop();
        mediaPlayers.Remove(rtspUrl);
    }
}