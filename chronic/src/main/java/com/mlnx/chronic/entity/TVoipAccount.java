package com.mlnx.chronic.entity;

public class TVoipAccount {

	private Integer id;

    private String voipAccount;

    private String voipPassword;
    
    private String dateCreated;
    
    private String friendName;
    
    private String subAccountSid;
    
    private String subToken;
    
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoipAccount() {
        return voipAccount;
    }
    
    public void setVoipAccount(String voipAccount) {
        this.voipAccount = voipAccount;
    }

    public String getVoipPassword() {
        return voipPassword;
    }

    public void setVoipPassword(String voipPassword) {
        this.voipPassword = voipPassword;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getSubAccountSid() {
		return subAccountSid;
	}

	public void setSubAccountSid(String subAccountSid) {
		this.subAccountSid = subAccountSid;
	}

	public String getSubToken() {
		return subToken;
	}

	public void setSubToken(String subToken) {
		this.subToken = subToken;
	}

	@Override
	public String toString() {
		return "TVoipAccount [id=" + id + ", voipAccount=" + voipAccount
				+ ", voipPassword=" + voipPassword + ", dateCreated="
				+ dateCreated + ", friendName=" + friendName
				+ ", subAccountSid=" + subAccountSid + ", subToken=" + subToken
				+ ", userId=" + userId + "]";
	}
    
}