import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdivinaPersonaje {
    private static int puntos;
    private static int intentos;
    private static int puntosTotales;
    private static ArrayList<Jugador> jugadores;
    static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Boolean salir = false;
        puntosTotales=0;
        ComprobarExiste();
        try (FileReader fichero = new FileReader("src/resources/personajes.json")) {
            Personaje[] personajes = gson.fromJson(fichero, Personaje[].class);
            System.out.println("Bienvenidooo jugador introduzca su nombre: ");
            String nombre = sc.nextLine();
            while (!salir) {
                intentos=0;
                puntos = 3;
                boolean acertar = false;
                System.out.println("Se esta eligiendo un personaje");
                int personajeAleatorio = random.nextInt(personajes.length);
                Personaje personajeActual = personajes[personajeAleatorio];

                for (int i = 0; i < personajeActual.getPistas().size(); i++) {
                    System.out.println(personajeActual.getPistas().get(i));
                    System.out.println("Introduce el personaje: ");
                    String personaje = sc.nextLine();
                    puntos-=intentos;
                    intentos++;

                    if (personajeActual.getNombre().equalsIgnoreCase(personaje)) {
                        acertar = true;
                        puntosTotales+=puntos;
                        System.out.println("Acertaste!!!");
                        break;
                    }
                }
                if (!acertar) {
                    System.out.println("Vaya no acertaste el personaje es: " + personajeActual.getNombre());
                }
                System.out.println("Desea volver a jugar? S/N");
                String res = sc.nextLine();
                if (res.equalsIgnoreCase("N")) {
                    salir = true;
                    Jugador jugador = new Jugador(puntosTotales, nombre);
                    jugadores.add(jugador);
                    crearDocumento();
                }

            }


        } catch (IOException e) {
            System.out.println("Error al leer el documento JSON.");
        }


    }
    public static void ComprobarExiste(){
        File archivoRanking = new File("src/resources/ranking.json");
        if (archivoRanking.exists()) {
            try (FileReader ficheroRanking = new FileReader(archivoRanking)) {
                Type tipoLista = new TypeToken<ArrayList<Jugador>>(){}.getType();
                jugadores = gson.fromJson(ficheroRanking, tipoLista);
            } catch (IOException e) {
                System.out.println("Error al leer el ranking previo.");
            }
        } else {
            jugadores = new ArrayList<>();
        }
    }

    private static void crearDocumento() {
        jugadores.sort(null);
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).setPosicionRanking(i + 1);
            System.out.println(jugadores.get(i));
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter ficheroJugador=new FileWriter("src/resources/ranking.json")){
            gson.toJson(jugadores,ficheroJugador);

        } catch (IOException e) {
            System.out.println("Error al escribir el documento JSON.");
        }

    }

}