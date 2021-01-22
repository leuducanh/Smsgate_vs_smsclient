package brandname.smpp_protocol.main;

import java.io.Serializable;

public interface Queue extends Serializable {
    int size();

    boolean isEmpty();

    Object dequeue();

    Object dequeue(Object var1);

    void enqueue(Object var1) throws IndexOutOfBoundsException;

    Object find(Object var1);

    Object[] toArray();
}

