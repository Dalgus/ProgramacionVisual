package prac8C;

import java.io.*;
import java.util.*;

class Alumno implements Serializable {

    String nombre;
    int edad;
    double nota;

}

public class Prac8C {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Crear el Scanner
        Scanner input = new Scanner(System.in);

        //Variables necesarias.
        int selection;
        boolean finalizado = false;
        int x;
        double y;

        alumno[] alumnos = new alumno[20];
        int top = 0;

        int m;
        int n;

        //Autor    
        System.out.println("Programa realizado por:");
        System.out.println("Héctor Fernández Rubio.");

        System.out.println("¿De cuantas filas quiere crear la tabla?:");
        m = input.nextInt();
        System.out.println("¿De cuantas columnas quiere crear la tabla?:");
        n = input.nextInt();

        int t[][] = new int[m][n];

        do {
            System.out.println("Menu):");
            System.out.println("1: Introducir notas.");
            System.out.println("2: Mostrar notas.");
            System.out.println("3: Calcular estadísticas.");
            System.out.println("4: Mostrar la nota de un alumno concreto.");
            System.out.println("5: Modificar la nota de un alumno concreto");
            System.out.println("6: Mostrar la nota escrita");
            System.out.println("7: Mostrar la nota del alumno con peor nota");
            System.out.println("8: Modificar todas las notas en un porcentaje");
            System.out.println("9: Rellenar Matriz");
            System.out.println("10: Mostrar Matriz.");
            System.out.println("11: Buscar un elemento en la matriz");
            System.out.println("12: Modificar un elemento en la matriz.");
            System.out.println("13: Guardar fichero.");
            System.out.println("14: Leer fichero.");
            System.out.println("15: Salir");

            selection = input.nextInt();
            switch (selection) {
                case 1: {
                    System.out.println("1: Introducir notas.");
                    top = insertarNotas(alumnos, top);
                }
                break;
                case 2: {
                    mostrarNotas(alumnos, top);
                }
                break;
                case 3: {
                    System.out.println("3: Calcular estadísticas.");
                    System.out.println("La nota media de los alumnos es " + mediaNotas(alumnos, top));
                    System.out.println("Porcentaje de aprobados " + aprobados(alumnos, top) + " %");
                    System.out.println("Porcentaje de notables " + notables(alumnos, top) + " %");
                    System.out.println("Porcentaje de sobresalientes " + sobresalientes(alumnos, top) + " %");
                    notaMaxima(alumnos, top);
                }
                break;
                case 4: {
                    System.out.println("4: Mostrar la nota de un alumno concreto.");
                    System.out.println("¿La nota de que alumno quiere mostrar?.");
                    x = input.nextInt();
                    System.out.println("La nota del alumno " + buscaAlumno(alumnos, x).getNombre() + " es " + buscaAlumno(alumnos, x).getNota());
                }
                break;
                case 5: {
                    System.out.println("5: Modificar la nota de un alumno concreto");
                    System.out.println("¿La nota de que alumno quiere modificar?.");
                    x = input.nextInt();
                    cambiaNota(alumnos, x);
                }
                break;
                case 6: {
                    System.out.println("6: Mostrar notas escritas.");
                    for (int i = 0; i < top; i++) {
                        System.out.print("La nota del alumno " + (i + 1) + " es:");
                        if ((alumnos[i].getNota() >= 0) && (alumnos[i].getNota() < 5)) {
                            System.out.print(" Suspenso \n");
                        } else if ((alumnos[i].getNota() >= 5) && (alumnos[i].getNota() < 7)) {
                            System.out.print(" Aprobado \n");
                        } else if ((alumnos[i].getNota() >= 7) && (alumnos[i].getNota() < 9)) {
                            System.out.print(" Notable \n");
                        } else if ((alumnos[i].getNota() >= 9) && (alumnos[i].getNota() < 9.6)) {
                            System.out.print(" Sobresaliente \n");
                        } else if ((alumnos[i].getNota() >= 9.6) && (alumnos[i].getNota() <= 10)) {
                            System.out.print(" Matricula de Honor \n");
                        }
                    }
                }
                break;
                case 7: {
                    peorNota(alumnos, top);
                }
                break;
                case 8: {
                    System.out.println("Modificar notas en porcentaje.");
                    System.out.println("¿En que porcentaje quiere modificar las notas?");
                    y = input.nextFloat();
                    y = y / 100;
                    porcentajeNotas(alumnos, y, top);
                }
                break;
                case 9: {
                    rellenarMatriz(t);
                }
                break;
                case 10: {
                    mostrarMatriz(t);
                }
                break;
                case 11: {
                    buscarElemeMatriz(t);
                }
                break;
                case 12: {
                    cambiarElemeMatriz(t);
                }
                break;
                case 13: {
                    crearFichero(alumnos, top);
                    //crearFicheroTXT(alumnos, top);
                }
                break;
                case 14: {
                    top = leerFichero(alumnos);
                }
                break;
                case 15: {
                    System.out.println("¡Hasta luego!");
                    finalizado = true;
                }
                break;
                default:

            }
        } while (finalizado != true);
    }

    private static int insertarNotas(alumno[] alumnos, int top) {
        int x;
        int tope;
        String nombre;
        int edad;
        double nota;
        //Crear el Scanner
        Scanner input = new Scanner(System.in);
        for (tope = top; tope < alumnos.length;) {
            System.out.println("Introduzca el nombre del alumno " + (tope + 1));
            nombre = input.next();
            System.out.println("Introduzca la edad del alumno " + (tope + 1));
            edad = input.nextInt();
            System.out.println("Introduzca la nota del alumno " + (tope + 1));
            nota = input.nextFloat();
            if ((nota < 0) || (nota > 10)) {
                System.out.println("La nota introducida no es valida, introduzca una nota entre 0 y 10");
            } else {
                alumnos[tope] = new alumno(nombre, edad, nota);
                tope = tope + 1;
                System.out.println("¿Desea introducir alguna nota más? 1)Si. 2)No.");
                x = input.nextInt();
                if (x == 2) {
                    break;
                }
            }
        }
        return tope;
    }

    private static void mostrarNotas(alumno[] alumnos, int top) {
        System.out.println("2: Mostrar notas.");
        int tope = top;
        for (int i = 0; i < tope; i++) {
            System.out.println("La nota del alumno " + alumnos[i].getNombre() + " es " + alumnos[i].getNota());
        }
    }

    private static float mediaNotas(alumno[] alumnos, int top) {
        int tope = top;
        float notas = 0;
        for (int i = 0; i < tope; i++) {
            notas += alumnos[i].getNota();
        }
        notas = notas / tope;
        return notas;
    }

    private static float aprobados(alumno[] alumnos, int top) {
        int tope = top;
        float contador = 0;
        for (int i = 0; i < tope; i++) {
            if (alumnos[i].getNota() >= 5) {
                contador++;
            }
        }
        return (contador / tope) * 100;
    }

    private static float notables(alumno[] alumnos, int top) {
        int tope = top;
        float contador = 0;
        for (int i = 0; i < tope; i++) {
            if ((alumnos[i].getNota() >= 7) && (alumnos[i].getNota() <= 9)) {
                contador++;
            }
        }
        return (contador / tope) * 100;
    }

    private static float sobresalientes(alumno[] alumnos, int top) {
        int tope = top;
        float contador = 0;
        for (int i = 0; i < tope; i++) {
            if (alumnos[i].getNota() >= 9) {
                contador++;
            }
        }
        return (contador / tope) * 100;
    }

    private static void notaMaxima(alumno[] alumnos, int top) {
        double max = 0;
        int tope = top;
        int pos = 0;
        for (int i = 0; i < tope; i++) {
            if (alumnos[i].getNota() > max) {
                max = alumnos[i].getNota();
                pos = i;
            }
        }
        System.out.println("El alumno " + (pos + 1) + " ha conseguido la nota maxima con la nota " + max);
    }

    private static alumno buscaAlumno(alumno[] alumnos, int x) {
        int pos = x;
        return alumnos[pos - 1];
    }

    private static void cambiaNota(alumno[] alumnos, int x) {
        //Crear el Scanner
        Scanner input = new Scanner(System.in);

        alumno a = buscaAlumno(alumnos, x);
        System.out.println(a.toString());
        System.out.println("¿A que nota la quiere modificar?");
        double y = input.nextDouble();
        if ((y < 0) || (y > 10)) {
            System.out.println("La nota introducida no es valida, introduzca una nota entre 0 y 10");
        } else {
            a.setNota(y);
        }
    }

    private static void porcentajeNotas(alumno[] alumnos, double y, int top) {
        int tope = top;
        for (int i = 0; i < tope; i++) {
            alumnos[i].setNota(alumnos[i].getNota() + (alumnos[i].getNota() * y));
            if (alumnos[i].getNota() < 0) {
                alumnos[i].setNota(0);
            } else if (alumnos[i].getNota() > 10) {
                alumnos[i].setNota(10);
            }
        }
    }

    private static void peorNota(alumno[] alumnos, int top) {
        int tope = top;
        double min = alumnos[0].getNota();
        for (int i = 0; i < tope; i++) {
            if (min > alumnos[i].getNota()) {
                min = alumnos[i].getNota();
            }
        }
        for (int i = 0; i < tope; i++) {
            if (alumnos[i].getNota() == min) {
                System.out.println("El alumno " + alumnos[i].getNombre() + " ha conseguido la nota minima con un: " + alumnos[i].getNota());
            }
        }
    }

    private static void rellenarMatriz(int t[][]) {
        System.out.println("Rellenar Matriz.");
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = i + j;
            }
        }
        System.out.println("Matriz llena");
    }

    private static void mostrarMatriz(int t[][]) {
        int a[][] = t;
        System.out.println("Mostrar Matriz.");
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                System.out.print(t[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static void buscarElemeMatriz(int t[][]) {
        int m;
        int n;
        //Crear el Scanner
        Scanner input = new Scanner(System.in);
        System.out.println("Buscar un elemento en la matriz.");
        System.out.println("¿En que fila desea buscar?");
        m = input.nextInt();
        System.out.println("¿En que columna desea buscar?");
        n = input.nextInt();
        for (int i = m; i < t.length;) {
            for (int j = n; j < t[i].length;) {
                System.out.println("El elemento de la fila " + i + " y la columna " + j + " es " + t[i][j]);
                break;
            }
            break;
        }
    }

    private static void cambiarElemeMatriz(int t[][]) {
        int m;
        int n;
        //Crear el Scanner
        Scanner input = new Scanner(System.in);
        System.out.println("Modificar un elemento en la matriz.");
        System.out.println("¿En que fila desea buscar?");
        m = input.nextInt();
        System.out.println("¿En que columna desea buscar?");
        n = input.nextInt();
        for (int i = m; i < t.length;) {
            for (int j = n; j < t[i].length;) {
                System.out.println("¿Que valor quiere darle a esta posicion de la matriz?");
                t[i][j] = input.nextInt();
                break;
            }
            break;

        }

    }

    private static class alumno implements Serializable {

        String nombre;
        int edad;
        double nota;

        public alumno(String nombre, int edad, double nota) {
            this.nombre = nombre;
            this.edad = edad;
            this.nota = nota;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setNota(double nota) {
            this.nota = nota;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }

        public double getNota() {
            return nota;
        }

        @Override
        public String toString() {
            return "Nombre del Alumno " + this.nombre + "\n" + "Edad del alumno " + this.edad + "\n" + "Nota del Alumno " + this.nota;
        }
    }

    private static void crearFichero(alumno[] alumnos, int top) throws IOException {
        int tope = top;
        try {
            FileOutputStream out = new FileOutputStream(new File("E:\\Descargas\\alumnos.dat"));
            ObjectOutputStream so = new ObjectOutputStream(out);

            so.writeInt(tope);  //Conseguimos una referencia para la lectura

            for (int i = 0; i < alumnos.length; i++) {
                so.writeObject(alumnos[i]);
            }
            so.close();

        } catch (IOException ex) {
            System.out.println("Alguna excepcion.");
        }
        System.out.println("Fichero binario creado.");
    }

    private static void crearFicheroTXT(alumno[] alumnos, int top) throws IOException {
        int tope = top;
        try {
            FileOutputStream out = new FileOutputStream(new File("E:\\Descargas\\alumnos.txt"));
            PrintWriter so = new PrintWriter(out);

            so.println(tope);
            for (int i = 0; i < tope; i++) {
                so.println(alumnos[i]);
            }
            so.close();

        } catch (IOException ex) {
            System.out.println("Alguna excepcion.");
        }
        System.out.println("Fichero de texto creado.");
    }

    private static int leerFichero(alumno[] alumnos) throws IOException {
        int tope = 0;
        int longitud = 0;
        String nombre = null;
        int edad = 0;
        double nota = 0;
        int i = 0;
        alumnos[i] = new alumno(nombre, edad, nota);

        try {
            FileInputStream in = new FileInputStream(new File("E:\\Descargas\\alumnos.dat"));
            ObjectInputStream os = new ObjectInputStream(in);

            longitud = os.readInt(); //Leemos la referencia para saber hasta donde leer
            tope = longitud;
            System.out.println("Tope: " + tope);
            System.out.println("Longitud: " + longitud);
            alumno a1 = (alumno) os.readObject();

            for (i = 0; i < longitud; i++) {

                nombre = a1.nombre;
                System.out.println("El nombre del alumno " + (i + 1) + " es: " + nombre);

                edad = a1.edad;
                System.out.println("La edad del alumno " + (i + 1) + " es; " + edad);

                nota = a1.nota;
                System.out.println("La nota del alumno " + (i + 1) + " es: " + nota);

                a1 = (alumno) os.readObject();
                //Ahora que ya leemos habría que copiarlo al array que ya tenemos

                alumnos[i] = new alumno(nombre, edad, nota);
            }

            System.out.println("Fichero binario leído.");

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado. " + e);
        } catch (IOException ex) {
            System.out.println("Alguna excepcion. " + ex);
        } catch (ClassNotFoundException exc) {
            System.out.println("Clase no encontrada. " + exc);
        } catch (NullPointerException nll) {
            System.out.println("Apuntas a la nada. " + nll);
        }
        tope = longitud;
        return tope;

    }
}