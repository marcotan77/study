package com.tan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanchusheng
 * @date 2023/8/24 17:05
 */
public class HtmlParseUtil {

    public static void main(String[] args) throws IOException {


        downPic(getPics());
//        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
    }
    public List<String> parseJD(String keywords) throws IOException{

        List<String> bookList = new ArrayList<String>();
        for (int i = 101; i < 300; i++) {
//            https://gz.17zwd.com/qijian/_api/shop/detail/getShopGoods?shopId=18367&spm=&page=3&_uid=c49341

            String url = "https://gz.17zwd.com/shop/18367.htm";

            // 解析网页(jsoup返回document就是浏览器document的对象)
            Document document = Jsoup.parse(new URL(url), 30000);

            Element element = document.getElementsByClass("fly-panel").get(0);
            // 获取所有的li元素
//        Elements elements = element.getElementsByTag("li")
            Integer id = i;
            String name = element.getElementsByTag("h1").eq(0).text();
            String author = element.getElementsByTag("span").eq(0).text().substring(3);
            String dynasty = element.getElementsByTag("span" ).eq(1).text().substring(3);
            String type = element.getElementsByTag("span" ).eq(2).text().substring(3);
            String content = element.getElementsByClass("detail-body").text();
            String authorDetails = element.getElementsByTag("p").eq(1).text();

            bookList.add(content);
            // 获取元素中的内容
//        for (Element el:elements) {
//            // 关于这种图片特别多的网站，所有的图片都是延迟加载的
//            String name = el.getElementsByTag("a").eq(0).text();
//            String author = el.getElementsByTag("a").eq(1).text().substring(3);
//            String dynasty = el.getElementsByTag("span" ).eq(0).text().substring(3);
//            String type = el.getElementsByTag("span" ).eq(1).text().substring(3);
//            //String price = el.getElementsByTag("p-price").eq(0).text();
//            bookList.add(new SysPoem());
////            System.out.println("img"+img);
////            System.out.println(price);
////            System.out.println(title);
////            System.out.println();
//        }
        }
        return bookList;

    }


