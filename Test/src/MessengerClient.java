import java.util.HashMap;

import com.sun.nio.sctp.SendFailedNotification;

import Model.TcpMessage;
import Model.TcpMessage.Command;
import Service.MessangerService;

public class MessengerClient extends TcpClient{
	private MessangerService messangerService;
	public MessengerClient(MessangerService messangerService) throws Exception {
		super();
		this.messangerService = messangerService;
		// TODO Auto-generated constructor stub
	}
	private JsonHelper jsonHelper = new JsonHelper();
	public Boolean test() {
		TcpMessage message = new TcpMessage();
		message.command = 1;
		message.chatnumber = 1;
		message.check = 2;
		message.friendcount = 3;
		message.usernumber = 4;
		String id = "けけけけけけ";
		String password = "aa";
		message.message = jsonHelper.logininfo(id, password);
		return Send(message);
	}
	public void Response(TcpMessage message) throws Exception{
		try {
			HashMap<String,String> hashMap = jsonHelper.getlogininfo(message.message);
			System.out.println(hashMap);
			messangerService.Responsetomethod(message);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
