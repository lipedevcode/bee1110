import java.io.IOException;
import java.util.Scanner;

public class Main {
    static class Node {
        Node prox;
        int numero_carta;
    }
    static Node topo;
    static Node base;
    static int quantidade_restante_cartas;
    static String cartas_descartadas;
    static String cartas_remanescentes;
    public static void init_pilha(int n){
        topo = null;
        base = null;
        quantidade_restante_cartas = n;
        for(int i = 1; i <=n; i++){
            if(topo == null){
                Node novo = new Node();
                novo.numero_carta = i;
                base = novo;
                topo = novo;
                topo.prox = base;
            }
            else {
                Node antigoUltimo = new Node();
                antigoUltimo.numero_carta = i;
                base.prox = antigoUltimo;
                base = antigoUltimo;
            }
        }
    }
    public static void joga_cartas_fora(){
        cartas_descartadas = "Discarded cards: ";
        cartas_remanescentes = "Remaining cards: ";
        // testar com while(quantidade_restante_cartas > 2)
        while (quantidade_restante_cartas >= 2) {
            if(topo.prox.prox == null)
                cartas_descartadas = cartas_descartadas + topo.numero_carta;
            else cartas_descartadas = cartas_descartadas + topo.numero_carta + ", ";
            topo = topo.prox;
            Node topoAux = topo;
            if(topo.prox != null)
                topo = topo.prox;
            base.prox = topoAux;
            topoAux.prox = null;
            base = topoAux;
            quantidade_restante_cartas --;
        }
        cartas_remanescentes = cartas_remanescentes + topo.numero_carta;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            if(n == 0)
                break;
            init_pilha(n);
            joga_cartas_fora();
            System.out.println(cartas_descartadas);
            System.out.println(cartas_remanescentes);
        }
    }
}
