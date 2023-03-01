import sys
from typing import Dict, List
from collections import deque


def dfs(graph: Dict[str, List[str]], node: str):
    visited = []
    stack = deque()

    visited.append(node)
    stack.append(node)

    while stack:
        s: str = stack.pop()
        print(s, end = " ")

        for n in reversed(graph[s]):
            if n not in visited:
                visited.append(n)
                stack.append(n)


graph: Dict[str, List[str]] = {
    'A': ['B', 'G'],
    'B': ['C', 'D', 'E'],
    'C': [],
    'D': [],
    'E': ['F'],
    'F': [],
    'G': ['H'],
    'H': ['I'],
    'I': []
}

dfs(graph, str(sys.argv[1]))
# Output: A G B H E D C H I
print()