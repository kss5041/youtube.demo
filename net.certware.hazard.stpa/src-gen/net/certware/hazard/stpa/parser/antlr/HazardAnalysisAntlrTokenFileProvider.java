/*
* generated by Xtext
*/
package net.certware.hazard.stpa.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class HazardAnalysisAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("net/certware/hazard/stpa/parser/antlr/internal/InternalHazardAnalysis.tokens");
	}
}
