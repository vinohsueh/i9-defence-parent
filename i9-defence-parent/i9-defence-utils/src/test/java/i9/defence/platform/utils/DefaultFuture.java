package i9.defence.platform.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultFuture<T> implements Future<T> {

    private T response;

    private Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    public void doReceived(T response) {
        lock.lock();
        try {
            // 设置response
            this.response = response;
            if (done != null) {
                // 唤醒阻塞的线程
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return response != null;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return get(100000, TimeUnit.MILLISECONDS);
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws RuntimeException {
        if (timeout <= 0) {
            timeout = 100000;
        }
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (!isDone()) {
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            if (!isDone()) {
                throw new RuntimeException();
            }
        }
        return returnFromResponse();
    }

    private T returnFromResponse() {
        return this.response;
    }
}
