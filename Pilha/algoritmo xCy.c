#include <locale.h>
#include <stdio.h>
#include <string.h>

#define N 100
char pilha[N];
int t;

void criapilha () {
   	t = 0;
}

void empilha (char y) {
   	pilha[t++] = y;
}

char desempilha () {
   	return pilha[--t];
} 

int pilhavazia () {
   	return t <= 0;
}

int algxCy(char s[]) {
	criapilha();
	int i;
	int acao = 0; //vari�vel de controle. 0 indica empilhar letras e 1 indica desempilhar letras
	// quando encontra a letra C, acao passa a valer 1
   	for (i = 0; s[i] != '\0'; ++i) {
    	char c;
    	if(s[i]=='C')
    		acao = 1;
    	else 
      	switch (acao) {
        	case 0: printf("\nempilhado %c",s[i]);
					empilha(s[i]);
                   	break;
         	case 1: if (pilhavazia ()) return 0;
                   	c = desempilha();
                   	if (c != s[i]) {
						printf("\nLetra n�o corresponde! Esperado %c, encontrado %c",c,s[i]);
					   	return 0;
					}
                   	printf("\ndesempilhado %c",s[i]);
					break;
      	}
   	}
   	return pilhavazia();
}



int main() {
	setlocale(LC_ALL,"");
	char *expressao = "BABBACABBABA";
	printf("\nExpress�o para an�lise: %s",expressao);
	if(algxCy(expressao))
		printf("\n\nExpress�o bem formada");
	else
		printf("\n\nExpress�o mal formada");
	return 0;
}




