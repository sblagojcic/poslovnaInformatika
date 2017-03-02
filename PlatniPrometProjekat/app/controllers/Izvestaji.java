package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cfg.Environment;

import com.mysql.jdbc.Connection;


import models.Faktura;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkaFakture;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Izvestaji extends Controller {
	public static void show(String mode){
		List<Faktura>fakture=Faktura.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(fakture, mode);
	}
	
	public static void report1(@Required Date datumPocetka,
			@Required Date datumZavrsetka){
			try {
	        String status = "P";  // i sve vrste
			Map params = new HashMap(1);
			params.put("datumPocetka", datumPocetka);
			params.put("datumZavrsetka", datumZavrsetka);
			JasperPrint jp = JasperFillManager.fillReport( "C://Users/Korisnik/workspacePoslovna/poslovnaInformatika/PlatniPrometProjekat/lib/kif.jasper",params,DriverManager.getConnection("jdbc:mysql://localhost:3306/projekatplay", "root", "1234") );
			JasperViewer.viewReport(jp, false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			show("");
	}	
	public static void report2(@Required Long id){

			show("");
	}	
	
	 static Connection getSimpleConnection() {
		    //See your driver documentation for the proper format of this string :
		    String DB_CONN_STRING = "jdbc:mysql://localhost:3306/projekatplay";
		    //Provided by your driver documentation. In this case, a MySql driver is used : 
		    String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
		    String USER_NAME = "root";
		    String PASSWORD = "1234";
		    
		    Connection result = null;
		    try {
		      Class.forName(DRIVER_CLASS_NAME).newInstance();
		    }
		    catch (Exception ex){
		      log("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
		    }

		    try {
		      result = (Connection) DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
		    }
		    catch (SQLException e){
		      log( "Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
		    }
		    return result;
		  }

		  private static void log(Object aObject){
		    System.out.println(aObject);
		  }
		

	
}
