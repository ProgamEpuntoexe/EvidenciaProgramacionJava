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
    //Dar de alta a los doctores/despedir
    public boolean obtenerAcceso(usuario datosUsuario) throws IOException{
        boolean sesionActiva = false;
        String nombreIntroducido = "";
        String passwordIntroducido = "";
        System.out.print("Ingrese el nombre de usuario: ");
        nombreIntroducido = entrada.readLine();
        System.out.print("Ingrese el su contraseña: ");
        passwordIntroducido = entrada.readLine();
        //Para introducir los datos
        if (verificar(datosUsuario,nombreIntroducido,passwordIntroducido)) {
            return true;
        }else{
            return false;
        }
    }
    private boolean verificar(usuario datosUsuario, String nombreIntroducido, String passwordIntroducido){
        if ((datosUsuario.nombreUsuario.equals(nombreIntroducido)) & (datosUsuario.password.equals(passwordIntroducido))){
            return true;
        }else{
            return false;
        }
    }
    public void mostrarListaDoctores(edificio hospital){
        doctor idea = new doctor("Dr","Medicina",2,1000);
        for (int i = 0; i < hospital.listaDoctores.size(); i++){
            System.out.println(i+". "+hospital.listaDoctores.get(0).nombre);
        }
    }
    public void darAltaDoctor(edificio hospital, int idDoctor){
        //Si hay doctores
        if (!hospital.listaDoctores.isEmpty()){
            hospital.listaDoctores.indexOf(idDoctor);
        }else{
            System.out.println("No hay doctores");
        }
    }
    public void agendarCita(edificio hospital, int idPaciente, int idDoctor){
        //Despues de introducir los datos del paciente
        //Si hay doctores
        if (!hospital.listaDoctoresDisponibles.isEmpty()){
            hospital.listaCitas.add(new cita(hospital.listaPacientes.get(idPaciente),hospital.listaDoctoresDisponibles.get(idDoctor),12,30,12,2,2023));
        }else{
            System.out.println("No hay Doctores disponibles");
        }
    }
    public void darAltaPaciente(edificio hospital, int idPaciente){
        //Si hay pacientes
        if (!hospital.listaPacientes.isEmpty()){
            hospital.listaPacientes.remove(idPaciente);
        }else{
            System.out.println("No hay pacientes");
        }
    }
}
//Clase Usuario
class usuario{
    public String password = "";
    public String nombreUsuario = "";
    public usuario(String contraLocal, String nombreLocal){
        password = contraLocal;
        nombreUsuario = nombreLocal;
    }
    public void cargarDatos() throws FileNotFoundException {
        File archivo = new File("datosGuardados/datosUsuario.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            nombreUsuario = escaneo.nextLine();
            password = escaneo.nextLine();
        }
    }
    public void guardarDatos(String nombre, String password) throws IOException{
        File archivo = new File("datosGuardados/datosUsuario.txt");
        if (!archivo.exists()){
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(nombre);
        pw.println(password);
        pw.close();
    }
}
//Clase MAIN
public class Main {
    public static void main(String[] args) throws IOException {
        usuario personaUtilizaPrograma = new usuario("","");
        personaUtilizaPrograma.guardarDatos("Rosa","1234");
        personaUtilizaPrograma.cargarDatos();
        programa pagina = new programa();
        String opciones = "";
        System.out.println(personaUtilizaPrograma.nombreUsuario);
        System.out.println(personaUtilizaPrograma.password);
        if (pagina.obtenerAcceso(personaUtilizaPrograma)){
            do{
                System.out.println("Opciones");
                System.out.println("1. Agendar una cita");
                System.out.println("2. Dar de alta a un doctor");
                System.out.println("3. Dar de alta a un paciente");
                System.out.println("4. Cerrar Sesion");
                opciones = "0";
            }while(opciones.charAt(0) != '0');
        }else{
            System.out.println("Error al intentar entrar");
        }
    }
}