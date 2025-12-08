//like a worker, defines what to do
public class FileDownloader implements Runnable{

    private final String fileName;
    private final int downloadTimeInSeconds;

    public FileDownloader(String fileName, int downloadTimeInSeconds){
        this.fileName = fileName;
        this.downloadTimeInSeconds = downloadTimeInSeconds;
    }

    @Override
    public void run() {
        //this will execute in new thread
        System.out.println("Starting: " + fileName + " (Time: " + downloadTimeInSeconds + " s)");

        try{
            //simulate network delay
            //thread.sleep pauses this specific thread only
            for(int i = 0; i <= 100; i++){
                System.out.println(" ..." + fileName + ": " + i + " %");
                //sleep for a fraction of time to simulate progress
                Thread.sleep(downloadTimeInSeconds * 10L);
                //dvide 100 multiply 1k
            }
        }catch(InterruptedException e){
            System.err.println("---------Interrupted"+ fileName);
        }

        System.out.println("Completeed "+ fileName);
    }
}
