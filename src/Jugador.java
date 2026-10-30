

public class Jugador implements Comparable<Jugador>{
    int puntuacion;
    int posicionRanking;
    String nombre;

    public Jugador(){};

    public Jugador(int puntuacion,String nombre) {
        this.puntuacion = puntuacion;
        this.nombre=nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPosicionRanking() {
        return posicionRanking;
    }

    public void setPosicionRanking(int posicionRanking) {
        this.posicionRanking = posicionRanking;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Jugador{" +
                "puntuacion=" + puntuacion +
                ", posicionRanking=" + posicionRanking +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public int compareTo(Jugador otroJugador) {
        return Integer.compare(otroJugador.puntuacion,this.puntuacion);
    }
}
