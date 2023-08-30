package com.tan.zookeeper.service;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

/**
 * zkClient 的 监听时间
 *
 * @author tanchusheng
 * @date 2023/8/30 17:58
 */
@Slf4j
@Service
public class ZkWatcher {

    private final ZkClient zkClient;
    private static final String NODE_PATH = "/path";
    private static final String LOG_SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++";

    public ZkWatcher (ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public void testSubscribeChildChanges () throws InterruptedException {

        /*
         * 匿名内部类重写是 IZkChildListener
         * 监听指定节点和指定节点下子节点的变化
         * parentPath：我们指定要监听的节点
         * currentChildren：指定要监听的节点下的所有子节点
         * 当指定节点和指定节点下子节点发生 增删 操作时会被监听到，更新节点子节点和父节点的内容是不会被监听的, 递归删除删除几次会触发几次
         * 这边可以看到日志 Delivering event #1 done
         */
        zkClient.subscribeChildChanges(NODE_PATH, (parentPath, currentChildren) -> {
            log.info("当前节点为: {}, 当前节点下的子节点为:{}", parentPath, currentChildren);
            log.info(LOG_SEPARATOR);
        });

        operateNode(zkClient);

    }

    public void testSubscribeDataChanges () throws InterruptedException {

        zkClient.subscribeDataChanges(NODE_PATH, new IZkDataListener() {

            /**
             * 监听节点的 删除操作
             * @param path 节点路径
             * @throws Exception 异常
             */
            @Override
            public void handleDataDeleted(String path) throws Exception {
                log.info("删除的节点为: {}", path);
                log.info(LOG_SEPARATOR);
            }

            /**
             * 可以监听节点的 创建 更新 操作
             * @param path 节点路径
             * @param data 变更的内容
             * @throws Exception 异常
             */
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                log.info("变更的节点为:{}, 变更内容为: {}",  path ,  data);
                log.info(LOG_SEPARATOR);
            }
        });

        operateNode(zkClient);

    }

    public static void operateNode (ZkClient zkClient) throws InterruptedException {

        // 递归删除
        zkClient.deleteRecursive(NODE_PATH);

        // 节点已经存在的话是会抛异常的 : ZkNodeExistsException
        zkClient.createPersistent(NODE_PATH);
        Thread.sleep(1000);

        //  SubscribeChildChanges 不会监听父节点的 update 操作； SubscribeDataChanges 会监听节点的 update 操作
        zkClient.writeData(NODE_PATH,"父节点发生变化");
        Thread.sleep(1000);

        zkClient.createPersistent(NODE_PATH + "/" + "c1", "c1内容");
        Thread.sleep(1000);

        zkClient.createPersistent(NODE_PATH + "/" + "c2", "c2内容");
        Thread.sleep(1000);

        //不会监听子节点的 update 操作
        zkClient.writeData(NODE_PATH + "/" + "c1","c1新内容");
        Thread.sleep(1000);

        zkClient.delete(NODE_PATH + "/c2");
        Thread.sleep(1000);

        // 使用此方法删除的节点如果不为空的话会抛异常 ZkException
        // zkClient.delete(NODE_PATH);

        // 递归删除: 这里发生了两次，一次是先删除/super/c1触发一次，然后再删除/super再触发一次
        zkClient.deleteRecursive(NODE_PATH);

        // 这个延时是必须的，因为如果你的 main 方法直接停止的话，监听程序也就关闭了，监听是异步的，后面的操作可能还没监听到线程就停止了这个不合理的
        Thread.sleep(10000);

        log.info("延时结束");

    }

}
