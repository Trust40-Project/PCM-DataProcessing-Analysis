package pcm.dataprocessing.analysis.launcher.delegate;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.prolog4j.manager.IProverManager;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "testDiscovery"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private ServiceReference<IProverManager> proverManagerReference;
	private IProverManager proverManager;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		proverManagerReference = context.getServiceReference(IProverManager.class);
		proverManager = context.getService(proverManagerReference);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		proverManager = null;
		context.ungetService(proverManagerReference);
		
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public IProverManager getProverManager() {
		return proverManager;
	}

}
