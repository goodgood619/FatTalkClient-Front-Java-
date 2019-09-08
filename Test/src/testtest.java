import Service.MessangerService;
import ViewModel.LoginViewModel;
import ViewModel.MainViewModel;

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
		MessangerService messangerService = new MessangerService();
		LoginViewModel loginViewModel = new LoginViewModel(messangerService);
		MainViewModel mainViewModel = new MainViewModel(messangerService);
		for(int i=0 ;i<3;i++){
			try {
				MessengerClient messengerClient = new MessengerClient(messangerService);
				messengerClient.test();
				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				}
		}
	}
}
