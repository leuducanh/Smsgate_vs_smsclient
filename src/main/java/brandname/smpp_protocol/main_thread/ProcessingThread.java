package brandname.smpp_protocol.main_thread;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProcessingThread implements Runnable {


    private String threadGroupName;
    private String threadName;
    private int threadStatus = 0;
    public static final int THREAD_INITIAL = 0;
    public static final int THREAD_PROCESSING = 1;
    public static final int THREAD_STOP = 2;
    public static AtomicInteger threadIndexGlobal = new AtomicInteger(0);
    private int threadIndex;
    private Object lock = new Object();

    public AtomicInteger currentThreadStatus = new AtomicInteger(0);

    public boolean keepProcessing = false;

    public ProcessingThread(String threadGroupName) {
        this.threadGroupName = threadGroupName;
    }

    public void start() {
        if (setInitializeState()) {
            keepProcessing = true;
            Thread wrapperThread = new Thread(this);
            threadIndex = threadIndexGlobal.incrementAndGet();
            this.threadName = generateThreadName(threadGroupName);
            wrapperThread.setName(threadName);
            wrapperThread.start();

            // wait for this runable running and change state
            while (isInInitializeState()) {
                Thread.yield();
            }
        } else {
            // dang o trang thai stop khoong the chay duoc
            throw new IllegalStateException();
        }
    }

    public int getProcessStatus() {
        synchronized (lock) {
            return threadStatus;
        }
    }

    public boolean isInInitializeState() {
        return currentThreadStatus.get() == THREAD_INITIAL;
    }

    public boolean isInProcessingState() {
        return currentThreadStatus.get() == THREAD_PROCESSING;
    }

    public boolean isInStopState() {
        return currentThreadStatus.get() == THREAD_STOP;
    }

    public boolean setInitializeState() {
        boolean changeStateSuccessfully = false;
        while (!changeStateSuccessfully) {
            if (isInInitializeState()) {
                return true;
            }
            if (isInProcessingState()) {
                return false;
            }
            if (isInStopState()) {
                changeStateSuccessfully = currentThreadStatus.compareAndSet(THREAD_STOP, THREAD_INITIAL);
            }
        }
        return changeStateSuccessfully;
    }

    public boolean setProcessingState() {
        boolean changeStateSuccessfully = false;
        while (!changeStateSuccessfully) {
            if (isInProcessingState()) {
                return true;
            }
            if (isInStopState()) {
                return false;
            }
            if (isInInitializeState()) {
                changeStateSuccessfully = currentThreadStatus.compareAndSet(THREAD_INITIAL, THREAD_PROCESSING);
            }
        }
        return changeStateSuccessfully;
    }

    public void stop() {
        if(isInProcessingState()) {
            keepProcessing = false;
            while(isInProcessingState()) {
                Thread.yield();
            }
        }
    }



    public boolean setStopState() {
        boolean changeStateSuccessfully = false;
        while (!changeStateSuccessfully) {
            if (isInStopState()) {
                return true;
            }
            if (isInInitializeState()) {
                return false;
            }
            if (isInProcessingState()) {
                changeStateSuccessfully = currentThreadStatus.compareAndSet(THREAD_PROCESSING, THREAD_STOP);
            }
        }
        return changeStateSuccessfully;
    }


    private String generateThreadName(String name) {
        return String.format("%s-%s", name, threadIndex);
    }

    public abstract void process();

    @Override
    public void run() {
        if (setProcessingState()) {
            try{
                while(keepProcessing) {
                    process();
                    Thread.yield();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                setStopState();
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
