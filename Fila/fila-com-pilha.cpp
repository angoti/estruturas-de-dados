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

struct fila { 
	int itens[TAMANHO]; //vetor para armazenar os elementos da fila
	int posicao;		 //indica o primeiro da fila no vetor  
	int qtdeElem;		 //indica a quantidade de elementos na fila
};

typedef struct fila Fila;

Fila* criarFila() {
	Fila* fila = (Fila*) malloc(sizeof(Fila));
	if(fila!=NULL){
		fila->posicao=0;
		fila->qtdeElem=0;
	}
	return fila;
}

int tamanhoFila(Fila* fila) {
	return fila->qtdeElem;
}

void imprimirFila(Fila* fila) {
	printf("\nFila: ");
	int i = fila->posicao;
	int contador;
	for(contador=0;contador<fila->qtdeElem;contador++){
		printf("%i ",fila->itens[i]);
		i = (i+1)%TAMANHO;//lembre-se do "vetor circular"
	}
	printf("\n");
	getch();
}

int inserir(Fila* fila, int item){
	if(fila->qtdeElem>=TAMANHO)
        return 0;
	int posicao=(fila->posicao+fila->qtdeElem)%TAMANHO;
	fila->itens[posicao]=item;
	fila->qtdeElem++;
	return 1;
}

int retirarDaFila(Fila* fila){
	if(fila->qtdeElem==0)
		return 0;
	int elemento = fila->itens[fila->posicao];
	fila->posicao = (fila->posicao+1)%TAMANHO;
	fila->qtdeElem--;
	return elemento;
}

void dequeue(){
}

void enqueue(){
	
}

int main() {
	
}
