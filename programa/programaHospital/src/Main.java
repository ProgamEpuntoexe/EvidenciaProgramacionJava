import java.io.*;
import java.util.*;

//Clase doctor
class doctor{
    public String nombre = "";
    public String especialidad = "";
    public int experiencia = 0;
    public float presupuesto = 0.0f;
    public doctor(String nombreLocal, String especialidadLocal, int experienciaLocal, float presupuestoLocal){
        nombre = nombreLocal;
        especialidad = especialidadLocal;
        experiencia = experienciaLocal;
        presupuesto = presupuestoLocal;
    }
}
//Clase paciente
class paciente{
    public String nombre = "";
    public String asunto = "";
    public int edad = 0;
    public boolean citado = false;
    public paciente(String nombreLocal,String asuntoLocal, int edadLocal, boolean citadoLocal){
        nombre = nombreLocal;
        asunto = asuntoLocal;
        edad = edadLocal;
        citado = citadoLocal;
    }
}
//Clase cita
class cita {
    public paciente pacienteAtender;
    public doctor doctorAtender;
    public int horarioMinutos = 0;
    public int horarioHoras = 0;
    public int numeroMes = 0;
    public int numeroDia = 0;
    public int numeroYear = 0;
    public cita(paciente pacienteLocal, doctor doctorLocal, int minutosLocal, int horasLocal, int mesLocal, int diaLocal, int yearlocal){
        pacienteAtender = pacienteLocal;
        doctorAtender = doctorLocal;
        horarioHoras = horasLocal;
        horarioMinutos = minutosLocal;
        numeroMes = mesLocal;
        numeroDia = diaLocal;
        numeroYear = yearlocal;
    }
}
//Clase recepcion
class recepcion{
    public String nombreRecepcionista = "";
    public float presupuesto = 0.0f;
    public recepcion(String nombreLocal, float presupuestoLocal){
        nombreRecepcionista = nombreLocal;
        presupuesto = presupuestoLocal;
    }
}
//Clase edificio
class edificio{
    public ArrayList<doctor> listaDoctores = new ArrayList<doctor>();
    public ArrayList<doctor> listaDoctoresDisponibles = new ArrayList<doctor>();
    public ArrayList<paciente> listaPacientes = new ArrayList<paciente>();
    public ArrayList<cita> listaCitas = new ArrayList<cita>();
    String nombre = "";
    public edificio(String nombreLocal){
        nombre = nombreLocal;
    }
}
//Clase programa
class programa{
    public boolean sesionActiva = false;
    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    public programa(){

    }

    public void acceder(usuario datosUsuario) throws IOException{
        //HOLA
        boolean sesionActiva = false;
        String nombreIntroducido = "";
        String passwordIntroducido = "";
        System.out.print("Ingrese el nombre de usuario: ");
        nombreIntroducido = entrada.readLine();
        System.out.print("Ingrese el su contraseña: ");
        passwordIntroducido = entrada.readLine();
        //Para introducir los datos
        if (verificar(datosUsuario,nombreIntroducido,passwordIntroducido)){
            //Verifica si el usuario esta autorizado o no
            if (datosUsuario.autorizado == true){
                System.out.println("Acceso al usuario consedido");
                sesionActiva = true;
            }else{
                System.out.println("El usuario no esta autorizado");
            }
        }
    }
}
//Clase Usuario
class usuario{
    public String password = "";
    public String nombreUsuario = "";
    public boolean autorizado = false;
    public usuario(String contraLocal, String nombreLocal,boolean autorizadoLocal){
        password = contraLocal;
        nombreUsuario = nombreLocal;
        autorizado = autorizadoLocal;
    }
}
//Clase MAIN
public class Main {
    public static void main(String[] args) {

    }
}