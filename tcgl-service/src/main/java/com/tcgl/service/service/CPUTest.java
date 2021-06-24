package com.tcgl.service.service;

/**
 * cpu
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 14:14
 */
import java.io.IOException;

/**
 * The type Cpu test.
 */
public class CPUTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CPUTestThread cpuTestThread = new CPUTestThread();

        for (int i = 0; i < 7; i++) {
            Thread cpuTest = new Thread(cpuTestThread);

            cpuTest.start();

        }

//Windows Task Manager shows

        try {
            Runtime.getRuntime().exec("taskmgr");

        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }

}


class CPUTestThread implements Runnable {
    @Override

    public void run() {
        int busyTime = 10;

        int idleTime = busyTime;

        long startTime = 0;

        while (true) {
            startTime = System.currentTimeMillis();

            System.out.println(System.currentTimeMillis()+","+startTime+","+(System.currentTimeMillis() - startTime));

// busy loop

            while ((System.currentTimeMillis() - startTime) <= busyTime)

                ;

// idle loop

            try {
                Thread.sleep(idleTime);

            } catch (InterruptedException e) {
                System.out.println(e);

            }

        }

    }

}

