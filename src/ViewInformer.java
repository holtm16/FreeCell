/**
 * This class serves as the interface used by AppViewInformer class.
 * @author Ryan Grant and Michael Holt
 */

public interface ViewInformer{

	/**
	 * Makes a move and repaints the panels if the conditions warrant.
	 * @param panel - the panel being pressed
     */
	public void panelPressed(AbstractPanel panel);

}
