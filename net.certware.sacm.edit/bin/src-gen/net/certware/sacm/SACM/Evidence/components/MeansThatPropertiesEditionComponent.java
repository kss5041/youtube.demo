// Copyright (c) 2013 United States Government as represented by the National Aeronautics and Space Administration.  All rights reserved.
package net.certware.sacm.SACM.Evidence.components;

// Start of user code for imports
import net.certware.sacm.SACM.Evidence.MeansThat;
import net.certware.sacm.SACM.Evidence.parts.EvidenceViewsRepository;
import net.certware.sacm.SACM.Evidence.parts.MeansThatPropertiesEditionPart;
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
public class MeansThatPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The Base part
	 * 
	 */
	private MeansThatPropertiesEditionPart basePart;

	/**
	 * The MeansThatBasePropertiesEditionComponent sub component
	 * 
	 */
	protected MeansThatBasePropertiesEditionComponent meansThatBasePropertiesEditionComponent;

	/**
	 * The Notes part
	 * 
	 */
	private NotesPropertiesEditionPart notesPart;

	/**
	 * The MeansThatNotesPropertiesEditionComponent sub component
	 * 
	 */
	protected MeansThatNotesPropertiesEditionComponent meansThatNotesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param meansThat the EObject to edit
	 * 
	 */
	public MeansThatPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject meansThat, String editing_mode) {
		super(editingContext, editing_mode);
		if (meansThat instanceof MeansThat) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(meansThat, PropertiesEditingProvider.class);
			meansThatBasePropertiesEditionComponent = (MeansThatBasePropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, MeansThatBasePropertiesEditionComponent.BASE_PART, MeansThatBasePropertiesEditionComponent.class);
			addSubComponent(meansThatBasePropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(meansThat, PropertiesEditingProvider.class);
			meansThatNotesPropertiesEditionComponent = (MeansThatNotesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, MeansThatNotesPropertiesEditionComponent.NOTES_PART, MeansThatNotesPropertiesEditionComponent.class);
			addSubComponent(meansThatNotesPropertiesEditionComponent);
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
		if (MeansThatBasePropertiesEditionComponent.BASE_PART.equals(key)) {
			basePart = (MeansThatPropertiesEditionPart)meansThatBasePropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)basePart;
		}
		if (MeansThatNotesPropertiesEditionComponent.NOTES_PART.equals(key)) {
			notesPart = (NotesPropertiesEditionPart)meansThatNotesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
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
		if (EvidenceViewsRepository.MeansThat.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			basePart = (MeansThatPropertiesEditionPart)propertiesEditionPart;
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
		if (key == EvidenceViewsRepository.MeansThat.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == EvidenceViewsRepository.Notes.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
