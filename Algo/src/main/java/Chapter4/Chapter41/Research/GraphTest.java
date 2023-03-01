package Chapter4.Chapter41.Research;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphTest<T> {
    private Map<T, List<T>> map = new HashMap<>();

    public void addVertex(T s) {
        if (!map.containsKey(s)) {
            map.put(s, new LinkedList<T>());
        } else {
            throw new IllegalArgumentException("The given vertex is already present in the graph.");
        }
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        map.get(source).add(destination);

        if (bidirectional) {
            map.get(destination).add(source);
        }
    }

    public int getVertexCount() {
        return map.keySet().size();
    }

    public int getEdgesCount(boolean bidirectional) {
        int count = 0;

        for (T v : map.keySet()) {
            count += map.get(v).size();
        }

        if (bidirectional) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(T s) {
        return map.containsKey(s);
    }

    public boolean hasEdge(T s, T d) {
        return map.get(s).contains(d);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + " ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
