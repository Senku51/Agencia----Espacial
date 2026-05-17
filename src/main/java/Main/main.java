package Main;

import Entidades.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class main {

    private static final DateTimeFormatter FMT_FECHA      = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static Scanner sc;

    private static servicios.AstronautaServicio astronautaServicio;
    private static servicios.EstacionSeguimientoServicio estacionServicio;
    private static servicios.MisionServicio misionServicio;
    private static servicios.ModeloVehiculoServicio modeloServicio;
    private static servicios.SateliteServicio sateliteServicio;
    private static servicios.TelemetriaServicio telemetriaServicio;
    private static servicios.VehiculoLanzamientoServicio vehiculoServicio;

    // =========================================================================
    // MAIN
    // =========================================================================

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenciaEspacialPU");
        EntityManager em = emf.createEntityManager();
        sc = new Scanner(System.in);

        repositorio.AstronautaRepositorio astronautaRepo = new repositorio.AstronautaRepositorio(em);
        repositorio.EstacionSeguimientoRepositorio estacionRepo   = new repositorio.EstacionSeguimientoRepositorio(em);
        repositorio.MisionRepositorio misionRepo     = new repositorio.MisionRepositorio(em);
        repositorio.ModeloVehiculoRepositorio modeloRepo     = new repositorio.ModeloVehiculoRepositorio(em);
        repositorio.SateliteRepositorio sateliteRepo   = new repositorio.SateliteRepositorio(em);
        repositorio.TelemetriaRepositorio telemetriaRepo = new repositorio.TelemetriaRepositorio(em);
        repositorio.VehiculoLanzamientoRepositorio vehiculoRepo   = new repositorio.VehiculoLanzamientoRepositorio(em);

        astronautaServicio  = new servicios.AstronautaServicio(astronautaRepo);
        estacionServicio    = new servicios.EstacionSeguimientoServicio(estacionRepo);
        misionServicio      = new servicios.MisionServicio(misionRepo, vehiculoRepo);
        modeloServicio      = new servicios.ModeloVehiculoServicio(modeloRepo);
        sateliteServicio    = new servicios.SateliteServicio(sateliteRepo);
        telemetriaServicio  = new servicios.TelemetriaServicio(telemetriaRepo);
        vehiculoServicio    = new servicios.VehiculoLanzamientoServicio(vehiculoRepo, modeloRepo);

        menuPrincipal();

        sc.close();
        em.close();
        emf.close();
    }

    // =========================================================================
    // MENÚ PRINCIPAL
    // =========================================================================

    private static void menuPrincipal() {
        int op;
        do {
            System.out.println("\n=== AGENCIA ESPACIAL ===");
            System.out.println("1. Misiones");
            System.out.println("2. Modelos de vehículo");
            System.out.println("3. Vehículos de lanzamiento");
            System.out.println("4. Astronautas");
            System.out.println("5. Satélites");
            System.out.println("6. Telemetría");
            System.out.println("7. Estaciones de seguimiento");
            System.out.println("0. Salir");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> menuMisiones();
                case 2 -> menuModelos();
                case 3 -> menuVehiculos();
                case 4 -> menuAstronautas();
                case 5 -> menuSatelites();
                case 6 -> menuTelemetria();
                case 7 -> menuEstaciones();
                case 0 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    // =========================================================================
    // MISIONES
    // =========================================================================

    private static void menuMisiones() {
        int op;
        do {
            System.out.println("\n-- MISIONES --");
            System.out.println("1. Registrar");
            System.out.println("2. Consultar por ID");
            System.out.println("3. Listar todas");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Astronautas de una misión (RF-008)");
            System.out.println("0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String nombre   = leerStr("Nombre: ");
                        String objetivo = leerStr("Objetivo principal: ");
                        LocalDate lanz  = leerFecha("Fecha lanzamiento (dd/MM/yyyy): ");
                        LocalDate fin   = leerFechaOpc("Fecha fin prevista (dd/MM/yyyy, Enter para omitir): ");
                        String estado   = leerStr("Estado actual: ");
                        boolean tripul  = leerSN("¿Es tripulada? (s/n): ");
                        int idVeh       = leerInt("ID vehículo de lanzamiento: ");
                        misionServicio.registrarMision(nombre, objetivo, lanz, fin, estado, tripul, idVeh);
                        System.out.println("Misión registrada.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    Mision m = misionServicio.obtenerPorId(leerInt("ID: "));
                    System.out.println(m != null ? m : "No encontrada.");
                }
                case 3 -> {
                    List<Mision> lista = misionServicio.listarTodas();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id          = leerInt("ID de la misión a actualizar: ");
                        String nombre   = leerStr("Nuevo nombre: ");
                        String objetivo = leerStr("Nuevo objetivo: ");
                        LocalDate lanz  = leerFecha("Nueva fecha lanzamiento (dd/MM/yyyy): ");
                        LocalDate fin   = leerFechaOpc("Nueva fecha fin (dd/MM/yyyy, Enter para omitir): ");
                        String estado   = leerStr("Nuevo estado: ");
                        boolean tripul  = leerSN("¿Es tripulada? (s/n): ");
                        int idVeh       = leerInt("ID vehículo: ");
                        misionServicio.actualizarMision(id, nombre, objetivo, lanz, fin, estado, tripul, idVeh);
                        System.out.println("Misión actualizada.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        misionServicio.eliminarMision(leerInt("ID: "));
                        System.out.println("Misión eliminada.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 6 -> {
                    try {
                        List<MisionAstronauta> lista = misionServicio.listarAstronautasPorMision(leerInt("ID de la misión: "));
                        if (lista.isEmpty()) System.out.println("Sin astronautas en esa misión.");
                        else lista.forEach(ma ->
                                System.out.println(ma.getAstronauta().getNombreCompleto() + " — " + ma.getRolDesempenado()));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // MODELOS DE VEHÍCULO
    // =========================================================================

    private static void menuModelos() {
        int op;
        do {
            System.out.println("\n-- MODELOS DE VEHÍCULO --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String nombre = leerStr("Nombre del modelo: ");
                        double cap    = leerDouble("Capacidad de carga (kg): ");
                        String pais   = leerStr("País de fabricación: ");
                        modeloServicio.registrarModelo(nombre, cap, pais);
                        System.out.println("Modelo registrado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    ModeloVehiculo m = modeloServicio.obtenerPorId(leerInt("ID: "));
                    System.out.println(m != null ? m : "No encontrado.");
                }
                case 3 -> {
                    List<ModeloVehiculo> lista = modeloServicio.listarTodos();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id        = leerInt("ID: ");
                        String nombre = leerStr("Nuevo nombre: ");
                        double cap    = leerDouble("Nueva capacidad (kg): ");
                        String pais   = leerStr("Nuevo país: ");
                        modeloServicio.actualizarModelo(id, nombre, cap, pais);
                        System.out.println("Modelo actualizado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        modeloServicio.eliminarModelo(leerInt("ID: "));
                        System.out.println("Modelo eliminado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // VEHÍCULOS DE LANZAMIENTO
    // =========================================================================

    private static void menuVehiculos() {
        int op;
        do {
            System.out.println("\n-- VEHÍCULOS DE LANZAMIENTO --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String serial = leerStr("Nombre serial: ");
                        int idModelo  = leerInt("ID del modelo: ");
                        vehiculoServicio.registrarVehiculo(serial, idModelo);
                        System.out.println("Vehículo registrado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    VehiculoLanzamiento v = vehiculoServicio.obtenerPorId(leerInt("ID: "));
                    System.out.println(v != null ? v : "No encontrado.");
                }
                case 3 -> {
                    List<VehiculoLanzamiento> lista = vehiculoServicio.listarTodos();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id        = leerInt("ID: ");
                        String serial = leerStr("Nuevo serial: ");
                        int idModelo  = leerInt("Nuevo ID del modelo: ");
                        vehiculoServicio.actualizarVehiculo(id, serial, idModelo);
                        System.out.println("Vehículo actualizado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        vehiculoServicio.eliminarVehiculo(leerInt("ID: "));
                        System.out.println("Vehículo eliminado.");
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // ASTRONAUTAS
    // =========================================================================

    private static void menuAstronautas() {
        int op;
        do {
            System.out.println("\n-- ASTRONAUTAS --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String nombre  = leerStr("Nombre completo: ");
                        String nac     = leerStr("Nacionalidad: ");
                        LocalDate fech = leerFecha("Fecha de nacimiento (dd/MM/yyyy): ");
                        String espec   = leerStr("Especialidad: ");
                        astronautaServicio.crearAstronauta(nombre, nac, fech, espec);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    Astronauta a = astronautaServicio.consultarPorId(leerInt("ID: "));
                    if (a != null) System.out.println(a);
                }
                case 3 -> {
                    List<Astronauta> lista = astronautaServicio.listarTodos();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id         = leerInt("ID: ");
                        String nombre  = leerStr("Nuevo nombre: ");
                        String nac     = leerStr("Nueva nacionalidad: ");
                        LocalDate fech = leerFecha("Nueva fecha de nacimiento (dd/MM/yyyy): ");
                        String espec   = leerStr("Nueva especialidad: ");
                        astronautaServicio.actualizarAstronauta(id, nombre, nac, fech, espec);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        astronautaServicio.eliminarAstronauta(leerInt("ID: "));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // SATÉLITES
    // =========================================================================

    private static void menuSatelites() {
        int op;
        do {
            System.out.println("\n-- SATÉLITES --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Listar  4. Actualizar  5. Eliminar");
            System.out.println("6. Satélites por misión (RF-006)  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String nombre  = leerStr("Nombre: ");
                        String tipo    = leerStr("Tipo: ");
                        double altitud = leerDouble("Altitud orbital (km): ");
                        LocalDate fech = leerFechaOpc("Fecha puesta en órbita (dd/MM/yyyy, Enter para omitir): ");
                        int idMision   = leerInt("ID de la misión: ");
                        Mision mision  = misionServicio.obtenerPorId(idMision);
                        if (mision == null) { System.out.println("Misión no encontrada."); break; }
                        sateliteServicio.crearSatelite(nombre, tipo, altitud, fech, mision);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    Satelite s = sateliteServicio.consultarPorId(leerInt("ID: "));
                    if (s != null) System.out.println(s);
                }
                case 3 -> {
                    List<Satelite> lista = sateliteServicio.listarTodos();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id         = leerInt("ID: ");
                        String nombre  = leerStr("Nuevo nombre: ");
                        String tipo    = leerStr("Nuevo tipo: ");
                        double altitud = leerDouble("Nueva altitud (km): ");
                        LocalDate fech = leerFechaOpc("Nueva fecha órbita (dd/MM/yyyy, Enter para omitir): ");
                        int idMision   = leerInt("ID de la misión: ");
                        Mision mision  = misionServicio.obtenerPorId(idMision);
                        if (mision == null) { System.out.println("Misión no encontrada."); break; }
                        sateliteServicio.actualizarSatelite(id, nombre, tipo, altitud, fech, mision);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        sateliteServicio.eliminarSatelite(leerInt("ID: "));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 6 -> {
                    List<Satelite> lista = sateliteServicio.listarPorMision(leerInt("ID de la misión: "));
                    if (lista.isEmpty()) System.out.println("Sin satélites para esa misión.");
                    else lista.forEach(System.out::println);
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // TELEMETRÍA
    // =========================================================================

    private static void menuTelemetria() {
        int op;
        do {
            System.out.println("\n-- TELEMETRÍA --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Actualizar  4. Eliminar");
            System.out.println("5. Telemetría por satélite (RF-007)  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        int idSat        = leerInt("ID del satélite: ");
                        Satelite sat     = sateliteServicio.consultarPorId(idSat);
                        if (sat == null) { System.out.println("Satélite no encontrado."); break; }
                        LocalDateTime fh = leerFechaHora("Fecha y hora (dd/MM/yyyy HH:mm): ");
                        double temp      = leerDouble("Temperatura (°C): ");
                        double vel       = leerDouble("Velocidad (km/h): ");
                        int bat          = leerInt("Nivel de batería (0-100): ");
                        telemetriaServicio.crearTelemetria(sat, fh, temp, vel, bat);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    Telemetria t = telemetriaServicio.consultarPorId(leerLong("ID del registro: "));
                    if (t != null) System.out.println(t);
                }
                case 3 -> {
                    try {
                        long id          = leerLong("ID del registro: ");
                        int idSat        = leerInt("ID del satélite: ");
                        Satelite sat     = sateliteServicio.consultarPorId(idSat);
                        if (sat == null) { System.out.println("Satélite no encontrado."); break; }
                        LocalDateTime fh = leerFechaHora("Nueva fecha y hora (dd/MM/yyyy HH:mm): ");
                        double temp      = leerDouble("Nueva temperatura: ");
                        double vel       = leerDouble("Nueva velocidad: ");
                        int bat          = leerInt("Nuevo nivel de batería (0-100): ");
                        telemetriaServicio.actualizarTelemetria(id, sat, fh, temp, vel, bat);
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 4 -> {
                    try {
                        telemetriaServicio.eliminarTelemetria(leerLong("ID del registro: "));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    List<Telemetria> lista = telemetriaServicio.listarPorSatelite(leerInt("ID del satélite: "));
                    if (lista.isEmpty()) System.out.println("Sin registros para ese satélite.");
                    else lista.forEach(System.out::println);
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // ESTACIONES DE SEGUIMIENTO
    // =========================================================================

    private static void menuEstaciones() {
        int op;
        do {
            System.out.println("\n-- ESTACIONES DE SEGUIMIENTO --");
            System.out.println("1. Registrar  2. Consultar por ID  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            op = leerInt("Opción: ");
            switch (op) {
                case 1 -> {
                    try {
                        String nombre = leerStr("Nombre de la estación: ");
                        double lat    = leerDouble("Latitud: ");
                        double lon    = leerDouble("Longitud: ");
                        String ciudad = leerStr("Ciudad: ");
                        String pais   = leerStr("País: ");
                        estacionServicio.crearEstacion(nombre, lat, lon, new Ubicacion(ciudad, pais));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 2 -> {
                    EstacionSeguimiento e = estacionServicio.consultarPorId(leerInt("ID: "));
                    if (e != null) System.out.println(e);
                }
                case 3 -> {
                    List<EstacionSeguimiento> lista = estacionServicio.listarTodas();
                    if (lista.isEmpty()) System.out.println("Sin registros.");
                    else lista.forEach(System.out::println);
                }
                case 4 -> {
                    try {
                        int id        = leerInt("ID: ");
                        String nombre = leerStr("Nuevo nombre: ");
                        double lat    = leerDouble("Nueva latitud: ");
                        double lon    = leerDouble("Nueva longitud: ");
                        String ciudad = leerStr("Nueva ciudad: ");
                        String pais   = leerStr("Nuevo país: ");
                        estacionServicio.actualizarEstacion(id, nombre, lat, lon, new Ubicacion(ciudad, pais));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
                case 5 -> {
                    try {
                        estacionServicio.eliminarEstacion(leerInt("ID: "));
                    } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                }
            }
        } while (op != 0);
    }

    // =========================================================================
    // UTILIDADES DE LECTURA
    // =========================================================================

    private static String leerStr(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int leerInt(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Introduce un número entero."); }
        }
    }

    private static long leerLong(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Long.parseLong(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Introduce un número válido."); }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Double.parseDouble(sc.nextLine().trim().replace(",", ".")); }
            catch (NumberFormatException e) { System.out.println("Introduce un número decimal."); }
        }
    }

    private static boolean leerSN(String msg) {
        while (true) {
            System.out.print(msg);
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s")) return true;
            if (r.equals("n")) return false;
            System.out.println("Introduce 's' o 'n'.");
        }
    }

    private static LocalDate leerFecha(String msg) {
        while (true) {
            System.out.print(msg);
            try { return LocalDate.parse(sc.nextLine().trim(), FMT_FECHA); }
            catch (DateTimeParseException e) { System.out.println("Formato incorrecto. Usa dd/MM/yyyy."); }
        }
    }

    private static LocalDate leerFechaOpc(String msg) {
        System.out.print(msg);
        String linea = sc.nextLine().trim();
        if (linea.isEmpty()) return null;
        try { return LocalDate.parse(linea, FMT_FECHA); }
        catch (DateTimeParseException e) { System.out.println("Formato incorrecto, se omite."); return null; }
    }

    private static LocalDateTime leerFechaHora(String msg) {
        while (true) {
            System.out.print(msg);
            try { return LocalDateTime.parse(sc.nextLine().trim(), FMT_FECHA_HORA); }
            catch (DateTimeParseException e) { System.out.println("Formato incorrecto. Usa dd/MM/yyyy HH:mm."); }
        }
    }
}