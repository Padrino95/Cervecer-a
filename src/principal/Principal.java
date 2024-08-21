package principal;

import ejercicio.Cerveceria;
import ejercicio.CervezaException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Cerveceria la_cerveceria = new Cerveceria("Cervezas EAG");

        //CERVEZAS DE PRUEBA


        int opcion=-1;
        String nombre, dis;
        char tipo;
        int existencias, unidades;
        boolean artesanal;
        double precio, porciento;
        String ruta;
        
        do {
            try {
                System.out.println("  .   *   ..  . *  *\r\n" + //
                                        "*  * @()Ooc()*   o  .\r\n" + //
                                        "    (Q@*0CG*O()  ___\r\n" + //
                                        "   |\\_________/|/ _ \\\r\n" + //
                                        "   |  |  |  |  | / | |\r\n" + //
                                        "   |  |  |  |  | | | |\r\n" + //
                                        "   |  |  |  |  | | | |\r\n" + //
                                        "   |  |  |  |  | | | |\r\n" + //
                                        "   |  |  |  |  | | | |\r\n" + //
                                        "   |  |  |  |  | \\_| |\r\n" + //
                                        "   |  |  |  |  |\\___/\r\n" + //
                                        "   |\\_|__|__|_/|\r\n" + //
                                        "    \\_________/\r\n" + //
                                        "");
                System.out.println("0.Salir");
                System.out.println("1.Ver todas las cervezas");
                System.out.println("2.Filtrar por nombre");
                System.out.println("3.Ver cervezas de un tipo concreto");
                System.out.println("4.Ver cervezas de un distribuidor concreto");
                System.out.println("5.Rebajar un % todas las cervezas");
                System.out.println("6.Ver la cerveza más cara");
                System.out.println("7.Ver las cervezas ordenadas alfabeticamente");
                System.out.println("8.Añadir Cerveza");
                System.out.println("9.Borrar cerveza por nombre");
                System.out.println("10.Vender cerveza");
                System.out.println("11.Reponer cerveza");
                System.out.println("12.Ver la cerveza más barata de un tipo concreto");
                System.out.println("13.Borrar sin stock");
                System.out.println("14.Mostrar stock por tipos de cervezas");
                System.out.println("15.Mostrar stock por distribuidor");
                System.out.println("16.Mostrar el tipo de cerveza con más stock de la cerveceria");
                System.out.println("17.Mostrar los tipos de cerveza sin stock de la cerveceria");
                System.out.println("18.Mostrar el distribuidor que más stock provee a la cerveceria");
                System.out.println("19.Guardar cervezas en un fichero de texto");
                System.out.println("20.Cargar cervezas desde un fichero de texto");
                System.out.print("Elige una opción disponible:");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("Adios");
                        break;
                    case 1:
                        //MOSTRAR TODAS LAS CERVEZAS
                        System.out.println(la_cerveceria.toString());
                        break;
                    case 2:
                        //MOSTRAR FILTRADAS POR NOMBRE
                        System.out.println("Dime el patrón de busqueda:");
                        nombre = teclado.nextLine();
                        System.out.println(la_cerveceria.filtrarCervezas(nombre));

                        break;
                    case 3:
                        //MOSTRAR POR TIPO
                        System.out.println("Dime el tipo (r,R,n,t):");
                        tipo = teclado.next().charAt(0);
                        teclado.nextLine();
                        System.out.println("Cervezas por tipo:\n" + la_cerveceria.verPorTipo(tipo));

                        break;
                    case 4:
                        //MOSTRAR POR DISTRIBUIDOR
                        System.out.println("Dime el distribuidor:");
                        dis = teclado.nextLine();
                        System.out.println("Cervezas del distribuidor:\n" + la_cerveceria.verPorDistribuidor(dis));

                        break;
                    case 5:
                        //REBAJAR UN %
                        System.out.println("Dime el porciento:");
                        porciento = teclado.nextDouble();
                        teclado.nextLine();
                        la_cerveceria.rebajar(porciento);
                        System.out.println("Cervezas rebajadas");
                        break;
                    case 6:
                        //LA CERVEZA MAS CARA
                        System.out.println("La mas cara es:\n" + la_cerveceria.masCara());
                        break;
                    case 7:
                        //MOSTRAR ORDENADAS ALFABETICAMENTE
                        System.out.println("Cervezas ordenadas por nombre:\n" + la_cerveceria.mostrarOrdenadasPorNombre());
                        break;

                    case 8:
                        //AÑADIR UN NUEVO OBJETO CERVEZA
                        System.out.println("Dime el nombre:");
                        nombre = teclado.nextLine();
                        System.out.println("Dime las existencias:");
                        existencias = teclado.nextInt();
                        System.out.println("Dime si es artesanal (true o false):");
                        artesanal = teclado.nextBoolean();
                        System.out.println("Dime el tipo (r,R,n,t):");
                        tipo = teclado.next().charAt(0);
                        System.out.println("Dime el precio:");
                        precio = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.println("Dime el distribuidor:");
                        dis = teclado.nextLine();
                        la_cerveceria.añadirCerveza(nombre, existencias, artesanal, tipo, precio, dis);
                        System.out.println("Cerveza añadida con éxito");
                        break;

                    case 9:
                        //BORAR UNA CERVEZA DE LA COLECCION POR NOMBRE
                        System.out.println("Dime el nombre a borrar:");
                        nombre = teclado.nextLine();
                        la_cerveceria.borrarCerveza(nombre);
                        System.out.println("Borrado con éxito");
                        break;
                    case 10:
                        //VENDER STOCK DE UN OBJETO CERVEZA
                        System.out.println("Dime el nombre a vender:");
                        nombre = teclado.nextLine();
                        System.out.println("Dime cuanto se vende:");
                        unidades = teclado.nextInt();
                        teclado.nextLine();
                        la_cerveceria.venderCerveza(nombre, unidades);
                        System.out.println("Vendida con éxito");
                        break;
                    case 11:
                        //REPONER STOCK DE UN OBJETO CERVEZA
                        System.out.println("Dime el nombre:");
                        nombre = teclado.nextLine();
                        System.out.println("Dime cuanto reponer:");
                        unidades = teclado.nextInt();
                        teclado.nextLine();
                        la_cerveceria.reponerCerveza(nombre, unidades);
                        System.out.println("Repuesta con éxito");
                        break;
                    case 12:
                        //MOSTRAR LA CERVEZA MAS BARATA DE UN TIPO CONCRETO
                        System.out.println("Dime el tipo (r,R,n,t):");
                        tipo = teclado.next().charAt(0);
                        teclado.nextLine();
                        System.out.println("La más barata de tipo " + tipo + " es:\n" + la_cerveceria.masBarataTipo(tipo));
                        break;
                    case 13:
                        //BORRAR TODAS LAS CERVEZAS CON STOCK A 0
                        la_cerveceria.borrarSinStock();
                        System.out.println("Borradas sin stock");
                        break;
                    case 14:
                        //RESUMEN STOCK POR TIPO DE CERVEZA
                        System.out.println("Stock por tipo de cervezas:\n");
                        System.out.println(la_cerveceria.stockPorTipo());
                        break;
                    case 15:
                        //RESUMEN STOCK POR NOMBRE DE DISTRIBUIDOR
                        System.out.println("Stock por distribuidor:\n");
                        System.out.println(la_cerveceria.stockPorDistribuidor());
                        break;
                    case 16:
                        //TIPO DE CERVEZA CON MAS STOCK
                        System.out.println("Tipo de cerveza más abundante:\n");
                        System.out.println(la_cerveceria.tipoMasStock());
                        break;
                    case 17:
                        //TIPOS SIN STOCK
                        System.out.println("Tipos de cerveza sin stock:\n");
                        System.out.println(la_cerveceria.tiposSinStock());
                        break;
                    case 18:
                        //DISTRIBUIDOR QUE MAS UNIDADES PROPORCIONA
                        System.out.println("Distribuidor que más provee:\n");
                        System.out.println(la_cerveceria.proveedorMasImportante());
                        break;
                    case 19:
                        System.out.print("Ruta para guardar las cervezas:");
                        ruta=teclado.next();
                        la_cerveceria.guardarCervezas(ruta);
                        System.out.println("Cervezas guardadas con éxito");
                        break;
                    case 20:
                        System.out.print("Ruta donde obtener las cervezas:");
                        ruta=teclado.next();
                        teclado.nextLine();
                        la_cerveceria.cargarCervezas(ruta);
                        System.out.println("Cervezas cargar con éxito");
                        break;
                    default:
                        System.out.println("Opcion erronea¡¡¡");
                }
            } catch (InputMismatchException ime) {
                teclado.nextLine();
                System.out.println("Datos de entrada incorrectos");
            } catch (CervezaException ce){
                System.out.println(ce.getMessage());
            } 

            System.out.println("Pulsa intro para continuar");
            teclado.nextLine();
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
        } while (opcion != 0);
    }
}
