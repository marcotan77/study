package com.xxx.prototype;

import com.xxx.prototype.after.QuestionBankController;
import org.junit.Test;

/**
 * @author tanchusheng
 * @date 2024/7/23 15:58
 */
public class ApiTest_After {

    @Test
    public void test_QuestionBank() throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }

}
