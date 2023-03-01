def dfs(graph, start, visited=None):
    if visited == None:
        visited = set()
    visited.add(start)

    print(start)

    for next in graph[start] - visited:
        dfs(graph, next, visited)

    return visited