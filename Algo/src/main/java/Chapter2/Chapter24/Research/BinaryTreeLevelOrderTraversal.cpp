#include <iostream>
#include <queue>

using namespace std;

struct Node {
    char data;
    
    Node *left;
    Node *right;
}

void levelOrder(Node* root) {
    if (root == nullptr) return;

    queue<Node*> Q;

    while (!Q.empty()) {
        Node* current = Q.front();
        
        cout << current->data << " ";

        if(current->left != nullptr) Q.push(current->left);
        if(current->right != nullptr) Q.push(current->right);

        Q.pop();
    }
}