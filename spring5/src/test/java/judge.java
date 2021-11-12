import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/5/22 10:55
 **/
public class judge {

    @Test
    public void isNull(){

        String str = "当日20：00";
        String pattern  ="\\D";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String result = m.replaceAll("").trim();
        Character ch=result.charAt(0);
        int index=str.indexOf(ch);
        String type = str.substring(0,index);
        String time = str.substring(index);
        System.out.println(type+"--"+time);

    }

}
