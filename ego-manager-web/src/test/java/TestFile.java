import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestFile {

    @Test
    public void  uploadFile() {
        boolean flag = false;
        try {
            //创建 FtpClient 对象
            FTPClient client = new FTPClient();
            //建立和 ftp服务器的链接
            client.connect("www.ego.com", 21);
            //登陆 ftp服务器
            Boolean isLogin = client.login("ftpuser", "1qaz2wsx");
            System.out.println(isLogin);
            //设置上传的文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //切换工作目录，文件上传后保存到那个目录
            if (!client.changeWorkingDirectory("/home/ftpuser/images")) {
                if (client.makeDirectory("/home/ftpuser/images")) {
                    client.changeWorkingDirectory("/home/ftpuser/images");
                }
            }
           InputStream local = new FileInputStream("C:\\Users\\lp\\Desktop\\3.jpg");
            //实现文件上传
            Boolean result = client.storeFile("2423123.jpg", local);
            System.out.println(result);
            local.close();
            client.logout();
            client.disconnect();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}