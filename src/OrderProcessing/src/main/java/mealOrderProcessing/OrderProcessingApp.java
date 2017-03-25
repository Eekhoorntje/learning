package mealOrderProcessing;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Hello world!
 *
 */
public class OrderProcessingApp 
{
    public static void main( String[] args )
    {
        LogManager.setLogFile(new File("c:\\temp\\MealLog.log"));
        LogManager.setLogLevel(LogManager.LogLevel.DEBUG);
        
        LogManager.logConsole("Starting Application", LogManager.LogLevel.LOW);
        
        new OrderProcessingApp().startEngine();
        
        LogManager.logConsole("Exiting Application", LogManager.LogLevel.LOW);
    }
    
    private static final int MSG_NBR = 10;
    
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
  
    public void startEngine() {
        startProducer();
        startConsumer();
        
        /*
         * Todo.  What about starting multiple producers?
         * Can the queue be monitored and if it grows over a certain capacity can more consumers be started?
         * How can we kill off consumers during low production periods?
         * Do consumers just have limited lives?
         * Do producers have limited lives? how can we start new producers
         * Can that be adjusted mid program
         * Maybe create a few types of food objects that will be put on the queue
         * Different foods have different times to consume.  Createa  few classes to simulate this. 
         */
    }
 
    // Producer thread
    private void startProducer() {
 
        final MyProducer<String> myProducer = new MyProducer<>(queue);
        final Supplier<String> supplier = () -> "Hello World";
        new Thread(() -> {
            for (int i = 0; i < MSG_NBR; i++) {
                myProducer.produce(supplier);
            }
        }).start();
    }
 
    // Consumer thread
    private void startConsumer() {
 
        final MyConsumer<String> myConsumer = new MyConsumer<>(queue);
        final Consumer<String> consumer = (s) -> System.out.println("Consumed message: " + s);
        new Thread(() -> {
            for (int i = 0; i < MSG_NBR; i++)
                myConsumer.consume(consumer);
        }).start();
    }
 
    static class MyProducer<T> {
 
        private BlockingQueue<T> queue;
 
        public MyProducer(BlockingQueue<T> queue) {
            this.queue = queue;
        }
 
        /**
         * Insert the supplied object in the queue
         * 
         * @param supplier
         *            Is responsible for supplying the object that will be put
         *            in the queue
         */
        public void produce(Supplier<T> supplier) {
            final T msg = supplier.get();
            try {
                queue.put(msg);
                System.out.println("Added message: " + msg);
                 
                // Simulate a long running process
                Thread.sleep(900);
                 
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
 
    static class MyConsumer<T> {
 
        private BlockingQueue<T> queue;
 
        public MyConsumer(BlockingQueue<T> queue) {
            this.queue = queue;
        }
 
        /**
         * Retrieves an object from the head of the queue and passes it to the
         * consumer
         * 
         * @param consumer
         *            Contains the logic on what to do with the retrieved object
         */
        public void consume(Consumer<T> consumer) {
            try {
                consumer.accept(queue.take());
                 
                // Simulate a long running process
                Thread.sleep(1250);
                 
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
