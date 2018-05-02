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

int main() {
	setlocale(LC_ALL,"");
	int numero;
    do {
        system("cls");
        printf("\nDigite um valor inteiro, ou zero para finalizar: ");
        scanf("%d",&numero);
        if(numero!=0) {
            insereNoInicio(numero);  
        }
    } while(numero!=0);   
    imprimeLista();
    
	printf("\nRemovendo do in�cio da lista: %i",removeDoInicio());
	
	imprimeLista();
    
	return 0;
}

