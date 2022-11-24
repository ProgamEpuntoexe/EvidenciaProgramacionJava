import java.io.*;
import java.util.*;

//Clase doctor
class doctor{
    public String nombre = "";
    public String especialidad = "";
    public int experiencia = 0;
    public float presupuesto = 0.0f;
    public boolean disponible = true;
    public doctor(String nombreLocal, String especialidadLocal, int experienciaLocal, float presupuestoLocal, boolean disponibleLocal){
        nombre = nombreLocal;
        especialidad = especialidadLocal;
        experiencia = experienciaLocal;
        presupuesto = presupuestoLocal;
        disponible = disponibleLocal;
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
    public int pacienteAtender;
    public int doctorAtender;
    public int horarioMinutos = 0;
    public int horarioHoras = 0;
    public int numeroMes = 0;
    public int numeroDia = 0;
    public int numeroYear = 0;
    public cita(int pacienteLocal,int doctorLocal, int minutosLocal, int horasLocal, int mesLocal, int diaLocal, int yearlocal){
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
    public ArrayList<paciente> listaPacientes = new ArrayList<paciente>();
    public ArrayList<cita> listaCitas = new ArrayList<cita>();
    String nombre = "";
    public edificio(String nombreLocal){
        nombre = nombreLocal;
    }
    public void cargarDatos() throws IOException{
        File archivo = new File("datosGuardados/datosDoctores.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            escaneo.useDelimiter("%-%");
            do{
                listaDoctores.add(new doctor(escaneo.next(),escaneo.next(),Integer.parseInt(escaneo.next()),Float.parseFloat(escaneo.next()),Boolean.parseBoolean(escaneo.next())));
                escaneo.nextLine();
            }while(escaneo.hasNextLine());
        }
        archivo = new File("datosGuardados/datosCitas.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            escaneo.useDelimiter("%-%");
            do{
                listaCitas.add(new cita(Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next())));
                //escaneo.nextLine();
            }while(escaneo.hasNextLine());
        }
        archivo = new File("datosGuardados/datosPacientes.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            escaneo.useDelimiter("%-%");
            do{
                listaPacientes.add(new paciente(escaneo.next(),escaneo.next(),Integer.parseInt(escaneo.next()),Boolean.parseBoolean(escaneo.next())));
                //escaneo.nextLine();
            }while(escaneo.hasNextLine());
        }
    }
    public void guardarDoctor(edificio hospital, String nombreLocal, String especialidadLocal, int experienciaLocal, float presupuestoLocal) throws IOException {
        File archivo = new File("datosGuardados/datosDoctores.txt");
        hospital.listaDoctores.add(new doctor(nombreLocal,especialidadLocal,experienciaLocal,presupuestoLocal,true));
        if (archivo.exists()) {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < hospital.listaDoctores.size(); i++) {
                pw.println(hospital.listaDoctores.get(i).nombre + "%-%" + hospital.listaDoctores.get(i).especialidad + "%-%" + hospital.listaDoctores.get(i).experiencia + "%-%" + hospital.listaDoctores.get(i).presupuesto + "%-%"+true+"%-%");
            }
            pw.close();
        }
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
        doctor idea = new doctor("Dr","Medicina",2,1000,true);
        for (int i = 0; i < hospital.listaDoctores.size(); i++){
            System.out.println((i+1)+". "+hospital.listaDoctores.get(i).nombre+" -"+hospital.listaDoctores.get(i).especialidad);
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
    public void agendarCita(edificio hospital) throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        String nombrePaciente = "";
        String asuntoPaciente = "";
        int edadPaciente = 0;
        int idDoctor = 0;
        int minutosLocal = 0;
        int horasLocal = 0;
        int mesLocal = 0;
        int yearLocal = 0;
        int diaLocal = 0;
        //Despues de introducir los datos del paciente
        //Si hay doctores
        if (!hospital.listaDoctores.isEmpty()){
            mostrarListaDoctores(hospital);
            System.out.print("Ingrese el id del doctor a realizar la cita: ");
            try{
                Integer.parseInt("1");
                idDoctor = Integer.parseInt(entrada.readLine());
            }catch(Exception e){
                System.out.println("Ocurrio un error");
            }
            System.out.println("---INGRESANDO DATOS DE PACIENTE---");
            try{
                System.out.print("Nombre del paciente: ");
                nombrePaciente = entrada.readLine();
                System.out.print("Asunto: ");
                asuntoPaciente = entrada.readLine();
                System.out.print("Edad: ");
                edadPaciente = Integer.parseInt(entrada.readLine());
                System.out.println("---INGRESANDO DATOS DE FECHA---");
                System.out.print("Año: ");
                yearLocal = Integer.parseInt(entrada.readLine());
                System.out.print("Mes: ");
                mesLocal = Integer.parseInt(entrada.readLine());
                System.out.print("Dia: ");
                diaLocal = Integer.parseInt(entrada.readLine());
                System.out.print("La hora en horas: ");
                horasLocal = Integer.parseInt(entrada.readLine());
                System.out.print("La hora en minutos: ");
                minutosLocal = Integer.parseInt(entrada.readLine());
                paciente registrarPaciente = new paciente(nombrePaciente,asuntoPaciente,edadPaciente,true);
                hospital.listaPacientes.add(registrarPaciente);
                hospital.listaCitas.add(new cita(hospital.listaPacientes.size()-1,idDoctor-1,minutosLocal,horasLocal,mesLocal,diaLocal,yearLocal));
                guardarCita(hospital);
                guardarPaciente(hospital);
            }catch(Exception e){
                System.out.println("Ocurrio un error");
            }
        }else{
            System.out.println("No hay Doctores disponibles");
        }
    }
    private void guardarCita(edificio hospital) throws IOException{
        File archivo = new File("datosGuardados/datosCitas.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        //int pacienteLocal,int doctorLocal, int minutosLocal, int horasLocal, int mesLocal, int diaLocal, int yearlocal
        for (int i = 0; i < hospital.listaCitas.size(); i++) {
            pw.println(hospital.listaCitas.get(i).pacienteAtender+"%-%"+hospital.listaCitas.get(i).doctorAtender+"%-%"+hospital.listaCitas.get(i).horarioMinutos+"%-%"+hospital.listaCitas.get(i).horarioHoras+"%-%"+hospital.listaCitas.get(i).numeroMes+"%-%"+hospital.listaCitas.get(i).numeroDia+"%-%"+hospital.listaCitas.get(i).numeroYear);
        }
        pw.close();
    }
    private void guardarPaciente(edificio hospital) throws IOException{
        File archivo = new File("datosGuardados/datosPacientes.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        //int pacienteLocal,int doctorLocal, int minutosLocal, int horasLocal, int mesLocal, int diaLocal, int yearlocal
        for (int i = 0; i < hospital.listaCitas.size(); i++) {
            //paciente(nombrePaciente,asuntoPaciente,edadPaciente,true)
            pw.println(hospital.listaPacientes.get(i).nombre+"%-%"+hospital.listaPacientes.get(i).asunto+"%-%"+hospital.listaPacientes.get(i).edad+"%-%"+hospital.listaPacientes.get(i).citado+"%-%");
        }
        pw.close();
    }
    public void eliminarCita(edificio Hospital) throws IOException{

    }
    public void mostrarCitas(edificio Hospital) throws IOException{
        for (int i = 0; i < Hospital.listaCitas.size(); i++){
            System.out.println((i+1)+".  -"+Hospital.listaCitas.get(i).numeroDia+"/"+Hospital.listaCitas.get(i).numeroMes+"/"+Hospital.listaCitas.get(i).numeroYear);
            System.out.println("    -Doctor: "+Hospital.listaDoctores.get(Hospital.listaCitas.get(i).doctorAtender).nombre);
            System.out.println("    -Paciente: "+Hospital.listaPacientes.get(Hospital.listaCitas.get(i).pacienteAtender).nombre+" de "+Hospital.listaPacientes.get(Hospital.listaCitas.get(i).pacienteAtender).edad+" años");
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
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        usuario personaUtilizaPrograma = new usuario("","");
        edificio hospital = new edificio("Hospital Angeles");
        personaUtilizaPrograma.cargarDatos();
        programa pagina = new programa();
        String opciones = "";
        //hospital.guardarDoctor(hospital,"dr alguien","Medicina",2,1234);
        //pagina.mostrarListaDoctores(hospital);
        System.out.println(personaUtilizaPrograma.nombreUsuario);
        System.out.println(personaUtilizaPrograma.password);
        hospital.cargarDatos();
        //System.out.println(hospital.listaCitas.get(0).numeroDia);
        //hospital.cargarDatos();
        System.out.println(hospital.listaPacientes.get(0));
        pagina.mostrarCitas(hospital);
        if (pagina.obtenerAcceso(personaUtilizaPrograma)){

            do{
                System.out.println("Opciones para el "+hospital.nombre);
                System.out.println("1. Agendar una cita");
                System.out.println("2. Dar de alta a un doctor");
                System.out.println("3. Dar de alta a un paciente");
                System.out.println("4. Agendar una cita");
                System.out.println("5. Cerrar Sesion");
                System.out.print("Ingresar el numero de opcion: ");
                opciones = entrada.readLine();
                if (opciones.isEmpty()){
                    System.out.println("Favor de introducir algo");
                    entrada.readLine();
                    opciones = "0";
                }else{
                    switch(opciones.charAt(0)){
                        case '1':{
                            pagina.agendarCita(hospital);
                            break;
                        }
                    }
                }
            }while(opciones.charAt(0) != '5');
        }else{
            System.out.println("Error al intentar entrar");
        }
    }
}