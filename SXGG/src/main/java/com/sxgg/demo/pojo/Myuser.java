package com.sxgg.demo.pojo;

public class Myuser {
    private Integer uid;

    private String password;

    private String userName;

    private Integer roleid;
    
    private Myrole roleMyrole;
     

    public Myrole getRoleMyrole() {
		return roleMyrole;
	}

	public void setRoleMyrole(Myrole roleMyrole) {
		this.roleMyrole = roleMyrole;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}