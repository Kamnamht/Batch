/*
 *
 */
package com.airtel.merchant.scheduler.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class SendSMSModel represents properties to be published to send SMS API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "communicationList" })
public class SendSMSModel {

	/** The communication list model. */
	@JsonProperty("communicationList")
	private List<CommunicationListModel> communicationListModel = null;

	/** The additional properties. */
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

	/**
	 * No args constructor for use in serialization.
	 */
	public SendSMSModel() {
	}

	/**
	 * Instantiates a new send SMS model.
	 *
	 * @param communicationListModel
	 *            the communication list model
	 */
	public SendSMSModel(List<CommunicationListModel> communicationListModel) {
		super();
		this.communicationListModel = communicationListModel;
	}

	/**
	 * Gets the communication list model.
	 *
	 * @return the communication list model
	 */
	@JsonProperty("communicationList")
	public List<CommunicationListModel> getCommunicationListModel() {
		return communicationListModel;
	}

	/**
	 * Sets the communication list.
	 *
	 * @param communicationListModel
	 *            the new communication list
	 */
	@JsonProperty("communicationList")
	public void setCommunicationList(List<CommunicationListModel> communicationListModel) {
		this.communicationListModel = communicationListModel;
	}

	/**
	 * Gets the additional properties.
	 *
	 * @return the additional properties
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	/**
	 * Sets the additional property.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		additionalProperties.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("communicationListModel", communicationListModel)
				.append("additionalProperties", additionalProperties).toString();
	}
}
