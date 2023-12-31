package nio;


import java.nio.IntBuffer;


public class NioBufferTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for(int i =0;i<intBuffer.capacity();i++){
            intBuffer.put(i);
        }

        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
