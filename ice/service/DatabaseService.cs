﻿using FirstServer.model;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Driver.Core.Clusters;
using Soup;

namespace FirstServer.service;

public class DatabaseService
{
    private MongoClient _client;
    private string _database;
    private const string SongCollection = "songs";

    public DatabaseService(string databaseUrl)
    {
        _client = new MongoClient(databaseUrl);
        _client.ListDatabases();
        if (_client.Cluster.Description.State != ClusterState.Connected)
        {
            throw new Exception("I think the database isn't up. Try after turn it on");
        }
    }

    public void setDatabase(string database)
    {
        _database = database;
    }
    
    public IMongoCollection<Song> GetSongCollection()
    {
        return _client.GetDatabase(_database).GetCollection<Song>(SongCollection);
    }

    public ObjectId InsertNewSong(SongData songData)
    {
        Song song = new Song(new ObjectId(), songData.title, new List<string>(songData.artists));
        GetSongCollection().InsertOne(song);

        return song.Id;
    }

    public void SaveSong(string songId, byte[] data)
    {
        Console.WriteLine(Path.Join(Program.SongPath, $"{songId}.mp3") + " created");

        var file = File.Create(Path.Join(Program.SongPath, $"{songId}.mp3"));
        file.Write(data);
        file.Flush();
        file.Close();
    }

    public byte[] GetSongData(string songId)
    {
        return File.ReadAllBytes(Path.Join(Program.SongPath, $"{songId}.mp3"));
    }
    
    public Song? FindSongById(string songId)
    {
        var filter = Builders<Song>.Filter.Eq("_id", ObjectId.Parse(songId));

        return GetSongCollection().Find(filter).FirstOrDefault();
    }
}