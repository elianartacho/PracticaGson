import java.util.ArrayList;

public class Personaje {
    private String nombre;
    private ArrayList <String>pistas;

    public Personaje() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getPistas() {
        return pistas;
    }

    public void setPistas(ArrayList<String> pistas) {
        this.pistas = pistas;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", pistas=" + pistas +
                '}';
    }
}
