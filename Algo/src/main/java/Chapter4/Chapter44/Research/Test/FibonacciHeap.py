"""
rank -> number of children of each node in a tree

consolidate() -> refers to the process of merging trees of the same rank (trees with the same number of children). This is executed to maintain the heap structure after extracting the minimum element. 
"""


class Node:
    def __init__(self, key):
        self.key = key
        self.degree = 0  # number of children in the node's child list
        self.mark = False  # only needed for decreaseKey
        
        # pointers
        self.parent = None
        self.child = None
        self.left = None
        self.right = None


class FibonacciHeap:
    def __init__(self):
        self.n = 0
        self.min: Node = None
        self.root_list: Node = None

    def minimum(self) -> Node:
        return self.min

    def insert(self, key: int) -> Node:
        # lazy operation => delay the work as long as possible
        # insert nodes into root list
        # let ```extract-min``` to most of the work:
        #     - iterate through the root list to find new minimum
        #     - consolidate nodes into min-heap-ordered trees

        node: Node = Node(key)
        node.left = node
        node.right = node

        # Insert the new node to the left of the root_list node
        self.merge_with_root_list(node)

        # Update self.min
        # If there is not self.min
        # or
        # If the key of the node that has to be inserted is less than the current min node key
        # update the self.min pointer
        if self.min is None or node.key < self.min.key:
            self.min = Node

        self.n += 1  # Change the number of nodes inside the list.

        return node

    def merge_with_root_list(self, node: Node) -> None:
        if self.root_list is None:
            self.root_list = node
        else:
            # insert the node to the end of the root list. The last element of the root list has a pointer to the first element.
            node.right = self.root_list
            node.left = self.root_list.left

            if self.root_list.left is not None:
                self.root_list.left.right = node

            self.root_list.left = node

    def union(self, FH2: 'FibonacciHeap') -> 'FibonacciHeap':
        FH = FibonacciHeap()
        FH.root_list = self.root_list

        # set min to lesser of FH1.min(self) and FH2.min
        FH.min = self.min if self.min.key < FH2.min.key else FH2.min

        # fix pointers to combine root lists
        last = FH2.root_list.left
        FH2.root_list.left = FH.root_list.left


if __name__ == "__main__":
    FH = FibonacciHeap()
