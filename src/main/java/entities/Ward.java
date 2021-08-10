package entities;

public class Ward {
	private int id;
	private String name;
	private String prefix;
	private int province_id;
	private int district_id;
	public Ward(int id, String name, String prefix, int province_id, int district_id) {
		super();
		this.id = id;
		this.name = name;
		this.prefix = prefix;
		this.province_id = province_id;
		this.district_id = district_id;
	}
	public Ward(int id, String name) {
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
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	
	
}
