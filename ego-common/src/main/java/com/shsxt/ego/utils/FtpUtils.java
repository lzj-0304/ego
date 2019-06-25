package com.shsxt.ego.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.InputStream;

public class FtpUtils {

	
	public static void main(String[] args) {
		
		/***
		 * 完成图片的上传，通过ftp将图片上传到图片服务器
		 * **/
		String hostname="192.168.30.30";
		int port=21;
		String username="ftpuser";
		String password="ftpuser";
		String pathname="/home/ftpuser/jd";
		String remote="demo.jpg";
		InputStream local=null;
		//uploadFile(hostname, port, username, password, pathname, remote,local);
	}

	public static boolean uploadFile(String hostname, 
			int port, String username, 
			String password, String pathname,
			String remote,InputStream local) {
		boolean flag=false;
		try{
			//创建FtpClient对象
			FTPClient client=new FTPClient();
			//建立和ftp服务器的链接
			client.connect(hostname, port);
			//登陆ftp服务器
			client.login(username, password);
			//设置上传的文件的类型
			client.setFileType(FTP.BINARY_FILE_TYPE);
			//切换工作目录，文件上传后保存到那个目录
			if(!client.changeWorkingDirectory(pathname)){
				if(client.makeDirectory(pathname)){
					client.changeWorkingDirectory(pathname);
				}
			}
			//实现文件上传
			flag=client.storeFile(remote, local);
			local.close();
			client.logout();
			client.disconnect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
}
