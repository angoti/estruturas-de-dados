#include <stdlib.h>
#include <stdio.h>
#include "pilha.h"

#define TAMANHO 100
struct pilha {
	int topo;
	char item[TAMANHO];
};

Pilha* criapilha(){
	Pilha* p = (Pilha*) malloc(sizeof(Pilha));
	if(p!=NULL)
		p->topo = 0;
	return p;
}

void liberar(Pilha* p){
	free(p);
}

void push(Pilha* p, char item){
	p->item[(p->topo)++] = item;
}

char pop(Pilha* p){
	if (empty(p)) {
		printf("pilha vazia");
		exit(1);
	}
	return(p->item[--(p->topo)]);
}

int empty(Pilha* p){
	return (p->topo <= 0);
}
