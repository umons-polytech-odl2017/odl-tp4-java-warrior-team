package exercise2;

import java.io.Serializable;

public class PhoneNumber implements Serializable{
	private final String number;

	public PhoneNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}
}
