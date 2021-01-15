package prac8c.java;

import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;


class Potion implements Serializable{
    String nombre, description;
    int id, type, points;
}

class Item implements Serializable{
    int id, type, experience;
    String name, description;
}


public class Prac8CJava {
    
    
    public static void buscar (Potion[] pocion , Item[] items){
       Scanner ent= new Scanner (System.in); 
       //variables para recoger los numeros por teclado
       int op, num;
        System.out.println("------------------------------");
        do{
        System.out.println("¿Qué deseas buscar?:1- Pocima, 2- Item ");
        op=ent.nextInt();
    }while(op<1 || op>2);
        
      if(op==1){
      
          System.out.println("Por favor, introduce una posicion existente para la lista seleccionada.");
          num=ent.nextInt();
          //se muestra la pocima del espacio que introducimos por teclado
          System.out.println("Id: "+pocion[num].id);
          System.out.println("Nombre: "+pocion[num].nombre);
          System.out.println("Descripcion: "+pocion[num].description);
          System.out.println("Tipo: "+pocion[num].type);
          System.out.println("Experiencia: "+pocion[num].points);
          
          
     }else if(op==2){
          System.out.println("Por favor, introduce una posicion existente para la lista seleccionada.");
          num=ent.nextInt();
          //se muestra el item del espacio que introducimos por teclado
          System.out.println("Id: "+items[num].id);
          System.out.println("Nombre: "+items[num].name);
          System.out.println("Descripcion: "+items[num].description);
          System.out.println("Tipo: "+items[num].type);
          System.out.println("Experiencia: "+items[num].experience);
     
     }
        



}
    
    public static void modificarPuntos (Potion[] pocion, Item[] items){
    Scanner ent = new Scanner (System.in);
    int cambio;
    final int EXPERIENCE=10;
        System.out.println("-------------------------");
        do{
            
        System.out.println("¿Cuantos puntos de bonificación quieres?(1-10)");
        cambio = ent.nextInt();
        
        }while(cambio<0 || cambio>EXPERIENCE);//para que no pueda subir mas de 10 niveles
        
        for(int i = 0; i <= 5; i++ ){
         if(items[i]!=null){ //para que no haya fallo si hay algun espacio en blanco
        //guardo en la experiencia de los items el cambio introducido por teclado    
        items[i].experience =items[i].experience +cambio;
        
        if(items[i].experience>50){
            
        items[i].experience=50;
        
        }}}
        
      for(int i = 0; i <= 10; i++ ){
        if(pocion[i]!=null){
        //lo mismo que con los items    
        pocion[i].points =pocion[i].points +cambio;
        
        if(pocion[i].points>35){
            
        pocion[i].points=35;//establecer como maximo 35 puntos
        }
        }
        }
        System.out.println("Has modificado los puntos de los items y de las pociones en un" + cambio);
    
    }

