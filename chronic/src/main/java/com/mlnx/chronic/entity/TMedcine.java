package com.mlnx.chronic.entity;

public class TMedcine {
    private Integer id;

    private String name;

    private String pic;

    private String description;

    private String type;
    
    private String adr;
    
    private String incompatibility;
    
    private String factory;
    
    private String permit;
    
    private String indications;
    
    private String store;
    
    private String constitute;
    
    private String temp;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getIncompatibility() {
		return incompatibility;
	}

	public void setIncompatibility(String incompatibility) {
		this.incompatibility = incompatibility;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getConstitute() {
		return constitute;
	}

	public void setConstitute(String constitute) {
		this.constitute = constitute;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "TMedcine [id=" + id + ", name=" + name + ", pic=" + pic
				+ ", description=" + description + ", type=" + type + ", adr="
				+ adr + ", incompatibility=" + incompatibility + ", factory="
				+ factory + ", permit=" + permit + ", indications="
				+ indications + ", store=" + store + ", constitute="
				+ constitute + ", temp=" + temp + "]";
	}

}