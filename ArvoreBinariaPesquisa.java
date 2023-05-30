package aula24_arvores_binarias_pesquisa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinariaPesquisa {
    class Nodo {
        public int item;
        public Nodo pai;
        public Nodo esquerda;
        public Nodo direita;

        public Nodo(int item) {
            this.item = item;
            this.pai = null;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private Nodo raiz;
    private int tamanho;

    public ArvoreBinariaPesquisa() {
        tamanho = 0;
    }

    private void adicionarRecursivamente(Nodo novoNodo, Nodo pai) {
        if (novoNodo.item < pai.item) {
            if (pai.esquerda == null) {
                pai.esquerda = novoNodo;
                novoNodo.pai = pai;
            } else
                adicionarRecursivamente(novoNodo, pai.esquerda);
        } else {
            if (pai.direita == null) {
                pai.direita = novoNodo;
                novoNodo.pai = pai;
            } else
                adicionarRecursivamente(novoNodo, pai.direita);
        }
    }

    public void adicionar(int item) {
        Nodo novoNodo = new Nodo(item);
        if (this.tamanho == 0)
            this.raiz = novoNodo;
        else
            adicionarRecursivamente(novoNodo, raiz);
        this.tamanho++;
    }

    public boolean estaVazia() {
        if (tamanho > 0)
            return false;
        else
            return true;
    }

    public int obterTamanho() {
        return this.tamanho;
    }

    public void limpar() {
        this.raiz = null;
        this.tamanho = 0;
    }

    public int obterEsquerda(int item) {
        Nodo aux = existe(item);
        if (aux != null) {
            return aux.esquerda.item;
        }
        return -1;
    }

    public int obterDireita(int item) {
        Nodo aux = existe(item);
        if (aux != null) {
            return aux.direita.item;
        }
        return -1;
    }

    public int obterPai(int item) {
        Nodo aux = existe(item);
        if (aux == null)
            return -1;
        else
            return aux.pai.item;
    }

    public ArrayList<Integer> elementosPreOrdem() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        preOrdemRecursivo(this.raiz, lista);
        return lista;
    }

    private void preOrdemRecursivo(Nodo pai, ArrayList<Integer> lista) {
        lista.add(pai.item);
        if (pai.esquerda == null)
            preOrdemRecursivo(raiz.esquerda, lista);
        if (pai.direita == null)
            preOrdemRecursivo(raiz.direita, lista);
    }

    public ArrayList<Integer> elementosCentralOrdem() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        centralOrdemRecursivo(this.raiz, lista);
        return lista;
    }

    private void centralOrdemRecursivo(Nodo pai, ArrayList<Integer> elementos) {
        if (pai.esquerda != null)
            centralOrdemRecursivo(pai.esquerda, elementos);
        elementos.add(pai.item);
        if (pai.direita != null)
            centralOrdemRecursivo(pai.direita, elementos);
    }

    public ArrayList<Integer> elementosPosOrdem() {
        ArrayList<Integer> elementos = new ArrayList<>();
        posOrdemRecursivo(this.raiz, elementos);
        return elementos;
    }

    private void posOrdemRecursivo(Nodo pai, ArrayList<Integer> elementos) {
        elementos.add(pai.item);
        if (pai.direita != null)
            posOrdemRecursivo(pai.direita, elementos);
        if (pai.esquerda != null)
            posOrdemRecursivo(pai.esquerda, elementos);
    }

    public ArrayList<Integer> elementosLargura() {
        ArrayList<Integer> elementos = new ArrayList<Integer>();
        Queue<Nodo> fila = new LinkedList<>();
        fila.add(this.raiz);
        while (!fila.isEmpty()) {
            Nodo aux = fila.poll();
            elementos.add(aux.item);
            if (aux.esquerda != null)
                fila.add(aux.esquerda);
            if (aux.direita != null)
                fila.add(aux.direita);
        }
        return null;
    }

    public int obterNivel(int item) {
        int nivel = 0;
        Nodo aux = existe(item);
        while (aux != null) {
            nivel++;
            aux = aux.pai;
        }
        return nivel;
    }

    public Nodo existe(int item) {
        Nodo aux = this.raiz;
        while (aux != null) {
            if (aux.item == item)
                return aux;
            if (aux.item > item)
                aux = aux.esquerda;
            else
                aux = aux.direita;
        }
        return null;
    }

    private boolean existeRecursivo(Nodo nodo, int chave) {
        if (nodo == null)
            return false;
        if (nodo.item == chave)
            return true;
        if (nodo.item > chave)
            return existeRecursivo(nodo.esquerda, chave);
        if (nodo.item < chave)
            return existeRecursivo(nodo.direita, chave);
        return false;
    }

    public int altura() {
        return alturaRecursivo(this.raiz, -1, -1);
    }

    private int alturaRecursivo(Nodo nodo, int altura_esq, int altura_dir) {
        if (nodo == null)
            return Math.max(altura_esq, altura_dir);
        else {
            if (nodo.esquerda != null) {
                altura_esq = alturaRecursivo(nodo.esquerda, altura_esq + 1, altura_dir);
            }
            if (nodo.direita != null) {
                altura_dir = alturaRecursivo(nodo.direita, altura_dir + 1, altura_esq);
            }
        }
        return Math.max(altura_esq, altura_dir);

    }

    public boolean ehInterno(int item) {
        Nodo nodo = existe(item);
        if (nodo != null) {
            if (nodo.esquerda != null || nodo.direita != null)
                return true;
        }
        return false;
    }

    public boolean ehExterno(int item) {
        Nodo nodo = existe(item);
        if (nodo != null) {
            if (nodo.esquerda == null && nodo.direita == null)
                return true;
        }
        return false;
    }

    public void remover(int item) {
        Nodo nodo = existe(item);
        nodo.pai = null;
        nodo.esquerda = null;
        nodo.direita = null;
        nodo.item = 0;
    }

    public void imprimirArvore() {
        imprimirArvoreRecusivamente(this.raiz, 0);
    }

    private void imprimirArvoreRecusivamente(Nodo raiz, int nivel) {
        if (raiz == null)
            return;
        nivel += 5;
        imprimirArvoreRecusivamente(raiz.direita, nivel);
        System.out.print("\n");
        for (int i = 5; i < nivel; i++)
            System.out.print(" ");
        System.out.print(raiz.item + "\n");
        for (int i = 5; i < nivel; i++)
            System.out.print(" ");
        imprimirArvoreRecusivamente(raiz.esquerda, nivel);
    }
}
