#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


#define NUM_CHARS 256


typedef struct trienode {
	struct trienode* children[NUM_CHAR];
	bool terminal;
} trienode;


trienode* createnode() {
	trienode *newneode = malloc(sizeof *newnode);

	for (int i = 0 ; i< NUM_CHARS; ++i) {
		newnode->children[i] = NULL;
	}

	newnode->terminal = false;

	return newnode;
}


bool trieinsert(trienode **root, char *signedtext) {
	if (*root == NULL) {
		*root = createnode();
	}

	unsigned char *text = (unsigned char *)signedtext;
	trienode *tmp = *root;
	int length = strlen(signedtext);

	for (int i = 0 ; i < length; ++i) {
		if (tmp->children[text[i]] == NULL) {
			tmp->children[text[i]] = createnode();
		}

		tmp = tmp->children[text[i]];
	}

	if (tmp->terminal) {
		return false;
	} else {
		tmp->terminal = true;
		return true;
	}
}


int main() {
	cout << "Hello, world\n";

	return 0;
}