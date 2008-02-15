/*
 * Created on 28-Jan-08
 */
package ca.neo.util;

import ca.neo.model.StructuralException;

/**
 * An object that fires an event when its properties change in such a way that it expects 
 * the user interface to display it differently. This allows the user interface to 
 * update when the object is changed through another means, such as scripting.   
 *   
 * @author Bryan Tripp
 */
public interface VisiblyMutable {

	/**
	 * @param listener Listener to add
	 */
	public void addChangeListener(Listener listener);
	
	/**
	 * @param listener Listener to remove
	 */
	public void removeChangeListener(Listener listener);

	/**
	 * A listener for changes to a VisiblyMutable object. 
	 *   
	 * @author Bryan Tripp
	 */
	public static interface Listener {
		
		/**
		 * @param e An object that has changed in some way (all properties
		 * 		that influence the display of the object should be checked)  
		 */
		public void changed(Event e) throws StructuralException;
	}
	
	/**
	 * Encapsulates a change to a VisiblyMutable object. The event doesn't 
	 * specify which changes occurred, just the object that changed. Therefore
	 * all properties of the object that influence its display should be checked.  
	 *  
	 * @author Bryan Tripp
	 */
	public static interface Event {
		
		/**
		 * @return An object that has changed in some way 
		 */
		public VisiblyMutable getObject();		
	}
	
	/**
	 * Encapsulates a change in the name of a VisiblyMutable object. This event means 
	 * that no other changes to the object have occurred except for the name change.  
	 * 
	 * @author Bryan Tripp
	 */
	public static interface NameChangeEvent extends Event {
		
		/**
		 * @return The previous name of the object
		 */
		public String getOldName();
		
		/**
		 * @return The new name of the object
		 */
		public String getNewName();
	}
	
}
