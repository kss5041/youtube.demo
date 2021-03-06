/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Modified for CertWare by Matt Barry
 *******************************************************************************/
package net.certware.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import net.certware.core.UrlBuilder.UrlParameter;

/**
 * URL builder for parameter strings.
 * @author Eclipse Toast
 * @since 1.0
 */
public class UrlBuilder {
	public static class UrlParameter extends Object {
		private String name;
		private String value;

		UrlParameter(String name, String value) {
			super();
			if (name == null)
				throw new IllegalArgumentException("URL name must not be null");
			if (value == null)
				throw new IllegalArgumentException("URL value must not be null");
			this.name = name;
			this.value = value;
		}

		void printOn(StringBuffer buffer) {
			buffer.append(this.name);
			buffer.append('=');
			buffer.append(this.value);
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer(50);
			printOn(buffer);
			String value = buffer.toString();
			return value;
		}
	}

	private String spec;
	private List<UrlParameter> parameters;

	/**
	 * Creates the initial URL string by specification.
	 * Creates an initial parameter list.
	 * @param spec specification string, must not be null
	 */
	public UrlBuilder(String spec) {
		super();
		if (spec == null)
			throw new IllegalArgumentException("URL spec must not be null");
		this.spec = spec;
		parameters = new ArrayList<UrlParameter>(5);
	}

	/**
	 * Adds a boolean valued parameter
	 * @param name parameter name
	 * @param value parameter value
	 */
	public void addParameter(String name, boolean value) {
		String valueString = String.valueOf(value);
		addParameter(name, valueString);
	}

	/**
	 * Adds an integer valued parameter
	 * @param name parameter name
	 * @param value parameter value
	 */
	public void addParameter(String name, int value) {
		String valueString = String.valueOf(value);
		addParameter(name, valueString);
	}

	/**
	 * Adds a string valued parameter
	 * @param name parameter name
	 * @param value parameter value
	 */
	public void addParameter(String name, String value) {
		UrlParameter parameter = new UrlParameter(name, value);
		addParameter(parameter);
	}

	private void addParameter(UrlParameter parameter) {
		synchronized (parameters) {
			parameters.add(parameter);
		}
	}

	/**
	 * Appends a path to the specification string
	 * @param path path to add, inserting slash prefix if necessary
	 */
	public void appendPath(String path) {
		// append to the spec, taking care of the slashes properly
		if (!spec.endsWith("/"))
			spec += '/';
		spec += path;
	}

	/**
	 * Gets the path given the URL as built.
	 * @return path
	 * @throws MalformedURLException
	 */
	public String getPath() throws MalformedURLException {
		// remove protocol, host & port, return a path with leading / and without trailing /
		String answer = toUrl().getFile();
		if (!answer.startsWith("/"))
			answer = "/" + answer;
		if (answer.endsWith("/"))
			answer = answer.substring(0, answer.length() - 1);
		if (answer.length() == 0)
			return "/";
		return answer;
	}

	private void printOn(StringBuffer buffer) {
		buffer.append(spec);
		printParametersOn(buffer);
	}

	private void printParametersOn(StringBuffer buffer) {
		synchronized (parameters) {
			boolean empty = parameters.isEmpty();
			if (empty == true)
				return;
			buffer.append('?');
			Iterator<UrlParameter> iterator = parameters.iterator();
			while (iterator.hasNext() == true) {
				UrlParameter parameter = (UrlParameter) iterator.next();
				parameter.printOn(buffer);
				boolean last = iterator.hasNext() == false;
				if (last == false) {
					buffer.append('&');
				}
			}
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(150);
		printOn(buffer);
		String value = buffer.toString();
		return value;
	}

	/**
	 * Converts the URL to a string.
	 * @return string value
	 * @throws MalformedURLException
	 */
	public URL toUrl() throws MalformedURLException {
		String value = toString();
		URL url = new URL(value);
		return url;
	}
}