    public static void guardarFicehros (Potion[] pocion, Item[] items)  {
    Scanner ent = new Scanner (System.in);
    //establezco las constantes para no salirme del array
    final int MAXPOTIONS=10;
    final int MAXITEMS=5;
    int selec;
    
        System.out.println("-----------------------");
        do{
        System.out.println("¿Que desea guardar?:1-Pocimas, 2-Items, 3-Ambos");
        selec= ent.nextInt();
        }while(selec<1 || selec>3);
        
        if(selec==1){
            try{
              //creo el fichero de texto pjpocimas  
             PrintStream pocimas;
             pocimas = new PrintStream(new FileOutputStream("pjpocimas.txt"));
             //recorro el registro en la posicion i, y los escribo en el .txt que he creado
             for(int i=0; i<MAXPOTIONS; i++){
                if(pocion[i]!=null){ 
                pocimas.println("Id: "+pocion[i].id);
                pocimas.println("Nombre: "+pocion[i].nombre);
                pocimas.println("Descripcion: "+pocion[i].description);
                pocimas.println("Tipo: "+pocion[i].type);
                pocimas.println("Experiencia: "+pocion[i].points);
                pocimas.println("------------------");
                }
               }
            }
            catch(FileNotFoundException e){
                System.out.println("Fichero no encontrado");
            }
            
        }else if(selec==2){
            try{
            //mismo porceso que en las pocimas  
            PrintStream it;
             it= new PrintStream(new FileOutputStream("pjitems.txt"));
             
             for(int i=0; i<MAXITEMS; i++){
                if(items[i]!=null){ 
                it.println("Id: "+items[i].id);
                it.println("Nombre: "+items[i].name);
                it.println("Descripcion: "+items[i].description);
                it.println("Tipo: "+items[i].type);
                it.println("Experiencia: "+items[i].experience);
                it.println("--------------------");
                }
            }
            }
            catch(FileNotFoundException e){
                System.out.println("Fichero no encontrado");
            }
        }else if(selec==3){
            try{
             //mismo proceso pero con ambos   
             PrintStream ambos;
             ambos = new PrintStream(new FileOutputStream("pjambos.txt"));
             
             for(int i=0; i<MAXPOTIONS; i++){
                if(pocion[i]!=null){ 
                ambos.println("Id: "+pocion[i].id);
                ambos.println("Nombre: "+pocion[i].nombre);
                ambos.println("Descripcion: "+pocion[i].description);
                ambos.println("Tipo: "+pocion[i].type);
                ambos.println("Experiencia: "+pocion[i].points);
                }}
                
                ambos.println("---------------");
                
             for(int i=0; i<MAXITEMS; i++){
                if(items[i]!=null){ 
                ambos.println("Id: "+items[i].id);
                ambos.println("Nombre: "+items[i].name);
                ambos.println("Descripcion: "+items[i].description);
                ambos.println("Tipo: "+items[i].type);
                ambos.println("Experiencia: "+items[i].experience);
                }
        }}
          catch(FileNotFoundException e){
                System.out.println("Fichero no encontrado");
          }catch(IOException exc){
                System.out.println("Fichero bien leido");
            }   
         
    }}         
    
    
    
