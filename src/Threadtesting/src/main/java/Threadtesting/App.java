package Threadtesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		System.out.println("Creating Executor!");

		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.submit(() -> {
			try
			{
				String threadName = Thread.currentThread().getName();
				Thread.sleep(1000);
				System.out.println("Hello " + threadName);
			} catch (InterruptedException e)
			{
				throw new IllegalStateException("task interrupted", e);
			}
		});
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});

		// Shutdown the executor
		try
		{
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e)
		{
			System.err.println("tasks interrupted");
		} finally
		{
			if (!executor.isTerminated())
			{
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}
}
