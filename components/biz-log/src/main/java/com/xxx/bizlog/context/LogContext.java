package com.xxx.bizlog.context;

/**
 * 日志上下文
 *
 * @author dngzs
 * @date 2023年06月01日14:20:28
 */
public class LogContext {

    private static final InheritableThreadLocal<String> OPT_SEQUENCE_NUMBER = new InheritableThreadLocal<>();

    public static void putSequenceNumber(String sequenceNumber) {
        OPT_SEQUENCE_NUMBER.set(sequenceNumber);
    }

    public static String getSequenceNumber() {
        return OPT_SEQUENCE_NUMBER.get();
    }

    public static void clear() {
        OPT_SEQUENCE_NUMBER.remove();
    }
}
