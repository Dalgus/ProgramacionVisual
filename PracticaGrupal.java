package PracticaGrupal;

import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

class Potion implements Serializable {

    String name, description;
    int id, type, points;

    public Potion(String nombre, String description, int id, int type, int points) {
        this.name = nombre;
        this.description = description;
        this.id = id;
        this.type = type;
        this.points = points;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNombre() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

}

class Item implements Serializable {

    int id, type, experience;
    String name, description;

    public Item(String nombre, String description, int id, int type, int experience) {
        this.name = nombre;
        this.description = description;
        this.id = id;
        this.type = type;
        this.experience = experience;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNombre() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

}

public class PracticaGrupal {

    public static void main(String[] args) throws IOException {
        final int MAXPOTIONS = 10;
        final int MAXITEMS = 5;
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        //Se definen 10 pocimas como máximo
        Potion[] pocima = new Potion[MAXPOTIONS];
        // Se definen 5 items como máximo   
        Item[] elemento = new Item[MAXITEMS];

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
            System.out.println("13. Terminar");

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
                    guardarFicheros(pocima, elemento);
                    break;
                case 10:
                    leerFicheros();
                    break;
                case 11:
                    guardarFicherosDat(pocima, elemento);
                    break;
                case 12:
                    leerFicherosDat(pocima, elemento);
                    break;
                default:
                    System.out.println("¡¡HASTA PRONTO!!");
            }
        } while (opción != 13);
    }

