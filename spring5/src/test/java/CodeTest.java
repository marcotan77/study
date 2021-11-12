import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @version 1.0
 * @description:
 * @author: Tcs
 * @date: 2021-03-03 09:31
 **/
public class CodeTest {

    public static void main(String[] args) {
        String a = "469024";
        String substring = a.substring(2, 3);
        System.out.println(substring);

        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());

//        getPolygon();
    }

    public static void getPolygon(){
        String polygon  = "POLYGON((116.2342415 40.20340.2391273,116.2306269 40.2393104,292,116.2446235 40.2038273,116.2407883 40.2037451,116.2342415 40.2036081))";
//        int start = polygon.lastIndexOf("(")+1;
//        int end = polygon.indexOf(")");
//        String substring = polygon.substring(start, end);

        String replace = polygon.replace("POLYGON((", "").replace("))", "");
        String[] split1 = replace.split("\\)\\),\\(\\(");
        System.out.println(replace.equals(replace));
        String[] strings = replace.split(",");
        List<List<Double>> list = new ArrayList<>();
        for (String s: strings) {
            String[] split = s.split(" ");
            List<Double> doubles = new ArrayList<>();
            for (String s1: split) {
                Double a = Double.valueOf(s1);
                doubles.add(a);
            }
            list.add(doubles);
        }
        JSONObject object = new JSONObject();
        object.put("type","Polygon");
        object.put("coordinates",list);
        System.out.println(object);

    }


// 根据orderList顺序排序，orderList不存在的元素放置在targetList最后面
private static void sort1(List<String> orderList, List<String> targetList) {
    targetList.sort(((o1, o2) -> {
        int io1 = orderList.indexOf(o1);
        int io2 = orderList.indexOf(o2);
        if (io1 != -1) {
            io1 = targetList.size() - io1;
        }
        if (io2 != -1) {
            io2 = targetList.size() - io2;
        }
        return io2 - io1;
    }));
}
    // 根据orderList顺序排序，orderList不存在的元素放置在targetList最前面
    private static void sort2(List<Integer> orderList, JSONArray targetList) {
        targetList.sort(((obj1, obj2) -> {
            int io1 = orderList.indexOf(((JSONObject) obj1).get("id"));
            int io2 = orderList.indexOf(((JSONObject) obj2).get("id"));
            return io1 - io2;
        }));
        System.out.println(targetList);
    }
//    public static void main(String[] args) {
//        List<JSONObject> result =new ArrayList<>();
//        List<JSONObject> result1 =new ArrayList<>();
//        HashMap<String, JSONObject> map = new HashMap<String,JSONObject>();
//        for (JSONObject sameOr : result) {
//            map.put(sameOr.get("siteId").toString(), sameOr);
//        }
//        for (JSONObject sameOr : result1) {
//            String sameOrNumber = sameOr.get("_id").toString();
//            JSONObject sameOr1 = map.get(sameOrNumber);
//            if (sameOr1 == null) {
//                continue;
//            }
//            sameOr.putAll(sameOr1);
//        }
//    }

}
