﻿using FirstServer.service;
using Ice;
using Soup;

namespace FirstServer.iceImpl.Soup;

public class FileUploaderI : FileUploaderDisp_
{
    private readonly Dictionary<string, Dictionary<int, byte[]>> _files;

    private DatabaseService _databaseService;

    public FileUploaderI(DatabaseService databaseService)
    {
        _files = new Dictionary<string, Dictionary<int, byte[]>>();
        _databaseService = databaseService;
    }

    public override string startUpload(SongData songData, Current current = null)
    {
        var songId = _databaseService.InsertNewSong(songData);
        _files.Add(songId.ToString(), new Dictionary<int, byte[]>());
        Console.WriteLine("startUpload for " + songId);

        return songId.ToString();
    }

    public override void sendChunk(byte[] data, string uniqueId, int pos, Current current = null)
    {
        if (!_files.ContainsKey(uniqueId))
        {
            throw new KeyNotFoundException();
        }

        _files[uniqueId][pos] = data;
    }

    public override void completeUpload(string uniqueId, Current current = null)
    {
        Console.WriteLine("complete upload for " + uniqueId);
        if (!_files.ContainsKey(uniqueId))
        {
            throw new KeyNotFoundException();
        }

        byte[] data = ConvertDictionaryToArray(_files[uniqueId]);

        _databaseService.SaveSong(uniqueId, data);
        
        _files.Remove(uniqueId);
    }

    public byte[] ConvertDictionaryToArray(Dictionary<int, byte[]> chunks)
    {
        if (chunks == null || chunks.Count == 0)
        {
            throw new ArgumentException("chunks must not be null or empty", nameof(chunks));
        }

        var orderedChunks = chunks.OrderBy(chunk => chunk.Key).ToList();
        int size = (orderedChunks[0].Value.Length * (orderedChunks.Count - 1)) +
                   orderedChunks[orderedChunks.Count - 1].Value.Length;
        byte[] result = new byte[size];
        int currentPosition = 0;

        foreach (var chunk in orderedChunks)
        {
            Buffer.BlockCopy(chunk.Value, 0, result, currentPosition, chunk.Value.Length);
            currentPosition += chunk.Value.Length;
        }

        return result;
    }
}