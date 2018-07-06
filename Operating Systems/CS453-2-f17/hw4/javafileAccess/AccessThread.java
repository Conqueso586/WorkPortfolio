
import java.util.*;
import java.text.*;

public class AccessThread extends Thread
{
	private FileAccessMonitor monitor;
	private Random generator = new Random();
	private final int RANGE = 3;
	private int id;
	private int iter;

	public AccessThread(int id, int iter, FileAccessMonitor monitor)
	{
		this.monitor = monitor;
		this.id = id;
		this.iter = iter;
	}

	public void run()
	{
		for (int i = 0; i < iter; i++)
		{
			int delay = generator.nextInt(RANGE)+1;
	
			monitor.startAccess(id);
			System.out.println("Thread: "+id+" starting file access for " + delay + " seconds"); 
			try{Thread.sleep(delay * 1000);} catch (InterruptedException e) {System.err.println(e);}
			monitor.endAccess(id);
			System.out.println("Thread: "+id+" ending file access");

		}
	}


}
