package Service;

import java.util.ArrayList;
import java.util.List;

import Model.TcpMessage;

public class MessangerDelegate {
	private List<Imessanger> testlist;
	public MessangerDelegate() {
		testlist = new ArrayList<Imessanger>();
	}
	public void addMethod(Imessanger imessanger) {
		testlist.add(imessanger);
	}
	public void removeMethod(Imessanger imessanger) {
		testlist.remove(imessanger);
	}
	public void ResponseMethod(TcpMessage message) {
		for(Imessanger imessanger : testlist) {
			imessanger.ResponseMessage(message);
		}
	}
}
