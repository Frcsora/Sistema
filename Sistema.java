package sistema;

import java.util.Scanner;
import java.util.InputMismatchException;
import sistema.Usuario;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.nio.file.StandardOpenOption;

public class Sistema {
    
    static Scanner sc = new Scanner(System.in);
    static boolean entero;
    static boolean on = true;
    static int opcion;
    static ArrayList<Usuario> users = new ArrayList<>();
    static String nombre;
    static FileWriter guardar;
    public static void main(String[] args) {
        try {
            File guardar = new File("guardar.txt");
            Scanner lector = new Scanner(guardar);
            while(lector.hasNextLine()){
                users.add(new Usuario(lector.nextLine()));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error");
        }
        do{
            do{
                try{
                    entero = true;
                    opcion = menu();
                    while (opcion < 0 && opcion > 3){
                        System.out.println("Elige una opcion correcta");
                        opcion = menu();
                    }
                } catch(InputMismatchException ex){
                    entero = false;
                    System.out.println("Elige una opcion correcta");
                    sc.nextLine();
                }
            }while (!entero);
            
            switch(opcion){
                case 1: 
                    boolean yaExiste = false;
                    System.out.println("Inserte el nombre del usuario");
                    nombre=sc.nextLine().toUpperCase();
                    
                    for (int i = 0 ; i < users.size() ; i++){
                        if(users.get(i).getNombre().equals(nombre)){
                            yaExiste = true;
                            break;
                        }
                    }
                    
                    if(!yaExiste){
                        users.add(new Usuario(nombre));
                        String rutaDirectorio = "C:/Users/franc/OneDrive/Documentos/proyecto_java";
                        Path rutaDir = Paths.get(rutaDirectorio, nombre);
                        
                        try{
                            Files.createDirectories(rutaDir);
                            System.out.println("Directorio creado con exito");
                        } catch (Exception e){
                            System.out.println("Error al crear el directorio");
                            users.remove(users.size() - 1);
                        }
                        
                        try{
                            File cuentas = new File(rutaDirectorio + "/" + nombre, nombre + "cuentas.xls");
                            if (cuentas.createNewFile()){
                                System.out.println("File created:" + cuentas.getName());
                            }
                            else{
                                System.out.println("File already exists");
                            }
                            File trabajo = new File(rutaDirectorio +"/" + nombre, nombre + "trabajo.txt");
                            if (trabajo.createNewFile()){
                                System.out.println("File created:" + trabajo.getName());
                            }
                            else{
                                System.out.println("File already exists");
                            }
                        } catch(IOException e){
                            System.out.println("Error");
                        }
                    } else {
                        System.out.println("El nombre no esta disponible");
                    }
                    break;
                    
                case 2: 
                    System.out.println("Inserte el nombre del usuario a eliminar");
                    nombre = sc.nextLine().toUpperCase();
                    
                    for (int i = 0 ; i < users.size() ; i++){
                        
                        if (users.get(i).getNombre() == null){
                            continue;
                        }
                        if (users.get(i).getNombre().equals(nombre)){
                            users.remove(i);
                            String ruta = "C:/Users/franc/OneDrive/Documentos/proyecto_java/" + nombre;
                            Path rutaDel = Paths.get(ruta);
                            File cuentas = new File(ruta, nombre + "cuentas.xls");
                            if (cuentas.delete()){
                                System.out.println("File created:" + cuentas.getName());
                            }
                            else{
                                System.out.println("File already exists");
                            }
                            File trabajo = new File(ruta, nombre + "trabajo.txt");
                            if (trabajo.delete()){
                                System.out.println("File created:" + trabajo.getName());
                            }
                            else{
                                System.out.println("File already exists");
                            }
                            try{
                                Files.delete(rutaDel);
                            }
                            catch(IOException e){
                                System.out.println("error");
                            }
                            break;
                        }
                    }
                    
                    break;
                    
                case 3:
                    
                    for (int i = 0 ; i < users.size() ; i++){
                        if (users.get(i).getNombre() != null){
                            System.out.println(users.get(i).getNombre());
                        }
                    }
                    
                    break;
                case 0:
                    try{
                        guardar = new FileWriter("guardar.txt");
                        for(int i = 0; i < users.size() ; i++) {
                            guardar.write(users.get(i).getNombre());
                        }
                        System.out.println("Se guardo correctamente");
                        guardar.close();
                    } catch(IOException e){
                        System.out.println("Error");
                    }
                    System.out.println("Gracias por trabajar con nosotros");
                    System.exit(0);
                    break;
            }
        }while(on);
    }
    
    static int menu(){
        System.out.println("Elija una opcion");
        System.out.println("1. Agregar usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Ver usuarios del sistema");
        System.out.println("0. Apagar el sistema");
        
        int eleccion = sc.nextInt();
        sc.nextLine();
        return eleccion;
    }
    
}
