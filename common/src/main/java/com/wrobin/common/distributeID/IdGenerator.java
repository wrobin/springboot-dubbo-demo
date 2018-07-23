package com.wrobin.common.distributeID;

import com.wrobin.common.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

/**
 * 分布式ID生成器
 * busId 业务线   5
 * workId 机器id  9（最好不同的进程配置成不同的workerId)
 * timestamp 时间戳 41
 * sequence  8
 * timestamp(41) | busId(5) | workId(9) | sequence(8)
 *
 * created by robin.wu on 2018/3/16
 */
public class IdGenerator {
    private long busId;
    private long workerId;
    private long sequence;
    private long lastTimestamp = -1L;

    private static final long busIdBits = 5L;
    private static final long workerIdBits = 9L;
    private static final long sequenceBits = 8L;
    private static final long epoch ;  //初始时间戳

    private static final long maxBusId = -1L ^ (-1L << busIdBits);
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    private static final long workerIdShit = sequenceBits;
    private static final long busIdShift = sequenceBits + workerIdBits;
    private static final long timestampShift = sequenceBits + workerIdBits + busIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static final IdGenerator instance = new IdGenerator();


    private static final String DEFAULT_APPLICATION_FILE = "application.properties";
    private static final String CONFIG_CURR_WORKER = "wrobin.curr.worker"; //虚拟机配置中或者 application.properties的配置 机器id
    private static final String CONFIG_CURR_BUS ="wrobin.curr.bus";//虚拟机配置中或者 application.properties的配置 机器id

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        epoch = calendar.getTimeInMillis();
        init();
    }

    /**
     * busId,workerId初始化
     */
    private static void init(){
        String workerId = System.getProperty(CONFIG_CURR_WORKER);
         if (isNotEmpty(workerId)) {
            instance.workerId(Long.valueOf(workerId));
        }

        String busId = System.getProperty(CONFIG_CURR_BUS);
        if (isNotEmpty(busId)) {
            instance.busId(Long.valueOf(busId));
        }

        Properties prop = loadProperties();
        workerId = prop.getProperty(CONFIG_CURR_WORKER);
        busId = prop.getProperty(CONFIG_CURR_BUS);
        if(isNotEmpty(workerId)) {
            instance.workerId(Long.valueOf(workerId));
        }
        if(isNotEmpty(busId)) {
            instance.busId(Long.valueOf(busId));
        }
    }

    private static Properties loadProperties(){
        Properties prop = new Properties();
        InputStream stream = null;

        try {
            stream = IdGenerator.class.getClassLoader().getResourceAsStream(DEFAULT_APPLICATION_FILE);
            if(stream !=null) {
                prop.load(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(stream !=null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }


    private IdGenerator(){}

    public static IdGenerator instance(){
        return instance;
    }

    public IdGenerator busId(final Long busIdParam) {
        if(busIdParam < 0 || busIdParam > maxBusId) {
            throw new IllegalArgumentException(String.format("business Id can't be greater than %d or less than 0",maxBusId));
        }
        this.busId = busIdParam;
        return this;
    }
    public IdGenerator workerId(final Long workerIdParam) {
        if(workerIdParam < 0 || workerIdParam >  maxWorkerId) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        this.workerId = workerIdParam;
        return this;
    }


    /**
     * 获取Id
     * @return
     */
    public Long nextId() {
        Long timestamp = this.timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException(StringUtil.format("clock is moving backwards.  Rejecting requests until %d.\n", lastTimestamp));
        }

        if (this.lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp =  timestamp;
        return  ((timestamp - epoch) << timestampShift) |
                (busId << busIdShift) |
                (workerId << workerIdShit) |
                sequence;
    }

    /**
     * 处理高并发情况下，时间戳相同的情况
     * @param lastTimestamp
     * @return
     */
    protected Long tilNextMillis(Long lastTimestamp){
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前系统时间戳
     * @return
     */
    protected Long timeGen(){
        return System.currentTimeMillis();
    }

    private static boolean isNotEmpty(String str){
        return str !=null && str.length() >0;
    }

}
