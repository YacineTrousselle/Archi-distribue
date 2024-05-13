package fr.frcsbcn.soup.proxy;

import com.zeroc.Ice.ObjectPrx;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import fr.frcsbcn.soup.generated.ChunkSize;
import fr.frcsbcn.soup.generated.FileUploaderPrx;
import fr.frcsbcn.soup.generated.SongData;

public class FileUploaderProxy extends AbstractProxy {
    private static final String PROXY_NAME = "FileUploader";

    public void uploadMusic(SongData songData, String filepath) {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            FileUploaderPrx fileUploaderPrx = FileUploaderPrx.checkedCast(base);

            String songId = fileUploaderPrx.startUpload(songData);

            System.out.println("Id: " + songId);
            System.out.println("Upload in progress...");

            fileUploaderPrx = fileUploaderPrx.ice_batchOneway();

            int pos = 0;
            byte[] data = Files.readAllBytes(Paths.get(filepath));
            for (int offset = 0; offset < data.length; offset += ChunkSize.value) {
                int end = Math.min(offset + ChunkSize.value, data.length);
                byte[] chunk = Arrays.copyOfRange(data, offset, end);
                fileUploaderPrx.sendChunk(chunk, songId, pos++);
            }
            fileUploaderPrx.completeUpload(songId);
            fileUploaderPrx.ice_flushBatchRequests();

            System.out.println("Transfer completed for " + songData.title);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadMusic(SongData songData, byte[] data) {
        try {
            ObjectPrx base = getBaseProxy(PROXY_NAME);
            FileUploaderPrx fileUploaderPrx = FileUploaderPrx.checkedCast(base);

            String songId = fileUploaderPrx.startUpload(songData);

            System.out.println("Id: " + songId);
            System.out.println("Upload in progress...");

            fileUploaderPrx = fileUploaderPrx.ice_batchOneway();

            int pos = 0;
            for (int offset = 0; offset < data.length; offset += ChunkSize.value) {
                int end = Math.min(offset + ChunkSize.value, data.length);
                byte[] chunk = Arrays.copyOfRange(data, offset, end);
                fileUploaderPrx.sendChunk(chunk, songId, pos++);
            }
            fileUploaderPrx.completeUpload(songId);
            fileUploaderPrx.ice_flushBatchRequests();

            System.out.println("Transfer completed for " + songData.title);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
