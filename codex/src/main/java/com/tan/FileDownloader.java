package com.tan;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FileDownloader {
    public static void main(String[] args) {

        List<String> pics = getPics();
        pics.forEach(item -> {
            String saveDir = "pic/";
            try {
                System.out.println("下载 ---："+item.substring(item.lastIndexOf("/") + 1));
                downloadFile(item, saveDir);
                System.out.println("文件下载成功！");
            } catch (IOException e) {
                System.out.println("文件下载失败: " + e.getMessage());
            }
        });

    }

    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);

        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream out = new FileOutputStream(saveDir + fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        }
    }

    public  static List<String> getPics(){
        String[] pics = new String[]{

                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670777012978166.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670794192847216.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670776878760304.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670776744542710.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670736646996206.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670736412115182.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670735791358454.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670735371927790.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670715809694064.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670715608367342.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670715172159990.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670714584957430.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670714282967542.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670696935326198.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670696281014510.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670695995801968.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670694989169142.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670695727366646.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670695526105326.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670694687178990.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670677155054070.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670676601340406.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670676165132790.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430670673464000880.jpg"

                    };

        return Arrays.asList(pics);
    };
}
