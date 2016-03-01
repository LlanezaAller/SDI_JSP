package uo.sdi.tests;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ShareMyTripTests {
	//TODO cambiar el nombre al proyecto
    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8280/IglesiasJavier-LlanezaInigo");
    }

    @Test
    public void testListarViajes() {
        beginAt("/");  // Navegar a la URL
        assertLinkPresent("listarViajes");  // Comprobar que existe el hipervínculo
        clickLink("listarViajes"); // Seguir el hipervínculo

        assertTitleEquals("ShareMyTrip - Listado de viajes");  // Comprobar título de la página
        

        // La base de datos contiene 2 viajes tal y como se entrega
        assertElementPresent("item_0"); // Comprobar elemento presente en la página
        assertElementPresent("item_1"); // Comprobar elemento presente en la página
    }

    @Test
    public void testIniciarSesionConExito() {
    	// Rellenando el formulario HTML
    	
        beginAt("/login.jsp");  // Navegar a la URL
        setTextField("user", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password", "usuario1");
        assertTextInElement("user","usuario1");
        assertTextInElement("password","usuario1");
        submit(); // Enviar formulario
        assertTitleEquals("Viajes");  // Comprobar título de la página
        assertTextPresent( "¡Bienvenido de nuevo, user1!");  // Comprobar cierto elemento contiene cierto texto
    }
    //assertTextInElement("name", "Fernando");  // Comprobar cierto elemento contiene cierto texto
    //assertTextPresent("Es Vd el usuario número:"); // Comprobar cierto texto está presente

    @Test
    public void testIniciarSesionSinExito() {
    	// Rellenando el formulario HTML
        beginAt("/login.jsp");  // Navegar a la URL
    	setTextField("user", "yoNoExisto"); // Rellenar primer campo de formulario
    	submit(); // Enviar formulario
    	assertTitleEquals("Entrar");  // Comprobar título de la página
    }
    @Test
    public void testIniciarSesionConExitoConQueryString() {
    	// Rellenando el formulario HTML
        beginAt("/validarse?nombreUsuario=user2");  // Navegar a la URL
        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
        assertTextInElement("login", "user2");  // Comprobar cierto elemento contiene cierto texto
        assertTextInElement("name", "Luisa");  // Comprobar cierto elemento contiene cierto texto
        assertTextPresent("Es Vd el usuario número:"); // Comprobar cierto texto está presente
    }
    

}