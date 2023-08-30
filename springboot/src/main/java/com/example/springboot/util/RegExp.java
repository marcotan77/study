package com.example.springboot.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public boolean match(String reg, String str) {
        return Pattern.matches(reg, str);
    }

    public List<String> find(String reg, String str) {
        Matcher matcher = Pattern.compile(reg).matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public List<String> find(String reg, String str, int index) {
        Matcher matcher = Pattern.compile(reg).matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group(index));
        }
        return list;
    }

    public String findString(String reg, String str, int index) {
        String returnStr = null;
        List<String> list = this.find(reg, str, index);
        if (list.size() != 0)
            returnStr = list.get(0);
        return returnStr;
    }

    public String findString(String reg, String str) {
        String returnStr = null;
        List<String> list = this.find(reg, str);
        if (list.size() != 0)
            returnStr = list.get(0);
        return returnStr;
    }

    public static List<String> getKeywords(String p) {
        String reg = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";
        RegExp re = new RegExp();
        List<String> list = re.find(reg, p);
        return list;
    }

    private static String getNewStr(String str,Map<String,String> map) {
        if (str.contains("$")) {
            String patternString = "\\$\\s*\\{\\s*(.+?)\\s*\\}";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(str);
            String text = "";
            while (matcher.find()) {
                String keyString = matcher.group(1);
                String value = map.get(keyString);
                text = matcher.replaceFirst(value);
                matcher = pattern.matcher(text);
            }
            return text;
        }
        return str;
    }

    public static void main(String[] args) {
        RegExp re = new RegExp();
        System.out.println(re.find("(a)b", "ababab", 1));
        Map<String,String> param = new HashMap<>();
        param.put("approvalNumber","AC202301030001");
        param.put("linkUrl","https://www.baidu.com");
        String templateContent = "您有[司机付款流程]待审批任务，审批编码:${ approvalNumber}，点击可${}查看具体审批内容!${linkUrl}";
        System.out.println(getNewStr(templateContent,param));
//        List<String> keywords = getKeywords(templateContent);
//        for (String keyword : keywords) {
//            templateContent.replace()
//        }
//
//        System.out.println(getKeywords("您有[司机付款流程]待审批任务，审批编码:${ approvalNumber}，点击可查看具体审批内容!"));
    }


}
