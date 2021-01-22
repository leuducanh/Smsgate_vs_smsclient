package brandname.smpp_protocol.main;


public class BlockQueue extends QueueImpl {
    private static final long serialVersionUID = 20091803113026L;
    private static final int TIMEWAIT = 2000;
    private int timeout;

    public BlockQueue() {
        this(2000);
    }

    public BlockQueue(int timeout) {
        this.timeout = timeout;
    }

    public BlockQueue(int timeout, int maxSize) {
        super(maxSize);
        this.timeout = timeout;
    }

    public void enqueue(Object obj) {
        synchronized(this.mutex) {
            if (this.maxQueueSize > 0 && this.size() >= this.maxQueueSize) {
                throw new IndexOutOfBoundsException("Queue is full. Element not added.");
            } else {
                this.queueData.add(obj);
                this.mutex.notifyAll();
            }
        }
    }

    public Object dequeue() {
        synchronized(this.mutex) {
            Object first = null;
            if (this.size() > 0) {
                first = this.queueData.removeFirst();
            } else {
                try {
                    if (this.timeout != 0) {
                        this.mutex.wait((long)this.timeout);
                        if (this.size() > 0) {
                            first = this.queueData.removeFirst();
                        }
                    } else {
                        while(this.isEmpty()) {
                            this.mutex.wait();
                        }

                        first = this.queueData.removeFirst();
                    }
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }

            return first;
        }
    }

    public Object dequeue(int to) {
        this.timeout = to;
        return this.dequeue();
    }
}

