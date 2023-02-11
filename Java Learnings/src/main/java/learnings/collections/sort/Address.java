package learnings.collections.sort;

public class Address {

	private String city;
	private String streetName;
	private int pincode;

	public Address(String city, String streetName, int pincode) {
		super();
		this.city = city;
		this.streetName = streetName;
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", streetName=" + streetName + ", pincode=" + pincode + "]";
	}

}
