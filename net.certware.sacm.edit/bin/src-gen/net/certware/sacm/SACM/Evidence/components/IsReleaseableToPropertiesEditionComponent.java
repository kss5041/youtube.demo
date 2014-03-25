// Copyright (c) 2013 United States Government as represented by the National Aeronautics and Space Administration.  All rights reserved.
package net.certware.sacm.SACM.Evidence.components;

// Start of user code for imports
import net.certware.sacm.SACM.Evidence.IsReleaseableTo;
import net.certware.sacm.SACM.Evidence.parts.EvidenceViewsRepository;
import net.certware.sacm.SACM.Evidence.parts.IsReleaseableToPropertiesEditionPart;
import net.certware.sacm.SACM.Evidence.parts.NotesPropertiesEditionPart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;


// End of user code

/**
 * @author Kestrel Technology LLC
 * 
 */
public class IsReleaseableToPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The Base part
	 * 
	 */
	private IsReleaseableToPropertiesEditionPart basePart;

	/**
	 * The IsReleaseableToBasePropertiesEditionComponent sub component
	 * 
	 */
	protected IsReleaseableToBasePropertiesEditionComponent isReleaseableToBasePropertiesEditionComponent;

	/**
	 * The Notes part
	 * 
	 */
	private NotesPropertiesEditionPart notesPart;

	/**
	 * The IsReleaseableToNotesPropertiesEditionComponent sub component
	 * 
	 */
	protected IsReleaseableToNotesPropertiesEditionComponent isReleaseableToNotesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param isReleaseableTo the EObject to edit
	 * 
	 */
	public IsReleaseableToPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject isReleaseableTo, String editing_mode) {
		super(editingContext, editing_mode);
		if (isReleaseableTo instanceof IsReleaseableTo) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(isReleaseableTo, PropertiesEditingProvider.class);
			isReleaseableToBasePropertiesEditionComponent = (IsReleaseableToBasePropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, IsReleaseableToBasePropertiesEditionComponent.BASE_PART, IsReleaseableToBasePropertiesEditionComponent.class);
			addSubComponent(isReleaseableToBasePropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(isReleaseableTo, PropertiesEditingProvider.class);
			isReleaseableToNotesPropertiesEditionComponent = (IsReleaseableToNotesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, IsReleaseableToNotesPropertiesEditionComponent.NOTES_PART, IsReleaseableToNotesPropertiesEditionComponent.class);
			addSubComponent(isReleaseableToNotesPropertiesEditionComponent);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      getPropertiesEditionPart(int, java.lang.String)
	 * 
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (IsReleaseableToBasePropertiesEditionComponent.BASE_PART.equals(key)) {
			basePart = (IsReleaseableToPropertiesEditionPart)isReleaseableToBasePropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)basePart;
		}
		if (IsReleaseableToNotesPropertiesEditionComponent.NOTES_PART.equals(key)) {
			notesPart = (NotesPropertiesEditionPart)isReleaseableToNotesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)notesPart;
		}
		return super.getPropertiesEditionPart(kind, key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      setPropertiesEditionPart(java.lang.Object, int,
	 *      org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart)
	 * 
	 */
	public void setPropertiesEditionPart(java.lang.Object key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (EvidenceViewsRepository.IsReleaseableTo.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			basePart = (IsReleaseableToPropertiesEditionPart)propertiesEditionPart;
		}
		if (EvidenceViewsRepository.Notes.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			notesPart = (NotesPropertiesEditionPart)propertiesEditionPart;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(java.lang.Object key, int kind, EObject element, ResourceSet allResource) {
		if (key == EvidenceViewsRepository.IsReleaseableTo.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == EvidenceViewsRepository.Notes.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
