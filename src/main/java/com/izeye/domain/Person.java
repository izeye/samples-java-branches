package com.izeye.domain;

/**
 * Person.
 *
 * @author Johnny Lim
 */
public class Person {

	private final String firstName;
	private final String lastName;
	private final Integer age;

	/**
	 * Create a {@code Person} instance.
	 *
	 * @param firstName first name
	 * @param lastName last name
	 * @deprecated Use {@link #Person(String, String, Integer)} instead.
	 */
	@Deprecated
	public Person(String firstName, String lastName) {
		this(firstName, lastName, null);
	}

	/**
	 * Create a {@code Person} instance.
	 *
	 * @param firstName first name
	 * @param lastName last name
	 * @param age age
	 */
	public Person(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	/**
	 * Return first name.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Return last name.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Return age.
	 *
	 * @return age
	 */
	public Integer getAge() {
		return this.age;
	}

}
