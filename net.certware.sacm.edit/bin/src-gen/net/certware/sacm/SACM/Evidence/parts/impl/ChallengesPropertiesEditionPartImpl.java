// Copyright (c) 2013 United States Government as represented by the National Aeronautics and Space Administration.  All rights reserved.
package net.certware.sacm.SACM.Evidence.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.List;

import net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart;
import net.certware.sacm.SACM.Evidence.parts.EvidenceViewsRepository;

import net.certware.sacm.SACM.Evidence.providers.EvidenceMessages;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

// End of user code

/**
 * @author Kestrel Technology LLC
 * 
 */
public class ChallengesPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ChallengesPropertiesEditionPart {

	protected Text id;
	protected ReferencesTable timing;
	protected List<ViewerFilter> timingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> timingFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable custody;
	protected List<ViewerFilter> custodyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> custodyFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable provenance;
	protected List<ViewerFilter> provenanceBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> provenanceFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable event;
	protected List<ViewerFilter> eventBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> eventFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable attribute;
	protected List<ViewerFilter> attributeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> attributeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer assertion;
	protected EObjectFlatComboViewer subject;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ChallengesPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		CompositionSequence challengesStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = challengesStep.addStep(EvidenceViewsRepository.Challenges.Properties.class);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.id);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.timing);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.custody);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.provenance);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.event);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.attribute);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.assertion);
		propertiesStep.addStep(EvidenceViewsRepository.Challenges.Properties.subject);
		
		
		composer = new PartComposer(challengesStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == EvidenceViewsRepository.Challenges.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.id) {
					return createIdText(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.timing) {
					return createTimingAdvancedTableComposition(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.custody) {
					return createCustodyAdvancedTableComposition(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.provenance) {
					return createProvenanceAdvancedTableComposition(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.event) {
					return createEventAdvancedTableComposition(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.attribute) {
					return createAttributeAdvancedTableComposition(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.assertion) {
					return createAssertionFlatComboViewer(parent);
				}
				if (key == EvidenceViewsRepository.Challenges.Properties.subject) {
					return createSubjectFlatComboViewer(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(EvidenceMessages.ChallengesPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createIdText(Composite parent) {
		createDescription(parent, EvidenceViewsRepository.Challenges.Properties.id, EvidenceMessages.ChallengesPropertiesEditionPart_IdLabel);
		id = SWTUtils.createScrollableText(parent, SWT.BORDER);
		GridData idData = new GridData(GridData.FILL_HORIZONTAL);
		id.setLayoutData(idData);
		id.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.id, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
			}

		});
		id.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.id, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
				}
			}

		});
		EditingUtils.setID(id, EvidenceViewsRepository.Challenges.Properties.id);
		EditingUtils.setEEFtype(id, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.id, EvidenceViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		// Start of user code for createIdText

		// End of user code
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createTimingAdvancedTableComposition(Composite parent) {
		this.timing = new ReferencesTable(getDescription(EvidenceViewsRepository.Challenges.Properties.timing, EvidenceMessages.ChallengesPropertiesEditionPart_TimingLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.timing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				timing.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.timing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				timing.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.timing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				timing.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.timing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				timing.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.timingFilters) {
			this.timing.addFilter(filter);
		}
		this.timing.setHelpText(propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.timing, EvidenceViewsRepository.SWT_KIND));
		this.timing.createControls(parent);
		this.timing.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.timing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData timingData = new GridData(GridData.FILL_HORIZONTAL);
		timingData.horizontalSpan = 3;
		this.timing.setLayoutData(timingData);
		this.timing.setLowerBound(0);
		this.timing.setUpperBound(-1);
		timing.setID(EvidenceViewsRepository.Challenges.Properties.timing);
		timing.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createTimingAdvancedTableComposition

		// End of user code
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createCustodyAdvancedTableComposition(Composite parent) {
		this.custody = new ReferencesTable(getDescription(EvidenceViewsRepository.Challenges.Properties.custody, EvidenceMessages.ChallengesPropertiesEditionPart_CustodyLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.custody, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				custody.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.custody, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				custody.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.custody, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				custody.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.custody, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				custody.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.custodyFilters) {
			this.custody.addFilter(filter);
		}
		this.custody.setHelpText(propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.custody, EvidenceViewsRepository.SWT_KIND));
		this.custody.createControls(parent);
		this.custody.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.custody, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData custodyData = new GridData(GridData.FILL_HORIZONTAL);
		custodyData.horizontalSpan = 3;
		this.custody.setLayoutData(custodyData);
		this.custody.setLowerBound(0);
		this.custody.setUpperBound(-1);
		custody.setID(EvidenceViewsRepository.Challenges.Properties.custody);
		custody.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createCustodyAdvancedTableComposition

		// End of user code
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createProvenanceAdvancedTableComposition(Composite parent) {
		this.provenance = new ReferencesTable(getDescription(EvidenceViewsRepository.Challenges.Properties.provenance, EvidenceMessages.ChallengesPropertiesEditionPart_ProvenanceLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.provenance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				provenance.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.provenance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				provenance.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.provenance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				provenance.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.provenance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				provenance.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.provenanceFilters) {
			this.provenance.addFilter(filter);
		}
		this.provenance.setHelpText(propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.provenance, EvidenceViewsRepository.SWT_KIND));
		this.provenance.createControls(parent);
		this.provenance.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.provenance, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData provenanceData = new GridData(GridData.FILL_HORIZONTAL);
		provenanceData.horizontalSpan = 3;
		this.provenance.setLayoutData(provenanceData);
		this.provenance.setLowerBound(0);
		this.provenance.setUpperBound(-1);
		provenance.setID(EvidenceViewsRepository.Challenges.Properties.provenance);
		provenance.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createProvenanceAdvancedTableComposition

		// End of user code
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createEventAdvancedTableComposition(Composite parent) {
		this.event = new ReferencesTable(getDescription(EvidenceViewsRepository.Challenges.Properties.event, EvidenceMessages.ChallengesPropertiesEditionPart_EventLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				event.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				event.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				event.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				event.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.eventFilters) {
			this.event.addFilter(filter);
		}
		this.event.setHelpText(propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.event, EvidenceViewsRepository.SWT_KIND));
		this.event.createControls(parent);
		this.event.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.event, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData eventData = new GridData(GridData.FILL_HORIZONTAL);
		eventData.horizontalSpan = 3;
		this.event.setLayoutData(eventData);
		this.event.setLowerBound(0);
		this.event.setUpperBound(-1);
		event.setID(EvidenceViewsRepository.Challenges.Properties.event);
		event.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createEventAdvancedTableComposition

		// End of user code
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createAttributeAdvancedTableComposition(Composite parent) {
		this.attribute = new ReferencesTable(getDescription(EvidenceViewsRepository.Challenges.Properties.attribute, EvidenceMessages.ChallengesPropertiesEditionPart_AttributeLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.attribute, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				attribute.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.attribute, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				attribute.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.attribute, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				attribute.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.attribute, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				attribute.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.attributeFilters) {
			this.attribute.addFilter(filter);
		}
		this.attribute.setHelpText(propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.attribute, EvidenceViewsRepository.SWT_KIND));
		this.attribute.createControls(parent);
		this.attribute.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.attribute, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData attributeData = new GridData(GridData.FILL_HORIZONTAL);
		attributeData.horizontalSpan = 3;
		this.attribute.setLayoutData(attributeData);
		this.attribute.setLowerBound(0);
		this.attribute.setUpperBound(-1);
		attribute.setID(EvidenceViewsRepository.Challenges.Properties.attribute);
		attribute.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createAttributeAdvancedTableComposition

		// End of user code
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createAssertionFlatComboViewer(Composite parent) {
		createDescription(parent, EvidenceViewsRepository.Challenges.Properties.assertion, EvidenceMessages.ChallengesPropertiesEditionPart_AssertionLabel);
		assertion = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(EvidenceViewsRepository.Challenges.Properties.assertion, EvidenceViewsRepository.SWT_KIND));
		assertion.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		assertion.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.assertion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getAssertion()));
			}

		});
		GridData assertionData = new GridData(GridData.FILL_HORIZONTAL);
		assertion.setLayoutData(assertionData);
		assertion.setID(EvidenceViewsRepository.Challenges.Properties.assertion);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.assertion, EvidenceViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		// Start of user code for createAssertionFlatComboViewer

		// End of user code
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createSubjectFlatComboViewer(Composite parent) {
		createDescription(parent, EvidenceViewsRepository.Challenges.Properties.subject, EvidenceMessages.ChallengesPropertiesEditionPart_SubjectLabel);
		subject = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(EvidenceViewsRepository.Challenges.Properties.subject, EvidenceViewsRepository.SWT_KIND));
		subject.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		subject.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChallengesPropertiesEditionPartImpl.this, EvidenceViewsRepository.Challenges.Properties.subject, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSubject()));
			}

		});
		GridData subjectData = new GridData(GridData.FILL_HORIZONTAL);
		subject.setLayoutData(subjectData);
		subject.setID(EvidenceViewsRepository.Challenges.Properties.subject);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EvidenceViewsRepository.Challenges.Properties.subject, EvidenceViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		// Start of user code for createSubjectFlatComboViewer

		// End of user code
		return parent;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#getId()
	 * 
	 */
	public String getId() {
		return id.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#setId(String newValue)
	 * 
	 */
	public void setId(String newValue) {
		if (newValue != null) {
			id.setText(newValue);
		} else {
			id.setText(""); //$NON-NLS-1$
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.id);
		if (eefElementEditorReadOnlyState && id.isEnabled()) {
			id.setEnabled(false);
			id.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !id.isEnabled()) {
			id.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initTiming(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initTiming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		timing.setContentProvider(contentProvider);
		timing.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.timing);
		if (eefElementEditorReadOnlyState && timing.isEnabled()) {
			timing.setEnabled(false);
			timing.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !timing.isEnabled()) {
			timing.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#updateTiming()
	 * 
	 */
	public void updateTiming() {
	timing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterTiming(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTiming(ViewerFilter filter) {
		timingFilters.add(filter);
		if (this.timing != null) {
			this.timing.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterTiming(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTiming(ViewerFilter filter) {
		timingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#isContainedInTimingTable(EObject element)
	 * 
	 */
	public boolean isContainedInTimingTable(EObject element) {
		return ((ReferencesTableSettings)timing.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initCustody(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initCustody(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		custody.setContentProvider(contentProvider);
		custody.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.custody);
		if (eefElementEditorReadOnlyState && custody.isEnabled()) {
			custody.setEnabled(false);
			custody.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !custody.isEnabled()) {
			custody.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#updateCustody()
	 * 
	 */
	public void updateCustody() {
	custody.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterCustody(ViewerFilter filter)
	 * 
	 */
	public void addFilterToCustody(ViewerFilter filter) {
		custodyFilters.add(filter);
		if (this.custody != null) {
			this.custody.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterCustody(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToCustody(ViewerFilter filter) {
		custodyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#isContainedInCustodyTable(EObject element)
	 * 
	 */
	public boolean isContainedInCustodyTable(EObject element) {
		return ((ReferencesTableSettings)custody.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initProvenance(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initProvenance(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		provenance.setContentProvider(contentProvider);
		provenance.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.provenance);
		if (eefElementEditorReadOnlyState && provenance.isEnabled()) {
			provenance.setEnabled(false);
			provenance.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !provenance.isEnabled()) {
			provenance.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#updateProvenance()
	 * 
	 */
	public void updateProvenance() {
	provenance.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterProvenance(ViewerFilter filter)
	 * 
	 */
	public void addFilterToProvenance(ViewerFilter filter) {
		provenanceFilters.add(filter);
		if (this.provenance != null) {
			this.provenance.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterProvenance(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToProvenance(ViewerFilter filter) {
		provenanceBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#isContainedInProvenanceTable(EObject element)
	 * 
	 */
	public boolean isContainedInProvenanceTable(EObject element) {
		return ((ReferencesTableSettings)provenance.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initEvent(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initEvent(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		event.setContentProvider(contentProvider);
		event.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.event);
		if (eefElementEditorReadOnlyState && event.isEnabled()) {
			event.setEnabled(false);
			event.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !event.isEnabled()) {
			event.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#updateEvent()
	 * 
	 */
	public void updateEvent() {
	event.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterEvent(ViewerFilter filter)
	 * 
	 */
	public void addFilterToEvent(ViewerFilter filter) {
		eventFilters.add(filter);
		if (this.event != null) {
			this.event.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterEvent(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter) {
		eventBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#isContainedInEventTable(EObject element)
	 * 
	 */
	public boolean isContainedInEventTable(EObject element) {
		return ((ReferencesTableSettings)event.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initAttribute(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initAttribute(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		attribute.setContentProvider(contentProvider);
		attribute.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.attribute);
		if (eefElementEditorReadOnlyState && attribute.isEnabled()) {
			attribute.setEnabled(false);
			attribute.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !attribute.isEnabled()) {
			attribute.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#updateAttribute()
	 * 
	 */
	public void updateAttribute() {
	attribute.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterAttribute(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAttribute(ViewerFilter filter) {
		attributeFilters.add(filter);
		if (this.attribute != null) {
			this.attribute.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterAttribute(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAttribute(ViewerFilter filter) {
		attributeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#isContainedInAttributeTable(EObject element)
	 * 
	 */
	public boolean isContainedInAttributeTable(EObject element) {
		return ((ReferencesTableSettings)attribute.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#getAssertion()
	 * 
	 */
	public EObject getAssertion() {
		if (assertion.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) assertion.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initAssertion(EObjectFlatComboSettings)
	 */
	public void initAssertion(EObjectFlatComboSettings settings) {
		assertion.setInput(settings);
		if (current != null) {
			assertion.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.assertion);
		if (eefElementEditorReadOnlyState && assertion.isEnabled()) {
			assertion.setEnabled(false);
			assertion.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !assertion.isEnabled()) {
			assertion.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#setAssertion(EObject newValue)
	 * 
	 */
	public void setAssertion(EObject newValue) {
		if (newValue != null) {
			assertion.setSelection(new StructuredSelection(newValue));
		} else {
			assertion.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.assertion);
		if (eefElementEditorReadOnlyState && assertion.isEnabled()) {
			assertion.setEnabled(false);
			assertion.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !assertion.isEnabled()) {
			assertion.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#setAssertionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAssertionButtonMode(ButtonsModeEnum newValue) {
		assertion.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterAssertion(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAssertion(ViewerFilter filter) {
		assertion.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterAssertion(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAssertion(ViewerFilter filter) {
		assertion.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#getSubject()
	 * 
	 */
	public EObject getSubject() {
		if (subject.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) subject.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#initSubject(EObjectFlatComboSettings)
	 */
	public void initSubject(EObjectFlatComboSettings settings) {
		subject.setInput(settings);
		if (current != null) {
			subject.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.subject);
		if (eefElementEditorReadOnlyState && subject.isEnabled()) {
			subject.setEnabled(false);
			subject.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !subject.isEnabled()) {
			subject.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#setSubject(EObject newValue)
	 * 
	 */
	public void setSubject(EObject newValue) {
		if (newValue != null) {
			subject.setSelection(new StructuredSelection(newValue));
		} else {
			subject.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(EvidenceViewsRepository.Challenges.Properties.subject);
		if (eefElementEditorReadOnlyState && subject.isEnabled()) {
			subject.setEnabled(false);
			subject.setToolTipText(EvidenceMessages.Challenges_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !subject.isEnabled()) {
			subject.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#setSubjectButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSubjectButtonMode(ButtonsModeEnum newValue) {
		subject.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addFilterSubject(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSubject(ViewerFilter filter) {
		subject.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see net.certware.sacm.SACM.Evidence.parts.ChallengesPropertiesEditionPart#addBusinessFilterSubject(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSubject(ViewerFilter filter) {
		subject.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return EvidenceMessages.Challenges_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
