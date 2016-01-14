package com.mlnx.chronic.entity;

public class TMedcine {
    private Integer id;

    private String name;

    private String pic;

    private String description;

    private String type;
    
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

	@Override
	public String toString() {
		return "TMedcine [id=" + id + ", name=" + name + ", pic=" + pic
				+ ", description=" + description + ", type=" + type + "]";
	}

}