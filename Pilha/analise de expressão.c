#include <locale.h>
#include <stdio.h>

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

// Esta fun��o devolve 1 se a string s cont�m uma
// sequ�ncia bem-formada de par�nteses e colchetes
// e devolve 0 se a sequ�ncia � malformada.
int bemFormada (char s[]) {
	criapilha ();
	int i;
   	for (i = 0; s[i] != '\0'; ++i) {
    	char c;
      	switch (s[i]) {
        	case ')': if (pilhavazia ()) return 0;
            		c = desempilha();
                   	if (c != '(') {
						printf("\nfechamento de escopo n�o correpondente! Esperado %c, encontrado )",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado )");
                   	break;
         	case ']': if (pilhavazia ()) return 0;
                   	c = desempilha();
                   	if (c != '[') {
						printf("\nfechamento de escopo n�o correpondente! Esperado %c, encontrado ]",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado ]");
					break;
            case '}': if (pilhavazia ()) return 0;
                   	c = desempilha();
                   	if (c != '{') {
						printf("\nfechamento de escopo n�o correpondente! Esperado %c, encontrado }",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado }");
                   	break;
         	case '(':
         	case '{':
			case '[': printf("\nempilhado %c",s[i]);
					  empilha(s[i]);
      	}
   	}
   	return pilhavazia();
}



int main() {
	setlocale(LC_ALL,"");
	char *expressao = "(((A))))";
	printf("\nExpress�o para an�lise: %s",expressao);
	if(bemFormada(expressao))
		printf("\n\nExpress�o bem formada");
	else
		printf("\n\nExpress�o mal formada");
	return 0;
}




