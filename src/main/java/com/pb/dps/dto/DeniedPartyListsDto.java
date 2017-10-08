package com.pb.dps.dto;

public class DeniedPartyListsDto {
	String EntityType= null;
	String Name= null;
	String Address= null;
	public String getEntityType() {
		return EntityType;
	}
	public void setEntityType(String entityType) {
		EntityType = entityType;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "DeniedPartyListsDto [EntityType=" + EntityType + ", Name=" + Name + ", Address=" + Address + "]";
	}
	

}
