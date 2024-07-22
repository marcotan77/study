package com.tan.test;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author tanchusheng
 * @date 2024/1/24 13:17
 */
public class RouteTestMain {

    public static void main(String[] args) {
        List<RouteDO> originRouteList = Arrays.asList(
                new RouteDO("A", 1,1, "121.213822,31.219627", 0, "上海市青浦区华新镇E通世界商务园华新园D座"),
                new RouteDO("A", 2, 2,"121.22425,31.220995", 0, "上海市青浦区华新镇陆家圩小区陆家圩小区华隆路1100弄")
//                new RouteDO("B", 1, "121.229808,31.219949", 0, "上海市青浦区华新镇华隆公园"),
//                new RouteDO("B", 2, "121.218886,31.218159", 0, "上海市青浦区华新镇华重公路辅路"),
//                new RouteDO("B", 2, "121.236599,31.221591", 0, "上海市青浦区华新镇上海西郊国际农产品交易中心")
        );

        // 根据 routeOrder 升序排序, 找出routeType 小于前面的 routeOrder 的数据

        RouteDO first = new RouteDO("A", 1,1, "121.213822,31.219627", 0, "上海市青浦区华新镇E通世界商务园华新园D座");

        List<RouteDO> routeDOS = closestRoutes(originRouteList, first);

        System.out.println("结果：" + JSON.toJSONString(routeDOS));
        System.out.println(calculateDistance("121.213822,31.219627", "121.229808,31.219949"));
        System.out.println(calculateDistance("121.213822,31.219627", "121.22425,31.220995"));
        // 定义一个新的resultList 初始化first加入到list
        // 1. 已知有一个list集合，需要根据lotlat计算出每条数据与 first 之间距离,将距离保存到distance
        // 2. 然后找出distance最小的RouteDO，把 RouteDO 加入到 resultList 中 ，保证resultList中相同的orderNo ,routeType 要从小到大排序
        // 3. 知道原始集合都加入到resultList 结束
        // 3. distance最小的数据赋值给first，
        // 继续重复1，2，3 步，直到原始list为空
        //根据calculateDistance 方法计算出距离


    }

    static List<RouteDO> closestRoutes(List<RouteDO> originRouteList, RouteDO first) {
        List<RouteDO> minDistanceList = new ArrayList<>();
        List<RouteDO> routeDOS = new ArrayList<>(originRouteList);

        while (!routeDOS.isEmpty()) {
            for (RouteDO route : routeDOS) {
                route.setDistance(calculateDistance(first, route));
            }

//            System.out.println("first:"+first.getOrderNo()+"_"+first.getRouteType()+"_"+first.getLotLat());

            // Sort the list based on routeType and distance
            routeDOS.sort(Comparator.comparing(RouteDO::getDistance));

//            Collections.sort(routeDOS, Comparator
//                    .comparing(RouteDO::getOrderNo)
//                    .thenComparing(RouteDO::getRouteType)
//                    .thenComparingDouble(RouteDO::getDistance));

////            Collections.sort(routeDOS, Comparator.comparing(RouteDO::getOrderNo)
////                    .thenComparing(RouteDO::getRouteType)
////                    .thenComparingDouble(RouteDO::getDistance));
//
//            for (int i = 0; i < routeDOS.size(); i++) {
//                for (int j = i + 1; j < routeDOS.size(); j++) {
//                    if (compare(routeDOS.get(i), routeDOS.get(j))) {
//                        Collections.swap(routeDOS, i, j);
//                    }
//                }
//            }

            for (RouteDO routeDO : routeDOS) {
                if (routeDO.getRouteType().equals(1)) {
                    first = routeDO;
                    break;
                } else {
                    boolean b = minDistanceList.stream().anyMatch(i -> i.getOrderNo().equals(routeDO.getOrderNo()));
                    if (b) {
                        first = routeDO;
                        break;
                    }
                }
            }


            System.out.println("排序之后：" + JSON.toJSONString(routeDOS));

            // Update the first with the minimum distance route
//            first = routeDOS.get(0);


            // Add the minimum distance route to the list
            minDistanceList.add(first);

            // Remove all routes with the same orderNo and routeType from the original list
            RouteDO finalFirst = first;
            routeDOS.removeIf(route ->
                    route.getOrderNo().equals(finalFirst.getOrderNo())
                            && route.getRouteType().equals(finalFirst.getRouteType())
                            && route.getLotLat().equals(finalFirst.getLotLat())
            );

//            System.out.println("Minimum Distance Route: " + first.getOrderNo() + ", " + first.getRouteType() +
//                    ", Distance: " + first.getDistance());
        }

        // Print or return the final minDistanceList
//        System.out.println("Final Minimum Distance List: " + minDistanceList);
        return minDistanceList;
    }

    private static boolean compare(RouteDO ewbRouteDO, RouteDO ewbRouteDO1) {
        if (ewbRouteDO == null || ewbRouteDO1 == null) {
            // 如果其中一个对象为空，直接返回false
            return false;
        }

        if (ewbRouteDO.getOrderNo().equals(ewbRouteDO1.getOrderNo())) {
            if (ewbRouteDO.getRouteType() > ewbRouteDO1.getRouteType()) {
                return true;
            } else if (ewbRouteDO.getRouteType().equals(ewbRouteDO1.getRouteType())) {
                return ewbRouteDO.getDistance() > ewbRouteDO1.getDistance();
            }
        } else {
            return ewbRouteDO.getDistance() > ewbRouteDO1.getDistance();
        }
        return false;
    }


    private static double calculateDistance(RouteDO r1, RouteDO r2) {
        String[] first1 = r1.getLotLat().split(",");
        String[] end1 = r2.getLotLat().split(",");
        double lat1 = Double.parseDouble(first1[0]);
        double lon1 = Double.parseDouble(first1[1]);
        double lat2 = Double.parseDouble(end1[0]);
        double lon2 = Double.parseDouble(end1[1]);

        // 将角度转换为弧度
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // Haversine公式计算球面上两点之间的距离
        double a = Math.sin((radLat2 - radLat1) / 2) * Math.sin((radLat2 - radLat1) / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin((radLon2 - radLon1) / 2) * Math.sin((radLon2 - radLon1) / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371000 * c; // 地球平均半径（单位：米）
        return distance;
    }

    private static double calculateDistance(String r1, String r2) {
        String[] first1 = r1.split(",");
        String[] end1 = r2.split(",");
        double lat1 = Double.parseDouble(first1[0]);
        double lon1 = Double.parseDouble(first1[1]);
        double lat2 = Double.parseDouble(end1[0]);
        double lon2 = Double.parseDouble(end1[1]);

        // 将角度转换为弧度
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // Haversine公式计算球面上两点之间的距离
        double a = Math.sin((radLat2 - radLat1) / 2) * Math.sin((radLat2 - radLat1) / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin((radLon2 - radLon1) / 2) * Math.sin((radLon2 - radLon1) / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371000 * c; // 地球平均半径（单位：米）
        return distance;
    }

}
