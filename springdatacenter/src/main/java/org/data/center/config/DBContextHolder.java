package org.data.center.config;


import org.data.center.type.DBType;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBType dbType) {
        contextHolder.set(dbType);
    }

    public static DBType get() {
        return contextHolder.get();
    }


    public static void master() {
        set(DBType.MASTER);
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBType.SLAVE);
        } else if (index == 1) {
            set(DBType.SLAVE);
        }
    }

}
