import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        boolean rodando = true;
        Scanner teclado = new Scanner(System.in);
        Pilha p = new Pilha();
        Fila f = new Fila();
        while (rodando) {
            System.out.println("Selecione a opção:");
            System.out.println("1 - Pilha");
            System.out.println("2 - Fila");
            System.out.println("3 - Printar todos");
            System.out.println("0 - Sair");
            System.out.print("Sua opção: ");
            int opcao = Integer.parseInt(teclado.nextLine());
            switch (opcao) {
                case 1:
                    boolean rodandoPilha = true;
                    while (rodandoPilha) {
                        System.out.println("Selecione a opção:");
                        System.out.println("1 - Adicionar na pilha");
                        System.out.println("2 - Remover da pilha");
                        System.out.println("3 - Printar topo da pilha");
                        System.out.println("4 - Printar a pilha");
                        System.out.println("0 - Sair");
                        System.out.print("Sua opção: ");
                        int opcaoPilha = Integer.parseInt(teclado.nextLine());
                        switch (opcaoPilha) {

                            case 1:
                                System.out.print("ID: ");
                                String novoID = teclado.nextLine();
                                System.out.print("Descrição: ");
                                String desc = teclado.nextLine();
                                System.out.print("Data: ");
                                String data = teclado.nextLine();
                                p.adicionar(new Elemento(novoID, desc, data));
                                break;
                        
                            case 2:
                                p.remover();
                                break;
                        
                            case 3:
                                System.out.println(p.topo());
                                break;
                        
                            case 4:
                                p.printar();
                                break;
                        
                            case 0:
                                rodandoPilha = false;
                                break;
                        
                            default:
                                break;
                        }
                    }
                        
                    break;
            
                case 2:

                    boolean rodandoFila = true;
                    while (rodandoFila) {
                        System.out.println("Selecione a opção:");
                        System.out.println("1 - Adicionar na fila");
                        System.out.println("2 - Remover da fila");
                        System.out.println("3 - Printar início da fila");
                        System.out.println("4 - Printar final da fila");
                        System.out.println("5 - Printar a fila");
                        System.out.println("0 - Sair");
                        System.out.print("Sua opção: ");
                        int opcaoFila = Integer.parseInt(teclado.nextLine());
                        switch (opcaoFila) {

                            case 1:
                                System.out.print("Nome: ");
                                String nome = teclado.nextLine();
                                System.out.print("Número: ");
                                String numero = teclado.nextLine();
                                System.out.print("Motivo: ");
                                String motivo = teclado.nextLine();
                                f.adicionar(new Elemento(nome, numero, motivo));
                                break;
                        
                            case 2:
                                f.remover();
                                break;
                        
                            case 3:
                                System.out.println(f.primeiro());
                                break;
                        
                            case 4:
                                System.out.println(f.ultimo());
                                break;
                        
                            case 5:
                                f.printar();
                                break;
                        
                            case 0:
                                rodandoFila = false;
                                break;
                        
                            default:
                                break;
                        }
                    }

                    break;
            
                case 3:
                    p.printar();
                    f.printar();
                    break;
            
                case 0:
                    rodando = false;
                    break;
            
                default:
                    break;
            }

        }
        teclado.close();
    }
}

class Pilha {
    Lista lista;
    public Pilha() {
        this.lista = new Lista();
    }
    public void adicionar(Elemento e) {
        lista.adicionarFinal(e);
    }
    public void remover() {
        lista.removerUltimo();
    }
    public Elemento topo() {
        return lista.getUtlimo();
    }
    public void printar() {
        lista.printar();
    }
}

class Fila {
    Lista lista;
    public Fila() {
        this.lista = new Lista();
    }
    public void adicionar(Elemento e) {
        lista.adicionarFinal(e);
    }
    public void remover() {
        lista.removerPrimeiro();
    }
    public Elemento primeiro() {
        return lista.getPrimeiro();
    }
    public Elemento ultimo() {
        return lista.getUtlimo();
    }
    public void printar() {
        lista.printar();
    }
}

class Lista {
    private int tamanho;
    private ListaItem primeiro;
    Lista() {
        tamanho = 0;
        primeiro = null;
    }
    void printar() {
        System.out.println("Tamanho: " + tamanho);
        ListaItem l = primeiro;
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("[%d]: " + l.item + "\n", i);
            l = l.proximo;
        }
    }
    boolean vazio() {
        return primeiro == null;
    }
    int getTamanho() {
        return tamanho;
    }
    Elemento getPrimeiro() {
        if (primeiro != null)
            return primeiro.item;
        else
            return null;
    }
    Elemento getUtlimo() {
        if (primeiro == null) {
            return null;
        }
        ListaItem atual = primeiro;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }
        return atual.item;
    }
    void adicionarFinal(Elemento item) {
        tamanho++;
        ListaItem novo = new ListaItem(item);
        if (vazio()) {
            primeiro = novo;
            return;
        }
        ListaItem l = primeiro;
        while (l.proximo != null) {
            l = l.proximo;
        }
        l.proximo = novo;
    }
    void adicionarPrimeiro(Elemento item) {
        adicionar(0, item);
    }
    void adicionar(int onde, Elemento item) {
        if (onde < 0) {
            System.out.println("Impossível adicionar item antes da lista.");
            return;
        }
        if (onde > tamanho) {
            System.out.println("Número maior que tamanho da lista");
            return;
        }
        if (onde == tamanho) {
            adicionarFinal(item);
            return;
        }
        tamanho++;
        ListaItem novo = new ListaItem(item);
        if (onde == 0) {
            novo.proximo = primeiro;
            primeiro = novo;
            return;
        }
        ListaItem antes = primeiro;
        ListaItem prox = primeiro.proximo;
        for (int i = 1; i < onde; i++) {
            antes = prox;
            prox = prox.proximo;
        }
        antes.proximo = novo;
        novo.proximo = prox;
    }
    void removerPrimeiro() {
        remover(0);
    }
    void remover(int onde) {
        if (onde < 0) {
            System.out.println("Impossível remover antes da lista");
            return;
        }
        if (onde >= tamanho) {
            System.out.println("Impossível remover maior que lista");
            return;
        }
        tamanho--;
        if (onde == 0) {
            if (primeiro != null) {
                primeiro = primeiro.proximo;
            }
            return;
        }
        ListaItem atual = primeiro;
        for (int i = 1; i < onde; i++) {
            atual = atual.proximo;
        }
        atual.proximo = atual.proximo.proximo;
    }
    void removerUltimo() {
        remover(tamanho - 1);
    }
}

class ListaItem {
    Elemento item;
    ListaItem proximo;
    ListaItem(Elemento item) {
        this.item = item;
        proximo = null;
    }
    public String toString() {
        return item.toString();
    }
}

class Elemento {
    String id;
    String campo1;
    String campo2;
    Elemento(String id, String campo1, String campo2) {
        this.id = id;
        this.campo1 = campo1;
        this.campo2 = campo2;
    }
    public String toString() {
        return String.format("%-6s | %-10s\t| %s", id, campo1, campo2);
    }
}
