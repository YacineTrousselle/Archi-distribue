using System.Net;
using System.Net.Sockets;
using FirstServer.iceImpl.Soup;
using FirstServer.service;
using Ice;
using LibVLCSharp.Shared;
using Microsoft.Extensions.Configuration;
using Exception = System.Exception;
using FileSenderI = FirstServer.iceImpl.Soup.FileSenderI;

namespace FirstServer;

public class ApplicationI : Application
{
    private DatabaseService _client;

    public ApplicationI(DatabaseService client)
    {
        _client = client;
    }

    public override int run(string[] args)
    {
        try
        {
            Core.Initialize();
            using (Communicator communicator = Application.communicator())
            {
                string ipAddress = Dns.GetHostEntry(Dns.GetHostName())
                    .AddressList.FirstOrDefault(ip => ip.AddressFamily == AddressFamily.InterNetwork)
                    .ToString();
                Console.WriteLine("Ip: " + ipAddress);

                var adapter =
                    communicator.createObjectAdapterWithEndpoints("SoupAdapter", "default -h 127.0.0.1 -p 10000");

                _client.setDatabase("songs");
                InitSoup(adapter, _client);
                adapter.activate();
                
                Console.Out.WriteLine("Server is running...");
                communicator.waitForShutdown();
            }
        }
        catch (Exception e)
        {
            Console.Error.WriteLine(e);

            return 1;
        }

        return 0;
    }

    private void InitSoup(ObjectAdapter adapter, DatabaseService databaseService)
    {
        adapter.add(new FileUploaderI(databaseService), Util.stringToIdentity("Soup.FileUploader"));
        adapter.add(new FileSenderI(databaseService), Util.stringToIdentity("Soup.FileSender"));
        adapter.add(new SongDataModuleI(databaseService), Util.stringToIdentity("Soup.SongDataModule"));
        adapter.add(new AudioPlayerI(databaseService), Util.stringToIdentity("Soup.AudioPlayer"));

        
    }
}

public class Program
{
    public static string SongPath;

    public static int Main(string[] args)
    {
        var builder = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("settings.json", optional: false, reloadOnChange: true);
        IConfiguration configuration = builder.Build().GetSection("Properties");
        CheckConfig(configuration);

        var client = new DatabaseService(configuration["databaseUrl"]);
        SongPath = configuration["songsPath"];

        Application application = new ApplicationI(client);

        return application.main(args);
    }

    private static void CheckConfig(IConfiguration configuration)
    {
        string[] necessaryKeys = ["databaseUrl", "songsPath"];
        foreach (var necessaryKey in necessaryKeys)
        {
            ArgumentNullException.ThrowIfNull(configuration[necessaryKey]);
        }
    }
}