    public static void insertarPocimaEItem(Potion[] pocion, Item[] items) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-----------INSERTAR PÓCIMA O ITEM-------------------");
        int opcion;
        // Ponemos un do...while para que nos pregunte si queremos insertar más pócimas o items.
        do {

            // Creamos una instrucción if...else if para escoger que tipo de objeto queremos introducir
            System.out.println("¿Qué quieres introducir? 1. Pócimas, 2. Items");
            opcion = entrada.nextInt();
            if ((opcion == 1)) {
                String name = null;
                String description = null;
                int id = 0;
                int type = 0;
                int puntos = 0;
                // Ponemos un if...else para que el jugador no introduzca más de 10 pócimas 
                if ((contarPociones(pocion) < pocion.length)) {
                    // Declaramos una variable de tipo entero y la igualamos al método ContarPociones para que vaya incremetando una 
                    // posición en el array cada vez que queramos introducir una pócima.
                    int pocionesExistentes = contarPociones(pocion);
                    pocion[pocionesExistentes] = new Potion(name, description, id, type, puntos);
                    // Declaramos una variable de tipo booleana (false) y a continuación la utilizamos en un bucle do..while para que se repita
                    // el proceso con el fin de evitar que dos ID tengan el mismo valor numérico.
                    boolean existe = false;
                    do {
                        // Declaramos otra variable booleana (false) para utilizarla en la siguiente iteración do...while, para que se repita 
                        // costantemente en el caso de que el jugador introduzca un dato que no sea de tipo int.
                        // Como medida para evitar que el programa se pare en caso de introducir un dato incorrecto (no int) utilizamos un try...catch.
                        // Este algoritmo lo utilizaremos cada vez que el programa nos pida un valor de tipo int.
                        boolean identificadorIncorrecto = false;
                        do {
                            try {
                                System.out.println("Introduzca el identificador de la pócima.");
                                pocion[pocionesExistentes].id = entrada.nextInt();
                                identificadorIncorrecto = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor introduce un número entero, para el identificador.");
                                entrada.next();
                                identificadorIncorrecto = true;
                            }
                        } while (identificadorIncorrecto);
                        existe = existeIdPocimas(pocionesExistentes, pocion);
                    } while (existe == true);
                    System.out.println("Introduzca el nombre de la pócima.");
                    pocion[pocionesExistentes].name = entrada.nextLine();
                    pocion[pocionesExistentes].name = entrada.nextLine();
                    System.out.println("Introduzca la descripción de la pócima.");
                    pocion[pocionesExistentes].description = entrada.nextLine();
                    boolean tipoIncorrecto = false;
                    do {
                        try {
                            System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                            pocion[pocionesExistentes].type = entrada.nextInt();
                            // Utilizamos un bucle while para que nos pida una y otra vez el tipo de pócima en caso de que no sea 1, 2 o 3.
                            while (pocion[pocionesExistentes].type < 1 || 3 < pocion[pocionesExistentes].type) {
                                System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                pocion[pocionesExistentes].type = entrada.nextInt();
                                tipoIncorrecto = false;
                            }
                            tipoIncorrecto = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor introduce un número entero, para el tipo de pócima.");
                            entrada.next();
                            tipoIncorrecto = true;
                        }
                    } while (tipoIncorrecto);

                    boolean puntosIncorrectos = false;
                    do {
                        try {
                            System.out.println("Introduzca el valor de los puntos.");
                            pocion[pocionesExistentes].points = entrada.nextInt();
                            // Utilizamos un bucle while para que nos pida una y otra vez el valor de los puntos en caso de ignorar el intervalo establecido.
                            while (pocion[pocionesExistentes].points < 5 || 35 < pocion[pocionesExistentes].points) {
                                System.out.println("Error, introduce un número que esté en el intevalo [5, 35].");
                                System.out.println("Introduzca el valor de los puntos.");
                                pocion[pocionesExistentes].points = entrada.nextInt();
                                puntosIncorrectos = false;
                            }
                            puntosIncorrectos = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor introduce un número entero, para los puntos.");
                            entrada.next();
                            puntosIncorrectos = true;
                        }
                    } while (puntosIncorrectos);
                    // Si tenemos ya 10 pócimas, saltará la instrucción else.
                } else {
                    System.out.println("Ya has insertado 10 pócimas, usa una pócima para añadir más.");
                }
            } // Fin de if.
            // Para los ítems, realizamos exactamente el mismo procedimiento empleado para las pócimas.
            else if ((opcion == 2)) {
                String name = null;
                String description = null;
                int id = 0;
                int type = 0;
                int experience = 0;
                if (contarItems(items) < items.length) {
                    int objetosEspeciales = contarItems(items);
                    items[objetosEspeciales] = new Item(name, description, id, type, experience);
                    boolean existe2 = false;
                    do {
                        boolean identificadorIncorrecto = false;
                        do {
                            try {
                                System.out.println("Introduzca el identificador del item.");
                                items[objetosEspeciales].id = entrada.nextInt();
                                identificadorIncorrecto = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor introduce un número entero, para el identificador.");
                                entrada.next();
                                identificadorIncorrecto = true;
                            }
                        } while (identificadorIncorrecto);

                        existe2 = existeIdItems(objetosEspeciales, items);
                    } while (existe2);

                    System.out.println("Introduzca el nombre del item.");
                    items[objetosEspeciales].name = entrada.nextLine();
                    items[objetosEspeciales].name = entrada.nextLine();
                    System.out.println("Introduzca la descripción del item.");
                    items[objetosEspeciales].description = entrada.nextLine();

                    boolean tipoIncorrecto = false;
                    do {
                        try {
                            System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                            items[objetosEspeciales].type = entrada.nextInt();
                            while (items[objetosEspeciales].type < 1 || 3 < items[objetosEspeciales].type) {
                                System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                items[objetosEspeciales].type = entrada.nextInt();
                                tipoIncorrecto = false;
                            }
                            tipoIncorrecto = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor introduce un número entero, para el tipo de item.");
                            entrada.next();
                            tipoIncorrecto = true;
                        }
                    } while (tipoIncorrecto);

                    boolean puntosIncorrectos = false;
                    do {
                        try {
                            System.out.println("Introduzca el valor de los puntos de experiencia.");
                            items[objetosEspeciales].experience = entrada.nextInt();
                            while (items[objetosEspeciales].experience < 15 || 50 < items[objetosEspeciales].experience) {
                                System.out.println("Error, introduce un número que esté en el intevalo [15, 50].");
                                System.out.println("Introduzca el valor de los puntos de experiencia.");
                                items[objetosEspeciales].experience = entrada.nextInt();
                                puntosIncorrectos = false;
                            }
                            puntosIncorrectos = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor introduce un número entero, para los puntos de experiencia.");
                            entrada.next();
                            puntosIncorrectos = true;
                        }
                    } while (puntosIncorrectos);
                } else {
                    System.out.println("Ya has insertado 5 items, usa un item para añadir más.");
                }
            }
            System.out.println("-------------------------------------------");
            System.out.println("¿Quieres añadir algo más? 1. SÍ, 2. NO");
        } while (entrada.nextInt() == 1); // Fin do...while
    }

    public static void posicionDeterminada(Potion[] pocion, Item[] items) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("---------------INSERTAR POCIMA O ITEM EN POSICION DETERMINADA--------------");
        System.out.println("¿Cuál es tu raza de personaje? 1: Humano, 2: Orco, 3: Elfo, 4: Enano");
        int raza = entrada.nextInt();
        // Ponemos una condición if...else para que salte una instrucción u otra dependiendo de la raza del personaje.
        if (raza == 3 || raza == 4) {
            int opcion;
            // Utilizamos exactamente el mismo algoritmo del método anterior con la diferencia de que al final creamos un pequeño algoritmo
            // para que el jugador tenga la libertad de insertar las pócimas o items en la posición que quiera, desplazando las demás una posición a 
            // la derecha.
            do {
                System.out.println("¿Qué quieres introducir? 1. Pócimas, 2. Items");
                opcion = entrada.nextInt();
                if ((opcion == 1)) {
                    String name = null;
                    String description = null;
                    int id = 0;
                    int type = 0;
                    int points = 0;
                    if ((contarPociones(pocion) < pocion.length)) {
                        int pocionesExistentes = contarPociones(pocion);
                        pocion[pocionesExistentes] = new Potion(name, description, id, type, points);
                        // Creamos un objeto y, como he explicado antes, utilizamos el mismo algoritmo que el del método anterior.
                        Potion nuevaPocion = new Potion(name, description, id, type, points);
                        boolean existe = false;
                        do {
                            boolean identificadorIncorrecto = false;
                            do {
                                try {
                                    System.out.println("Introduzca el identificador de la pócima.");
                                    nuevaPocion.id = entrada.nextInt();
                                    identificadorIncorrecto = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor introduce un número entero, para el identificador.");
                                    entrada.next();
                                    identificadorIncorrecto = true;
                                }
                            } while (identificadorIncorrecto);
                            existe = existeIdPocimas2(pocionesExistentes, nuevaPocion, pocion);
                        } while (existe);
                        System.out.println("Introduzca el nombre de la pócima.");
                        nuevaPocion.name = entrada.nextLine();
                        nuevaPocion.name = entrada.nextLine();
                        System.out.println("Introduzca la descripción de la pócima.");
                        nuevaPocion.description = entrada.nextLine();
                        boolean tipoIncorrecto = false;
                        do {
                            try {
                                System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                nuevaPocion.type = entrada.nextInt();
                                while (nuevaPocion.type < 1 || 3 < nuevaPocion.type) {
                                    System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                    System.out.println("Introduzca el tipo de pócima: 1 -> Vida, 2 -> Magia, 3 -> Veneno");
                                    nuevaPocion.type = entrada.nextInt();
                                    tipoIncorrecto = false;
                                }
                                tipoIncorrecto = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor introduce un número entero, para el tipo de pócima.");
                                entrada.next();
                                tipoIncorrecto = true;
                            }
                        } while (tipoIncorrecto);

                        boolean puntosIncorrectos = false;
                        do {
                            try {
                                System.out.println("Introduzca el valor de los puntos.");
                                nuevaPocion.points = entrada.nextInt();
                                while (nuevaPocion.points < 5 || nuevaPocion.points > 35) {
                                    System.out.println("Error, introduce un número que esté en el intevalo [5, 35].");
                                    System.out.println("Introduzca el valor de los puntos.");
                                    nuevaPocion.points = entrada.nextInt();
                                    puntosIncorrectos = false;
                                }
                                puntosIncorrectos = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor introduce un número entero, para los puntos.");
                                entrada.next();
                                puntosIncorrectos = true;
                            }
                        } while (puntosIncorrectos);

                        // Preguntamos en que posición queremos insertar la pócima.
                        System.out.println("¿En qué posición quieres insertar la nueva pócima? (entre 1 y " + contarPociones(pocion) + ")");
                        int posicion = entrada.nextInt();
                        // Usamos un while para evitar que el jugador inserte la pócima en una posición vacía.
                        while (posicion < 1 || posicion > contarPociones(pocion)) {
                            System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarPociones(pocion) + "]");
                            posicion = entrada.nextInt();
                        }
                        // A continuación programamos un bucle for para que vaya desplazando las pócimas una posición a la derecha hasta la 
                        // posición que hemos introducido por teclado.
                        for (int i = (contarPociones(pocion) - 2); i >= (posicion - 1); i--) {
                            pocion[i + 1] = pocion[i];
                        }
                        // Le asignamos al objeto, que hemos creado al principio, la posición que previamente habíamos introducido por teclado.
                        pocion[posicion - 1] = nuevaPocion;
                        // Si ya tenemos 10 pócimas se ejecutará la instrucción else.
                    } else {
                        System.out.println("Ya has insertado 10 pócimas, usa una pócima para añadir más.");
                    }
                } // Utilizamos el mismo pseudocódigo para los ítems.
                else if ((opcion == 2)) {
                    String name = null;
                    String description = null;
                    int id = 0;
                    int type = 0;
                    int experience = 0;
                    if ((contarItems(items) < items.length)) {
                        int objetosEspeciales = contarItems(items);
                        items[objetosEspeciales] = new Item(name, description, id, type, experience);
                        Item nuevoItem = new Item(name, description, id, type, experience);
                        boolean existe2 = false;
                        do {
                            boolean identificadorIncorrecto = false;
                            do {
                                try {
                                    System.out.println("Introduzca el identificador del item.");
                                    nuevoItem.id = entrada.nextInt();
                                    identificadorIncorrecto = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor introduce un número entero, para el identificador.");
                                    entrada.next();
                                    identificadorIncorrecto = true;
                                }
                            } while (identificadorIncorrecto);
                            existe2 = existeIdItems2(objetosEspeciales, nuevoItem, items);
                        } while (existe2);
                        System.out.println("Introduzca el nombre del item.");
                        nuevoItem.name = entrada.nextLine();
                        nuevoItem.name = entrada.nextLine();
                        System.out.println("Introduzca la descripción del item.");
                        nuevoItem.description = entrada.nextLine();

                        boolean tipoIncorrecto = false;
                        do {
                            try {
                                System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                nuevoItem.type = entrada.nextInt();
                                while (nuevoItem.type < 1 || 3 < nuevoItem.type) {
                                    System.out.println("Error, el número insertado no coincide con ningún tipo, vuelve a intentarlo.");
                                    System.out.println("Introduzca el tipo de item: 1 -> Miscelánea, 2 -> Arma, 3 -> Armadura");
                                    nuevoItem.type = entrada.nextInt();
                                    tipoIncorrecto = false;
                                }
                                tipoIncorrecto = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor introduce un número entero, para el tipo de item.");
                                entrada.next();
                                tipoIncorrecto = true;
                            }
                        } while (tipoIncorrecto);

                        boolean puntosIncorrectos = false;
                        do {
                            try {
                                System.out.println("Introduzca el valor de los puntos de experiencia.");
                                nuevoItem.experience = entrada.nextInt();
                                while (nuevoItem.experience < 15 || nuevoItem.experience > 50) {
                                    System.out.println("Error, introduce un número que esté en el intevalo [15, 50].");
                                    System.out.println("Introduzca el valor de los puntos de experiencia.");
                                    nuevoItem.experience = entrada.nextInt();
                                    puntosIncorrectos = false;
                                }
                                puntosIncorrectos = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor, introduce un número entero para los puntos de experiencia.");
                                entrada.next();
                                puntosIncorrectos = true;
                            }
                        } while (puntosIncorrectos);

                        System.out.println("¿En qué posición quieres insertar el nuevo item? (entre 1 y " + contarItems(items) + ")");
                        int posicion = entrada.nextInt();
                        while (posicion < 1 || posicion > contarItems(items)) {
                            System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarItems(items) + "]");
                            posicion = entrada.nextInt();
                        }
                        for (int i = (contarItems(items) - 2); i >= (posicion - 1); i--) {
                            items[i + 1] = items[i];
                        }
                        items[posicion - 1] = nuevoItem;
                    } else {
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
//    Creamos un método de tipo int para que devuelva el número de pócimas insertadas (tope).
//    Para ello insertamos como parámetros de entrada el array Potion.
//    A continuación declaramos la variable boundPotions y la inicializamos a 0. Creamos un bucle for 
//    para que recorra el array hasta el tamaño de su longitud y dentro creamos una instrucción if para 
//    que vaya incrementando + 1 la variable boundPotions (tope) cada vez que introducimos un elemento nuevo en el array.
//    Por último retornamos el tope.

    public static int contarPociones(Potion[] pocion) {
        int boundPotions = 0;
        for (int i = 0; i < pocion.length; i++) {
            if (pocion[i] != null) {
                boundPotions++;
            }
        }
        return boundPotions;
    }
//    Hacemos exactamente el mismo algoritmo para contar el número de ítems introducidos.

    public static int contarItems(Item[] items) {
        int boundItems = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                boundItems++;
            }
        }
        return boundItems;
    }

    //    Hacemos un método para quitar los pócimas que queramos del array.
    //    Para ello creamos una condición if...else para que salte la instrucción 
    //    else si no tenemos ninguna pócima en el array.
    //    Después declaramos dos variables de tipo entero. El tope que es la cantidad de pócimas insertadas - 1 y la posición que
    //    es igual a 0, porque queremos eliminar siempre la 1ª pócima.
    //    Creamos un bucle for para que vaya desplazando las pócimas no usadas a un índice inferior del que tenían originalmente. 
    //    La última posición que es el tope la eliminamos porque no la necesitamos.
    //    Creamos otra condición if...else para que salte un mensaje según la cantidad de pócimas que tenga almacenadas el jugador.
    public static void usarPócima(Potion[] pocion) {
        System.out.println("-------------------USAR PÓCIMA------------------");
        if (contarPociones(pocion) > 0) {
            int tope = (contarPociones(pocion) - 1);
            int posición = 0;
            for (int m = posición; m < tope; m++) {
                pocion[m] = pocion[m + 1];
            }
            pocion[tope] = null;
            if (tope == 0) {
                System.out.println("Ya has usado todas las pócimas.");
            } else {
                System.out.println("Pócima usada.");
            }
        } else {
            System.out.println("No hay pócimas cargadas");
        }
    }

    //  Este método sirve para evitar introducir dos identificadores con el mismo número.
    // Creamos un bucle for que va recorriendo el array y dentro ponemos una condición if que lo que hace es identificar si hay dos 
    // pócimas o ítems con el mismo ID.
    public static boolean existeIdPocimas(int pociones, Potion[] pocion) {
        for (int i = 0; i < pociones; i++) {
            if (pocion[i].id == pocion[pociones].id) {
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }

    //  Este método sirve para evitar introducir dos identificadores con el mismo número.
    // Creamos un bucle for que va recorriendo el array y dentro ponemos una condición if que lo que hace es identificar si hay dos 
    // pócimas o ítems con el mismo ID.
    public static boolean existeIdPocimas2(int pociones, Potion nuevaPocion, Potion[] pocion) {
        for (int i = 0; i < pociones; i++) {
            if (pocion[i].id == nuevaPocion.id) {
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }

    //  Este método sirve para evitar introducir dos identificadores con el mismo número.
    // Creamos un bucle for que va recorriendo el array y dentro ponemos una condición if que lo que hace es identificar si hay dos 
    // pócimas o ítems con el mismo ID.
    public static boolean existeIdItems(int objetosEspeciales, Item[] items) {
        for (int i = 0; i < objetosEspeciales; i++) {
            if (items[i].id == items[objetosEspeciales].id) {
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }

    //  Este método sirve para evitar introducir dos identificadores con el mismo número.
    // Creamos un bucle for que va recorriendo el array y dentro ponemos una condición if que lo que hace es identificar si hay dos 
    // pócimas o ítems con el mismo ID.
    public static boolean existeIdItems2(int objetosEspeciales, Item nuevoItem, Item[] items) {
        for (int i = 0; i < objetosEspeciales; i++) {
            if (items[i].id == nuevoItem.id) {
                System.out.println("Este identificador ya existe");
                return true;
            }
        }
        return false;
    }

    public static void buscar(Potion[] pocion, Item[] items) {
        Scanner ent = new Scanner(System.in);
        //variables para recoger los numeros por teclado
        int op, num;
        System.out.println("------------------------------");
        do {
            System.out.println("¿Qué deseas buscar?:1- Pocima, 2- Item ");
            op = ent.nextInt();
        } while (op < 1 || op > 2);

        if (op == 1) {
            System.out.println("Por favor, introduce una posicion existente para la lista seleccionada.");
            num = ent.nextInt();
            while (num < 1 || num > contarPociones(pocion)) {
                System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarPociones(pocion) + "]");
                num = ent.nextInt();
            }
            //se muestra la pocima del espacio que introducimos por teclado
            System.out.println("Id: " + pocion[num - 1].id);
            System.out.println("Nombre: " + pocion[num - 1].name);
            System.out.println("Descripcion: " + pocion[num - 1].description);
            System.out.println("Tipo: " + pocion[num - 1].type);
            System.out.println("Experiencia: " + pocion[num - 1].points);
        } else if (op == 2) {
            System.out.println("Por favor, introduce una posicion existente para la lista seleccionada.");
            num = ent.nextInt();
            while (num < 1 || num > contarItems(items)) {
                System.out.println("Error, inserta un número comprendido en el intervalo [1, " + contarItems(items) + "]");
                num = ent.nextInt();
            }
            //se muestra el item del espacio que introducimos por teclado
            System.out.println("Id: " + items[num - 1].id);
            System.out.println("Nombre: " + items[num - 1].name);
            System.out.println("Descripcion: " + items[num - 1].description);
            System.out.println("Tipo: " + items[num - 1].type);
            System.out.println("Experiencia: " + items[num - 1].experience);
        }
    }

    public static void modificarPuntos(Potion[] pocion, Item[] items) {
        Scanner ent = new Scanner(System.in);
        int cambio;
        final int EXPERIENCE = 10;
        System.out.println("-------------------------");
        do {
            System.out.println("¿Cuantos puntos de bonificación quieres?(1-10)");
            cambio = ent.nextInt();
        } while (cambio < 0 || cambio > EXPERIENCE);//para que no pueda subir mas de 10 niveles

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                //para que no haya fallo si hay algun espacio en blanco
                //guardo en la experiencia de los items el cambio introducido por teclado    
                items[i].experience = items[i].experience + cambio;
                if (items[i].experience > 50) {
                    items[i].experience = 50;
                }
            }
        }

        for (int i = 0; i < pocion.length; i++) {
            if (pocion[i] != null) {
                //lo mismo que con las pociones    
                pocion[i].points = pocion[i].points + cambio;
                if (pocion[i].points > 35) {
                    pocion[i].points = 35;//establecer como maximo 35 puntos
                }
            }
        }
        System.out.println("Has modificado los puntos de los items y de las pociones con " + cambio + " puntos más.");
    }

    public static void guardarFicheros(Potion[] pocion, Item[] items) {
        Scanner ent = new Scanner(System.in);
        //establezco las constantes para no salirme del array
        final int MAXPOTIONS = 10;
        final int MAXITEMS = 5;
        int selec;

        System.out.println("-----------------------");
        do {
            System.out.println("¿Que desea guardar?:1-Pocimas, 2-Items, 3-Ambos");
            selec = ent.nextInt();
        } while (selec < 1 || selec > 3);

        if (selec == 1) {
            try {
                //creo el fichero de texto pjpocimas  
                PrintStream pocimas;
                pocimas = new PrintStream(new FileOutputStream("E:\\CAP\\pocimas.txt"));
                //recorro el registro en la posicion i, y los escribo en el .txt que he creado
                for (int i = 0; i < MAXPOTIONS; i++) {
                    if (pocion[i] != null) {
                        pocimas.println("Id: " + pocion[i].id);
                        pocimas.println("Nombre: " + pocion[i].name);
                        pocimas.println("Descripcion: " + pocion[i].description);
                        pocimas.println("Tipo: " + pocion[i].type);
                        pocimas.println("Experiencia: " + pocion[i].points);
                        pocimas.println("------------------");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            }

        } else if (selec == 2) {
            try {
                //mismo porceso que en las pocimas  
                PrintStream it;
                it = new PrintStream(new FileOutputStream("E:\\CAP\\items.txt"));

                for (int i = 0; i < MAXITEMS; i++) {
                    if (items[i] != null) {
                        it.println("Id: " + items[i].id);
                        it.println("Nombre: " + items[i].name);
                        it.println("Descripcion: " + items[i].description);
                        it.println("Tipo: " + items[i].type);
                        it.println("Experiencia: " + items[i].experience);
                        it.println("--------------------");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            }
        } else if (selec == 3) {
            try {
                //mismo proceso pero con ambos   
                PrintStream ambos;
                ambos = new PrintStream(new FileOutputStream("E:\\CAP\\ambos.txt"));

                for (int i = 0; i < MAXPOTIONS; i++) {
                    if (pocion[i] != null) {
                        ambos.println("Id: " + pocion[i].id);
                        ambos.println("Nombre: " + pocion[i].name);
                        ambos.println("Descripcion: " + pocion[i].description);
                        ambos.println("Tipo: " + pocion[i].type);
                        ambos.println("Experiencia: " + pocion[i].points);
                    }
                }
                ambos.println("---------------");

                for (int i = 0; i < MAXITEMS; i++) {
                    if (items[i] != null) {
                        ambos.println("Id: " + items[i].id);
                        ambos.println("Nombre: " + items[i].name);
                        ambos.println("Descripcion: " + items[i].description);
                        ambos.println("Tipo: " + items[i].type);
                        ambos.println("Experiencia: " + items[i].experience);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            } catch (IOException exc) {
                System.out.println("Fichero bien leido");
            }
        }
    }

    public static void leerFicheros() {
        Scanner ent = new Scanner(System.in);
        int selec;
        do {
            System.out.println("¿Que desea leer?:1-Pocimas, 2-Items, 3-Ambos");
            selec = ent.nextInt();
        } while (selec < 1 || selec > 3);

        if (selec == 1) {
            try {
                File myObj = new File("E:\\CAP\\pocimas.txt");
                Scanner reader = new Scanner(myObj);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
                System.out.println("Archivo TXT leido correctamente.");
            } catch (FileNotFoundException e) {
                System.out.println("Alguna excepcion.");
                e.printStackTrace();
            }
        }

        if (selec == 2) {
            try {
                File myObj = new File("E:\\CAP\\items.txt");
                Scanner reader = new Scanner(myObj);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
                System.out.println("Archivo TXT leido correctamente.");
            } catch (FileNotFoundException e) {
                System.out.println("Alguna excepcion.");
                e.printStackTrace();
            }
        }

        if (selec == 3) {
            try {
                File myObj = new File("E:\\CAP\\ambos.txt");
                Scanner reader = new Scanner(myObj);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
                System.out.println("Archivo TXT leido correctamente.");
            } catch (FileNotFoundException e) {
                System.out.println("Alguna excepcion.");
                e.printStackTrace();
            }
        }
    }

    public static void guardarFicherosDat(Potion[] pocion, Item[] items) throws IOException {
        Scanner ent = new Scanner(System.in);
        //establezco las constantes para no salirme del array
        final int MAXPOTIONS = 10;
        final int MAXITEMS = 5;
        int selec;

        System.out.println("-----------------------");
        do {
            System.out.println("¿Que desea guardar?:1-Pocimas, 2-Items, 3-Ambos");
            selec = ent.nextInt();
        } while (selec < 1 || selec > 3);

        if (selec == 1) {
            try {
                FileOutputStream out = new FileOutputStream(new File("E:\\CAP\\pocimas.dat"));
                ObjectOutputStream pocimas = new ObjectOutputStream(out);
                //creo el fichero de texto pjpocimas  

                pocimas.writeInt(contarPociones(pocion));  //Conseguimos una referencia para la lectura

                //recorro el registro en la posicion i, y los escribo en el .txt que he creado
                for (int i = 0; i < MAXPOTIONS; i++) {
                    if (pocion[i] != null) {
                        pocimas.writeObject(pocion[i]);
                    }
                }
                pocimas.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            } catch (IOException exc) {
                System.out.println("Fichero bien escrito");
            }

        } else if (selec == 2) {
            try {
                //mismo porceso que en las pocimas 
                FileOutputStream out = new FileOutputStream(new File("E:\\CAP\\items.dat"));
                ObjectOutputStream it = new ObjectOutputStream(out);

                it.writeInt(contarItems(items));

                for (int i = 0; i < MAXITEMS; i++) {
                    if (items[i] != null) {
                        it.writeObject(items[i]);
                    }
                }
                it.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            } catch (IOException exc) {
                System.out.println("Fichero bien escrito");
            }
        }
    }

    public static void leerFicherosDat(Potion[] pocion, Item[] items) throws FileNotFoundException, IOException {
        Scanner ent = new Scanner(System.in);
        int selec;
        do {
            System.out.println("¿Que desea leer?:1-Pocimas, 2-Items");
            selec = ent.nextInt();
        } while (selec < 1 || selec > 3);

        if (selec == 1) {
            int tope = 0;
            int longitud = 0;
            String name = null;
            String description = null;
            int id;
            int type;
            int points;
            int i = 0;
            //pocion[i] = new Potion();

            try {
                FileInputStream in = new FileInputStream(new File("E:\\CAP\\pocimas.dat"));
                ObjectInputStream pocimas = new ObjectInputStream(in);

                longitud = pocimas.readInt(); //Leemos la referencia para saber hasta donde leer
                tope = longitud;
                System.out.println("Tope: " + tope);
                System.out.println("Longitud: " + longitud);
                Potion a1 = (Potion) pocimas.readObject();

                while (a1 != null) {

                    id = a1.id;
                    System.out.println("El identificador de la pocion " + (i + 1) + " es: " + id);

                    name = a1.name;
                    System.out.println("El nombre de la pocion " + (i + 1) + " es: " + name);

                    description = a1.description;
                    System.out.println("La descripcion de la pocion " + (i + 1) + " es: " + description);

                    type = a1.type;
                    System.out.println("El tipo de la pocion " + (i + 1) + " es: " + type);

                    points = a1.points;
                    System.out.println("Los puntos de la pocion " + (i + 1) + " son: " + points);

                    //Ahora que ya leemos habría que copiarlo al array que ya tenemos
                    pocion[i] = new Potion(name, description, id, type, points);
                    i++;
                    a1 = (Potion) pocimas.readObject();

                }

                System.out.println("Fichero binario leído.");

            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado. " + e);

            } catch (EOFException exception) {
                System.out.println("Fichero leido");
            } catch (IOException ex) {
                System.out.println("Alguna excepcion. " + ex);
            } catch (ClassNotFoundException exc) {
                System.out.println("Clase no encontrada. " + exc);
            } catch (NullPointerException nll) {
                System.out.println("Apuntas a la nada. " + nll);
            }
            tope = longitud;
            //return tope;
        }

        if (selec == 2) {
            int tope = 0;
            int longitud = 0;
            String name = null;
            String description = null;
            int id;
            int type;
            int experience;
            int i = 0;
            //pocion[i] = new Potion();

            try {
                FileInputStream in = new FileInputStream(new File("E:\\CAP\\items.dat"));
                ObjectInputStream it = new ObjectInputStream(in);

                longitud = it.readInt(); //Leemos la referencia para saber hasta donde leer
                tope = longitud;
                System.out.println("Tope: " + tope);
                System.out.println("Longitud: " + longitud);
                Item a1 = (Item) it.readObject();

                while (a1 != null) {

                    id = a1.id;
                    System.out.println("El identificador del item " + (i + 1) + " es: " + id);

                    name = a1.name;
                    System.out.println("El nombre del objeto " + (i + 1) + " es: " + name);

                    description = a1.description;
                    System.out.println("La descripcion del objeto " + (i + 1) + " es: " + description);

                    type = a1.type;
                    System.out.println("El tipo del objeto " + (i + 1) + " es: " + type);

                    experience = a1.experience;
                    System.out.println("Los puntos de la pocion " + (i + 1) + " son: " + experience);

                    //Ahora que ya leemos habría que copiarlo al array que ya tenemos
                    items[i] = new Item(name, description, id, type, experience);
                    i++;
                    a1 = (Item) it.readObject();

                }

                System.out.println("Fichero binario leído.");

            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado. " + e);
            } catch (EOFException exception) {
                System.out.println("Fichero leido");
            } catch (IOException ex) {
                System.out.println("Alguna excepcion. " + ex);
            } catch (ClassNotFoundException exc) {
                System.out.println("Clase no encontrada. " + exc);
            } catch (NullPointerException nll) {
                System.out.println("Apuntas a la nada. " + nll);
            }
            tope = longitud;
            //return tope;
        }
    }
}
