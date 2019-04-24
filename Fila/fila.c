#include <stdio.h>
#include <stdlib.h>
#define TAMANHO 50 

struct fila { 
	char itens[TAMANHO]; //vetor para armazenar os elementos da fila
	int posicao;	     //indica o primeiro da fila no vetor  
	int qtdeElem;	     //indica a quantidade de elementos na fila
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
		printf("%c ",fila->itens[i]);
		i = (i+1)%TAMANHO;//lembre-se do "vetor circular"
	}
	printf("\n");
}

int inserir(Fila* fila, char item){
	if(fila->qtdeElem>=TAMANHO)
        return 0;
	int posicao=(fila->posicao+fila->qtdeElem)%TAMANHO;
	fila->itens[posicao]=item;
	fila->qtdeElem++;
	return 1;
}

char excluir(Fila* fila){
	if(fila->qtdeElem==0)
		return 0;
	char elemento = fila->itens[fila->posicao];
	fila->posicao = (fila->posicao+1)%TAMANHO;
	fila->qtdeElem--;
	return elemento;
}

int main() {
	Fila* fila = criarFila();
	inserir(fila,'A');
	inserir(fila,'B');
	inserir(fila,'C');
	imprimirFila(fila);
	printf("\nItem excluido: %c", excluir(fila));
	imprimirFila(fila);
	printf("\nItem excluido: %c", excluir(fila));
	printf("\nItem excluido: %c", excluir(fila));
	imprimirFila(fila);
	printf("\nItem excluido: %c", excluir(fila));
	printf("\nItem excluido: %c", excluir(fila));
}
