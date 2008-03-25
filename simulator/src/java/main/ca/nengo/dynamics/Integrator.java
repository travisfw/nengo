/*
 * Created on 1-Jun-2006
 */
package ca.nengo.dynamics;

import java.io.Serializable;

import ca.nengo.util.TimeSeries;

/**
 * A numerical integrator of ordinary differential equations.  
 *  
 * @author Bryan Tripp
 */
public interface Integrator extends Serializable, Cloneable {

	/**
	 * Integrates the given system over the time span defined by the input time series. 
	 * 
	 * @param system The DynamicalSystem to solve. 
	 * @param input Input vector to the system, defined at the desired start and end times 
	 * 		of integration, and optionally at times in between. The way in which the 
	 * 		integrator interpolates between inputs at different times is decided by the 
	 * 		Integrator implementation. 
	 * @return Time series of output vector
	 */
	public TimeSeries integrate(DynamicalSystem system, TimeSeries input);
	
	public Integrator clone() throws CloneNotSupportedException;

	
}