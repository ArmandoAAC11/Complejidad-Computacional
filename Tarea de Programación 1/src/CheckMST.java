import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Se implementa el algoritmo no determinista polinomial para el problema del Arbol Generador de Peso Minimo
 * @author Armando Aquino
 * */
public class CheckMST {

    /**
     * Implementacion del algoritmo CheckMST
     * @param G Grafica
     * @param B la cota a no exceder
     * */
    public static List<Arista> AlgoritmoCheckMST(Grafica G, int B){

        int n = G.V; //Obtenemos el orden de la grafica

        // ************************** FASE ADIVINADORA ***************************
        List<Arista> E = G.getAristas();
        List<Arista> tree = new ArrayList<>();
        int pesoT = 0;
        int numE = 0;  // Contador del numero de aristas
        Random dado = new Random(); // Lo utilizaremos como nd-choice

        while(numE < n - 1){
            int j = dado.nextInt(E.size()); // Lanzamos el dado equilibrado de |E| caras
            Arista ej = E.get(j);

            tree.add(ej);
            pesoT = pesoT + ej.peso;
            E.remove(j);
            numE++;
        }

        // ************************** FASE VERIFICADORA ***************************
        if(pesoT <= B){
            System.out.println("SI se satisface la pregunta de decisión para I = (G, " +B+ ")");
        }else{
            System.out.println("No se satisface la pregunta de decisión para I = (G, " +B+ ")");
        }
        return tree;
    }

    // Main
    public static void main(String [] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Petersen1B.txt")); // Leemos el archivo
        int B = Integer.parseInt(reader.readLine());
        String nVertices = reader.readLine();
        String [] elementos = nVertices.split(", ");
        int V = elementos.length;

        Grafica G = new Grafica(V);

        String line;
        while((line = reader.readLine()) != null){
            String[] partes = line.split(", ");
            int u = Integer.parseInt(partes[0]);  //Un extremo de la arista
            int v = Integer.parseInt(partes[1]); // Otro extremo de la arista
            int peso = Integer.parseInt(partes[2]); // Peso de la arista
            G.agregarArista(u, v, peso);
        }
        reader.close();

        List<Arista> aristasArbol = AlgoritmoCheckMST(G, B); // Aplicamos nuestro algoritmo
        System.out.println("Aristas del árbol:");
        for (Arista arista : aristasArbol) {
            System.out.println(arista.u + " - " + arista.v + " (" + arista.peso + ")");
        }
    }
}
