package com.mlnx.chronic.entity2;

public class TAdminUser {
    private Integer id;

    private String username;

    private String password;
    
    private String role;
    
    private Integer permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "TAdminUser [id=" + id + ", username=" + username
				+ ", password=" + password + ", role=" + role + ", permission="
				+ permission + "]";
	}
    
}