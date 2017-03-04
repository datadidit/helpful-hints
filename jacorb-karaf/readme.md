## Jacorb Karaf ##
***

Add necessary CORBA properties to system.properties

	org.omg.CORBA.ORBSingletonClass=org.jacorb.orb.ORBSingleton
	org.omg.PortableInterceptor.ORBInitializerClass.standard_init=org.jacorb.orb.standardInterceptors.IORInterceptorInitializer
	org.omg.CORBA.ORBClass=org.jacorb.orb.ORB
	
Add jacorb.jars to lib/ext

If you want to use the com.sun.ORB then do the following:





	