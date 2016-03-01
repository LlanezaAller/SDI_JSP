package uo.sdi.tests;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ShareMyTripTests {
	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/IglesiasJavier-LlanezaInigo");
	}

	@Test
	public void testListarViajes() {
		beginAt("/"); // Navegar a la URL
		assertTitleEquals("Viajes"); // Comprobar título de la página

		// La base de datos contiene 3 viajes tal y como se entrega pero no se
		// muestran
		// Debido al conflicto con el datatables.
	}

	@Test
	public void testIniciarSesionConExito() {
		// Rellenando el formulario HTML

		beginAt("/login.jsp"); // Navegar a la URL
		setTextField("user", "usuario1"); // Rellenar primer campo de formulario
		setTextField("password", "usuario1");
		assertTextInElement("user", "usuario1");
		assertTextInElement("password", "usuario1");
		submit(); // Enviar formulario
		assertTitleEquals("Viajes");
		assertTextPresent("¡Bienvenido de nuevo, usuario1!");
	}

	// assertTextInElement("name", "Fernando"); // Comprobar cierto elemento
	// contiene cierto texto
	// assertTextPresent("Es Vd el usuario número:"); // Comprobar cierto texto
	// está presente

	@Test
	public void testIniciarSesionSinExito() {
		// Rellenando el formulario HTML
		beginAt("/login.jsp"); // Navegar a la URL
		setTextField("user", "yoNoExisto"); // Rellenar primer campo de
											// formulario
		submit(); // Enviar formulario
		assertTitleEquals("Entrar"); // Comprobar título de la página
	}

	@Test
	public void testRegistrarUnNuevoUsuario() {
		beginAt("/registro.jsp"); // Navegar a la URL
		setTextField("name", "usuarioTestNombre");
		setTextField("surname", "usuarioTestApellido");
		setTextField("email", "usuarioTest@email.com");
		setTextField("user", "usuarioTest");
		setTextField("password", "usuarioTest");
		setTextField("repeatPassword", "usuarioTest");
		submit(); // Enviar formulario
		assertTitleEquals("Viajes");
		assertTextPresent("Bienvenido, usuarioTest");
	}

	@Test
	public void testRegistrarUnNuevoUsuarioLoginRepetido() {
		beginAt("/registro.jsp"); // Navegar a la URL
		setTextField("name", "usuarioTestNombre");
		setTextField("surname", "usuarioTestApellido");
		setTextField("email", "usuarioTest@email.com");
		setTextField("user", "usuarioTest");
		setTextField("password", "usuarioTest");
		setTextField("repeatPassword", "usuarioTest");
		submit(); // Enviar formulario
		assertTitleEquals("Crear una cuenta");
		assertTextPresent("El nombre de usuario usuarioTest ya está en uso.");
	}

	@Test
	public void testRegistrarUnViajeConExito() {
		// Nos logueamos
		beginAt("/login.jsp"); // Navegar a la URL
		setTextField("user", "usuario1"); // Rellenar primer campo de formulario
		setTextField("password", "usuario1");
		assertTextInElement("user", "usuario1");
		assertTextInElement("password", "usuario1");
		submit(); // Enviar formulario
		assertTitleEquals("Viajes");
		assertTextPresent("¡Bienvenido de nuevo, usuario1!");
		//beginAt("/crearViaje.jsp"); // Navegar a la URL
		clickLink("crearViaje");
		assertTitleEquals("Crear Viaje");
		setTextField("freeSeats", "2");
		setTextField("totalCost", "5.0");
		setTextField("limitDatetime", "20-10-2017T10:00");
		setTextField("departureCountry", "Spain");
		setTextField("departureState", "Asturias");
		setTextField("departureCity", "Gijon");
		setTextField("departureZip", "33200");
		setTextField("departureStreet", "C/ Salida");
		setTextField("departureDatetime", "21-10-2017T10:00");
		setTextField("arrivalCountry", "Spain");
		setTextField("arrivalState", "Asturias");
		setTextField("arrivalCity", "Oviedo");
		setTextField("arrivalZip", "33300");
		setTextField("arrivalStreet", "C/ Llegada");
		setTextField("arrivalDatetime", "21-10-2017T10:40");
		setTextField("description", "Asunto: Examen");
		submit();
		assertTitleEquals("Viajes");
		assertTextPresent("Gijon");
	}

	@Test
	public void resetearDB() {
		beginAt("/dataBaseReset"); // Navegar a la URL
	}

}