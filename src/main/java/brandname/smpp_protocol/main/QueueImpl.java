package brandname.smpp_protocol.main;

import java.util.LinkedList;
import java.util.ListIterator;

public  class QueueImpl implements Queue {
    protected int maxQueueSize = 0;
    protected LinkedList queueData = new LinkedList();
    protected Object mutex;
    private static final long serialVersionUID = 20091803113026L;

    public QueueImpl() {
        this.mutex = this;
    }

    public QueueImpl(int maxSize) {
        this.maxQueueSize = maxSize;
        this.mutex = this;
    }

    public int size() {
        synchronized(this.mutex) {
            return this.queueData.size();
        }
    }

    public boolean isEmpty() {
        synchronized(this.mutex) {
            return this.queueData.isEmpty();
        }
    }

    public Object dequeue() {
        synchronized(this.mutex) {
            Object first = null;
            if (this.size() > 0) {
                first = this.queueData.removeFirst();
            }

            return first;
        }
    }

    public Object dequeue(Object obj) {
        synchronized(this.mutex) {
            Object found = this.find(obj);
            if (found != null) {
                this.queueData.remove(found);
            }

            return found;
        }
    }

    public void enqueue(Object obj) throws IndexOutOfBoundsException {
        synchronized(this.mutex) {
            if (this.maxQueueSize > 0 && this.size() >= this.maxQueueSize) {
                throw new IndexOutOfBoundsException("Queue is full. Element not added.");
            } else {
                this.queueData.add(obj);
            }
        }
    }

    public Object find(Object obj) {
        synchronized(this.mutex) {
            ListIterator iter = this.queueData.listIterator(0);

            Object current;
            do {
                if (!iter.hasNext()) {
                    return null;
                }

                current = iter.next();
            } while(!current.equals(obj));

            return current;
        }
    }

    public Object[] toArray() {
        return this.queueData.toArray();
    }
}

