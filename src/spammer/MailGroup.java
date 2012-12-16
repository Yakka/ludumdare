package spammer;

public class MailGroup
{
	public int initialCount;
	public int remaining;
	public int blockedCount; // how many mails of this group got blocked?
	public int spammedCount; // how many matching mails of this group succeeded?
	private boolean notified;

	public MailGroup(int count)
	{
		initialCount = count;
		remaining = initialCount;
	}

	public void notifyAvatar()
	{
		if(!notified)
		{
			System.out.println(blockedCount + ", " + spammedCount);
			if(blockedCount > spammedCount)
			{
				System.out.println("angry");
				GamePlay.get().avatar.gettingAngry();
			}
			else if(spammedCount > blockedCount)
			{
				System.out.println("cash");
				GamePlay.get().avatar.gettingCash();
			}
			notified = true;
		}
	}

}
