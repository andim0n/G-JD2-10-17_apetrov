package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	String name;
	String mail;
	String password;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserAccount [name=" + name + ", mail=" + mail + ", password=" + password
				+ "]";
	}

}
