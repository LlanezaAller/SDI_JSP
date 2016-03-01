package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextInElement;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ShareMyTripTests {
	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/IglesiasJavier-LlanezaInigo");
		
	}
	@AfterClass
	public static void cleanUp(){
		beginAt("/dataBaseReset");
	}


	@Test
	public void testListarViajes() {
		beginAt("/"); // Navegar a la URL
		assertTitleEquals("Viajes"); // Comprobar título de la página
		assertTextPresent("ciudad21"); // Comprobamos que aparece el viaje
										// posible
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
		clickLink("crearViaje");
		assertTitleEquals("Crear Viaje");
		setTextField("freeSeats", "2");
		setTextField("totalCost", "5.0");
		setTextField("limitDatetime", "20/10/2015T22:33");
		setTextField("departureCountry", "Spain");
		setTextField("departureState", "Asturias");
		setTextField("departureCity", "Gijon");
		setTextField("departureZip", "33200");
		setTextField("departureStreet", "C/ Salida");
		setTextField("departureDatetime", "21/10/2015T10:00");
		setTextField("arrivalCountry", "Spain");
		setTextField("arrivalState", "Asturias");
		setTextField("arrivalCity", "Oviedo");
		setTextField("arrivalZip", "33300");
		setTextField("arrivalStreet", "C/ Llegada");
		setTextField("arrivalDatetime", "21/10/2015T10:00");
		setTextField("description", "Asunto: Examen");
		submit();
		assertTitleEquals("Viajes");
	}

}