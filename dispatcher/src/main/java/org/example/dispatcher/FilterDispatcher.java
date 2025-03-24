package org.example.dispatcher;

import org.example.dispatcher.dto.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterDispatcher implements Dispather {

    private final Dispather dispather;
    private final List<OrderFilter> filters = new ArrayList<>();

    public FilterDispatcher(OrderFilterConfig orderFilterConfig, Dispather dispather) {
        orderFilterConfig.addFilter(this.filters);
        Collections.sort(this.filters);
        this.dispather = dispather;
    }

    @Override
    public void dispatch(Request request) {
        VirtualFilter virtualFilter = new VirtualFilter();
        virtualFilter.doFilter(request);
    }

    private class VirtualFilter implements Filter {

        private int count = 0;

        public void doFilter(Request request) {
            doFilter(null, request);
        }

        @Override
        public void doFilter(Filter filter, Request request) {
            if (count >= filters.size()) {
                dispather.dispatch(request);
                return;
            }
            filters.get(count++).doFilter(this, request);
        }
    }
}
