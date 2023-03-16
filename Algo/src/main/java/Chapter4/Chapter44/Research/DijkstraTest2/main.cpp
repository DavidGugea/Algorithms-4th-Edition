#include <bits/stdc++.h>
using namespace std;

void DijkstrasTest();

int main() {
    DijkstrasTest();
    return 0;
}

class Node;
class Edge;

void Dijkstras();
vector<Node*>* AdjacentRemainingNodes(Node* node);
Node* ExtractSmallest(vector<Node*>& nodes);
int Distance(Node* node1, Node* node2);
bool Contains(vector<Node*>& nodes, Node* node);
void PrintShortestRouteTo(Node* destination);

vector<Node*> nodes;
vector<Edge*> edges;

class Node {
public:
    char id;
    Node* previous;
    int distanceFromStart;

    Node(char id) : id(id), previous(NULL), distanceFromStart(INT_MAX) {
        nodes.push_back(this);
    }
}

class Edge {
public:
    Node* node1;
    Node* node2;
    int distance;

    Edge(Node* node1, Node* node2, int distance) : node1(node1), node2(node2), distance(distance) {
        edges.push_back(this)
    }

    Connects(Node node1, Node node2) {
        if (
            (node1 == this->node1 || node1 == this->node2)
            && 
            (node2 == this->node1 || node2 == this->node2)
        ) {
            return true;
        } else {
            return false;
        }
    }
}

void DijkstrasTest() {
    Node* a = new Node('a');
    Node* b = new Node('b');
    Node* c = new Node('c');
    Node* d = new Node('d');
    Node* e = new Node('e');
    Node* f = new Node('f');
    Node* g = new Node('g');

    Edge* e1 = new Edge(a, c, 1);
    Edge* e2 = new Edge(a, d, 2);
    Edge* e3 = new Edge(b, c, 2);
    Edge* e4 = new Edge(c, d, 1);
    Edge* e5 = new Edge(b, f, 3);
    Edge* e6 = new Edge(c, e, 3);
    Edge* e7 = new Edge(e, f, 2);
    Edge* e8 = new Edge(d, g, 1);
    Edge* e9 = new Edge(g, f, 1);

    a->distanceFromStart = 0;  // set start node
    Dijkstras();
    PrintShortestRouteTo(f);
}


void Dijkstras() {
    while (nodes.size() > 0) {
        // Can be done more efficiently using a priority queue
        Node* smallest = ExtractSmallest(nodes);
        vector<Node*>* adjacentNodes = AdjacentRemainingNodes(smallest);

        const int size = adjacentNodes->size();

        for (int i = 0 ; i < size; ++i) {
            Node* adjacent = adjacentNodes->at(i);

            int distance = Distance(smallest, adjacent) + smallest->distanceFromStart;

            if (distance < adjacent->distanceFromStart) {
                adjacent->distanceFromStart = distance;
                adjacent->previous = smallest;
            }
        }

        delete adjacentNodes;
    }
}

void Dijkstras2() {
    while (nodes.size() > 0) {
        Node* smallest = ExtractSmallest(nodes);
        vector<Node*>* adjacentNodes = AdjacentRemainingNodes(smallest);
        int size = adjacentNodes->size();

        for (int i = 0 ; i < size; ++i) {
            Node* adjacent = adjacentNodes->at(i);

            int distance = Distance(smallest, adjacent) + smallest->distanceFromStart;

            if (distance < adjacentNodes->distanceFromStart) {
                adjacent->distanceFromStart = distance;
                adjacent->previous = smallest;
            }
        }

        delete adjacentNodes;
    }
}

// Find the node with the smallest distance.
// remove it, and return it.
Node* ExtractSmallest(vector<Node*>& nodes) {
    int size = nodes.size();
    if (size == 0) return NULL;

    int smallestPosition = 0;
    Node* smallest = nodes.at(0);

    for (int i = 1 ; i < size; ++i) {
        Node* current = nodes.at(i);
        if (current->distanceFromStart < smallest->distanceFromStart) {
            smallest = current;
            smallestPosition = i;
        }
    }

    nodes.erase(nodes.begin() + smallestPosition);
    return smallest;
}

// Return all nodes adjacent to 'node' which are still in the 'nodes' collection
vector<Node*>* AdjacentRemainingNodes(Node* node) {
    // iterate over all the edges
    // Check if the edges contain the given node 'node'
    // If they do, save the other node inside the 'adjacent' node
    // If the an adjacent node was found and the adjacent node is inside
    // the 'nodes' list, push the adjacent node inside the adjacentNodes list
    // Return the adjacentNodes list.

    vector<Node*>* adjacentNodes = new vector<Node*>();
    const int size = edges.size();

    for (int i = 0 ; i < size; ++i) {
        Edge* edge = edges.at(i);
        Node* adjacent = NULL;

        if (edge->node1 == node) {
            adjacent = edge->node2;
        } else if (edge->node2 == node) {
            adjacent = edge->node1;
        }

        if (adjacent && Contains(nodes, adjacent)) {
            adjacentNodes->push_back(adjacent);
        }
    }

    return adjacentNodes;
}

// Return distance between two connected nodes
int Distance(Node* node1, Node* node2) {
    const int size = edges.size();

    for (int i = 0 ; i < size; ++i) {
        Edge* edge = edges.at(i);

        if (edge->Connects(node1, node2)) {
            return edge->distance;
        }
    }

    return -1;
}

// Does the 'nodes' vector contain 'node'
bool Contains(vector<Node*>& nodes, Node* node) {
    const int size = nodes.size();
    
    for (int i = 0 ; i < size; ++i) {
        if(nodes == nodes.at(i)) {
            return true;
        }
    }

    return false;
}

void PrintShortestRouteTo(Node* destination) {
    Node* previous = destination;
    cout << "Distance from start: " << destination->distanceFromStart << endl;

    while (previous) {
        cout << previous->id << " ";
        previous = previous->previous;
    }

    cout << endl;
}