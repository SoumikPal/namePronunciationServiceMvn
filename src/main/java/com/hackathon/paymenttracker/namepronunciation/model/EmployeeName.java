package com.hackathon.paymenttracker.namepronunciation.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;


@Container(containerName = "EmployeeName", ru = "400")
public class EmployeeName {
	
	@Id
    @GeneratedValue
    private String id;
	
	@PartitionKey
	private String name;
	private boolean pref;
	private String defaultAudio;
	private String defaultPronunciation;
	private String customAudio;
	private String customPronunciation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPref() {
		return pref;
	}
	public void setPref(boolean pref) {
		this.pref = pref;
	}
	public String getDefaultAudio() {
		return defaultAudio;
	}
	public void setDefaultAudio(String defaultAudio) {
		this.defaultAudio = defaultAudio;
	}
	public String getDefaultPronunciation() {
		return defaultPronunciation;
	}
	public void setDefaultPronunciation(String defaultPronunciation) {
		this.defaultPronunciation = defaultPronunciation;
	}
	public String getCustomAudio() {
		return customAudio;
	}
	public void setCustomAudio(String customAudio) {
		this.customAudio = customAudio;
	}
	public String getCustomPronunciation() {
		return customPronunciation;
	}
	public void setCustomPronunciation(String customPronunciation) {
		this.customPronunciation = customPronunciation;
	}
    
	
	

}
