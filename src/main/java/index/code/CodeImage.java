package index.code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public class CodeImage {

    private static final String OUTPUT_PATH = "E:/test/2.png";
    private static final String CHARSET = "UTF-8";
    private static final String LOGO = "";

    public static void main(String[] args) {
//        String text = "https://m.baidu.com";
        String text = "https://www.baidu.com/s?wd=%E6%88%91%E6%98%AF%E5%A4%A7%E5%82%BB%E9%80%BC&rsv_spt=1&rsv_iqid=0xee3700020006902b&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_dl=tb&rsv_sug3=9&rsv_sug1=7&rsv_sug7=100&rsv_enter=1&rsv_sug2=0&rsv_btype=i&prefixsug=%25E6%2588%2591%25E6%2598%25AF%25E5%25A4%25A7%25E5%2582%25BB%25E9%2580%25BC&rsp=2&inputT=5297&rsv_sug4=6767";
        int width = 300;
        int height = 300;
        try {
            long s = System.currentTimeMillis();
            generateQRCodeImage(text, width, height);
            long e = System.currentTimeMillis();
            System.out.println("耗时：" + (e-s) + "ms");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hashTable = new Hashtable<>();
        hashTable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashTable.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hashTable.put(EncodeHintType.MARGIN, 2);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hashTable);
        Path path = FileSystems.getDefault().getPath(CodeImage.OUTPUT_PATH);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    private static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hashTable = new Hashtable<>();
        hashTable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashTable.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hashTable.put(EncodeHintType.MARGIN, 2);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hashTable);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }
}
