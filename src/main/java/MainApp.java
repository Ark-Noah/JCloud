import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.domain.Location;

import java.io.IOException;


public class MainApp {
    private static String provider = "aws-s3";
    private static String identity = ""; //insert your access key id here
    private static String credential = ""; //insert your secret key here
    private static String containerName = ""; //insert bucket name here

    public static void main(String args[]) throws IOException {

        BlobStoreContext context = ContextBuilder.newBuilder(provider)
                .credentials(identity, credential)
                .buildView(BlobStoreContext.class);


        BlobStore blobStore = context.getBlobStore();
        //createBucket(blobStore);
        //uploadFile(blobStore);
        //deleteBucket(blobStore);
        context.close();
    }

    private static void createBucket(BlobStore blobStore){
        Location location = null;
        blobStore.createContainerInLocation(location, containerName);
    }

    private static void uploadFile(BlobStore blobStore){
        Blob blob = blobStore.blobBuilder("NoahDemo.html").
                payload("<html><body>Cloud Services - JCloud demo</body></html>").contentType("text/html").build();
        blobStore.putBlob(containerName, blob);
        System.out.println("File uploaded successfully!");
    }

    private static void deleteBucket(BlobStore blobStore){
        blobStore.deleteContainer(containerName);
        System.out.println("Bucket " + containerName + " deleted!");
    }
}
