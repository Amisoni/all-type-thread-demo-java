class abc extends Thread
{
	public void run()
	{	
		synchronized(this)
		{
			for(int i=0;i<10;i++)
			{
				System.out.println("Series One:"+Thread.currentThread						().getPriority());
				if(i==2)
				{
					try
					{
						Thread.sleep(1000);
						this.wait();
					}
					catch(Exception e)
					{}				
				}
			
			}
			this.notify();
		}
	}
}

class pqr extends Thread
{
	abc a;
	pqr(abc a)
	{
		this.a=a;
	}
	public void run()
	{	
			
		synchronized(this.a)
		{
			for(int i=0;i<10;i++)
			{
				System.out.println("Series two:"+Thread.currentThread						().getPriority());
				if(i==2)
				{
					try
					{
																	this.a.wait();
					}
					catch(Exception e)
					{}				
				}
			
			}
		this.a.notify();
			
		}
	}
}

class xyz extends Thread
{
	abc a;
	xyz(abc a)
	{
		this.a=a;
	}
	public void run()
	{	
		synchronized(this.a)
		{
			for(int i=0;i<10;i++)
			{
				System.out.println("Series three:"+Thread.currentThread						().getPriority());
				
			
			this.a.notify();
			}
		}
	}
}

class demo
{
	public static void main(String args[])
	{
		abc a=new abc();
		xyz x=new xyz(a);
		pqr y=new pqr(a);

		a.setPriority(10);
		a.start();

		x.setPriority(1);
		x.start();

		y.setPriority(5);
		y.start();			
	}
}