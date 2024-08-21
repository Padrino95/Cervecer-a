package ejercicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cerveceria {

    private String nombre_cerveceria;
    private HashMap<String, Cerveza> almacen;

    //CONSTRUCTOR
    public Cerveceria(String nombre_cerveceria) {
        if (!nombre_cerveceria.equals("")) {
            this.nombre_cerveceria = nombre_cerveceria;
            this.almacen = new HashMap<>();
        } else {
            throw new CervezaException("La cerveceria debe tener un nombre");
        }

    }

    //=====================================================================
    //OPCION 1
    //=====================================================================
    public String toString() {
        String res = "";

        res += "BIENVENIDOS A " + this.nombre_cerveceria + "\n";

        if (this.almacen.isEmpty()) {
            res += "NO TENEMOS CERVEZAS DISPONIBLES\n";
        } else {
            res += "NUESTRAS CERVEZAS DISPONIBLES SON:\n";
            for (Cerveza cer : this.almacen.values()) {
                res += cer.toString();
            }
        }

        return res;
    }

    //=====================================================================
    //OPCION 2
    //=====================================================================
    public String filtrarCervezas(String patron) {
        String res = "";

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getNombre().toLowerCase().contains(patron.toLowerCase())) {
                res += cer.toString();
            }
        }

        if (res.equals("")) {
            res = "NO COINCIDE NINGUNA CERVEZA CON ESE PATRÓN DE BUSQUEDA\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 3
    //=====================================================================
    public String verPorTipo(char tipo) {
        String res = "";

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getTipo() == tipo) {
                res += cer.toString();
            }
        }

        //PREGUNTAR SI A ENTRADO ALGUNA VEZ EN EL IF DEL BUCLE
        if (res.equals("")) {
            res = "NO HAY NINGUNA DE ESE TIPO\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 4
    //=====================================================================
    public String verPorDistribuidor(String dis) {
        String res = "";

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getDistribuidor().equalsIgnoreCase(dis)) {
                res += cer.toString();
            }
        }

        if (res.equals("")) {
            res = "NO HAY NINGUNA DE ESE DISTRIBUIDOR\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 5
    //=====================================================================
    public void rebajar(double porciento) {
        //10

        for (Cerveza cer : this.almacen.values()) {
            double descuento = cer.getPrecio() * porciento / 100;
            double precio_actual = cer.getPrecio();
            double nuevo_precio = precio_actual - descuento;

            cer.setPrecio(cer.getPrecio() - cer.getPrecio() * porciento / 100);
        }
    }

    //=====================================================================
    //OPCION 6
    //=====================================================================
    public String masCara() {
        String res = "";

        if (this.almacen.isEmpty()) {
            res = "NO HAY CERVEZAS\n";
        } else {
            ArrayList<Cerveza> ordenado
                    = new ArrayList<>(this.almacen.values());

            ordenado.sort((a, b) -> Double.compare(b.getPrecio(), a.getPrecio())
            );
            res = ordenado.get(0).toString();
        }

        //FUNCIONA PERO NO ES UN MECANISMO MUY EFICIENTE
//        Cerveza mayor=this.almacen.get(0);
//        
//        for(Cerveza cer:this.almacen){
//            if(cer.getPrecio()>mayor.getPrecio()){
//                mayor=cer;
//            }
//        }
//        
//        res=mayor.toString();
//        
        return res;
    }

    //=====================================================================
    //OPCION 7
    //=====================================================================
    public String mostrarOrdenadasPorNombre() {
        String res = "";

        if (this.almacen.isEmpty()) {
            res = "NO HAY CERVEZAS\n";
        } else {
            ArrayList<Cerveza> ordenado
                    = new ArrayList<>(this.almacen.values());
            ordenado.sort((a, b)
                    -> a.getNombre().compareTo(b.getNombre())
            //b.getNombre().compareTo(a.getNombre())
            );
            for (Cerveza cer : ordenado) {
                res += cer.toString();
            }
        }

        return res;
    }

    //=====================================================================
    //OPCION 8
    //=====================================================================
    private Cerveza buscarCerveza(String nombre) {
        return this.almacen.get(nombre);

    }

    public void añadirCerveza(String nombre, int stock, boolean artesanal, char tipo, double precio, String distribuidor) {
        //DEJAR ESTO PARA LA PLANTILLA PARA PODER PROBAR EL TOSTRING ANTES Y DESPUES PONER LO DE DOS CERVEZAS IGUALES ETC
        Cerveza nueva, busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda == null) {//SI NO EXISTE UNA CERVEZA CON ESE NOMBRE
            nueva = new Cerveza(nombre, stock, artesanal, tipo, precio, distribuidor);
            this.almacen.put(nombre, nueva);
        } else {
            throw new CervezaException("Ya existe ese nombre de cerveza.No se añade");
        }

    }

    //=====================================================================
    //OPCION 9
    //=====================================================================
    public void borrarCerveza(String nombre) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda == null) {
            throw new CervezaException("No se encuentra la cerveza a borrar");
        } else {
            this.almacen.remove(nombre);
        }

    }

    //=====================================================================
    //OPCION 10
    //=====================================================================
    public void venderCerveza(String nombre, int cantidad) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda != null) {
            busqueda.servir_cerveza(cantidad);
        } else {
            throw new CervezaException("No existe esa cerveza");
        }

    }

    //=====================================================================
    //OPCION 11
    //=====================================================================
    public void reponerCerveza(String nombre, int cantidad) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda != null) {
            busqueda.reponer_cerveza(cantidad);
        } else {
            throw new CervezaException("No existe esa cerveza");
        }
    }

    //=====================================================================
    //OPCION 12
    //===================================================================== 
    private ArrayList<Cerveza> filtrarPorTipo(char tipo) {
        ArrayList<Cerveza> res = new ArrayList<>();

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getTipo() == tipo) {
                res.add(cer);
            }
        }

        return res;
    }

    public String masBarataTipo(char tipo) {
        ArrayList<Cerveza> solo_tipo
                = this.filtrarPorTipo(tipo);
        String res;

        if (solo_tipo.isEmpty()) {
            res = "NO HAY CERVEZAS DE ESE TIPO\n";
        } else {
            solo_tipo.sort((a, b) -> Double.
                    compare(a.getPrecio(),
                            b.getPrecio()));
            res = solo_tipo.get(0).toString();
        }

        return res;
    }

    //=====================================================================
    //OPCION 13
    //=====================================================================
    public void borrarSinStock() {
        Iterator<Cerveza> puntero = this.almacen.values().iterator();

        while (puntero.hasNext()) {
            Cerveza cer = puntero.next();
            if (cer.getStock() == 0) {
                puntero.remove();
            }
        }
    }

    //=====================================================================
    //OPCION 14
    //=====================================================================
    private HashMap<Character, Integer> sumasTipos() {
        HashMap<Character, Integer> sumas = new HashMap<>();

        for (Cerveza cer : this.almacen.values()) {
            int stock_actual = sumas.getOrDefault(cer.getTipo(), 0);
            sumas.put(cer.getTipo(), stock_actual + cer.getStock());
        }

        return sumas;
    }

    public String stockPorTipo() {
        String res = "";
        HashMap<Character, Integer> sumas = this.sumasTipos();

        for (Entry<Character, Integer> par : sumas.entrySet()) {
            String tipoCompleto = Cerveza.getTipoCompleto(par.getKey());
            res += tipoCompleto + "->" + par.getValue() + "\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 15
    //=====================================================================
    public String stockPorDistribuidor() {
        return "";
    }

    //=====================================================================
    //OPCION 16
    //=====================================================================
    public String tipoMasStock() {
        String res = "";
        HashMap<Character, Integer> sumas = this.sumasTipos();

        ArrayList<Entry<Character, Integer>> ordenado
                = new ArrayList<>(sumas.entrySet());

        ordenado.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        return Cerveza.getTipoCompleto(ordenado.get(0).getKey());
    }

    //=====================================================================
    //OPCION 17
    //=====================================================================
    public String tiposSinStock() {
        String res = "";

        HashMap<Character, Integer> sumas = this.sumasTipos();
        ArrayList<Character> todos = Cerveza.todosLosTipos();

        for (Character tipo : todos) {
            if (!sumas.containsKey(tipo)) {
                res += Cerveza.getTipoCompleto(tipo);
            }
        }

        return res;
    }

    //=====================================================================
    //OPCION 18
    //=====================================================================
    public String proveedorMasImportante() {
        return "";
    }

    //=====================================================================
    //OPCION 19
    //=====================================================================
    public void guardarCervezas(String ruta) {
        try {
            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);

            for (Cerveza cer : this.almacen.values()) {
                pw.println(
                        cer.getNombre() + ":"
                        + cer.getPrecio() + ":"
                        + cer.getStock() + ":"
                        + cer.getTipo() + ":"
                        + cer.isArtesanal() + ":"
                        + cer.getDistribuidor()
                );
            }

            pw.close();
            fw.close();
        } catch (IOException ex) {
            throw new CervezaException("Error de escritura de las cervezas en fichero");
        }
    }

    //=====================================================================
    //OPCION 20
    //=====================================================================
    public void cargarCervezas(String ruta) {
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String linea, nombre, distribuidor;
            int stock;
            double precio;
            boolean artesanal;
            char tipo;
            String[] partes;
            Cerveza nueva;

            while ((linea = br.readLine()) != null) {
                partes = linea.split(":");
                nombre = partes[0];
                if (!this.almacen.containsKey(nombre)) {
                    stock = Integer.parseInt(partes[2]);
                    precio = Double.parseDouble(partes[1]);
                    artesanal = Boolean.parseBoolean(partes[4]);
                    distribuidor = partes[5];
                    tipo = partes[3].charAt(0);

                    nueva = new Cerveza(nombre, stock,
                            artesanal, tipo,
                            precio, distribuidor);
                    this.almacen.put(nombre, nueva);
                }
            }

            br.close();
            fr.close();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new CervezaException("Faltas datos en el fichero");
        } catch (NumberFormatException nfe) {
            throw new CervezaException("Tipo de dato incorrecto");
        } catch (FileNotFoundException fnf) {
            throw new CervezaException("No existe fichero de cervezas");
        } catch (IOException io) {
            throw new CervezaException("Error de lectura en disco");
        }
    }

}
