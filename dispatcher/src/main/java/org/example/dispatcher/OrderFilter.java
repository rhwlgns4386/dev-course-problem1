package org.example.dispatcher;

public abstract class OrderFilter implements Filter, Comparable<OrderFilter> {

    private final int order;

    public OrderFilter(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(OrderFilter o) {
        return Integer.compare(order, o.order);
    }
}
