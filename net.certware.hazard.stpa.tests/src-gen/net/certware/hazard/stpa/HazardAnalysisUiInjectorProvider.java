/*
* generated by Xtext
*/
package net.certware.hazard.stpa;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class HazardAnalysisUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return net.certware.hazard.stpa.ui.internal.HazardAnalysisActivator.getInstance().getInjector("net.certware.hazard.stpa.HazardAnalysis");
	}
	
}
