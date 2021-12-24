package com.tan.thread.MultiThreadTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 具体执行业务任务 需要 实现ITask接口  在execute中重写业务逻辑
 * TestTask<BR>
 *
 * @version 2.0
 */
public class TestTask implements ITask<ResultBean<String>, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Override
    public ResultBean execute(Integer e, Map<String, Object> params) {
        /**
         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
         */
        int addNum = Integer.valueOf(String.valueOf(params.get("addNum")));
        e = e + addNum;
        ResultBean<String> resultBean = ResultBean.newInstance();
        resultBean.setData(e.toString());
        return resultBean;
    }

    public static void main(String[] args) {
        // 需要多线程处理的大量数据list
        List<Integer> data = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            data.add(i + 1);
        }

        // 创建多线程处理任务
        MultiThreadUtil<Integer> threadUtils = MultiThreadUtil.newInstance(5);
        ITask<ResultBean<String>, Integer> task = new TestTask();
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("addNum", 4);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(data, params, task);
        logger.info(resultBean.json());
    }


}