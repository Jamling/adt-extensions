/**
 * 
 */
package cn.ieclipse.adt.ext;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.ui.IStartup;

import cn.ieclipse.adt.ext.popup.actions.EditCompAdapterFactory;

/**
 * @author Jamling
 * 
 */
public class Startup implements IStartup {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IStartup#earlyStartup()
     */
    public void earlyStartup() {
        System.out.println("startup");
        IAdapterManager manager = Platform.getAdapterManager();
        IAdapterFactory factory = new EditCompAdapterFactory();
        // manager.registerAdapters(factory, IActionFilter.class);
        manager.registerAdapters(factory, ICompilationUnit.class);
        // manager.registerAdapters(factory, IResource.class);
    }
}
