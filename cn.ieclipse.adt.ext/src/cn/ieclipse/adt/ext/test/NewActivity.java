/**
 * 
 */
package cn.ieclipse.adt.ext.test;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cn.ieclipse.adt.ext.wizards.NewActivityWizard;

/**
 * @author Jamling
 * 
 */
public class NewActivity {

    /**
     * @param args
     */
    public static void main(String[] args) {
        WizardDialog dialog = new WizardDialog(
                new Shell(),
                new NewActivityWizard(""));

        dialog.open();
        Display.getCurrent().dispose();
    }

}
