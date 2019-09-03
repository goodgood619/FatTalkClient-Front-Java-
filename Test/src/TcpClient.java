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
	public TcpClient() throws Exception{
		AsynchronousSocketChannel asynchronousChannel = AsynchronousSocketChannel.open();
		asynchronousChannel.connect(new InetSocketAddress("127.0.0.1",3300),asynchronousChannel,new CompletionHandler<Void,AsynchronousSocketChannel>() {

			@Override
			public void completed(Void result, AsynchronousSocketChannel attachment) {
				// TODO Auto-generated method stub
				Receive(asynchronousChannel);
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
					AsynchronousSocketChannel asynchronousChannel = AsynchronousSocketChannel.open();
					Future<Void> ret = asynchronousChannel.connect(new InetSocketAddress("127.0.0.1",3300));
					ret.get();
					byte[] bytesend = message.tobytedata();
					ByteBuffer buffer = ByteBuffer.wrap(bytesend);
					asynchronousChannel.write(buffer);
					//ReadHandler readHandler = new ReadHandler();
					//asynchronousChannel.write(buffer,null, readHandler);
					//System.out.println("Send to Server: "+writeFuture);
					/*
					while(true) {
					ByteBuffer buffer2 = ByteBuffer.allocate(1024);
					Future resultFuture = asynchronousChannel.read(buffer2);
					TcpMessage receiveMessage = new TcpMessage(buffer2);
					}
					*/
					//thread.join(0);
					
 				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
		}
		return ok;
	}
	/*
	class ReadHandler implements CompletionHandler<Integer,Void>{
		@Override
		public void completed(Integer result, Void attachment) {
			// TODO Auto-generated method stub
			try{
				AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
				Future<Void> receiveFuture = asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1",3300));
				receiveFuture.get();
				ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
				Future receiveFuture2 =asynchronousSocketChannel.read(receiveBuffer);
				TcpMessage reMessage = new TcpMessage(receiveBuffer);
				receiveBuffer.flip();
				asynchronousSocketChannel.read(receiveBuffer,null,this); // 본인을 handler로 계속 부르면됨 (그럼 계속 받겠지 ㅇ)
			}
			catch (Exception e) {
				
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			// TODO Auto-generated method stub
			exc.printStackTrace();
		}
	}
	*/
	private void Receive(AsynchronousSocketChannel asynchronousSocketChannel) {
		ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
		asynchronousSocketChannel.read(receiveBuffer, asynchronousSocketChannel,new CompletionHandler<Integer,AsynchronousSocketChannel>() {

			@Override
			public void completed(Integer result, AsynchronousSocketChannel attachment) {
				// TODO Auto-generated method stub
				try {
				TcpMessage tcpMessage = new TcpMessage(receiveBuffer);
				receiveBuffer.flip();
				asynchronousSocketChannel.read(receiveBuffer,asynchronousSocketChannel,this);
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
				// TODO Auto-generated method stub
				System.out.println("서버로부터 읽기에 실패하였습니다.");
			}
		});
	}
	
}
