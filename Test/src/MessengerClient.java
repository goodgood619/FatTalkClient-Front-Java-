import com.sun.nio.sctp.SendFailedNotification;

import Model.TcpMessage;
import Model.TcpMessage.Command;

public class MessengerClient extends TcpClient{
	public MessengerClient() throws Exception {
		super();
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
}
