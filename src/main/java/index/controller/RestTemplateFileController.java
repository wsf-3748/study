package index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

@RestController
public class RestTemplateFileController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/downSmallFile")
    public void test1() {
        Instant start = Instant.now();
        System.out.println("test1()");
        /*String url = "http://localhost:8080/GraduationProject/music/gaobaiqiqiu.mp3";
        String path = "D:/musicTest/gaobaiqiqiu.mp3";*/
        String url = "http://localhost:8080/GraduationProject/img/poster.png";
        String path = "D:/musicTest/poster1.png";
        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
        System.out.println("状态码：" + rsp.getStatusCode());
        try {
            Files.write(Paths.get(path), Objects.requireNonNull(rsp.getBody(), "未获取到下载文件"));
        } catch (Exception e) {
            System.out.println("下载失败：" + e.getMessage());
        }
        System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
    }

    @GetMapping("/downSmallFile2")
    public void test2() {
        Instant start = Instant.now();
        System.out.println("test2()");
        String url = "http://localhost:8080/GraduationProject/img/poster.png";
        String path = "D:/musicTest/poster2.png";
        URL ulrStr;
        InputStream is = null;
        OutputStream os = null;
        BufferedOutputStream buff = null;
        try {
            ulrStr = new URL(url);
            URLConnection connection = ulrStr.openConnection();

            is = connection.getInputStream();
            os = new FileOutputStream(path);
            buff = new BufferedOutputStream(os);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/downSmallFile3")
    public void test3() {
        Instant start = Instant.now();
        System.out.println("test3()");
        String url = "http://localhost:8080/GraduationProject/img/poster.png";
        String path = "D:/musicTest/poster3.png";
        URL ulrStr;
        InputStream is = null;
        OutputStream os = null;
        try {
            ulrStr = new URL(url);
            URLConnection connection = ulrStr.openConnection();

            is = connection.getInputStream();
            os = new FileOutputStream(path);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/downBigFile4")
    public void test4() {
        Instant start = Instant.now();
        System.out.println("test4()");
        String url = "http://localhost:8080/GraduationProject/music/test.mp4";
        String path = "D:/musicTest/1_test.mp4";
        /*String url = "http://localhost:8080/GraduationProject/img/musicLogo.jpg";
        String path = "D:/musicTest/musicLogo.jpg";*/

        //定义请求头的接收类型
        RequestCallback requestCallback = request -> request.getHeaders().
                setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
        //对响应进行流式处理而不是将其全部加载到内存中
        restTemplate.execute(url, HttpMethod.GET, requestCallback, ClientHttpResponse -> {
            Files.copy(ClientHttpResponse.getBody(), Paths.get(path));
            return null;
        });

        System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
    }

    @GetMapping("/downBigFile5")
    public void test5() {
        Instant start = Instant.now();
        System.out.println("test5()");
        String url = "http://localhost:8080/GraduationProject/music/test.mp4";
        String path = "D:/musicTest/2_test.mp4";
        URL ulrStr;
        InputStream is = null;
        OutputStream os = null;
        BufferedOutputStream buff = null;
        try {
            ulrStr = new URL(url);
            URLConnection connection = ulrStr.openConnection();

            is = connection.getInputStream();
            os = new FileOutputStream(path);
            buff = new BufferedOutputStream(os);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                buff.write(b, 0, len);
            }
            System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/downBigFile6")
    public void test6() {
        Instant start = Instant.now();
        System.out.println("test6()");
        String url = "http://localhost:8080/GraduationProject/music/test.mp4";
        String path = "D:/musicTest/3_test.mp4";
        URL ulrStr;
        InputStream is = null;
        OutputStream os = null;
        try {
            ulrStr = new URL(url);
            URLConnection connection = ulrStr.openConnection();

            is = connection.getInputStream();
            os = new FileOutputStream(path);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("下载完成，耗时：" + ChronoUnit.MILLIS.between(start, Instant.now()) + "ms");
        } catch (Exception e) {
            System.out.println("失败：" + e.getMessage());
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/uploadFile7")
    public void test7() {
        // 上传接口
        String url = "http://localhost:9080/MybatisTest/upload";
        // 待上传的文件
        String filePath = "D:/musicTest/poster3.png";
        // 封装请求参数
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);
        map.add("userName", "dafei");
        map.add("passWord", "123456");

        // 发送请求并输出结果
        System.out.println("正在上传文件...");
        String str = restTemplate.postForObject(url, map, String.class);
        System.out.println("返回：" + str);
    }

    @PostMapping("/upload")
    public String uploadFile(String userName, String passWord, MultipartFile file) {
        System.out.println("正在接收...");
        System.out.println("userName：" + userName);
        System.out.println("passWord：" + passWord);
        String fileName = file.getOriginalFilename();
        System.out.println("文件原始名：" + fileName);
        try {
            String content = new String(file.getBytes(), "UTF-8");
            System.out.println("内容：" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传完成";
    }
}
