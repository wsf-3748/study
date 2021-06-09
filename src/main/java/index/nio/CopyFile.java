package index.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.*;

public class CopyFile {

    public static void copyFile(String src, String dst) throws IOException, FileNotFoundException {
        //源文件输入流
        FileInputStream fi = new FileInputStream(new File(src));
        //目标文件输出流
        FileOutputStream fo = new FileOutputStream(new File(dst));

        //获得传输通道channel
        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();

        //获得容器buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //判断是否读完文件：阻塞式
            int eof = inChannel.read(buffer);
            if (eof == -1) {
                break;
            }
            //重设一下buffer的limit=position，position=0
            buffer.flip();

            //开始写
            outChannel.write(buffer);

            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
    }

    /*
     * 分散读取(Scattering Reads)：将通道中的数据分散到多个缓冲区中
     * 聚集写入(Gathering Writes)：将多个缓冲区中的数据聚集到通道中
     */
    public void rw() throws IOException {
        // rw：代表读写模式
        RandomAccessFile file = new RandomAccessFile("E:\\全板测试视频（请首先观看本视频）.mp4","rw");
        //获取通道
        FileChannel channel = file.getChannel();

        // 分配制定缓冲区
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024*2);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024*2);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(1024*2);

        // 分散读取
        ByteBuffer[] buffers = {byteBuffer1,byteBuffer2,byteBuffer3};
        channel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip(); // flip() 方法将 Buffer 从写模式切换到读模式
        }

        // 聚集写入
        RandomAccessFile file2 = new RandomAccessFile("E:\\全板测试视频（请首先观看本视频）2.mp4","rw");
        // 获取通道
        FileChannel channel2 = file2.getChannel();
        channel2.write(buffers);

        channel.close();
        channel2.close();
    }

    public static void test() throws CharacterCodingException {
        //获取NIO字符集
        Charset cs = StandardCharsets.UTF_8;
        //获取编码器
        CharsetEncoder ce = cs.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs.newDecoder();

        //申请1024字节的空间地址
        CharBuffer cb = CharBuffer.allocate(1024);
        //写入内容
        cb.put("ld加油");

        //转化为读模式
        cb.flip();

        //编码
        ByteBuffer bB = ce.encode(cb);

        //编码后的内容
        //以什么编码就以什么解码
        //GBK一个中文字符占两个字节
        //UTF-8一个中文占三个字节
        for (int i = 0; i < 8; i++) {
            //get()返回字节之后，position会自动加1
            //get(index)返回字节后，position并未移动
            System.out.println(bB.get());
        }

        //转化为读模式
        bB.flip();

        //解码
        System.out.println(cd.decode(bB));
    }
}
