package wuwei.server.app;

import wuwei.server.socket.CmdServerSocketThread;

public class ServerSocketApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cmdPort=8023;
		boolean isFail=true;
		
		while(true){
			try{
				if(isFail){
					isFail=false;
					CmdServerSocketThread CmdServerSocket=new CmdServerSocketThread();
					CmdServerSocket.start();
					CmdServerSocket.join();
					System.out.println("The CmdServerSocketTread is finished!");
				}
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				isFail=true;
			}
		}
	}

}
