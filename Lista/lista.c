// tarefa: implementar fun��es para:
// 1. inserir um elemento no final da lista
// 2. excluir o �ltimo elemento da lista
// 3. inserir um elemento na en�sima posi��o
// 4. excluir o en�simo elemento

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <locale.h>

typedef struct no No;

struct no {
	int info;
	No *next;
};

No *lista = NULL;

No* getnode() {
	No* no = (No*) malloc(sizeof(No));
  	if(no==NULL) {
    	printf("\nMem�ria insuficiente!");
	    exit(0);
  	}
  	return no;
}          

void freenode(No *no){
	free(no);
}

void imprimeLista() {
	printf("\n\nConte�do da lista: ");
	No *l = lista;
	while(l!=NULL){
		printf("%i ",l->info);
		l=l->next;
	}
}

int contaQuantidadeDeElementosDaLista() {
	int contador = 0;
	No *l = lista;
	while(l!=NULL){
		contador++;
		l = l->next;
	}
	return contador;
}

void insereNoInicio(int numero){
	No *p = getnode();
    p->info = numero;
	p->next = lista;
	lista = p;
}

int removeDoInicio(){
	No *p = lista;
	lista = p->next;
	int x = p->info;
	freenode(p);
	return x;
}

/********************************************************************************************/
// 3. inserir um elemento na en�sima posi��o
// primeiro preciso localirar a en�sima posi��o 
// o par�metro posicao representa a posi��o do n� na lista, sendo um a primeira posi��o 
No* localizaEnesimaPosicaoDaLista(int posicao) {
	int contador = 1;
	No *l = lista;
	while(contador<posicao){
		contador++;
		l = l->next;
	}
	return l;
}

// agora podemos desenvolver a fun��o que insere na en�sima posi��o
// para inserir um novo n� na enesima posi��o preciso saber o ponteiro que aponta para o 
// n� anterior � posi��o em que ser� inserido o novo n�
void insereNaEnesimaPosicao(int posicao, int info) {
	if(posicao==1){
		insereNoInicio(info);
	} else if(posicao <= contaQuantidadeDeElementosDaLista()){
		No *posicaoAnterior = localizaEnesimaPosicaoDaLista(posicao-1);
		No *p = getnode();
    	p->info = info;
		p->next = posicaoAnterior->next;
		posicaoAnterior->next = p;
	} else printf("\n\n A lista n�o possui esta Posi��o! Elemento n�o inserido!");
}       
/******************************************************************************/

/*******************************************************************************/
// 1. inserir um elemento no final da lista
void insereNoFinalDaLista(int info) {
	int tamanho = contaQuantidadeDeElementosDaLista();
	if(tamanho>0){
		No* ultimaPosicao = localizaEnesimaPosicaoDaLista(tamanho);
		No *p = getnode();
    	p->info = info;
		p->next = NULL;
		ultimaPosicao->next = p;
	} else insereNoInicio(info);
}

/*******************************************************************************/

int main() {
	setlocale(LC_ALL,"");
	int numero;
    do {
        system("cls");
        printf("\nDigite um valor inteiro, ou zero para finalizar: ");
        scanf("%d",&numero);
        if(numero!=0) {
            insereNoFinalDaLista(numero);  
        }
    } while(numero!=0);   
    imprimeLista();
    
	//No *elemento = localizaEnesimaPosicaoDaLista(2);
	//printf("\n\n info na posi��o 2:%i",elemento->info);
    
    insereNaEnesimaPosicao(4,1000);
    
    //insereNoFinalDaLista(1000);
    //printf("\n%i",contaQuantidadeDeElementosDaLista());
    imprimeLista();
	return 0;
}

