package pcm.dataprocessing.analysis.launcher.ui;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;
import pcm.dataprocessing.analysis.launcher.constants.Constants;
/**
 * Adjudsts a given launch configuration with analysis goal settings.
 * 
 * @author Mirko Sowa
 *
 */
public class AdvancedConfigurationTab extends AbstractLaunchConfigurationTab {

	private Composite comp;
	private Group argAndReturnIndexing;
	private Group optimNegations;
	private Group shortAssign;


	@Override
	public void createControl(Composite parent) {

		//TODO Refractor as True-False Class
		
		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(1, false));

		argAndReturnIndexing = new Group(comp, SWT.BORDER);
		argAndReturnIndexing.setText(Constants.ADV_ARG_AND_RETURN.getConstant());
		argAndReturnIndexing.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button[] argAndReturnRadio = new Button[2];

		argAndReturnRadio[0] = new Button(argAndReturnIndexing, SWT.RADIO);
		argAndReturnRadio[0].setText("False");
		argAndReturnRadio[1] = new Button(argAndReturnIndexing, SWT.RADIO);
		argAndReturnRadio[0].setText("True");


		
	
	
		optimNegations = new Group(comp, SWT.BORDER);
		optimNegations.setText(Constants.ADV_OPTIM_NEGATION.getConstant());
		optimNegations.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button[] optimNegationRadio = new Button[2];

		optimNegationRadio[0] = new Button(optimNegations, SWT.RADIO);
		optimNegationRadio[0].setText("False");
		optimNegationRadio[1] = new Button(optimNegations, SWT.RADIO);
		optimNegationRadio[0].setText("True");

		shortAssign = new Group(comp, SWT.BORDER);
		shortAssign.setText(Constants.ADV_SHORT_ASSIGN.getConstant());
		shortAssign.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button[] shortAssignRadio  = new Button[2];

		shortAssignRadio[0] = new Button(optimNegations, SWT.RADIO);
		shortAssignRadio[0].setText("False");
		shortAssignRadio[1] = new Button(optimNegations, SWT.RADIO);
		shortAssignRadio[0].setText("True");
	
		setControl(comp);

	}


	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(Constants.ADV_ARG_AND_RETURN.getConstant(), false);
		configuration.setAttribute(Constants.ADV_OPTIM_NEGATION.getConstant(), false);
		configuration.setAttribute(Constants.ADV_SHORT_ASSIGN.getConstant(), false);


	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {

	}

	@Override
	public String getName() {
		// TODO remove hardcoded
		return "test";
	}

}