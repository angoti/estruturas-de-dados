#include <locale.h>
#include <stdio.h>

#include "pilha.c"


// Esta função devolve 1 se a string s contém uma
// sequência bem-formada de parênteses e colchetes
// e devolve 0 se a sequência é malformada.
int bemFormada (char s[]) {
	Pilha *p = criapilha();
	int i;
   	for (i = 0; s[i] != '\0'; ++i) {
    	char c;
      	switch (s[i]) {
        	case ')': if (empty(p)) return 0;
            		c = pop(p);
                   	if (c != '(') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado )",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado )");
                   	break;
         	case ']': if (empty(p)) return 0;
                   	c = pop(p);
                   	if (c != '[') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado ]",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado ]");
					break;
            case '}': if (empty(p)) return 0;
                   	c = pop(p);
                   	if (c != '{') {
						printf("\nfechamento de escopo não correpondente! Esperado %c, encontrado }",c+1);
					   	return 0;
					}
                   	printf("\ndesempilhado }");
                   	break;
         	case '(':
         	case '{':
			case '[': printf("\nempilhado %c",s[i]);
					  push(p,s[i]);
      	}
   	}
   	int retorno = empty(p);
   	liberar(p);
   	return retorno;
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
