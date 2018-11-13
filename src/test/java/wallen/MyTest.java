package wallen;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyTest {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        Disruptor<Order> disruptor = new Disruptor<>(Order::new, 4*1024, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith((Order event, long sequence, boolean endOfBatch) ->
                    System.out.println("accept event" + event.getData()));
        disruptor.start();

    }
}


class DataFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new Order();
    }
}

class Order {
    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    private long data;
}