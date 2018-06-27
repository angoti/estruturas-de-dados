#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

/* Uma �rvore bin�ria (= binary tree) � formada de n�s; 
   cada n� tem um conte�do (n�mero,letra, palavra etc) e os
   endere�os de duas sub�rvores: uma esquerda e uma direita */
 
typedef struct no No;
struct no {
   int  item;  // conte�do do n�
   No *ae, *ad;  // ae - �rvore esquerda, ad - �rvore diretia
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

/* Quando dizemos um n� x devemos entender que x � o endere�o de um n�.
 Nesses termos, o filho esquerdo de um n�  x  �  x->ae 
 e o filho direito �  x->ad. Um n� x � uma folha se n�o tem filhos,
 ou seja, se  x->ae e x->ad valem  NULL.*/

int imprimeMenu() {
	int op;
	system("cls");
    printf("---------------M E N U-------------");
    printf("\n\n1- Insere n� na �rvore");
    printf("\n2- Exibe em Pr�-Ordem");
    printf("\n3- Exibe em Ordem");
    printf("\n4- Exibe em P�s-Ordem");
    printf("\n5- Quantidade de n�s na �rvore");
    printf("\n6- Soma do conte�do de todos n�s");
    printf("\n7- Exibe o n� com o maior valor");
    printf("\n8- SAIR");
    printf("\n\n Digite a op��o: ");
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
                printf("Digite o valor do n�: ");
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
