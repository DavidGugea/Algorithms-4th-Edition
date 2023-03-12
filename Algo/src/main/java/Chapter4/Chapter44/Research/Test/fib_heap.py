import math


class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.parent = self.child = self.left = self.right = None
        self.degree = 0
        self.mark = False


class FibonacciHeap:
    def __init__(self) -> None:
        # pointer to the head and minimum node in the root list
        self.root_list = None
        self.min_node = None

        # maintain total node count in full fibonacci heap
        self.total_nodes = 0

    # function to iterate through a doubly linked list
    def iterate(self, head):
        node = stop = head
        flag = False
        while True:
            if node == stop and flag is True:
                break
            elif node == stop:
                flag = True
            
            yield node
            node = node.right

    # return min node in O(1) time
    def find_min(self):
        return self.min_node

    # remove a node from the doubly linked root list
    def remove_from_root_list(self, node: Node) -> None:
        if node == self.root_list:
            self.root_list = node.right

        node.left.right = node.right
        node.right.left = node.left

    # remove a node from the doubly linked child list
    def remove_from_child_list(self, parent: Node, node: Node) -> None:
        if parent.child == parent.child.right:
            # if child == child.right that means that the child list only contains one child because the child has a reference to itself.
            parent.child = None
        elif parent.child == node:
            # reset parent pointer
            parent.child = node.right
            node.right.parent = parent

        node.left.right = node.right
        node.right.left = node.left


    def merge_with_root_list(self, node: Node) -> None:
        if self.root_list is None:
            self.root_list = node
        else:
            node.right = self.root_list.right
            node.left = self.root_list
            self.root_list.right.left = node
            self.root_list.right = node

    # merge a node with the doubly linked child list of a root node
    def merge_with_child_list(self, parent: Node, node: Node) -> None:
        if parent.child is None:
            parent.child = node
        else:
            node.right = parent.child.right
            node.left = parent.child
            parent.child.right.left = node
            parent.child.right = node


    # if a child node becomes smaller than its parent node we
    # cut this child node off and bring it up to the root list
    def cut(self, x: Node, y: Node):
        self.remove_from_child_list(y, x)
        y.degree -= 1
        self.merge_with_root_list(x)
        x.parent = None
        x.mark = False

    # cascading cut of parent node to obtain good time bounds
    def cascading_cut(self, y):
        z = y.parent
        if z is not None:
            if y.mark is False:
                y.mark = True
            else:
                self.cut(y, z)
                self.cascading_cut(z)

    # merge two fibonacci heaps in O(1) time by concatenating the root lists
    # the root of the new root list becomes equal to the first list and the second
    # list is simply appended to the end (then the proper min node is determined)
    def merge(self, h2: 'FibonacciHeap') -> 'FibonacciHeap':
        H = FibonacciHeap()
        H.root_list, H.min_node = self.root_list, self.min_node

        # fix pointers when merging the two heaps
        last = h2.root_list.left

        h2.root_list.left = H.root_list.left
        H.root_list.left.right = h2.root_list

        H.root_list.left = last
        H.root_list.left.right = H.root_list

        # update min node if needed
        if h2.min_node.key < H.min_node.key:
            H.min_node = h2.min_node

        # update total nodes
        H.total_nodes = self.total_nodes = h2.total_nodes

        return H

    # modify the key of some node in the heap in O(1) time
    def decrease_key(self, x, k):
        if k > x.key:
            return None

        x.key = k
        y = x.parent

        if y is not None and x.key < y.key:
            self.cut(x, y)
            self.cascading_cut(y)

        # update min_node if necessary
        if x.key < self.min_node.key:
            self.min_node = x

    # insert new node into the unordered root list in O(1) time
    # returns the node so that it can be used for decrease_key later
    def insert(self, key, value=None):
        n = self.Node(key, value)
        n.left = n.right = n

        self.merge_with_root_list(n)

        if self.min_node is None or n.key < self.min_node.key:
            self.min_node = n

        self.total_nodes += 1

        return n

    def find_min(self) -> Node:
        return self.min_node

    # extract (delete) the min node from the heap in O(log n) time
    def extract_min(self) -> Node:
        z = self.min_node

        if z is not None:
            if z.child is not None:
                # attach child nodes to root list
                children = [x for x in self.iterate(z.child)]
                for i in range(0, len(children)):
                    self.merge_with_root_list(children[i])
                    children[i].parent = None

            self.remove_from_root_list(z)

            # set new min node in heap
            if z == z.right:
                self.min_node = self.root_list = None
            else:
                self.min_node = z.right
                self.consolidate()

            self.total_nodes -= 1

        return z

    # combine root nodes of equla degree to consodliate the heap
    # by creating a list of unordered binomal trees
    def consolidate(self):
        A = [None] * int(math.log(self.total_nodes) * 2)
        nodes = [w for w in self.iterate(self.root_list)]

        for w in range(0, len(nodes)):
            x = nodes[w]
            d = x.degree

            while A[d] != None:
                y = A[d]
                if x.key > y.key:
                    temp = x
                    x, y = y, temp

                self.heap_link(y, x)
                A[d] = None
                d += 1

            A[d] = x

        # find new min node - no need to reconstruct new root list below
        # because root list was iteratively changing as we were moving
        # nodes around in the above loop
        for i in range(0, len(A)):
            if A[i] is not None:
                if A[i].key < self.min_node.key:
                    self.min_node = A[i]


    # actual linking of one node to another in the root list
    # while also updating the child linked list
    def heap_link(self, y, x):
        self.remove_from_root_list(y)
        y.left = y.right = y
        self.merge_with_child_list(x, y)
        x.degree += 1
        y.parent = x
        y.mark = False