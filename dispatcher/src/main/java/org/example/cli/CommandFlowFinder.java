package org.example.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandFlowFinder<K> {
    private final Map<K, CommandFlow<K>> store;

    public CommandFlowFinder(Map<K, CommandFlow<K>> store) {
        this.store = store;
    }

    public CommandFlowFinder(List<CommandFlow<K>> flows) {
        this(toMap(flows));
    }

    public Optional<CommandFlow<K>> find(K key) {
        return Optional.ofNullable(store.get(key));
    }

    private static <K> Map<K, CommandFlow<K>> toMap(List<CommandFlow<K>> flows) {
        HashMap<K, CommandFlow<K>> result = new HashMap<>();
        flows.forEach(flow -> result.put(flow.getKey(), flow));
        return result;
    }
}
