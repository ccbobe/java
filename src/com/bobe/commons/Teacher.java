package com.bobe.commons;

import java.util.Arrays;
import java.util.List;

public class Teacher {
    private int id;
    private String name;
    private int age;
    private char sex;
    private String addr;
    private String major;
    private int[] qq;
    
    private List<String> baby;
    
    
	public List<String> getBaby() {
		return baby;
	}
	public void setBaby(List<String> baby) {
		this.baby = baby;
	}
	public int[] getQq() {
		return qq;
	}
	public void setQq(int[] qq) {
		this.qq = qq;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public char getSex() {
		return sex;
	}
	public String getAddr() {
		return addr;
	}
	public String getMajor() {
		return major;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + age;
		result = prime * result + id;
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sex;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Teacher))
			return false;
		Teacher other = (Teacher) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}
    
  
    
    @Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", addr=" + addr + ", major=" + major
				+ ", qq=" + Arrays.toString(qq) + ", baby=" + baby + "]";
	}
	public String sayHello(String say){
    	return say;
    }
}
