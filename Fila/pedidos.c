#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

#define TAMANHO 50 
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

void imprimeMenu() {
	setlocale(LC_ALL,"");
	system("cls");
	printf("\n[1] novo pedido (lê do teclado código numérico e insere na fila)");
	printf("\n[2] atender (retirar da fila e mostra na tela e guarda numa outra lista)");
	printf("\n[3] listar novos pedidos (não atendidos)");
	printf("\n[4] listar pedidos atendidos");
	printf("\n[0] sair");   
}

void novoPedido(Fila* fila){
	int codigo = 0;
	printf("Digite o código do pedido: ");
	scanf("%i",&codigo);
	inserir(fila,codigo);
	printf("Pedido inserido na fila com sucesso");
	getch();
}

void atenderPedido(Fila* filaNovosPedidos,Fila* filaPedidosAtendidos){
	int cod = retirarDaFila(filaNovosPedidos);
	printf("Pedido atendido código: %i",cod);
	inserir(filaPedidosAtendidos,cod);
	getch();
	
}

int main() {
	Fila* filaNovosPedidos = criarFila();
	Fila* filaPedidosAtendidos = criarFila();
	int op;
	while(1) {
		imprimeMenu();
		printf("\nEscolha uma opção: ");
		scanf("%i",&op);
		switch(op){
			case 1: novoPedido(filaNovosPedidos); break;
			case 2: atenderPedido(filaNovosPedidos,filaPedidosAtendidos); break;
			case 3: imprimirFila(filaNovosPedidos); break;
			case 4: imprimirFila(filaPedidosAtendidos); break;
			case 0:exit(0);
		}
	}
}
