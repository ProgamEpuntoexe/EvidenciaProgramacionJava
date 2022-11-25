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
            while(escaneo.hasNext()){
                listaDoctores.add(new doctor(escaneo.next(),escaneo.next(),Integer.parseInt(escaneo.next()),Float.parseFloat(escaneo.next()),Boolean.parseBoolean(escaneo.next())));
                escaneo.nextLine();
            }
        }
        archivo = new File("datosGuardados/datosCitas.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            escaneo.useDelimiter("%-%");
            while(escaneo.hasNext()){
                listaCitas.add(new cita(Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next()),Integer.parseInt(escaneo.next())));
                escaneo.nextLine();
            }
        }
        archivo = new File("datosGuardados/datosPacientes.txt");
        if (archivo.exists()){
            Scanner escaneo = new Scanner(archivo);
            escaneo.useDelimiter("%-%");
            while(escaneo.hasNext()){
                listaPacientes.add(new paciente(escaneo.next(),escaneo.next(),Integer.parseInt(escaneo.next()),Boolean.parseBoolean(escaneo.next())));
                escaneo.nextLine();
            }
        }
    }
    public void guardarDoctor(edificio hospital, String nombreLocal, String especialidadLocal, int experienciaLocal, float presupuestoLocal) throws IOException {
        File archivo = new File("datosGuardados/datosDoctores.txt");
        hospital.listaDoctores.add(new doctor(nombreLocal,especialidadLocal,experienciaLocal,presupuestoLocal,true));
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
    public void cambioDisponibilidad(int idDoctor, boolean cambioDisponibilidad) throws IOException{
        listaDoctores.get(idDoctor).disponible = cambioDisponibilidad;
        File archivo = new File("datosGuardados/datosDoctores.txt");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < listaDoctores.size(); i++) {
                pw.println(listaDoctores.get(i).nombre + "%-%" +listaDoctores.get(i).especialidad + "%-%" + listaDoctores.get(i).experiencia + "%-%" + listaDoctores.get(i).presupuesto + "%-%"+listaDoctores.get(i).disponible+"%-%");
            }
            pw.close();
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
        String disponible = "";
        for (int i = 0; i < hospital.listaDoctores.size(); i++){
            if (hospital.listaDoctores.get(i).disponible == true){
                disponible = "- disponible";
            }else{
                disponible = "- NO disponible";
            }
            System.out.println((i+1)+". "+hospital.listaDoctores.get(i).nombre+" -"+hospital.listaDoctores.get(i).especialidad+" "+disponible);
        }
    }
    public void mostrarListaDoctoresDisponibles(edificio hospital){
        doctor idea = new doctor("Dr","Medicina",2,1000,true);
        int doctores = 1;
        for (int i = 0; i < hospital.listaDoctores.size(); i++){
            if (hospital.listaDoctores.get(i).disponible == true){
                System.out.println(doctores+". "+hospital.listaDoctores.get(i).nombre+" -"+hospital.listaDoctores.get(i).especialidad);
                doctores += 1;
            }
        }
    }
    public void darAltaDoctor(edificio hospital, int idDoctor) throws IOException{
        for (int i = 0; i < hospital.listaCitas.size(); i++){
            if (hospital.listaCitas.get(i).doctorAtender == idDoctor-1){
                darAltaPaciente(hospital, hospital.listaCitas.get(i).pacienteAtender+1);
            }
        }
        hospital.listaDoctores.remove(idDoctor-1);
        File archivo = new File("datosGuardados/datosDoctores.txt");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < hospital.listaDoctores.size(); i++) {
                pw.println(hospital.listaDoctores.get(i).nombre + "%-%" + hospital.listaDoctores.get(i).especialidad + "%-%" + hospital.listaDoctores.get(i).experiencia + "%-%" + hospital.listaDoctores.get(i).presupuesto + "%-%"+true+"%-%");
            }
            pw.close();
        eliminarCitaDoctor(hospital, idDoctor);
    }
    private void eliminarCitaDoctor(edificio hospital, int idDoctor) throws IOException{
        for (int i = 0; i < hospital.listaCitas.size(); i++){
            if (hospital.listaCitas.get(i).doctorAtender == idDoctor-1){
                hospital.listaCitas.remove(i);
            }
        }
        File archivo = new File("datosGuardados/datosCitas.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < hospital.listaCitas.size(); i++) {
            pw.println(hospital.listaCitas.get(i).pacienteAtender+"%-%"+hospital.listaCitas.get(i).doctorAtender+"%-%"+hospital.listaCitas.get(i).horarioMinutos+"%-%"+hospital.listaCitas.get(i).horarioHoras+"%-%"+hospital.listaCitas.get(i).numeroMes+"%-%"+hospital.listaCitas.get(i).numeroDia+"%-%"+hospital.listaCitas.get(i).numeroYear+"%-%");
        }
        pw.close();

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
                hospital.cambioDisponibilidad(idDoctor-1,false);
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
            pw.println(hospital.listaCitas.get(i).pacienteAtender+"%-%"+hospital.listaCitas.get(i).doctorAtender+"%-%"+hospital.listaCitas.get(i).horarioMinutos+"%-%"+hospital.listaCitas.get(i).horarioHoras+"%-%"+hospital.listaCitas.get(i).numeroMes+"%-%"+hospital.listaCitas.get(i).numeroDia+"%-%"+hospital.listaCitas.get(i).numeroYear+"%-%");
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
    public void mostrarCitas(edificio Hospital) throws IOException{
        if (Hospital.listaCitas.isEmpty()){
            System.out.println("No hay citas registradas");
        }
        for (int i = 0; i < Hospital.listaCitas.size(); i++){
            System.out.println((i+1)+".  -"+Hospital.listaCitas.get(i).numeroDia+"/"+Hospital.listaCitas.get(i).numeroMes+"/"+Hospital.listaCitas.get(i).numeroYear);
            System.out.println("    -Doctor: "+Hospital.listaDoctores.get(Hospital.listaCitas.get(i).doctorAtender).nombre);
            System.out.println("    -Paciente: "+Hospital.listaPacientes.get(Hospital.listaCitas.get(i).pacienteAtender).nombre+" de "+Hospital.listaPacientes.get(Hospital.listaCitas.get(i).pacienteAtender).edad+" años");
        }
    }
    public void darAltaPaciente(edificio hospital, int idPaciente) throws IOException{
        hospital.listaPacientes.remove(idPaciente-1);
        File archivo = new File("datosGuardados/datosPacientes.txt");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < hospital.listaPacientes.size(); i++) {
                pw.println(hospital.listaPacientes.get(i).nombre+"%-%"+hospital.listaPacientes.get(i).asunto+"%-%"+hospital.listaPacientes.get(i).edad+"%-%"+hospital.listaPacientes.get(i).citado+"%-%");
            }
            pw.close();
        eliminarCitaPaciente(hospital, idPaciente);
    }
    private void eliminarCitaPaciente(edificio hospital, int idPaciente) throws IOException{
        for (int i = 0; i < hospital.listaCitas.size(); i++){
            if (hospital.listaCitas.get(i).pacienteAtender == idPaciente-1){
                hospital.listaDoctores.get(hospital.listaCitas.get(i).doctorAtender).disponible = true;
                hospital.listaCitas.remove(i);
            }
        }
        File archivo = new File("datosGuardados/datosCitas.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < hospital.listaCitas.size(); i++) {
            pw.println(hospital.listaCitas.get(i).pacienteAtender+"%-%"+hospital.listaCitas.get(i).doctorAtender+"%-%"+hospital.listaCitas.get(i).horarioMinutos+"%-%"+hospital.listaCitas.get(i).horarioHoras+"%-%"+hospital.listaCitas.get(i).numeroMes+"%-%"+hospital.listaCitas.get(i).numeroDia+"%-%"+hospital.listaCitas.get(i).numeroYear+"%-%");
        }
        pw.close();
        archivo = new File("datosGuardados/datosDoctores.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        fw = new FileWriter(archivo);
        pw = new PrintWriter(fw);
        for (int i = 0; i < hospital.listaDoctores.size(); i++) {
            pw.println(hospital.listaDoctores.get(i).nombre + "%-%" + hospital.listaDoctores.get(i).especialidad + "%-%" + hospital.listaDoctores.get(i).experiencia + "%-%" + hospital.listaDoctores.get(i).presupuesto + "%-%"+hospital.listaDoctores.get(i).disponible+"%-%");
        }
        pw.close();
    }
    public void mostrarListaPacientes(edificio hospital){
        for (int i = 0; i < hospital.listaPacientes.size(); i++){
            hospital.listaPacientes.get(i);
            System.out.println((i+1)+". "+hospital.listaPacientes.get(i).nombre+" |edad: "+hospital.listaPacientes.get(i).edad+" |Motivo: "+hospital.listaPacientes.get(i).asunto);
        }
    }
    public void eliminarCita(edificio hospital, int idCita) throws IOException{
        hospital.listaCitas.remove(idCita-1);
        File archivo = new File("datosGuardados/datosCitas.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        FileWriter fw = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < hospital.listaCitas.size(); i++) {
            pw.println(hospital.listaCitas.get(i).pacienteAtender+"%-%"+hospital.listaCitas.get(i).doctorAtender+"%-%"+hospital.listaCitas.get(i).horarioMinutos+"%-%"+hospital.listaCitas.get(i).horarioHoras+"%-%"+hospital.listaCitas.get(i).numeroMes+"%-%"+hospital.listaCitas.get(i).numeroDia+"%-%"+hospital.listaCitas.get(i).numeroYear+"%-%");
        }
        pw.close();
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
        }else{
            nombreUsuario = "Rosa";
            password = "1234";
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
        usuario personaUtilizaPrograma = new usuario("Rosa","1234");
        edificio hospital = new edificio("Hospital Angeles");
        personaUtilizaPrograma.cargarDatos();
        programa pagina = new programa();
        String opciones = "";
        String nombre = "";
        String especialidad = "";
        String password = "";
        int experiencia = 1;
        float presupuesto = 0.0f;
        //hospital.guardarDoctor(hospital,"dr alguien","Medicina",2,1234);
        hospital.cargarDatos();
        if (pagina.obtenerAcceso(personaUtilizaPrograma)){

            do{
                System.out.println("Opciones para el "+hospital.nombre);
                System.out.println("a. Agendar una cita");
                System.out.println("b. Dar de alta a un doctor");
                System.out.println("c. Dar de alta a un paciente");
                System.out.println("d. Ver citas");
                System.out.println("e. Eliminar una cita");
                System.out.println("f. Cambiar datos de Usuario");
                System.out.println("g. Agregar un doctor");
                System.out.println("h. Cerrar Sesion");
                System.out.print("Ingresar el numero de opcion: ");
                opciones = entrada.readLine();
                if (opciones.isEmpty()){
                    System.out.println("Favor de introducir algo");
                    entrada.readLine();
                    opciones = "0";
                }else{
                    switch(opciones.charAt(0)){
                        case 'a':{
                            pagina.agendarCita(hospital);
                            break;
                        }
                        case 'b':{
                            pagina.mostrarListaDoctores(hospital);
                            try{
                                System.out.print("Seleccione el ID del doctor a dar de alta: ");
                                pagina.darAltaDoctor(hospital, Integer.parseInt(entrada.readLine()));
                            }catch (Exception e){
                                System.out.println("Hubo un error al dar de alta");
                            }
                            //pagina.darAltaDoctor();
                            break;
                        }
                        case 'c':{
                            pagina.mostrarListaPacientes(hospital);
                            try{
                                System.out.print("Seleccione el ID del paciente a dar de alta: ");
                                pagina.darAltaPaciente(hospital, Integer.parseInt(entrada.readLine()));
                            }catch (Exception e){
                                System.out.println("Hubo un error al dar de alta");
                            }
                            break;
                        }
                        case 'd':{
                            pagina.mostrarCitas(hospital);
                            entrada.readLine();
                            break;
                        }
                        case 'e':{
                            try{
                                int idCita = 0;
                                pagina.mostrarCitas(hospital);
                                System.out.print("Seleccione el ID de la cita a eliminar");
                                idCita = Integer.parseInt(entrada.readLine());
                                pagina.eliminarCita(hospital, idCita);
                                hospital.cambioDisponibilidad(hospital.listaCitas.get(idCita).doctorAtender, true);
                            }catch(Exception e){
                                System.out.println("Hubo un error al eliminar la cita");
                            }
                            break;
                        }
                        case 'f':{
                            try{
                                System.out.print("Ingrese el nuevo nombre de usuario: ");
                                nombre = entrada.readLine();
                                System.out.print("Ingrese la nueva contraseña: ");
                                password = entrada.readLine();
                                personaUtilizaPrograma.guardarDatos(nombre,password);
                            }catch (Exception e){
                                System.out.println("Hubo un error al cambiar los datos");
                            }
                        }
                        case 'g':{
                            try{
                                System.out.print("Ingresar el nombre del doctor: ");
                                nombre = entrada.readLine();
                                System.out.print("Ingresar la especialidad del doctor: ");
                                especialidad = entrada.readLine();
                                System.out.print("Ingresar la experiencia del doctor (En años): ");
                                experiencia = Integer.parseInt(entrada.readLine());
                                System.out.print("Ingresar el presupuesto del doctor: ");
                                presupuesto = Float.parseFloat(entrada.readLine());
                                hospital.guardarDoctor(hospital, nombre, especialidad, experiencia, presupuesto);
                            }catch (Exception e){
                                System.out.println("Hubo un error al registrar el doctor");
                            }
                            break;
                        }
                    }
                }
            }while(opciones.charAt(0) != 'h');
        }else{
            System.out.println("Error al intentar entrar");
        }
    }
}