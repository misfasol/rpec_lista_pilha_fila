class Main {
    public static void main(String[] args) {
        Elemento[] filaAtendimento = new Elemento[] {
            new Elemento("CLI001", "Maria Silva", "Dúvida sobre produto"),
            new Elemento("CLI002", "João Souza", "Reclamação de serviço"),
            new Elemento("CLI003", "Ana Costa", "Solicitação de reembolso"),
            new Elemento("CLI004", "Pedro Alves", "Informações de entrega"),
            new Elemento("CLI005", "Carla Dias", "Agendamento de visita"),
            new Elemento("CLI006", "Lucas Martins", "Alteração de pedido"),
            new Elemento("CLI007", "Patrícia Rocha", "Cancelamento de contrato"),
            new Elemento("CLI008", "Rafael Lima", "Renovação de assinatura"),
            new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalação"),
            new Elemento("CLI010", "Carlos Eduardo", "Pedido de orçamento")
        };
        Fila f = new Fila();
        for (int i = 0; i < 5; i++) {
            f.adicionar(filaAtendimento[i]);
        }
        f.printar();
        f.remover();
        f.remover();
        f.remover();
        for (int i = 0; i < 5; i++) {
            f.adicionar(filaAtendimento[i+5]);
        }
        f.printar();
        for (int i = 0; i < 7; i++) {
            f.remover();
        }
        f.printar();

        Elemento[] historico = new Elemento[] {
            new Elemento("REQ001", "Instalação de software", "2024-08-20 10:30"),
            new Elemento("REQ002", "Manutenção preventiva", "2024-08-20 11:00"),
            new Elemento("REQ003", "Atualização de sistema", "2024-08-20 11:30"),
            new Elemento("REQ004", "Suporte técnico", "2024-08-20 12:00"),
            new Elemento("REQ005", "Troca de equipamento", "2024-08-20 12:30"),
            new Elemento("REQ006", "Consulta de garantia", "2024-08-20 13:00"),
            new Elemento("REQ007", "Reparo de impressora", "2024-08-20 13:30"),
            new Elemento("REQ008", "Configuração de rede", "2024-08-20 14:00"),
            new Elemento("REQ009", "Restauração de dados", "2024-08-20 14:30"),
            new Elemento("REQ010", "Consulta técnica", "2024-08-20 15:00")
        };
        Pilha p = new Pilha();
        for (int i = 0; i < 5; i++) {
            p.adicionar(historico[i]);
        }
        p.printar();
        p.remover();
        p.remover();
        p.remover();
        for (int i = 0; i < 5; i++) {
            p.adicionar(historico[i+5]);
        }
        p.printar();
        for (int i = 0; i < 7; i++) {
            p.remover();
        }
        p.printar();
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
        return String.format("%s | %-25s\t| %s", id, campo1, campo2);
    }
}
