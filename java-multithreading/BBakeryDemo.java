import java.util.LinkedList;
import java.util.Queue;

public class BBakeryDemo {

    public static void main(String[] args) {
        //the shared resource
        BakeryShelf shelf = new BakeryShelf(3);

        //create producer
        Thread baker = new Thread(new Baker(shelf));

        //create consumer
        Thread customer = new Thread(new Customer(shelf));

        baker.setName("Baker");
        customer.setName("Customer");
        //start them
        baker.start();
        customer.start();
    }

    //the shared resource / monitor
    static class BakeryShelf{
        private final Queue<Integer> shelf = new LinkedList<>();
        private final int capacity;

        public BakeryShelf(int capacity){
            this.capacity = capacity;
        }

        //method for baker to add bread
        public synchronized void putBread(int breadId) throws InterruptedException{
            //check if we need to wait
            //use while not if to handle spurious wakeup
            while (shelf.size()==capacity) {
                System.out.println(Thread.currentThread().getName() + "  sees shelf is full, waiting...");
                wait();
            }

            //do the work
            shelf.add(breadId);
            System.out.println(Thread.currentThread().getName() + " added Bread #" + breadId + ". [Shelf: " + shelf.size() + "/" + capacity + "]");
            //notify other customers, like baker added bread now consume.
            notifyAll();
        }

        //method for customer to take bread
        public synchronized void takeBread() throws InterruptedException{
        //check if need to wait
            while (shelf.isEmpty()) {
                System.out.println(Thread.currentThread().getName()+"  sees shelf is empty. waiting...");
                wait();
            }

            //do the work
            int breadId = shelf.poll();
            System.out.println(Thread.currentThread().getName() + " ate Bread #" + breadId + ". [Shelf: " + shelf.size() + "/" + capacity + "]");

            //notify other, space available
            notifyAll();
        }
    }


    //The PRODUCER class
    static class Baker implements Runnable {
        private final BakeryShelf shelf;

        public Baker(BakeryShelf shelf){
            this.shelf = shelf;
        }

        @Override
        public void run(){
            int breadId = 1;
            try{
                while (true) {
                    shelf.putBread(breadId++);
                    Thread.sleep(2000);//s to bake
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt(); //necessary
            }
        }
    }

    //the consumer /runnable
    static class Customer implements Runnable{
        private final BakeryShelf shelf;

        public Customer(BakeryShelf shelf){
            this.shelf = shelf;
        }

        @Override
        public void run(){
            try{
                while(true){
                    shelf.takeBread();
                    Thread.sleep(1000); // s to eat
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    
}