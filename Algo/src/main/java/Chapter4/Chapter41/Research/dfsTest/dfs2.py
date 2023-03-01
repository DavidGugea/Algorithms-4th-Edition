def dfs(G, v):
    marked = [False] * G.size()
    visit(v)
    marked[v] = True

    for w in G.neighbours(v):
        if not marked[w]:
            dfs(G, w)