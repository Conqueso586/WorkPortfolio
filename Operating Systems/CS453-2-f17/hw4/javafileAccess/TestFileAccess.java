
/**
  Watch the behavior by running it for 3 threads.

  java TestAlarm 3 

  To check, redirect output to a file.

  java TestAlarm 3 > log

  Then sort it to check the results:

  cat log | sort --key=1,2 --stable | less

*/
public class TestFileAccess
{
	public static void main (String[] args)
	{
		if (args.length == 0) {
			System.err.println("Usage: java TestAlarm <numThreads> <iterations> <max>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		int iter = Integer.parseInt(args[1]);
		int max = Integer.parseInt(args[2]);

		FileAccessMonitor monitor = new FileAccessMonitor(max);
		for (int i=1; i<=n; i++)
		{
			System.out.println("Thread: "+i+" starting up"); 

			new AccessThread(i, iter, monitor).start();
		}
	}

}
