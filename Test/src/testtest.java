
public class testtest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Thread clienThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MessengerClient messengerClient;
				try {
					messengerClient = new MessengerClient();
					messengerClient.test();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		clienThread.start();
		clienThread.wait(10000);
		*/
		while(true) {
		MessengerClient messengerClient = new MessengerClient();
		messengerClient.test();
		}
	}

}