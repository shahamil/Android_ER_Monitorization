package backend;

import java.io.Serializable;

/**
 * A Representation of a Person.
 * @author SameerKhan
 *
 */
public abstract class Person implements Serializable{

	/**
	 * Serial id for Person.
	 */
	private static final long serialVersionUID = -7207840227074407999L;
	private String name; // Name of the person.
	
	/**
	 * Creates a Person with the given name.
	 * @param name
	 */
	public Person(String name){
		this.name = name ; 
	}
	
	/**
	 * Returns name of the person.
	 * @return
	 */
	public String getName(){
		return this.name;
	}
}
