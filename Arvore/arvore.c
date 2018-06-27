#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

/* Uma árvore binária (= binary tree) é formada de nós; 
   cada nó tem um conteúdo (número,letra, palavra etc) e os
   endereços de duas subárvores: uma esquerda e uma direita */
 
typedef struct no No;
struct no {
   int  item;  // conteúdo do nó
   No *ae, *ad;  // ae - árvore esquerda, ad - árvore diretia
};

No* criaNo() {
   No* no = (No*) malloc(sizeof(No));
   no->ae = NULL;
   no->ad = NULL;
}

//-------------------------------------------------------------------------

void preOrdem(No *raiz) {
   if(raiz != NULL) {
        printf("%i ",raiz->item);
        preOrdem(raiz->ae);
        preOrdem(raiz->ad);
     }
}

//-------------------------------------------------------------------------

void emOrdem(No *raiz) {
   if(raiz != NULL) {        
        emOrdem(raiz->ae);
        printf("%i ",raiz->item);
        emOrdem(raiz->ad);
     }
}

//-------------------------------------------------------------------------

void posOrdem(No *raiz) {
   if(raiz != NULL) {        
        posOrdem(raiz->ae);        
        posOrdem(raiz->ad);
        printf("%i ",raiz->item);
     }
}

//-------------------------------------------------------------------------

No* geraArvore(No *raiz, int item) {
	if(raiz==NULL) {
    	No *no = criaNo();
     	no->item = item;
     	raiz = no;
     	return raiz;  
   } else {
     	if(raiz->item > item) {
       		if(raiz->ae == NULL) {
       			No* no = criaNo();
         		no->item = item;
         		raiz->ae = no; 
         		return raiz;
       		} else
       			raiz->ae = geraArvore(raiz->ae, item);
		} else {
			if(raiz->ad == NULL) {
				No* no = criaNo();
         		no->item = item;
         		raiz->ad = no;   
         		return raiz;         
       		} else
          		raiz->ad = geraArvore(raiz->ad, item);   
		}
   }
   return raiz;
}

/* Quando dizemos um nó x devemos entender que x é o endereço de um nó.
 Nesses termos, o filho esquerdo de um nó  x  é  x->ae 
 e o filho direito é  x->ad. Um nó x é uma folha se não tem filhos,
 ou seja, se  x->ae e x->ad valem  NULL.*/

int imprimeMenu() {
	int op;
	system("cls");
    printf("---------------M E N U-------------");
    printf("\n\n1- Insere nó na Árvore");
    printf("\n2- Exibe em Pré-Ordem");
    printf("\n3- Exibe em Ordem");
    printf("\n4- Exibe em Pós-Ordem");
    printf("\n5- Quantidade de nós na Árvore");
    printf("\n6- Soma do conteúdo de todos nós");
    printf("\n7- Exibe o nó com o maior valor");
    printf("\n8- SAIR");
    printf("\n\n Digite a opção: ");
    scanf("%d",&op);
    return op;
}

void quantidadeDeNosNaArvore(No* raiz){
	
}

void somaDoConteudoDeTodosNos(No* raiz) { 
	
}

void maiorValor(No* raiz){
	
}

int main() {
	setlocale(LC_ALL,"");
	No* raiz = NULL;
  	int op, item;
  	while(1) {
  		op = imprimeMenu();
	    switch(op) {
	    	case 1 : system("cls");
                printf("Digite o valor do nó: ");
                scanf("%d",&item);
                raiz = geraArvore(raiz,item);
                break;
                  
       		case 2 : system("cls");
                preOrdem(raiz);
                system("pause>>null");
                break;
       
       		case 3 : system("cls");
                emOrdem(raiz);
                system("pause>>null");
                break;
                  
       		case 4 : system("cls");
                posOrdem(raiz);
                system("pause>>null");
                break;  
                  
       		case 5 : quantidadeDeNosNaArvore(raiz);
                  break;                               
                  
       		case 6 : somaDoConteudoDeTodosNos(raiz);
                  break;
                  
       		case 7 : maiorValor(raiz);
                  break; 
                  
       		case 8 : return 0;
       	}
    }         
	return 0;
}
