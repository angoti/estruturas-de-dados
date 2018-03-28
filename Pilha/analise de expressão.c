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

// Esta função devolve 1 se a string s contém uma
// sequência bem-formada de parênteses e colchetes
// e devolve 0 se a sequência é malformada.
int bemFormada (char s[]) {
	criapilha ();
	int i;
   	for (i = 0; s[i] != '\0'; ++i) {
    	char c;
      	switch (s[i]) {
        	case ')': if (pilhavazia ()) return 0;
            		c = desempilha();
                   	if (c != '(') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado )",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado )");
                   	break;
         	case ']': if (pilhavazia ()) return 0;
                   	c = desempilha();
                   	if (c != '[') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado ]",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado ]");
					break;
            case '}': if (pilhavazia ()) return 0;
                   	c = desempilha();
                   	if (c != '{') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado }",c+1);
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
	printf("\nExpressão para análise: %s",expressao);
	if(bemFormada(expressao))
		printf("\n\nExpressão bem formada");
	else
		printf("\n\nExpressão mal formada");
	return 0;
}




