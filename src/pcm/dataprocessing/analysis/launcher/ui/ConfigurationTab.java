package pcm.dataprocessing.analysis.launcher.ui;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.prolog4j.IProverFactory;
import org.prolog4j.ProverInformation;
import org.prolog4j.Query;
import pcm.dataprocessing.analysis.launcher.constants.Constants;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;
import pcm.dataprocessing.analysis.launcher.delegate.Activator;
import pcm.dataprocessing.analysis.launcher.query.QueryInformation;

/**
 * Main configuration tab for the launch configuration
 * 
 * @author Mirko Sowa
 *
 */
public class ConfigurationTab extends AbstractLaunchConfigurationTab {

	private Composite comp;

	private Text usageText;
	private Text allocText;
	private Text chText;

	private Combo prologCombo;
	private Combo analysisCombo;

	private Group analysisGroup;
	private Group prologGroup;

	private Map<ProverInformation, IProverFactory> proversMap;
	private Map<QueryInformation, Query> queryMap;

	public ConfigurationTab() {
		Activator sharedInstance = Activator.getInstance();
		if (sharedInstance != null) {
			proversMap = sharedInstance.getProverManagerInstance().getProvers();
			queryMap = sharedInstance.getQueryMangerInstance().getQueries();
		} else {
			// TODO empty Map?
		}
	}

	@Override
	public String getName() {
		return Constants.NAME.getConstant();
	}

	@Override
	public String getMessage() {
		return "Please select specified files.";
	}

	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		return true;

	}

	@Override
	public boolean canSave() {
		return !usageText.getText().isEmpty() && !allocText.getText().isEmpty() && !chText.getText().isEmpty();
	}
//TODO add "default" as constant
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(Constants.USAGE_MODEL_LABEL.getConstant(), "");
		configuration.setAttribute(Constants.ALLOCATION_MODEL_LABEL.getConstant(), "");
		configuration.setAttribute(Constants.CHARACTERISTICS_MODEL_LABEL.getConstant(), "");
		configuration.setAttribute(Constants.ANALYSIS_GOAL_LABEL.getConstant(), "default"); // Saving id of
																							// QueryInformation
		configuration.setAttribute(Constants.PROLOG_INTERPRETER_LABEL.getConstant(), "default"); // Saving the ID of the
																									// ProverInformation
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		usageText.setText("");
		allocText.setText("");
		chText.setText("");
		analysisCombo.select(0);
		prologCombo.select(0);

		try {
			usageText.setText(configuration.getAttribute(Constants.USAGE_MODEL_LABEL.getConstant(), ""));
			allocText.setText(configuration.getAttribute(Constants.ALLOCATION_MODEL_LABEL.getConstant(), ""));
			chText.setText(configuration.getAttribute(Constants.CHARACTERISTICS_MODEL_LABEL.getConstant(), ""));

			String analysisConfig = configuration.getAttribute(Constants.ANALYSIS_GOAL_LABEL.getConstant(), "default");
			
			if (!analysisConfig.equals("default")) {
				for (Map.Entry<QueryInformation, Query> entry : queryMap.entrySet()) {
					if ((entry.getKey().getId()).equals(analysisConfig)) {
						analysisCombo.select(analysisCombo.indexOf(entry.getKey().getName()));
					}
					;
				}
			}

			String prologCongfig = configuration.getAttribute(Constants.PROLOG_INTERPRETER_LABEL.getConstant(), "default");

			if (!prologCongfig.equals("default")) {
				for (Map.Entry<ProverInformation, IProverFactory> entry : proversMap.entrySet()) {
					if ((entry.getKey().getId()).equals(prologCongfig)) {
						prologCombo.select(prologCombo.indexOf(entry.getKey().getName()));
					}
					;
				}
			}

		} catch (CoreException e) {
			// ignored
		}

	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(Constants.USAGE_MODEL_LABEL.getConstant(), usageText.getText());
		configuration.setAttribute(Constants.ALLOCATION_MODEL_LABEL.getConstant(), allocText.getText());
		configuration.setAttribute(Constants.CHARACTERISTICS_MODEL_LABEL.getConstant(), chText.getText());

		String analysisName = analysisCombo.getItem(analysisCombo.getSelectionIndex());
		String analysisId = (String) analysisCombo.getData(analysisName);

		for (Map.Entry<QueryInformation, Query> entry : queryMap.entrySet()) {
			if ((entry.getKey().getId()).equals(analysisId)) {
				configuration.setAttribute(Constants.ANALYSIS_GOAL_LABEL.getConstant(), entry.getKey().getId());
			}
		}

		
		String prologName = prologCombo.getItem(prologCombo.getSelectionIndex());
		String prologId = (String) prologCombo.getData(analysisName);

		for (Map.Entry<ProverInformation, IProverFactory> entry : proversMap.entrySet()) {
			if ((entry.getKey().getId()).equals(prologId)) {
				configuration.setAttribute(Constants.PROLOG_INTERPRETER_LABEL.getConstant(), entry.getKey().getId());
			}
		}

	}

	@Override
	public void createControl(Composite parent) {

		final ModifyListener modifyListener = new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				// ConfigurationTab.this.setDirty(true);
				// ConfigurationTab.this.updateLaunchConfigurationDialog();
			}
		};

		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(1, false));

		/* Usage Model */

		usageText = new Text(comp, SWT.BORDER);
		usageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		TabHelper.createFileInputSection(comp, modifyListener, Constants.USAGE_MODEL_LABEL.getConstant(),
				new String[] { "*.usagemodel" }, usageText, Display.getCurrent().getActiveShell(), "");

		/* Allocation-Model */

		allocText = new Text(comp, SWT.BORDER);
		allocText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		TabHelper.createFileInputSection(comp, modifyListener, Constants.ALLOCATION_MODEL_LABEL.getConstant(),
				new String[] { "*.allocation" }, allocText, Display.getCurrent().getActiveShell(), "");

		/* Characteristics-Type-Model */

		chText = new Text(comp, SWT.BORDER);
		chText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		TabHelper.createFileInputSection(comp, modifyListener, Constants.CHARACTERISTICS_MODEL_LABEL.getConstant(),
				new String[] { "*.xmi" }, chText, Display.getCurrent().getActiveShell(), "");

		/* Analysis Goal */

		analysisGroup = new Group(comp, SWT.NONE);
		analysisGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		analysisGroup.setText(Constants.ANALYSIS_GOAL_LABEL.getConstant());
		analysisGroup.setLayout(new GridLayout(1, true));

		analysisCombo = new Combo(analysisGroup, SWT.DROP_DOWN);

		for (Map.Entry<QueryInformation, Query> entry : queryMap.entrySet()) {
			String s = entry.getKey().getName();
			analysisCombo.add(s);
			analysisCombo.setData(s, entry.getKey().getId());
		}

		analysisCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		/* Prolog Interpreter */

		prologGroup = new Group(comp, SWT.NONE);
		prologGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		prologGroup.setText(Constants.PROLOG_INTERPRETER_LABEL.getConstant());
		prologGroup.setLayout(new GridLayout(1, true));

		prologCombo = new Combo(prologGroup, SWT.DROP_DOWN);

		for (Map.Entry<ProverInformation, IProverFactory> entry : proversMap.entrySet()) {
			String s = entry.getKey().getName();
			prologCombo.add(s);
			prologCombo.setData(s, entry.getKey().getId());
		}

		// prologCombo.add(Constants.PROLOG_INTERPRETER_ONE.getConstant());
		prologCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		setControl(comp);

	}

}