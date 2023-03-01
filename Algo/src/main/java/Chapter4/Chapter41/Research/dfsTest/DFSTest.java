package Chapter4.Chapter41.Research.dfsTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFSTest {
    public HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

    public void addVertex(int v) {
        map.put(v, new HashSet<>());
    }

    public void addEdge(int v1, int v2) {
        map.get(v1).add(v2);
        map.get(v2).add(v1);
    }

    public void dfs(int startV) {
        Stack<Integer> stack = new Stack<>();
    }
}
