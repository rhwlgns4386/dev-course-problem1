package org.example.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandFlowFinder<K, C> {
    private final Map<K, CommandFlow<K, C>> store;

    public CommandFlowFinder(Map<K, CommandFlow<K, C>> store) {
        this.store = store;
    }

    public CommandFlowFinder(List<CommandFlow<K,C>> flows) {
        this(toMap(flows));
    }

    public Optional<CommandFlow<K,C>> find(K key) {
        return Optional.ofNullable(store.get(key));
    }

    private static <K, C> Map<K, CommandFlow<K, C>> toMap(List<CommandFlow<K, C>> flows) {
        HashMap<K, CommandFlow<K, C>> result = new HashMap<>();
        flows.forEach(flow -> result.put(flow.getKey(), flow));
        return result;
    }
}
