import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que implementa el algoritmo de aproximación para el problema Subset Sum.
 */
public class SubsetSum {

    /**
     * Lee los parámetros desde un archivo y ejecuta el algoritmo de aproximación de Subset Sum.
     * @param args Argumentos de línea de comando
     * @throws IOException Error al leer el archivo.
     */
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("SubsetSumEjemploLibro.txt"));
        BufferedReader br = new BufferedReader(new FileReader("SubsetSumEjemploGrande.txt"));

        
        double epsilon = Double.parseDouble(br.readLine().trim()); // Lee los parametros epsilon, t y S del archivo
        int t = Integer.parseInt(br.readLine().trim());
        String[] sArray = br.readLine().split(",");
        List<Integer> S = new ArrayList<>();
        for (String numStr : sArray) {
            S.add(Integer.parseInt(numStr.trim()));
        }
        br.close();

        int resultado = ApproxSubsetSum(S, t, epsilon);  // Ejecutamos el algoritmo
        System.out.println("Solucion Encontrada: " + resultado);
    }

    /**
     * Implementa el algoritmo APPROX-SUBSET-SUM 
     * @param S La lista de números enteros del conjunto.
     * @param t El valor objetivo.
     * @param epsilon El factor de poda
     * @return El valor más cercano  a t 
     */
    public static int ApproxSubsetSum(List<Integer> S, int t, double epsilon) {
        List<Integer> L = new ArrayList<>();
        L.add(0);

        for (int x : S) {
            List<Integer> Li = new ArrayList<>();
            // Agregamos elementos de L y L + x
            for (int y : L) {
                Li.add(y);
                if (y + x <= t) {
                    Li.add(y + x);
                }
            }

            Collections.sort(Li);
            L = Trim(Li, epsilon / (2 * S.size()));
            L.removeIf(n -> n > t); // Eliminamos los elementos mayores que t
        }

        // Encontrar el valor más grande en L
        if (L.isEmpty()) {
            return 0;
        } else {
            return L.get(L.size() - 1);
        }
    }

    /**
     * Reduce el tamanio de una lista eliminando elementos según el factor de poda.
     * @param L La lista.
     * @param delta El factor de poda.
     * @return Una lista reducida basada en el factor de poda.
     */
    private static List<Integer> Trim(List<Integer> L, double delta) {
        List<Integer> Laux = new ArrayList<>();
        Laux.add(L.get(0));
        int last = L.get(0);

        for (int i = 1; i < L.size(); i++) {
            int actual = L.get(i);
            if (actual > last * (1 + delta)) {
                Laux.add(actual);
                last = actual;
            }
        }
        return Laux;
    }
}
