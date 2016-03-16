package com.mlnx.chronic.entity2;

import java.util.Date;

public class TApp {
    private Integer id;

    private String name;

    private String version;

    private String stable;

    private Date timestamp;

    private String content;

    private String path;
    
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStable() {
        return stable;
    }

    public void setStable(String stable) {
        this.stable = stable;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "TApp [id=" + id + ", name=" + name + ", version=" + version
				+ ", stable=" + stable + ", timestamp=" + timestamp
				+ ", content=" + content + ", path=" + path + "]";
	}
    
}