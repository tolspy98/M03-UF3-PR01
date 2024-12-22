import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        System.out.println("🎯 Bienvenido al juego 'Guess the Movie'!");
        ArrayList<String> movies = loadMovies();
        System.out.println("Películas cargadas: " + movies.size());
    }
    public static ArrayList<String> loadMovies() {
        ArrayList<String> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("movies.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                movies.add(line.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de películas: " + e.getMessage());
        }
        return movies;
    }
}