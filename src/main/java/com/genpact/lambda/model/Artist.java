package com.genpact.lambda.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Artist {
	private String name;
	private Integer age;
	private String address;
	private List<Works> works;
	private BigDecimal big;
	private Date date;

	

	public Artist() {
	}

	public Artist(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Works> getWorks() {
		return works;
	}

	public void setWorks(List<Works> works) {
		this.works = works;
	}

	public BigDecimal getBig() {
		return big;
	}

	public void setBig(BigDecimal big) {
		this.big = big;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Artist(String name, int age, String address, List<Works> works) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.works = works;
	}

	
	
	@Override
	public String toString() {
		return "Artist [name=" + name + ", age=" + age + ", address=" + address + ", works=" + works + ", big=" + big + ", date=" + date + "]";
	}



	public class Works {
		private String name;
		private Date publishTime;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getPublishTime() {
			return publishTime;
		}

		public void setPublishTime(Date publishTime) {
			this.publishTime = publishTime;
		}

		public Works(String name, Date publishTime) {
			super();
			this.name = name;
			this.publishTime = publishTime;
		}
		
		

		public Works() {
		}

		@Override
		public String toString() {
			return "Works [name=" + name + ", publishTime=" + publishTime + "]";
		}
		
		

	}

}


