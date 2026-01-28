import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VThreads {
    public static void main(String[] args) {
        
        long start = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            AtomicInteger count = new AtomicInteger(0);

            //submit tasks
            for(int i = 0; i < 100_000; i++){
                executor.submit(() -> {
                    try{
                        Thread.sleep(Duration.ofSeconds(1));
                        count.incrementAndGet();
                    }catch(InterruptedException e){
                        System.out.println("meow..");
                    }
                });
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        long end = System.currentTimeMillis();
        System.out.println("Finished in: "+(end-start)+"ms");
    }
}
