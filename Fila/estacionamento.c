#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

#define TAMANHO 3 
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

void entrada(Fila *fila) {
	int placa;
	printf("\nDigite a placa do carro (apenas números): ");
	scanf("%i",&placa);
	int cod = inserir(fila,placa);
	if(cod==1){
		printf("Carro estacionado.");
	} else {
		printf("Não existe vaga.");
	}
}

void saida(Fila *fila) {
	int placaSaindo,placa;
	printf("\nDigite a placa do carro (apenas números): ");
	scanf("%i",&placaSaindo);
	int tamanho = tamanhoFila(fila);
	int contador = 0;
	for(;contador<tamanho;contador++){
		placa = retirarDaFila(fila);
		if(placa==placaSaindo){
			printf("Carro saindo do estacionamento com %i deslocamentos",contador);
			break;
		} else {
			inserir(fila,placa);
			imprimirFila(fila);
		}
	}
}

int main() {
	setlocale(LC_ALL,"");
	Fila* estacionamento = criarFila();
	char op;
	while(1) {
		printf("\n\n==> ");
		scanf(" %c",&op);
		switch(op){
			case 'C': 
			case 'c': entrada(estacionamento);break;
			case 'P': 
			case 'p': saida(estacionamento);break;
			case '0': exit(0);
		}
	}
}
