public class ThreadManagerApp {
    public static void main(String[] args) {
        System.out.println(" --- Download Manager / Main -----");
        long startTime = System.currentTimeMillis();

        //runnable objects created, nthn running yet
        FileDownloader task1 = new FileDownloader("Movie.mp4", 5);
        FileDownloader task2 = new FileDownloader("Song.mp3", 2);
        FileDownloader task3 = new FileDownloader("Document.pdf", 3);

        //thread objects(workers)
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        //start the threads 
        //this moves them to runnable, and jvm takes over
        thread1.start();
        thread2.start();
        thread3.start();

        //if call thread1.run() directly it would just run sequentially on main 
        //thread, so always use start

        System.out.println("=== Main Thread waiting for downloads... ===");

        //join( wait for threads to die)
        //the main thread will pause here until t1,t2,t3 joined are terminated
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("===  All Downloads Finished ===");
        System.out.println("Total Time: " + (endTime - startTime) / 1000.0 + " seconds");
    }

}
