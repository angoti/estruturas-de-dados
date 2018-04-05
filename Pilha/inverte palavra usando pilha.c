#include <locale.h>
#include <stdio.h>
#include "pilha.c"

void empilharCaracteresDaPalavraLida(Pilha *p, char s[]) {
	int i;
   	for (i = 0; s[i] != '\0'; ++i) {
  		push(p,s[i]);    	
    }
}

void desempilharEImprimir(Pilha *p){
	char letra;
	while(!empty(p)){
		letra = pop(p);
		printf("%c",letra);
	}
}

int main() {
	setlocale(LC_ALL,"");
	char palavra[100];
	printf("Digite a palavra a ser invertida: ");
	gets(palavra);
	Pilha *pilha = criapilha();
	empilharCaracteresDaPalavraLida(pilha,palavra);
	desempilharEImprimir(pilha);
	return 0;
}
