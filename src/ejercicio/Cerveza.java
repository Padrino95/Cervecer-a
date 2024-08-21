package ejercicio;

import java.util.ArrayList;
import java.util.HashMap;

public class Cerveza {

    private String nombre;
    private int orden, stock;
    private boolean artesanal;
    private char tipo;
    private double precio;
    private String distribuidor;

    private static int contador = 1;

    private final static HashMap<Character, String> tiposCompletos
            = new HashMap<>() {
        {
            put('r', "Rubia");
            put('R', "Roja");
            put('n', "Negra");
            put('t', "Tostada");
        }
    };

    public Cerveza(String nombre, int stock, boolean artesanal, char tipo, double precio, String distribuidor) {
        if (!nombre.equals("")
                && stock >= 0
                && precio > 0
                && !distribuidor.equals("")
                && tiposCompletos.containsKey(tipo)) {
            this.nombre = nombre;
            this.orden = this.contador;
            this.contador++;
            this.stock = stock;
            this.artesanal = artesanal;
            this.tipo = tipo;
            this.precio = precio;
            this.distribuidor = distribuidor;
        } else {
            throw new CervezaException("Fallo al construir");
        }
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new CervezaException("ERROR:Precio incorrecto");
        }
    }

    public void servir_cerveza(int unidades) {
        if (unidades > this.stock) {
            int auxiliar=this.stock;
            this.stock = 0;
            throw new CervezaException("NO HAY SUFICIENTES SE HAN SERVIDO " + auxiliar);
        } else if (this.stock == 0) {
            throw new CervezaException("NO HAY STOCK ACTUALMENTE");
        } else {
            this.stock -= unidades;
        }
    }

    public void reponer_cerveza(int unidades) {
        if(unidades>0){
            this.stock += unidades;
        }else{
            throw new CervezaException("Unidades deben ser >0");
        }
    }

    public void setTipo(char tipo) {
        if (this.tiposCompletos.containsKey(tipo)) {
            this.tipo = tipo;
        } else {
            throw new CervezaException("Tipo incorrecto");
        }
    }

    public String toString() {
        String res = "";
        res += "-----------------\n"
                + "ORDEN DE LLEGADA:" + this.orden + "\n"
                + "NOMBRE:" + this.nombre + "\n"
                + "STOCK:" + this.stock + "\n"
                + "PRECIO:" + this.precio + "\n"
                + "DISTRIBUIDOR:" + this.distribuidor + "\n";
        if (this.artesanal) {
            res += "FABRICACION ARTESANAL\n";
        } else {
            res += "FABRICACION INDUSTRIAL\n";
        }
        res += "TIPO:" + this.tiposCompletos.get(this.tipo) + "\n";

        res += "-----------------\n";
        return res;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getOrden() {
        return this.orden;
    }

    public int getStock() {
        return this.stock;
    }

    public boolean isArtesanal() {
        return this.artesanal;
    }

    public char getTipo() {

        return this.tipo;
    }

    public static String getTipoCompleto(char tipo) {
        return tiposCompletos.get(tipo);
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getDistribuidor() {
        return this.distribuidor;
    }

    public boolean equals(Cerveza c) {
        return this.nombre.equals(c.nombre);
    }

    public static ArrayList<Character> todosLosTipos() {
        ArrayList<Character> res
                = new ArrayList<>(tiposCompletos.keySet());

        return res;
    }

}
