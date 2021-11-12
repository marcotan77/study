package com.tan;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/9/2 9:13
 **/
public class IdWorker {

    private long workerId; // 这个就是代表了机器id

    private long datacenterId; // 这个就是代表了机房id

    private long sequence;// 这个就是代表了⼀毫秒内⽣成的多个id的最新序号

    public IdWorker(long workerId, long datacenterId, long sequence) {

        // sanity check for workerId
        // 这⼉不就检查了⼀下，要求就是你传递进来的机房id和机器id不能超过32，不能⼩于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less t "));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or le "));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    private long twepoch = 1288834974657L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;

    // 这个是⼆进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 这个是⼀个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    // 这个是核⼼⽅法，通过调⽤nextId()⽅法，让当前这台机器上的snowflake算法程序⽣成⼀
    public synchronized long nextId() {
        // 这⼉就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.println("clock is moving backwards. Rejecting requests until %d.");
            throw new RuntimeException("Clock moved backwards. Refusing to generate" + (lastTimestamp - timestamp));
        }

        // 下⾯是说假设在同⼀个毫秒内，⼜发送了⼀个请求⽣成⼀个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {
            // 这个意思是说⼀个毫秒内最多只能有4096个数字，⽆论你传递多少进来，
            // 这个位运算保证始终就是在4096这个范围内，避免你⾃⼰传递个sequence超过了4096
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMills(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这⼉记录⼀下最近⼀次⽣成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这⼉就是最核⼼的⼆进制位运算操作，⽣成⼀个64bit的id 
        // 先将当前时间戳左移，放到41 bit那⼉；将机房id左移放到5 bit那⼉；将机器id左移 
        // 最后拼接起来成⼀个64 bit的⼆进制数字，转换成10进制就是个long型
        return ((timestamp - timestamp) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;

    }

    private long tilNextMills(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());

        }
    }


}