    public static void downPic(List<String> pics) {
        pics.forEach(item -> {
            HttpsURLConnection httpsURLConnection = null;
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                //1.创建URL对象
                URL url = new URL(item);
                //2.与URL建立连接：首先要在一个 URL 对象上通过方法 openConnection() 生成对应的 URLConnection
                //对象。
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.connect();
                //3.获取输入流，并创建输出流对象
                is = httpsURLConnection.getInputStream();
                System.out.println("下载 ---："+item.substring(item.lastIndexOf("/") + 1));
                fos = new FileOutputStream(new File(item.substring(item.lastIndexOf("/") + 1)));
                //4.输出图片
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //5.关闭资源
                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpsURLConnection != null)
                    httpsURLConnection.disconnect();
            }
        });

    }

    public  static List<String> getPics(){
        String[] pics = new String[]{"https://img01-gms.17zwd.com/imgextra/62010032/i1/6ae8a625-adda-4793-98b4-76b154743958.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/92924d98-2f2f-4b73-be20-4a1c88fd260d.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/3eb9df7d-af05-4f9d-a61a-98feb8d5d28d.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/e70ed3de-d9ae-4fc4-be43-f50ab40243f0.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/45980af3-fa41-484c-b01a-40521c8042fd.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/5aa77f66-c5e2-4862-8f76-71a9f4a44a0d.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/f2bfc168-10ee-4eef-be00-06eb34a3dcf8.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/595bbf2b-bf54-436a-b442-2c438b5d7d95.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/6dbb293b-db94-4b0d-8155-95d4671d3841.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/60a6f710-2a30-4d34-ac5c-d100dc62eebb.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/09aa6d8f-9666-4dd1-9e5d-8cdd3f1e50ca.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/256a37c8-ffa6-4b4a-9a52-c49dc830bccf.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/498eb36b-0443-4648-ab6d-9d4176cd1927.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/f557abaa-c1f1-4c74-8ef1-2c0042d758d1.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/a94cd7ad-7b65-4746-847c-b140d436f097.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/0bdfea50-4228-4379-ad20-f85736cb8218.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/4166ef4e-e388-4f82-90e2-da9f60a17b55.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/5be340ed-1ac5-4783-9a92-25a668993974.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/e35c79f6-4921-4587-a460-66ebd3fe353e.jpeg",
                "https://img.alicdn.com/bao/uploaded/i1/1738343413/O1CN01RGl72K1b5CUSIO9yG_!!1738343413.jpg",
                "https://img.alicdn.com/bao/uploaded/i2/1738343413/O1CN011ry1IJ1b5CR1WzLr7_!!1738343413.jpg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/0649900f-ddaa-4d47-bc56-b9d9485c4a1c.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/1a9b3b65-3a9d-419d-a7ed-21f42e2fe86b.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/1356f08d-63f9-4d4e-93af-592993fd6142.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/aa053185-0cf1-49d7-95a1-edacba0253ab.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/c7006abe-8f6b-43f9-b524-f73af85ecdd1.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/c13e3856-efac-4fa1-a443-ec3a0007bc81.jpeg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430671376563568878.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430671357739532790.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430671376261579120.jpg",
                "https://img01-gms.17zwd.com/imgextra01/62010032/i1/430671182082081006.jpg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/88dcd019-4420-48b0-bfa6-c516b4074b12.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/0d32d243-aa34-4236-a637-4c22c8567293.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/85214d97-5db4-4559-be6f-be5460ce5e71.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/61d6cdac-cce8-4f58-bb69-6095f77c0ede.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/d5edf43f-6125-41b8-a289-5fdcc7ed3469.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/41549a2e-a531-4306-b766-6ccdd261c2b8.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/e0bcd614-7799-4c68-9cf2-0638b98fcef2.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/24fd7f2e-dcc9-47e3-845b-c0905fc26358.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/88b1d9ec-f100-4659-8358-cff62d3d1685.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/724a6850-cf13-44ee-8488-583ea7fe54ac.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/5fae3e38-f68e-419d-9894-795385f3be03.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/a98ce652-c0a4-4571-8b31-752d8c8f919e.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/b39f3097-02cd-4d34-b23d-22784c8a071e.png",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/23b53faf-b394-41c1-9905-b578deb952d5.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/ee4c383f-66d6-4bc0-8f50-9c06b157581a.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/53274b68-00fb-4d3d-9e67-3c0c9c319687.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/5293f447-9d5a-4322-91c5-e08321a1e44d.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/1b8c8f12-7e6c-4df6-b5b8-3b287a827a96.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/110a1bcc-9674-4c92-8841-57b1b8519fef.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/7886e447-6c12-4fab-865d-d0b6c833dfe6.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/14295371-fe39-49ac-a636-1d47188758aa.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/e113adb4-6d2d-4c92-8aba-f81add3d937e.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/d6578595-c9af-4698-a040-fd3aae6e1aa9.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/b9105232-d179-4592-b703-6169605c05a4.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/09fd8fb7-f846-4731-89d2-1947713d0a6a.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/a52e7127-768f-4f2d-99b2-d71fecd180a3.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/462cd76e-4b07-4816-ab76-aa1a7c7705e8.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/f92f8af0-548b-4671-8d7b-53a21c2118f0.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/fec18b48-02eb-4110-9364-5e686fd910de.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/610a8862-30a6-472d-9f59-6c28039e6863.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/d5dffab5-2af1-4c5b-83b1-b60a06a6bfc4.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/863e6006-6e70-41dd-aae8-4ba36951aad8.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/fe9b314b-f105-4cb0-9453-40b831d1c459.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/443cbf7f-e2f0-46fc-a165-7eafe5548690.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/c56b508c-384d-4749-98c3-be0b52f62254.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/1e317e4f-3350-4c74-a220-af793cdd50a5.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/cfbf81f1-03fb-421e-be93-a406e22c4c0d.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/d96ab94f-6aa3-4632-9dc9-2c48f9cb7501.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/74f2030b-95b8-44f3-87f2-b76ac46a3369.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/5ad4dca9-cb75-4cec-bf83-28e7ec726cce.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/6c57a7fc-ca5f-4e0b-b1bd-da21de7f069f.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/2e5600d8-eade-4f61-9bb9-d59bae4c8af4.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/678ebec2-88dc-4128-9477-b2f781affb73.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/88e447f7-edc6-45c6-acb9-07014dd7190f.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/b027ad1f-4836-4f60-843d-e50e82186254.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/f2d9ac77-3a59-4b2c-9acc-8f5bb59f640e.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/81b9ef2d-fe50-44ae-aff4-983aea12b649.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/dc69bacd-6cfe-4b89-812f-9d7b0358dbd3.jpeg",
                "https://img01-gms.17zwd.com/imgextra/62010032/i1/54c7a9bc-4429-45bf-8af7-89b6faba5720.jpeg"};

        return Arrays.asList(pics);
    };

}
