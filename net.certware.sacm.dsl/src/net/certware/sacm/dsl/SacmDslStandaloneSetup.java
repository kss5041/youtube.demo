/*
* generated by Xtext
*/
package net.certware.sacm.dsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class SacmDslStandaloneSetup extends SacmDslStandaloneSetupGenerated{

	public static void doSetup() {
		new SacmDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

