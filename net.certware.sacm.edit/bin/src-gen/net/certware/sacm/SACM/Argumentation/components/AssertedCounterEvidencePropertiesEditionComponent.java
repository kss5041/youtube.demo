// Copyright (c) 2013 United States Government as represented by the National Aeronautics and Space Administration.  All rights reserved.
package net.certware.sacm.SACM.Argumentation.components;

// Start of user code for imports
import net.certware.sacm.SACM.Argumentation.AssertedCounterEvidence;
import net.certware.sacm.SACM.Argumentation.parts.ArgumentationViewsRepository;
import net.certware.sacm.SACM.Argumentation.parts.AssertedCounterEvidencePropertiesEditionPart;
import net.certware.sacm.SACM.Argumentation.parts.NotesPropertiesEditionPart;

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
public class AssertedCounterEvidencePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The Base part
	 * 
	 */
	private AssertedCounterEvidencePropertiesEditionPart basePart;

	/**
	 * The AssertedCounterEvidenceBasePropertiesEditionComponent sub component
	 * 
	 */
	protected AssertedCounterEvidenceBasePropertiesEditionComponent assertedCounterEvidenceBasePropertiesEditionComponent;

	/**
	 * The Notes part
	 * 
	 */
	private NotesPropertiesEditionPart notesPart;

	/**
	 * The AssertedCounterEvidenceNotesPropertiesEditionComponent sub component
	 * 
	 */
	protected AssertedCounterEvidenceNotesPropertiesEditionComponent assertedCounterEvidenceNotesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param assertedCounterEvidence the EObject to edit
	 * 
	 */
	public AssertedCounterEvidencePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject assertedCounterEvidence, String editing_mode) {
		super(editingContext, editing_mode);
		if (assertedCounterEvidence instanceof AssertedCounterEvidence) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(assertedCounterEvidence, PropertiesEditingProvider.class);
			assertedCounterEvidenceBasePropertiesEditionComponent = (AssertedCounterEvidenceBasePropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, AssertedCounterEvidenceBasePropertiesEditionComponent.BASE_PART, AssertedCounterEvidenceBasePropertiesEditionComponent.class);
			addSubComponent(assertedCounterEvidenceBasePropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(assertedCounterEvidence, PropertiesEditingProvider.class);
			assertedCounterEvidenceNotesPropertiesEditionComponent = (AssertedCounterEvidenceNotesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, AssertedCounterEvidenceNotesPropertiesEditionComponent.NOTES_PART, AssertedCounterEvidenceNotesPropertiesEditionComponent.class);
			addSubComponent(assertedCounterEvidenceNotesPropertiesEditionComponent);
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
		if (AssertedCounterEvidenceBasePropertiesEditionComponent.BASE_PART.equals(key)) {
			basePart = (AssertedCounterEvidencePropertiesEditionPart)assertedCounterEvidenceBasePropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)basePart;
		}
		if (AssertedCounterEvidenceNotesPropertiesEditionComponent.NOTES_PART.equals(key)) {
			notesPart = (NotesPropertiesEditionPart)assertedCounterEvidenceNotesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
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
		if (ArgumentationViewsRepository.AssertedCounterEvidence.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			basePart = (AssertedCounterEvidencePropertiesEditionPart)propertiesEditionPart;
		}
		if (ArgumentationViewsRepository.Notes.class == key) {
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
		if (key == ArgumentationViewsRepository.AssertedCounterEvidence.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == ArgumentationViewsRepository.Notes.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
