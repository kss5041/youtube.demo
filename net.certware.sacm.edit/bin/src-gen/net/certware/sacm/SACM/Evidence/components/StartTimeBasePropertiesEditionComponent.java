// Copyright (c) 2013 United States Government as represented by the National Aeronautics and Space Administration.  All rights reserved.
package net.certware.sacm.SACM.Evidence.components;

// Start of user code for imports
import net.certware.sacm.SACM.Datetime;
import net.certware.sacm.SACM.SACMFactory;
import net.certware.sacm.SACM.SACMPackage;
import net.certware.sacm.SACM.Evidence.CustodyProperty;
import net.certware.sacm.SACM.Evidence.EvidenceEvent;
import net.certware.sacm.SACM.Evidence.EvidencePackage;
import net.certware.sacm.SACM.Evidence.Provenance;
import net.certware.sacm.SACM.Evidence.StartTime;
import net.certware.sacm.SACM.Evidence.TimingProperty;
import net.certware.sacm.SACM.Evidence.parts.EvidenceViewsRepository;
import net.certware.sacm.SACM.Evidence.parts.StartTimePropertiesEditionPart;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


// End of user code

/**
 * @author Kestrel Technology LLC
 * 
 */
public class StartTimeBasePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for timing ReferencesTable
	 */
	protected ReferencesTableSettings timingSettings;
	
	/**
	 * Settings for custody ReferencesTable
	 */
	protected ReferencesTableSettings custodySettings;
	
	/**
	 * Settings for provenance ReferencesTable
	 */
	protected ReferencesTableSettings provenanceSettings;
	
	/**
	 * Settings for event ReferencesTable
	 */
	protected ReferencesTableSettings eventSettings;
	
	/**
	 * Settings for datetime EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings datetimeSettings;
	
	
	/**
	 * Default constructor
	 * 
	 */
	public StartTimeBasePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject startTime, String editing_mode) {
		super(editingContext, startTime, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = EvidenceViewsRepository.class;
		partKey = EvidenceViewsRepository.StartTime.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final StartTime startTime = (StartTime)elt;
			final StartTimePropertiesEditionPart basePart = (StartTimePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.id))
				basePart.setId(EEFConverterUtil.convertToString(SACMPackage.Literals.STRING, startTime.getId()));
			
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.timing)) {
				timingSettings = new ReferencesTableSettings(startTime, EvidencePackage.eINSTANCE.getEvidenceElement_Timing());
				basePart.initTiming(timingSettings);
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.custody)) {
				custodySettings = new ReferencesTableSettings(startTime, EvidencePackage.eINSTANCE.getEvidenceElement_Custody());
				basePart.initCustody(custodySettings);
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.provenance)) {
				provenanceSettings = new ReferencesTableSettings(startTime, EvidencePackage.eINSTANCE.getEvidenceElement_Provenance());
				basePart.initProvenance(provenanceSettings);
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.event)) {
				eventSettings = new ReferencesTableSettings(startTime, EvidencePackage.eINSTANCE.getEvidenceElement_Event());
				basePart.initEvent(eventSettings);
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.datetime)) {
				// init part
				datetimeSettings = new EObjectFlatComboSettings(startTime, EvidencePackage.eINSTANCE.getStartTime_Datetime());
				basePart.initDatetime(datetimeSettings);
				// set the button mode
				basePart.setDatetimeButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.timing)) {
				basePart.addFilterToTiming(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof TimingProperty); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for timing
				// End of user code
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.custody)) {
				basePart.addFilterToCustody(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof CustodyProperty); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for custody
				// End of user code
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.provenance)) {
				basePart.addFilterToProvenance(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Provenance); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for provenance
				// End of user code
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.event)) {
				basePart.addFilterToEvent(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof EvidenceEvent); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for event
				// End of user code
			}
			if (isAccessible(EvidenceViewsRepository.StartTime.Properties.datetime)) {
				basePart.addFilterToDatetime(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Datetime);
					}
					
				});
				// Start of user code for additional businessfilters for datetime
				// End of user code
			}
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}









	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.id) {
			return SACMPackage.eINSTANCE.getModelElement_Id();
		}
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.timing) {
			return EvidencePackage.eINSTANCE.getEvidenceElement_Timing();
		}
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.custody) {
			return EvidencePackage.eINSTANCE.getEvidenceElement_Custody();
		}
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.provenance) {
			return EvidencePackage.eINSTANCE.getEvidenceElement_Provenance();
		}
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.event) {
			return EvidencePackage.eINSTANCE.getEvidenceElement_Event();
		}
		if (editorKey == EvidenceViewsRepository.StartTime.Properties.datetime) {
			return EvidencePackage.eINSTANCE.getStartTime_Datetime();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		StartTime startTime = (StartTime)semanticObject;
		if (EvidenceViewsRepository.StartTime.Properties.id == event.getAffectedEditor()) {
			startTime.setId((java.lang.String)EEFConverterUtil.createFromString(SACMPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (EvidenceViewsRepository.StartTime.Properties.timing == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, timingSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				timingSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				timingSettings.move(event.getNewIndex(), (TimingProperty) event.getNewValue());
			}
		}
		if (EvidenceViewsRepository.StartTime.Properties.custody == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, custodySettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				custodySettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				custodySettings.move(event.getNewIndex(), (CustodyProperty) event.getNewValue());
			}
		}
		if (EvidenceViewsRepository.StartTime.Properties.provenance == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, provenanceSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				provenanceSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				provenanceSettings.move(event.getNewIndex(), (Provenance) event.getNewValue());
			}
		}
		if (EvidenceViewsRepository.StartTime.Properties.event == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, eventSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				eventSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				eventSettings.move(event.getNewIndex(), (EvidenceEvent) event.getNewValue());
			}
		}
		if (EvidenceViewsRepository.StartTime.Properties.datetime == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				datetimeSettings.setToReference((Datetime)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Datetime eObject = SACMFactory.eINSTANCE.createDatetime();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				datetimeSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			StartTimePropertiesEditionPart basePart = (StartTimePropertiesEditionPart)editingPart;
			if (SACMPackage.eINSTANCE.getModelElement_Id().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(EvidenceViewsRepository.StartTime.Properties.id)) {
				if (msg.getNewValue() != null) {
					basePart.setId(EcoreUtil.convertToString(SACMPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setId("");
				}
			}
			if (EvidencePackage.eINSTANCE.getEvidenceElement_Timing().equals(msg.getFeature()) && isAccessible(EvidenceViewsRepository.StartTime.Properties.timing))
				basePart.updateTiming();
			if (EvidencePackage.eINSTANCE.getEvidenceElement_Custody().equals(msg.getFeature()) && isAccessible(EvidenceViewsRepository.StartTime.Properties.custody))
				basePart.updateCustody();
			if (EvidencePackage.eINSTANCE.getEvidenceElement_Provenance().equals(msg.getFeature()) && isAccessible(EvidenceViewsRepository.StartTime.Properties.provenance))
				basePart.updateProvenance();
			if (EvidencePackage.eINSTANCE.getEvidenceElement_Event().equals(msg.getFeature()) && isAccessible(EvidenceViewsRepository.StartTime.Properties.event))
				basePart.updateEvent();
			if (EvidencePackage.eINSTANCE.getStartTime_Datetime().equals(msg.getFeature()) && basePart != null && isAccessible(EvidenceViewsRepository.StartTime.Properties.datetime))
				basePart.setDatetime((EObject)msg.getNewValue());
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			SACMPackage.eINSTANCE.getModelElement_Id(),
			EvidencePackage.eINSTANCE.getEvidenceElement_Timing(),
			EvidencePackage.eINSTANCE.getEvidenceElement_Custody(),
			EvidencePackage.eINSTANCE.getEvidenceElement_Provenance(),
			EvidencePackage.eINSTANCE.getEvidenceElement_Event(),
			EvidencePackage.eINSTANCE.getStartTime_Datetime()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == EvidenceViewsRepository.StartTime.Properties.id || key == EvidenceViewsRepository.StartTime.Properties.datetime;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (EvidenceViewsRepository.StartTime.Properties.id == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(SACMPackage.eINSTANCE.getModelElement_Id().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(SACMPackage.eINSTANCE.getModelElement_Id().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

}
