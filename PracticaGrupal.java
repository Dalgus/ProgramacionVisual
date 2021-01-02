package prac8C;

import java.io.*;
import java.util.*;

class Potion implements Serializable{
    String nombre, description;
    int id, type, points;
}

class Item implements Serializable{
    int id, type, experience;
    String name, description;
}


public class Prac8CJava {

    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner (System.in);
        //Se definen 10 pocimas como máximo
        Potion [] pocima = new Potion [10];
        // Se definen 5 items como máximo   
        Item [] elemento = new Item [5];
        System.out.println("RPG-URJC del GRUPO L, formado por: Héctor Fernández Rubio, Umesh Mostajo Sáez, Jesús Navas Torres, Victor Sanz Valenzuela.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        int opción;
        do {
            System.out.println(" ");
            System.out.println("Bienvenido a Ihnildur, ¿Qué deseas hacer?");
            System.out.println("Menú:");
            System.out.println("1. Insertar pócima o ítem.");
            System.out.println("2. Insertar pócima o ítem en posición determinada (Elfo o Enano).");
            System.out.println("3. Usar pócima.");
            System.out.println("4. Tirar ítem.");
            System.out.println("5. Mostrar pócimas e ítems.");
            System.out.println("6. Mostrar pócimas e ítems ordenados alfabeticamente.");
            System.out.println("7. Buscar pócima o ítem.");
            System.out.println("8. Subida de nivel.");
            System.out.println("9. Guardar pócimas e ítems a fichero de textos.");
            System.out.println("10. Cargar última partida desde texto.");
            System.out.println("11. Guardar pócimas e ítems a disco.");
            System.out.println("12. Cargar pócimas e ítems desde disco.");
            System.out.println("13. Mostrar puntos de un tipo de pócima.");
            System.out.println("14. Terminar");
            
            opción = entrada.nextInt();
        
            switch (opción) {
            case 1:
                insertarPocimaEItem(pocima, elemento);
                break;
            case 2:
                posicionDeterminada(pocima, elemento);
                break;
            case 3:
                System.out.println("Menú:");
                break;
            case 4:
                System.out.println("Menú:");
                break;
            case 5:
                System.out.println("Menú:");
                break;
            case 6:
                System.out.println("Menú:");
                break;
            case 7:
                System.out.println("Menú:");
                break;
            case 8:
                System.out.println("Menú:");
                break;
            case 9:
                System.out.println("Menú:");
                break;
            case 10:
                System.out.println("Menú:");
                break;
            case 11:
                System.out.println("Menú:");
                break;
            case 12:
                System.out.println("Menú:");
                break;
            case 13:
                System.out.println("Menú:");
                break;
            default:
                System.out.println("Menú:");
            }
        } while (opción != 14);
    }
    
    public static void insertarPocimaEItem (Potion[] pocion, Item [] items){
        Scanner entrada = new Scanner (System.in);
        System.out.println("-------------------------------------------");
        int pociones = contarPociones(pocion);
        int objetosEspeciales = contarItems(items);
        int opcion;
        do{    
            System.out.println("¿Qué quieres introducir? 1. Pócimas, 2. Items");
            opcion = entrada.nextInt();
            if ((opcion == 1) && (pociones < pocion.length)){
                pocion[pociones] = new Potion();
                boolean existe = false;
                do{
                    System.out.println("Introduzca el identificador de la pócima.");
                    pocion[pociones].id = entrada.nextInt();
                    for(int i = 0; i < pociones; i++){
                        if (pocion[i].id == pocion[pociones].id){
                            System.out.println("Este identificador ya existe");
                            existe = true;
                        }
                        else {
                            existe = false;
                        }
                    }
                } while(existe);
                System.out.println("Introduzca el nombre de la pócima.");
                pocion[pociones].nombre = entrada.next();
                System.out.println("Introduzca la descripción de la pócima.");
                pocion[pociones].description = entrada.next();
                System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                pocion[pociones].type = entrada.nextInt();
                System.out.println("Introduzca el valor de los puntos.");
                pocion[pociones].points = entrada.nextInt();
                pociones++;
            }
            else if ((opcion == 2) && (objetosEspeciales < items.length)){
                items[objetosEspeciales] = new Item();
                boolean existe = false;
                do{
                    System.out.println("Introduzca el identificador del item.");
                    items[objetosEspeciales].id = entrada.nextInt();
                    for(int i = 0; i < objetosEspeciales; i++){
                        if (items[i].id == items[objetosEspeciales].id){
                            System.out.println("Este identificador ya existe");
                            existe = true;
                        }
                        else {
                            existe = false;
                        }
                    }
                } while(existe);
                System.out.println("Introduzca el nombre del item.");
                items[objetosEspeciales].name = entrada.next();
                System.out.println("Introduzca la descripción del item.");
                items[objetosEspeciales].description = entrada.next();
                System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                items[objetosEspeciales].type = entrada.nextInt();
                System.out.println("Introduzca el valor de la experiencia.");
                items[objetosEspeciales].experience = entrada.nextInt();
                objetosEspeciales++;
            }
            System.out.println("¿Quieres añadir algo más? 1. SÍ, 2. NO");
        } while (entrada.nextInt() == 1);
    } 
    
    public static void posicionDeterminada(Potion[] pocion, Item[] items ){
       Scanner entrada = new Scanner(System.in);
       System.out.println("-------------------------------------------");
       System.out.println("¿Cuál es tu raza de personaje? 1: Humano, 2: Orco, 3: Elfo, 4: Enano");
       int raza = entrada.nextInt();
       if (raza == 3 || raza == 4){
           insertarPocimaEItem(pocion, items);
       }
       else{
           System.out.println("No tienes esta ventaja. Vuelve al menú principal y selecciona crear pócima o ítem.");
           System.out.println("-------------------------------------------");
       }
    }
    public static int contarPociones(Potion[] pocion){
        int i = 0;
        while (pocion[i] != null){
            i++;
        } 
       return i;
    }
    public static int contarItems(Item[] items){
        int i = 0;
        while (items[i] != null){
            i++;
        } 
       return i;
    }
   
}
