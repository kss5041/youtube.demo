/*
* generated by Xtext
*/
package net.certware.sacm.dsl;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class SacmDslUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return net.certware.sacm.dsl.ui.internal.SacmDslActivator.getInstance().getInjector("net.certware.sacm.dsl.SacmDsl");
	}
	
}
