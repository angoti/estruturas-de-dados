typedef struct pilha Pilha;

Pilha* criapilha();
void liberar(Pilha* p);
void push(Pilha* p, char item);
char pop(Pilha* p);
int empty(Pilha* p);
