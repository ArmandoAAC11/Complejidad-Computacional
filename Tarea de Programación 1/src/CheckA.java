import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Se implementa el algoritmo no determinista polinomial para el problema de Alcanzabilidad
 * @author Armando Aquino
 * */
public class CheckA {

    /**
     * Implementacion del algoritmo CheckA
     * @param G Grafica
     * @param s Vértice origen
     * @param t Vértice return
     * @return Lista que representa el camino si existe
     * */
    public static List<Integer> AlgoritmoCheckA(Grafica G, int s, int t){

        // ************************** FASE ADIVINADORA ***************************
        List<Integer> camino = new ArrayList<>(); // Creamos nuestro posible camino que no repite vertices
        camino.add(s);                            // Le agregamos el vértice origen
        List<Integer> V = G.getVertices();
        V.remove(s);                              // A los vértices de la gráfica le quitamos el vertice origen
        Random dado = new Random();               // Lo utilizaremoos como nd-choice

        while(!camino.contains(t)){
            int j = dado.nextInt(V.size());
            Integer vj = V.get(j);
            camino.add(vj);
            V.remove(vj);
        }

        // ************************** FASE VERIFICADORA ***************************
        boolean esCaminoValido = true;
        for (int i = 0; i < camino.size() - 1; i++){
            int u = camino.get(i);
            int v = camino.get(i + 1);
            boolean existeArista = false;

            for(Arista arista : G.getAristas()){
                if ((arista.u == u && arista.v == v) || (arista.u == v && arista.v == u)){
                    //return null;
                    existeArista = true;
                    break;
                }
            }

            if (!existeArista) {
                esCaminoValido = false;
                break;  // No es un camino válido, terminamos la verificación
            }
        }

        if (esCaminoValido) {
            return camino;
        } else {
            return null;
        }
    }

    //Main
    public static void main(String [] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Grafica1A.txt")); // Leemos el archivo
        String nVertices = reader.readLine();
        String [] elementos = nVertices.split(", ");
        int V = elementos.length;
        Grafica G = new Grafica(V);
        for (String elemento : elementos) {
            G.agregarVertice(Integer.parseInt(elemento));
        }
        int s = Integer.parseInt(reader.readLine());
        int t = Integer.parseInt(reader.readLine());
        String line;
        while((line = reader.readLine()) != null){
            String[] partes = line.split(", ");
            int u = Integer.parseInt(partes[0]);  //Un extremo de la arista
            int v = Integer.parseInt(partes[1]); // Otro extremo de la arista
            G.agregarArista(u, v);
        }
        reader.close();

        List<Integer> respuesta = AlgoritmoCheckA(G, s, t); // Aplicamos nuestro algoritmo
        if (respuesta != null){
            System.out.println("SI se satisface la pregunta de decisión para I = (G, " +s+ ", " +t+ ")");
            System.out.println("El camino es:");
            for(Integer v: respuesta){
                System.out.println(v);
            }
        }else{
            System.out.println("NO se satisface la pregunta de decisión para I = (G, " +s+ ", " +t+ ")");
        }
    }
}
