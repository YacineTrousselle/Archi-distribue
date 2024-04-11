using FirstServer.model;
using FirstServer.service;
using Ice;
using Soup;

namespace FirstServer.iceImpl.Soup;

public class FileSenderI : FileSenderDisp_
{
    private DatabaseService _databaseService;
    
    public FileSenderI(DatabaseService databaseService)
    {
        _databaseService = databaseService;
    }

    public override SongData getSongData(string songId, Current current = null)
    {
        Song? song = _databaseService.FindSongById(songId);
        ArgumentNullException.ThrowIfNull(song);
        byte[] data = File.ReadAllBytes(Path.Join(Program.SongPath, $"{songId}.mp3"));

        return new SongData
        {
            title = song.Title,
            artists = song.Artists.ToArray(),
            filesize = data.Length
        };
    }

    public override byte[] getChunk(string songId, int pos, Current current = null)
    {
        byte[] data = File.ReadAllBytes(Path.Join(Program.SongPath, $"{songId}.mp3"));
        int offset = pos * ChunkSize.value;

        return data.Slice(offset, Math.Min(ChunkSize.value, data.Length - offset));
    }
}