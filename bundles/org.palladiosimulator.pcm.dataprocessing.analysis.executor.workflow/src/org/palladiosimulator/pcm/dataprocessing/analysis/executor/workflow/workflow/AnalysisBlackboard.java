package org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.IQuery;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.System;
import org.prolog4j.IProverFactory;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import edu.kit.ipd.sdq.dataflow.systemmodel.SystemTranslator;

/**
 * A blackboard implementation, inherits from {@link MDSDBlackboard}, and holds
 * additional {@link System}, {@link SystemTranslator}, {@link IProverFactory}
 * and {@link IQuery}.
 * 
 * @author Mirko Sowa
 *
 */
public class AnalysisBlackboard extends MDSDBlackboard {

	org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.System dataFlowSystemModel = null;
	SystemTranslator systemTranslator = null;
	IProverFactory proverFactory = null;
	IQuery query = null;

	/**
	 * @return the dataFlowSystemModel
	 */
	public org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.System getDataFlowSystemModel() {
		return dataFlowSystemModel;
	}

	/**
	 * @return the systemTranslator
	 */
	public SystemTranslator getSystemTranslator() {
		return systemTranslator;
	}

	/**
	 * @return the proverFactory
	 */
	public IProverFactory getProverFactory() {
		return proverFactory;
	}

	/**
	 * @return the query
	 */
	public IQuery getQuery() {
		return query;
	}

	/**
	 * @param dataFlowSystemModel the dataFlowSystemModel to set
	 */
	public void setDataFlowSystemModel(
			org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.System dataFlowSystemModel) {
		this.dataFlowSystemModel = dataFlowSystemModel;
	}

	/**
	 * @param systemTranslator the systemTranslator to set
	 */
	public void setSystemTranslator(SystemTranslator systemTranslator) {
		this.systemTranslator = systemTranslator;
	}

	/**
	 * @param proverFactory the proverFactory to set
	 */
	public void setProverFactory(IProverFactory proverFactory) {
		this.proverFactory = proverFactory;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(IQuery query) {
		this.query = query;
	}

}