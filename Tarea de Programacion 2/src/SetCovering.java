import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase SetCovering que implementa el algoritmo de aproximacion GREEDY-SET-COVER.
 */
public class SetCovering {

    /**
     * Método main que lee un archivo de entrada y ejecuta el algoritmo GRREDY-SET-COVER.
     * @param args Argumentos de línea de comando.
     * @throws IOException Error al leer el archivo.
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("SetCoveringEjemploLibro.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("SetCoveringEjemploGrande.txt")); // Ejemplo Grande
        String line;

        line = br.readLine();
        List<Integer> X = new ArrayList<>();              // Lee la primera línea para el conjunto X
        for (String numStr : line.split(",")) {
            X.add(Integer.parseInt(numStr.trim()));
        }

        List<List<Integer>> F = new ArrayList<>();        // Lee la familia de subconjuntos F
        while ((line = br.readLine()) != null) {
            List<Integer> subset = new ArrayList<>();
            for (String numStr : line.split(",")) {
                subset.add(Integer.parseInt(numStr.trim()));
            }
            F.add(subset);
        }
        br.close();

        List<List<Integer>> solution = GreedySetCover(X, F); // Ejecutamos el algoritmo
        System.out.println("Solución Encontrada: " + solution);
    }

    /**
     * Implementacion del algoritmo de aproximacion GREEDY-SET-COVER
     * @param X El conjunto finito
     * @param F Familia de subconjuntos de X
     * @return Solucion obtenida.
     */
    public static List<List<Integer>> GreedySetCover(List<Integer> X, List<List<Integer>> F) {
        List<Integer> U = new ArrayList<>(X); // Creamos la copia de X
        List<List<Integer>> C = new ArrayList<>();

        while (!U.isEmpty()) {
            List<Integer> greedySet = null;
            int maxCover = -1;

            for (List<Integer> S : F) {  // Encontrar el subconjunto que maximiza el número de elementos
                List<Integer> temp = intersection(S, U);
                if (temp.size() > maxCover) {
                    greedySet = S;
                    maxCover = temp.size();
                }
            }

            if (greedySet != null) {          // Actualizamos U y C
                U.removeAll(greedySet);
                C.add(greedySet);
            }
        }

        return C;
    }

    /**
     * Calcula la intersección de dos listas de enteros.
     * @param lista1 La primera lista.
     * @param lista2 La segunda lista
     * @return Una lista que contiene los elementos de la primer y segunda lista.
     */
    private static List<Integer> intersection(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> resultado = new ArrayList<>();
        for (Integer item : lista1) {
            if (lista2.contains(item)) {
                resultado.add(item);
            }
        }
        return resultado;
    }
}

