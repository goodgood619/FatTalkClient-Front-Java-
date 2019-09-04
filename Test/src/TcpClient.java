import java.io.*;
import java.net.InetAddress;
import java.net.*;
import java.nio.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;
import java.util.concurrent.*;
import java.nio.channels.CompletionHandler;
import Model.TcpMessage;
public abstract class TcpClient {
	private JsonHelper jsonHelper = new JsonHelper();
	private AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
	public TcpClient() throws Exception{
			asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1",3300),asynchronousSocketChannel,new CompletionHandler<Void,AsynchronousSocketChannel>() {
				//ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
				
				@Override
				public void completed(Void result, AsynchronousSocketChannel attachment) {
					// TODO Auto-generated method stub
					System.out.println("서버와 연결에 성공하였습니다");
					Receive(asynchronousSocketChannel);
				}

				@Override
				public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
					// TODO Auto-generated method stub
					System.out.println("서버와 연결에 실패하였습니다");
				}
			});
	}
	protected Boolean Send(TcpMessage message) {
		Boolean ok = true;
				// TODO Auto-generated method stub
				try {
					byte[] bytesend = message.tobytedata();
					ByteBuffer buffer = ByteBuffer.wrap(bytesend);
					asynchronousSocketChannel.write(buffer);
					
 				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
		}
		return ok;
	}
	private void Receive(AsynchronousSocketChannel asynchronousSocketChannel) {
		ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
		System.out.println("Receive진입");
		boolean connected = asynchronousSocketChannel.isOpen();
			if(connected) {
				asynchronousSocketChannel.read(receiveBuffer,null,new CompletionHandler<Integer,AsynchronousSocketChannel>() {

				@Override
				public void completed(Integer result, AsynchronousSocketChannel attachment) {
					// TODO Auto-generated method stub
					try {
						System.out.println("읽기 성공");
					TcpMessage tcpMessage = new TcpMessage(receiveBuffer);
					HashMap<String,String> hashMap = jsonHelper.getlogininfo(tcpMessage.message);
					System.out.println((String)hashMap.get("NickName"));
					receiveBuffer.flip();
					asynchronousSocketChannel.read(receiveBuffer, null, this);
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					//Receive(asynchronousSocketChannel);
				}

				@Override
				public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
					// TODO Auto-generated method stub
					System.out.println("서버로부터 읽기에 실패하였습니다.");
						}
					});
			}
	}
	abstract void Response(TcpMessage message) throws Exception;
	
}
