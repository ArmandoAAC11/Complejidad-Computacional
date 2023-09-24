import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase se modelan las gráficas
 * @author Armando Aquino
 **/
class Grafica {

    int V;     //Vertices de la grafica
    List<Arista> aristas; //Aristas de la gráfica

    /**
     * Constructor para las gráficas
     * @param V Numero de vértices de la gráfica
     **/
    public Grafica(int V) {
        this.V = V;
        aristas = new ArrayList<>();
    }

    /**
     * Metodo que agrega aristas a la grafica
     * @param  u Extremo de la arista
     * @param v Otro extremo de la arista
     * @param peso Peso de la arista
     * */
    public void agregarArista(int u, int v, int peso) {
        Arista arista = new Arista(u, v, peso);
        aristas.add(arista);
    }

    /**
     * Para obtener la lista de arista
     * @return La lista con las aristas;
     * */
    public  List<Arista> getAristas(){
        return aristas;
    }

}

/**
 * En esta clase se modelan las aristas de la gráfica
 * */
class Arista implements Comparable<Arista> {
    int u, v, peso;  // Extremos de la arista y su peso

    /**
     * Constructor para las aristas
     * @param  u Extremo de la arista
     * @param  v Extremo de la arista
     * @param  peso Peso de la arista
     * */
    public Arista(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }

    /**
     * Comparamos aristas en funcion de sus pesos
     * @param otraA Arista
     * */
    @Override
    public int compareTo(Arista otraA) {
        return Integer.compare(this.peso, otraA.peso);
    }
}