    public static void main(String[] args) {
        final int MAXPOTIONS = 10;
        final int MAXITEMS = 5;
        // TODO code application logic here
        Scanner entrada = new Scanner (System.in);
        //Se definen 10 pocimas como máximo
        Potion [] pocima = new Potion [MAXPOTIONS];
        // Se definen 5 items como máximo   
        Item [] elemento = new Item [MAXITEMS];
        
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
                usarPócima(pocima);
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
                buscar(pocima, elemento);
                break;
            case 8:
                modificarPuntos(pocima, elemento);
                break;
            case 9:
                guardarFicehros(pocima,elemento);
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
        System.out.println("-----------INSERTAR PÓCIMA O ITEM-------------------");
        int opcion;
        do{    
            System.out.println("¿Qué quieres introducir? 1. Pócimas, 2. Items");
            opcion = entrada.nextInt();
            if ((opcion == 1)){
                if ((contarPociones(pocion) < pocion.length)){ 
                    int pocionesExistentes = contarPociones(pocion);
                    pocion[pocionesExistentes] = new Potion();
                    boolean existe = false;
                    do{
                        boolean identificadorIncorrecto = false;
                        do{
                            try{
                                System.out.println("Introduzca el identificador de la pócima.");
                                pocion[pocionesExistentes].id = entrada.nextInt();
                                identificadorIncorrecto = false;
                            } 
                            catch (InputMismatchException e){
                                System.out.println("Por favor introduce un número entero, para el identificador.");
                                entrada.next();
                                identificadorIncorrecto = true;
                            }
                        } while(identificadorIncorrecto);
                        existe = existeIdPocimas(pocionesExistentes, pocion);
                    } while(existe == true);
                    System.out.println("Introduzca el nombre de la pócima.");
                    pocion[pocionesExistentes].nombre = entrada.next();
                    System.out.println("Introduzca la descripción de la pócima.");
                    pocion[pocionesExistentes].description = entrada.next();
                    boolean tipoIncorrecto = false;
                    do{
                        try{
                            System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                            pocion[pocionesExistentes].type = entrada.nextInt();
                            while (pocion[pocionesExistentes].type < 1 || 3 < pocion[pocionesExistentes].type){
                                System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                pocion[pocionesExistentes].type = entrada.nextInt();
                                tipoIncorrecto = false;
                            }
                            tipoIncorrecto = false;
                        }
                        catch (InputMismatchException e){
                            System.out.println("Por favor introduce un número entero, para el tipo de pócima.");
                            entrada.next();
                            tipoIncorrecto = true;
                        }
                    } while(tipoIncorrecto);
                    
                    boolean puntosIncorrectos = false;
                    do{        
                        try{    
                            System.out.println("Introduzca el valor de los puntos.");
                            pocion[pocionesExistentes].points = entrada.nextInt();
                            while (pocion[pocionesExistentes].points < 5 || 35 < pocion[pocionesExistentes].points){
                                System.out.println("Error, introduce un número que esté en el intevalo [5, 35].");
                                System.out.println("Introduzca el valor de los puntos.");                        
                                pocion[pocionesExistentes].points = entrada.nextInt();
                                puntosIncorrectos = false;
                            }
                            puntosIncorrectos = false;
                        }catch (InputMismatchException e){
                            System.out.println("Por favor introduce un número entero, para los puntos.");
                            entrada.next();
                            puntosIncorrectos = true;
                        }
                    }while(puntosIncorrectos);
                } else  {
                    System.out.println("Ya has insertado 10 pócimas, usa una pócima para añadir más.");
                }
            }
            
            else if ((opcion == 2)){
                if (contarItems(items) < items.length){
                    int objetosEspeciales = contarItems(items);
                    items[objetosEspeciales] = new Item();
                    boolean existe2 = false;
                    do{
                        boolean identificadorIncorrecto = false;
                        do{
                            try{
                                System.out.println("Introduzca el identificador del item.");
                                items[objetosEspeciales].id = entrada.nextInt();
                                identificadorIncorrecto = false;
                            } 
                            catch (InputMismatchException e){
                                System.out.println("Por favor introduce un número entero, para el identificador.");
                                entrada.next();
                                identificadorIncorrecto = true;
                            }
                        } while(identificadorIncorrecto);
                        
                        existe2 = existeIdItems(objetosEspeciales, items);
                    } while(existe2);
                    
                    System.out.println("Introduzca el nombre del item.");
                    items[objetosEspeciales].name = entrada.next();
                    System.out.println("Introduzca la descripción del item.");
                    items[objetosEspeciales].description = entrada.next();
                    
                    boolean tipoIncorrecto = false;
                    do{
                        try{
                            System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                            items[objetosEspeciales].type = entrada.nextInt();
                            while (items[objetosEspeciales].type < 1 || 3 < items[objetosEspeciales].type){
                                System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                items[objetosEspeciales].type = entrada.nextInt();
                                tipoIncorrecto = false;
                            }
                            tipoIncorrecto = false;
                        }
                        catch (InputMismatchException e){
                            System.out.println("Por favor introduce un número entero, para el tipo de item.");
                            entrada.next();
                            tipoIncorrecto = true;
                        }
                    } while(tipoIncorrecto);
                    
                    boolean puntosIncorrectos = false;
                    do{        
                        try{    
                            System.out.println("Introduzca el valor de los puntos de experiencia.");
                            items[objetosEspeciales].experience = entrada.nextInt();
                            while (items[objetosEspeciales].experience < 15 || 50 < items[objetosEspeciales].experience){
                                System.out.println("Error, introduce un número que esté en el intevalo [15, 50].");
                                System.out.println("Introduzca el valor de los puntos de experiencia.");                        
                                items[objetosEspeciales].experience = entrada.nextInt();
                                puntosIncorrectos = false;
                            }
                            puntosIncorrectos = false;
                        }catch (InputMismatchException e){
                            System.out.println("Por favor introduce un número entero, para los puntos de experiencia.");
                            entrada.next();
                            puntosIncorrectos = true;
                        }
                    } while(puntosIncorrectos);
                } else  {
                    System.out.println("Ya has insertado 5 items, usa un item para añadir más.");
                }
            }
            System.out.println("-------------------------------------------");
            System.out.println("¿Quieres añadir algo más? 1. SÍ, 2. NO");
        } while (entrada.nextInt() == 1);
    } 
    
