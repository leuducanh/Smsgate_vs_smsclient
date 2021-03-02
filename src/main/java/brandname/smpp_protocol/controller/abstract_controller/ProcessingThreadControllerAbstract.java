package brandname.smpp_protocol.controller.abstract_controller;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProcessingThreadControllerAbstract implements Runnable {
    public static final int INITIAL = 0;
    public static final int RUNNING = 1;
    public static final int STOPPING = 2;
    public static final int SHUTDOWN = 3;
    private int threadId;
    private String threadName;
    private AtomicInteger currentStatus = new AtomicInteger(0);
    private static AtomicInteger globalId = new AtomicInteger(1);
    private String groupName;

    public ProcessingThreadControllerAbstract(String groupName) {
        this.groupName = groupName;

        setInitialState();
        if(isInitialState()) {
            threadId =globalId.getAndIncrement();
            threadName = groupName + "_" + threadId;
        }
    }

    public int getCurrentStatus() {
        return currentStatus.get();
    }


    protected abstract void process();

    protected abstract void releaseResource();

    @Override
    public void run() {
        if(setRunningState()) {
            try {
                while (isRunningState()) {
                    process();
                }
            } catch (Exception e) {

            } finally {
                releaseResource();
                setShutdownState();
            }
        }
    }

    public boolean start() {
        if(isInitialState()){
            Thread wrapperThread = new Thread(this);
            wrapperThread.setName(threadName);
            wrapperThread.start();
            while(isInitialState()) {
                Thread.yield();
            }
            return true;
        }
        return false;
    }

    public boolean shutdown() {
        boolean isStopped = setStopState();
        if(isStopped) {
            while(isStoppingState()) {
                Thread.yield();
            }
        }
        return false;
    }

    private boolean isRunningState() {
        return currentStatus.get() == RUNNING;
    }

    private boolean setRunningState() {
        boolean changeSuccessfully = false;
        while (!changeSuccessfully) {
            if (isStoppingState() || isShutDownState()) {
                return false;
            }
            if (isRunningState()) {
                return true;
            }
            if (isInitialState()) {
                changeSuccessfully = currentStatus.compareAndSet(INITIAL, RUNNING);
            }
        }
        return true;
    }


    private boolean isInitialState() {
        return currentStatus.get() == INITIAL;
    }

    private boolean setInitialState() {
        boolean changeSuccessfully = false;
        while (!changeSuccessfully) {
            if (isRunningState() || isStoppingState()) {
                return false;
            }
            if (isInitialState()) {
                return true;
            }
            if (isShutDownState()) {
                changeSuccessfully = currentStatus.compareAndSet(SHUTDOWN, INITIAL);
            }
        }
        return true;
    }

    private boolean isStoppingState() {
        return currentStatus.get() == STOPPING;
    }


    private boolean setStopState() {
        boolean changeSuccessfully = false;
        while (!changeSuccessfully) {
            if (isInitialState() || isShutDownState()) {
                return false;
            }
            if (isStoppingState()) {
                return true;
            }
            if (isRunningState()) {
                changeSuccessfully = currentStatus.compareAndSet(RUNNING, STOPPING);
            }
        }
        return true;
    }

    private boolean isShutDownState() {
        return currentStatus.get() == SHUTDOWN;
    }

    private boolean setShutdownState() {
        boolean changeSuccessfully = false;
        while (!changeSuccessfully) {
            if (isInitialState() || isRunningState()) {
                return false;
            }
            if (isShutDownState()) {
                return true;
            }
            if (isStoppingState()) {
                changeSuccessfully = currentStatus.compareAndSet(STOPPING, SHUTDOWN);
            }
        }
        return true;
    }

}
