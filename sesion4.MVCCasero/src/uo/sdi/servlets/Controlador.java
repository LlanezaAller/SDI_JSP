package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.AceptarUsuarioAction;
import uo.sdi.acciones.ActionExecuter;
import uo.sdi.acciones.CancelarSolicitudAction;
import uo.sdi.acciones.CancelarViajeAction;
import uo.sdi.acciones.ConseguirViajeAction;
import uo.sdi.acciones.CrearViajeAction;
import uo.sdi.acciones.DataBaseResetAction;
import uo.sdi.acciones.ExcluirUsuarioAction;
import uo.sdi.acciones.ListarMisViajesAction;
import uo.sdi.acciones.ListarRatingsAction;
import uo.sdi.acciones.ListarViajesAction;
import uo.sdi.acciones.LogoutAction;
import uo.sdi.acciones.ModificarDatosAction;
import uo.sdi.acciones.RegistrarseAction;
import uo.sdi.acciones.SolicitarPlazaAction;
import uo.sdi.acciones.ValidarseAction;
import uo.sdi.acciones.ValorarAction;
import uo.sdi.acciones.VerViajeAction;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class Controlador extends javax.servlet.http.HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActionExecuter executor = new ActionExecuter();
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion, objeto Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol, <opcion, <resultado, JSP>>>

	public void init() throws ServletException {  
		crearMapaAcciones();
		crearMapaDeJSP();
    }
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException {
		
		String opcion, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;
		
		try {
			opcion=req.getServletPath().replace(Messages.getString("MapaAcciones0"),Messages.getString("MapaAcciones1")); //$NON-NLS-1$ //$NON-NLS-2$
				
			rolAntes=obtenerRolDeSesion(req);
			
			accion=buscarAccionParaOpcion(rolAntes, opcion);
				
			resultado=executor.execute(accion, req, res);
				
			rolDespues=obtenerRolDeSesion(req);
			
			jspSiguiente=buscarJSPSegun(rolDespues, opcion, resultado);

			req.setAttribute(Messages.getString("MapaAcciones2"), jspSiguiente); //$NON-NLS-1$

		} catch(Exception e) {
			
			req.getSession().invalidate();
			
			Log.error(Messages.getString("MapaAcciones3"),e); //$NON-NLS-1$
			e.printStackTrace();
			Message m = new Message(Message.ERROR, Messages.getString("MapaAcciones4")); //$NON-NLS-1$
			req.setAttribute(Messages.getString("MapaAcciones5"), m); //$NON-NLS-1$
			jspSiguiente=Messages.getString("MapaAcciones6"); //$NON-NLS-1$
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspSiguiente); 
			
		dispatcher.forward(req, res);			
	}			
	
	
	private String obtenerRolDeSesion(HttpServletRequest req) {
		HttpSession sesion=req.getSession();
		if (sesion.getAttribute(Messages.getString("MapaAcciones7"))==null) //$NON-NLS-1$
			return Messages.getString("MapaAcciones8"); //$NON-NLS-1$
		else
			return Messages.getString("MapaAcciones9"); //$NON-NLS-1$
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion) {
		
		Accion accion=mapaDeAcciones.get(rol).get(opcion);
		Log.debug(Messages.getString("MapaAcciones10"),accion,opcion,rol); //$NON-NLS-1$
		return accion;
	}
	
	
	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado) {
		
		String jspSiguiente=mapaDeNavegacion.get(rol).get(opcion).get(resultado);
		Log.debug(Messages.getString("MapaAcciones11"),jspSiguiente,resultado,opcion,rol); //$NON-NLS-1$
		return jspSiguiente;		
	}
		
		
	private void crearMapaAcciones() {
		
		mapaDeAcciones=new HashMap<String,Map<String,Accion>>();
		
		Map<String,Accion> mapaPublico=new HashMap<String,Accion>();
		mapaPublico.put(Messages.getString("MapaAcciones12"), new ValidarseAction()); //$NON-NLS-1$
		mapaPublico.put(Messages.getString("MapaAcciones13"), new RegistrarseAction()); //$NON-NLS-1$
		mapaPublico.put(Messages.getString("MapaAcciones14"), new ListarViajesAction()); //$NON-NLS-1$
		mapaPublico.put(Messages.getString("MapaAcciones15"), new DataBaseResetAction()); //$NON-NLS-1$
		mapaDeAcciones.put(Messages.getString("MapaAcciones16"), mapaPublico); //$NON-NLS-1$
		
		Map<String,Accion> mapaRegistrado=new HashMap<String,Accion>();
		mapaRegistrado.put(Messages.getString("MapaAcciones17"), new ListarViajesAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones18"), new CrearViajeAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones19"), new VerViajeAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones20"), new ValorarAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones21"), new SolicitarPlazaAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones22"), new CancelarSolicitudAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones23"), new AceptarUsuarioAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones24"), new ExcluirUsuarioAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones25"), new ConseguirViajeAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones26"), new CancelarViajeAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones27"), new ListarMisViajesAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones28"), new ListarRatingsAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones29"), new ModificarDatosAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones30"), new LogoutAction()); //$NON-NLS-1$
		mapaRegistrado.put(Messages.getString("MapaAcciones31"), new DataBaseResetAction()); //$NON-NLS-1$
		mapaDeAcciones.put(Messages.getString("MapaAcciones32"), mapaRegistrado); //$NON-NLS-1$
	}
	
	
	private void crearMapaDeJSP() {
				
		mapaDeNavegacion=new HashMap<String,Map<String, Map<String, String>>>();

		// Crear mapas auxiliares vacíos
		Map<String, Map<String, String>> opcionResJSP=new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP=new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put(Messages.getString("MapaAcciones33"),Messages.getString("MapaAcciones34")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones35"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones36"),Messages.getString("MapaAcciones37")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones38"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones39"),Messages.getString("MapaAcciones40")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones41"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones42"),Messages.getString("MapaAcciones43")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones44"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones45"),Messages.getString("MapaAcciones46")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones47"), resJSP); //$NON-NLS-1$
		
		
		mapaDeNavegacion.put(Messages.getString("MapaAcciones48"),opcionResJSP); //$NON-NLS-1$
		
		// Crear mapas auxiliares vacíos
		opcionResJSP=new HashMap<String, Map<String, String>>();
		resJSP=new HashMap<String, String>();
		
		// Mapa de navegación de usuarios registrados
		resJSP.put(Messages.getString("MapaAcciones49"),Messages.getString("MapaAcciones50")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones51"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones52"),Messages.getString("MapaAcciones53")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones54"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones55"),Messages.getString("MapaAcciones56")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones57"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones58"),Messages.getString("MapaAcciones59")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones60"),Messages.getString("MapaAcciones61")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones62"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones63"),Messages.getString("MapaAcciones64")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones65"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones66"),Messages.getString("MapaAcciones67")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones68"),Messages.getString("MapaAcciones69")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones70"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones71"),Messages.getString("MapaAcciones72")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones73"),Messages.getString("MapaAcciones74")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones75"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones76"),Messages.getString("MapaAcciones77")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones78"),Messages.getString("MapaAcciones79")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones80"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones81"),Messages.getString("MapaAcciones82")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones83"),Messages.getString("MapaAcciones84")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones85"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones86"),Messages.getString("MapaAcciones87")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones88"),Messages.getString("MapaAcciones89")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones90"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones91"),Messages.getString("MapaAcciones92")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones93"),Messages.getString("MapaAcciones94")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones95"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones96"),Messages.getString("MapaAcciones97")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones98"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones99"),Messages.getString("MapaAcciones100")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones101"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones102"),Messages.getString("MapaAcciones103")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones104"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones105"),Messages.getString("MapaAcciones106")); //$NON-NLS-1$ //$NON-NLS-2$
		resJSP.put(Messages.getString("MapaAcciones107"),Messages.getString("MapaAcciones108")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones109"), resJSP); //$NON-NLS-1$
		resJSP=new HashMap<String, String>();
		resJSP.put(Messages.getString("MapaAcciones110"),Messages.getString("MapaAcciones111")); //$NON-NLS-1$ //$NON-NLS-2$
		opcionResJSP.put(Messages.getString("MapaAcciones112"), resJSP); //$NON-NLS-1$
		
		mapaDeNavegacion.put(Messages.getString("MapaAcciones113"),opcionResJSP); //$NON-NLS-1$
	}
			
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		doGet(req, res);
	}

}