package br.net.neuromancer.polis.domains.model;

import org.springframework.data.annotation.Id;

public class DomainGovBR {
	
	@Id
	private String domain;
	private String document;
	private String name;
	private String uf;
	private String city;
	private String cep;
	private String inclusionDate;
	private String lastUpdate;
	private String ticketNo;
	private String filler;
	
	public DomainGovBR() {
		super();
	}

	public DomainGovBR(String domain, String document, String name, String uf, String city, String cep,
			String inclusionDate, String lastUpdate, String ticketNo, String filler) {
		super();
		this.domain = domain;
		this.document = document;
		this.name = name;
		this.uf = uf;
		this.city = city;
		this.cep = cep;
		this.inclusionDate = inclusionDate;
		this.lastUpdate = lastUpdate;
		this.ticketNo = ticketNo;
	}
	
	@Override
	public String toString() {
		return "DomainGovBR [domain=" + domain + ", name=" + name + "]";
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getInclusionDate() {
		return inclusionDate;
	}
	public void setInclusionDate(String inclusionDate) {
		this.inclusionDate = inclusionDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	

}
