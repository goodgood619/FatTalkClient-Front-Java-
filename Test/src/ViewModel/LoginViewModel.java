package ViewModel;

import Model.TcpMessage;
import Service.Imessanger;
import Service.MessangerService;

public class LoginViewModel {
	
	public LoginViewModel(MessangerService messangerService) {
		messangerService.addtomethod(new Imessanger() {
			
			@Override
			public void ResponseMessage(TcpMessage message) {
				// TODO Auto-generated method stub
				switch (message.command) {
				case 1:
					System.out.println("login view react ok");
					break;

				default:
					throw new IllegalArgumentException("Unexpected value: " + message.command);
				}
			}
		});
	}
}
