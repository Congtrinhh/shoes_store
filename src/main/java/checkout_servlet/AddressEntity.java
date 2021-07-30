package checkout_servlet;

// thực thể đại diện cho tỉnh/tp hoặc xã/phường hoặc quận/huyện đều được
public class AddressEntity { 
	private int id;
	private String name;
	public AddressEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
