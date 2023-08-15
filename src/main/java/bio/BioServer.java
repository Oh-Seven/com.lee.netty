package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BioServer
 * @author lee
 * @version 1.0
 * @description TODO
 * @date 2023年08月15日 02:07:00
 */
public class BioServer {

    // 可以使用 telnet 127.0.0.1 6666 进行通信
    // 进入后 ctrl+} 随后使用send 命令和服务端进行通信
    public static void main(String[] args) {
        try {
            // 新建线程池
            ExecutorService executorService = Executors.newCachedThreadPool();
            // 服务器指定端口
            ServerSocket serverSocket = new ServerSocket(6666);

            System.out.println("服务器启动成功");

            while(true){
                // 等待客户端连接 这里回阻塞
                final Socket accept = serverSocket.accept();
                System.out.println("连接到一个客户端");

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        bioServerHandler(accept);
                    }
                });

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void bioServerHandler(Socket socket){
        try {
            //用于接收数据的数组
            byte[] bytes = new byte[1024];
            // 从socket中读取数据，并存储到bytes数组中
            InputStream inputStream = socket.getInputStream();

            while (true) {
                // 这里也会阻塞
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes, 0, read));
                }else{
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
