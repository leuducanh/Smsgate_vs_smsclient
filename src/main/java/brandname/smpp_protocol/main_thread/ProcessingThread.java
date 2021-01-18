package brandname.smpp_protocol.main_thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ProcessingThread implements Runnable {


    private String threadName;
    private boolean isProcessing = false;
    private int threadStatus = 0;
    public static final int THREAD_INITIAL = 0;
    public static final int THREAD_PROCESSING = 1;
    public static final int THREAD_STOP = 2;
    public static AtomicInteger threadIndex = new AtomicInteger(0);
    public static Object lock = new Object();

    public ProcessingThread(String threadGroupName) {
        this.threadName = generateThreadName(threadGroupName);
    }

    public void start() {

    }

    public int getProcessStatus() {
        synchronized (lock) {
            return threadStatus;
        }
    }

    private String generateThreadName(String name) {
        return String.format("%s-%s",name, getNextThreadIndex());
    }

    private int getNextThreadIndex() {
        return threadIndex.incrementAndGet();
    }

    @Override
    public void run() {

    }
}
