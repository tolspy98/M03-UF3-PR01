import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        ArrayList<String> movies = loadMovies();
        System.out.println("Películas cargadas: " + movies.size());
        System.out.println("🎯 Bienvenido al juego 'Guess the Movie'!");



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
    public static String getRandomMovie(ArrayList<String> movies) {
        Random random = new Random();
        return movies.get(random.nextInt(movies.size()));
    }
    public static String hideMovieTitle(String title) {
        StringBuilder hidden = new StringBuilder();
        for (char c : title.toCharArray()) {
            if (Character.isLetter(c)) {
                hidden.append('*');
            } else {
                hidden.append(c);
            }
        }
        return hidden.toString();
    }
    public static int showMenu() {
        System.out.println("\nElige una opción:");
        System.out.println("[1] Adivinar una letra");
        System.out.println("[2] Adivinar el título de la película");
        System.out.println("[3] Salir");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String guessLetter(String title, String hiddenTitle, char letter, HashSet<Character> guessedLetters) {
        if (guessedLetters.contains(Character.toUpperCase(letter))) {
            System.out.println("Ya has adivinado esa letra. Intenta otra.");
            return hiddenTitle;
        }
        guessedLetters.add(Character.toUpperCase(letter));

        StringBuilder updatedTitle = new StringBuilder(hiddenTitle);
        boolean found = false;
        for (int i = 0; i < title.length(); i++) {
            if (Character.toUpperCase(title.charAt(i)) == Character.toUpperCase(letter)) {
                updatedTitle.setCharAt(i, title.charAt(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("La letra no está en el título.");
        } else {
            System.out.println("¡Correcto!");
        }

        return updatedTitle.toString();
    }
    public static boolean guessTitle(String title, String guessedTitle) {
        return title.equalsIgnoreCase(guessedTitle);
    }



}