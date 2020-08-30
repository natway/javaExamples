package queuepackage;

public class Person implements Comparable<Person>{
private String firstName =  "BLANK";
private String lastName = "BLANK";
private int age = 0;

public Person(String fName, String lName, int years) {
	firstName = fName;
	lastName = lName;
	age = years;
}
public Person() {
	
}
// required by comparable interface. Allows persons to be sorted by last name
public int compareTo(Person p) {
	return (0 - this.getLastName().compareTo( p.getLastName() ) );
}

@Override
public String toString() {
	return (lastName + ", " + firstName + " - " + age);
}

public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public int getAge() {
	return age;
}
protected void setFirstName(String s){
	firstName = s;
}
protected void setLastName(String s){
	lastName = s;
}
protected void setAge(int i){
	age = i;
}
}