    public static void posicionDeterminada(Potion[] pocion, Item[] items ){
       Scanner entrada = new Scanner(System.in);
       System.out.println("---------------INSERTAR POCIMA O ITEM EN POSICION DETERMINADA--------------");
       System.out.println("¿Cuál es tu raza de personaje? 1: Humano, 2: Orco, 3: Elfo, 4: Enano");
       int raza = entrada.nextInt();
       if (raza == 3 || raza == 4){
            int opcion;
            do{    
                System.out.println("¿Qué quieres introducir? 1. Pócimas, 2. Items");
                opcion = entrada.nextInt();
                if ((opcion == 1)){
                    if ((contarPociones(pocion) < pocion.length)){
                        int pocionesExistentes = contarPociones(pocion);
                        pocion[pocionesExistentes] = new Potion();
                        Potion nuevaPocion = new Potion();
                        boolean existe = false;
                        do{
                            boolean identificadorIncorrecto = false;
                            do{
                                try{
                                    System.out.println("Introduzca el identificador de la pócima.");
                                    nuevaPocion.id = entrada.nextInt();
                                    identificadorIncorrecto = false;
                                } 
                                catch (InputMismatchException e){
                                    System.out.println("Por favor introduce un número entero, para el identificador.");
                                    entrada.next();
                                    identificadorIncorrecto = true;
                                }
                            } while(identificadorIncorrecto);
                            existe = existeIdPocimas2(pocionesExistentes, nuevaPocion, pocion);
                        } while(existe);
                        System.out.println("Introduzca el nombre de la pócima.");
                        nuevaPocion.nombre = entrada.next();
                        System.out.println("Introduzca la descripción de la pócima.");
                        nuevaPocion.description = entrada.next();
                        boolean tipoIncorrecto = false;
                        do{
                            try{
                                System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                nuevaPocion.type = entrada.nextInt();
                                while (nuevaPocion.type < 1 || 3 < nuevaPocion.type){
                                    System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                    System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                    nuevaPocion.type = entrada.nextInt();
                                    tipoIncorrecto = false;
                                }
                                tipoIncorrecto = false;
                            }
                            catch (InputMismatchException e){
                                System.out.println("Por favor introduce un número entero, para el tipo de pócima.");
                                entrada.next();
                                tipoIncorrecto = true;
                            }
                        } while(tipoIncorrecto);
                        
                        boolean puntosIncorrectos = false;
                        do{        
                            try{    
                                System.out.println("Introduzca el valor de los puntos.");
                                nuevaPocion.points = entrada.nextInt();
                                while (nuevaPocion.points < 5 || nuevaPocion.points > 35){
                                    System.out.println("Error, introduce un número que esté en el intevalo [5, 35].");
                                    System.out.println("Introduzca el valor de los puntos.");                        
                                    nuevaPocion.points = entrada.nextInt();
                                    puntosIncorrectos = false;
                                }
                                puntosIncorrectos = false;
                            }catch (InputMismatchException e){
                                System.out.println("Por favor introduce un número entero, para los puntos.");
                                entrada.next();
                                puntosIncorrectos = true;
                            }
                        }while(puntosIncorrectos);
                        
                        System.out.println("¿En qué posición quieres insertar la nueva pócima? (entre 1 y " + contarPociones(pocion) + ")");
                        int posicion = entrada.nextInt();
                        while (posicion < 1 || posicion > contarPociones(pocion)){
                            System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarPociones(pocion) + "]");
                            posicion = entrada.nextInt();
                        }
                        for (int i = (contarPociones(pocion) - 2); i >= (posicion - 1); i--){
                            pocion[i + 1] = pocion[i];
                        }
                        pocion[posicion - 1] = nuevaPocion;
                    } else  {
                        System.out.println("Ya has insertado 10 pócimas, usa una pócima para añadir más.");
                    }
                }
                
                else if ((opcion == 2)){
                    if ((contarItems(items) < items.length)){ 
                        int objetosEspeciales = contarItems(items);
                        items[objetosEspeciales] = new Item();
                        Item nuevoItem = new Item();
                        boolean existe2 = false;
                        do{
                            boolean identificadorIncorrecto = false;
                            do{
                                try{
                                    System.out.println("Introduzca el identificador del item.");
                                    nuevoItem.id = entrada.nextInt();
                                    identificadorIncorrecto = false;
                                } 
                                catch (InputMismatchException e){
                                    System.out.println("Por favor introduce un número entero, para el identificador.");
                                    entrada.next();
                                    identificadorIncorrecto = true;
                                }
                            } while(identificadorIncorrecto);
                            existe2 = existeIdItems2(objetosEspeciales, nuevoItem, items);
                        } while(existe2);
                        System.out.println("Introduzca el nombre del item.");
                        nuevoItem.name = entrada.next();
                        System.out.println("Introduzca la descripción del item.");
                        nuevoItem.description = entrada.next();

                        boolean tipoIncorrecto = false;
                        do{
                            try{
                                System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                nuevoItem.type = entrada.nextInt();
                                while (nuevoItem.type < 1 || 3 < nuevoItem.type){
                                    System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                    System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                    nuevoItem.type = entrada.nextInt();
                                    tipoIncorrecto = false;
                                }
                                tipoIncorrecto = false;
                            }
                            catch (InputMismatchException e){
                                System.out.println("Por favor introduce un número entero, para el tipo de item.");
                                entrada.next();
                                tipoIncorrecto = true;
                            }
                        } while(tipoIncorrecto);
                        
                        boolean puntosIncorrectos = false;
                        do{        
                            try{    
                                System.out.println("Introduzca el valor de los puntos de experiencia.");
                                nuevoItem.experience = entrada.nextInt();
                                while (nuevoItem.experience < 15 || nuevoItem.experience > 50){
                                    System.out.println("Error, introduce un número que esté en el intevalo [15, 50].");
                                    System.out.println("Introduzca el valor de los puntos de experiencia.");                        
                                    nuevoItem.experience = entrada.nextInt();
                                    puntosIncorrectos = false;
                                }
                                puntosIncorrectos = false;
                            }catch (InputMismatchException e){
                                System.out.println("Por favor, introduce un número entero para los puntos de experiencia.");
                                entrada.next();
                                puntosIncorrectos = true;
                            }
                        }while(puntosIncorrectos);
                        
                        System.out.println("¿En qué posición quieres insertar el nuevo item? (entre 1 y " + contarItems(items) + ")");
                        int posicion = entrada.nextInt();
                        while (posicion < 1 || posicion > contarItems(items)){
                            System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarItems(items) + "]");
                            posicion = entrada.nextInt();
                        }
                        for (int i = (contarItems(items) - 2); i >= (posicion - 1); i--){
                            items[i + 1] = items[i];
                        }
                        items[posicion - 1] = nuevoItem;
                    } else  {
                        System.out.println("Ya has insertado 5 items, usa un item para añadir más.");
                    }
                }
                System.out.println("-------------------------------------------");
                System.out.println("¿Quieres añadir algo más? 1. SÍ, 2. NO");
            } while (entrada.nextInt() == 1);
       } else {
           System.out.println("No tienes esta ventaja. Vuelve al menú principal y selecciona crear pócima o ítem.");
           System.out.println("-------------------------------------------");
       }
    }
    
    public static int contarPociones(Potion[] pocion){
        int boundPotions = 0;
        for (int i = 0; i < pocion.length; i++){
            if (pocion[i] != null){
                boundPotions++;
            }
        }
       return boundPotions;
    }
    
    public static int contarItems(Item[] items){
        int boundItems = 0;
        for (int i = 0; i < items.length; i++){
            if (items[i] != null){
                boundItems++;
            }
        }
       return boundItems;
    }
    
    public static void usarPócima(Potion[] pocion){
        System.out.println("-------------------USAR PÓCIMA------------------");
        if(contarPociones(pocion) > 0){
            int tope = (contarPociones(pocion) - 1);
            int posición = 0;
            for (int m = posición; m < tope; m++){
                pocion[m] = pocion[m + 1];
            }
            pocion[tope] = null;
            if (tope ==0){
                System.out.println("Ya has usado todas las pócimas.");
            }
            else{
                System.out.println("Pócima usada.");
            }
        } else {
            System.out.println("No hay pócimas cargadas");
        }
    }
    
    public static boolean existeIdPocimas (int pociones, Potion[] pocion){
        for(int i = 0; i < pociones; i++){
            if (pocion[i].id == pocion[pociones].id){
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }
    
    public static boolean existeIdPocimas2 (int pociones, Potion nuevaPocion, Potion[] pocion){
        for(int i = 0; i < pociones; i++){
            if (pocion[i].id == nuevaPocion.id){
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }
    
    public static boolean existeIdItems (int objetosEspeciales, Item[] items){
        for(int i = 0; i < objetosEspeciales; i++){
            if (items[i].id == items[objetosEspeciales].id){
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }
    
    public static boolean existeIdItems2 (int objetosEspeciales, Item nuevoItem, Item[] items){
        for(int i = 0; i < objetosEspeciales; i++){
            if (items[i].id == nuevoItem.id){
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }
}
