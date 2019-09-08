package Service;

import Model.TcpMessage;

public class MessangerService {
	private MessangerDelegate messangerDelegate;
	public MessangerService() {
		messangerDelegate = new MessangerDelegate();
	}
	public void addtomethod(Imessanger imessanger) {
		messangerDelegate.addMethod(imessanger);
	}
	public void deletetomethod(Imessanger imessanger) {
		messangerDelegate.removeMethod(imessanger);
	}
	public void Responsetomethod(TcpMessage message) {
		messangerDelegate.ResponseMethod(message);
	}
	
}
