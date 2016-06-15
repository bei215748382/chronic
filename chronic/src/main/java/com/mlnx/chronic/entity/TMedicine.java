package com.mlnx.chronic.entity;

public class TMedicine {
    private Integer id;

    private String name; //药品名称

    private String pic;//药品图片

    private String description;//药品描述

    private String type;//药品类型
    
    private String adr;//不良反应
    
    private String incompatibility;//禁忌
    
    private String factory;//药厂公司
    
    private String permit;//国药准字号
    
    private String indications;//使用症状
    
    private String store;//存储
    
    private String constitute;//药品组成
    
    private String temp;//临时变量
    
    private String price;//价格
    
    private String sum;//总量
    
    private String validTime;//有效期
    
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "TMedicine [id=" + id + ", name=" + name + ", pic=" + pic
                + ", description=" + description + ", type=" + type + ", adr="
                + adr + ", incompatibility=" + incompatibility + ", factory="
                + factory + ", permit=" + permit + ", indications="
                + indications + ", store=" + store + ", constitute="
                + constitute + ", temp=" + temp + ", price=" + price + ", sum="
                + sum + ", validTime=" + validTime + "]";
    }

}