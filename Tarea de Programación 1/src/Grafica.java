import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase se modelan las gráficas
 * @author Armando Aquino
 **/
class Grafica {

    int V;     //Vertices de la grafica
    List<Arista> aristas; //Aristas de la gráfica
    ArrayList<Integer> vertices;  // Vertices de la gráfica

    /**
     * Constructor para las gráficas
     * @param V Numero de vértices de la gráfica
     **/
    public Grafica(int V) {
        this.V = V;
        aristas = new ArrayList<>();
        vertices = new ArrayList<>();
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
     * Metodo que agrega aristas a la grafica
     * @param  u Extremo de la arista
     * @param v Otro extremo de la arista
     * */
    public void agregarArista(int u, int v) {
        Arista arista = new Arista(u, v);
        aristas.add(arista);
    }

    /**
     * Para obtener la lista de arista
     * @return La lista con las aristas;
     * */
    public  List<Arista> getAristas(){
        return aristas;
    }

    /**
     * Metodo que agrega vertices a la grafica
     * @param v Vertice de la gráfica
     * */
    public void agregarVertice(int v) {
        vertices.add(v);
    }

    /**
     * Para obtener la lista de vertices
     * @return La lista con los vertices
     * */
    public  List<Integer> getVertices(){
        return vertices;
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

    public Arista(int u, int v) {
        this.u = u;
        this.v = v;
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