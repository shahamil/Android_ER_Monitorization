package backend;

import java.io.Serializable;

/**
 * A Representation of a Doctor.
 * @author SameerKhan
 *
 */
public class Doctor extends Person implements Serializable {

	/**
	 * Serial id for Doctor.
	 */
	private static final long serialVersionUID = 3140342762975768985L;
	
	/**
	 * Creates a Doctor with the given name.
	 * @param name
	 */
	public Doctor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Set patient as treated.
	 * @param time
	 * @param patient
	 */
	public void treatPatient(String time, Patient patient){
		patient.getCurrentReport().updateTreated(time, true);
	}
}
