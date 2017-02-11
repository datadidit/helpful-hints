package datadidit.helpful.hints;

import java.util.Properties;
import java.util.logging.Logger;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import CF.DomainManager;
import CF.DomainManagerHelper;

/**
 * Hello world!
 *
 */
public class App {
	Logger logger = Logger.getLogger(App.class.getName());
	private static final String CORBA_NAME_SERVICE = "ORBInitRef.NameService";
	private static final int DEFAULT_NAMESERVICE_PORT = 2809;
	private static final String DEFAULT_HOSTNAME = "localhost";
	private Properties connectionProperties = new Properties();

	public void init() throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName  {
		// connectionProperties.put("com.sun.CORBA.transport.ORBUseNIOSelectToWait","false");
		// connectionProperties.put("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		// connectionProperties.put("org.omg.PortableInterceptor.ORBInitializerClass.standard_init", "org.jacorb.orb.standardInterceptors.IORInterceptorInitializer");
		connectionProperties.put(CORBA_NAME_SERVICE, "corbaname::" + DEFAULT_HOSTNAME + ":" + DEFAULT_NAMESERVICE_PORT);
		// System.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		// System.getProperty("jacorb.classloaderpolicy", "forname");
		logger.info("Hitting init");
		// ClassLoader cl = Thread.currentThread().getContextClassLoader();
		// Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null, connectionProperties);
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		String domainManagerUrl = "REDHAWK_DEV/REDHAWK_DEV";
		DomainManager domainManager = DomainManagerHelper.narrow(ncRef.resolve_str(domainManagerUrl));
		logger.info(domainManager.name());
		// Thread.currentThread().setContextClassLoader(cl);
		logger.info("Able to hit");

		// this.findDomainManagers(ncRef, ncRef, "");
	}
}
