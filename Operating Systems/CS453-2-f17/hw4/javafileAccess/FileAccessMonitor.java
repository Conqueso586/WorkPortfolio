
import java.util.*;

public class FileAccessMonitor extends TimerTask
{
	private int max;
	private int sum;
	private Timer timer;
	public FileAccessMonitor(int max)
	{
		this.max = max;
		this.sum = 0;
		timer = new Timer(true); //timer with a daemon thread
		timer.schedule(this,30000); 
	}

	public synchronized void startAccess(long id)
	{

		while (sum + id > max) 
		{
			try {wait();} catch (InterruptedException e) {System.err.println(e);}
		}
		sum += id;
	}

	public synchronized void endAccess(long id)
	{
		sum -= id;
		notifyAll();
	}

	public synchronized void run(){
	
		System.out.println("Ending program, deadlocked");
		System.exit(0);
	}
}

