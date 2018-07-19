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

/**
 * The Class CommunicationListModel.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunicationListModel {

	/** The start date. */
	@JsonProperty("startDate")
	private String startDate;

	/** The end date. */
	@JsonProperty("endDate")
	private String endDate;

	/** The lang id. */
	@JsonProperty("langId")
	private String langId;

	/** The template code. */
	@JsonProperty("templateCode")
	private String templateCode;

	/** The frequency. */
	@JsonProperty("frequency")
	private String frequency;

	/** The custom class name. */
	@JsonProperty("customClassName")
	private String customClassName;

	/** The masking regex. */
	@JsonProperty("maskingRegex")
	private String maskingRegex;

	/** The subject. */
	@JsonProperty("subject")
	private String subject;

	/** The category. */
	@JsonProperty("category")
	private String category;

	/** The index. */
	@JsonProperty("index")
	private Integer index;

	/** The content. */
	@JsonProperty("content")
	private String content;

	/** The max retry count. */
	@JsonProperty("maxRetryCount")
	private Integer maxRetryCount;

	/** The attachment. */
	@JsonProperty("attachment")
	private String attachment;

	/** The black out end time. */
	@JsonProperty("blackOutEndTime")
	private Object blackOutEndTime;

	/** The type. */
	@JsonProperty("type")
	private String type;

	/** The tamplate data model. */
	@JsonProperty("tamplateDataModel")
	private String tamplateDataModel;

	/** The ref 1. */
	@JsonProperty("ref1")
	private Object ref1;

	/** The max. */
	@JsonProperty("max")
	private String max;

	/** The ref 3. */
	@JsonProperty("ref3")
	private Object ref3;

	/** The ref 2. */
	@JsonProperty("ref2")
	private Object ref2;

	/** The ref 5. */
	@JsonProperty("ref5")
	private Object ref5;

	/** The ref 4. */
	@JsonProperty("ref4")
	private Object ref4;

	/** The recipient. */
	@JsonProperty("recipient")
	private List<String> recipient = null;

	/** The sender. */
	@JsonProperty("sender")
	private String sender;

	/** The application key. */
	@JsonProperty("applicationKey")
	private String applicationKey;

	/** The delivery report required. */
	@JsonProperty("deliveryReportRequired")
	private Boolean deliveryReportRequired;

	/** The external ref no. */
	@JsonProperty("externalRefNo")
	private String externalRefNo;

	/** The mode. */
	@JsonProperty("mode")
	private String mode;

	/** The black out start time. */
	@JsonProperty("blackOutStartTime")
	private Object blackOutStartTime;

	/** The application code. */
	@JsonProperty("applicationCode")
	private String applicationCode;

	/** The masking required. */
	@JsonProperty("maskingRequired")
	private Boolean maskingRequired;

	/** The additional properties. */
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	@JsonProperty("startDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the lang id.
	 *
	 * @return the lang id
	 */
	@JsonProperty("langId")
	public String getLangId() {
		return langId;
	}

	/**
	 * Sets the lang id.
	 *
	 * @param langId
	 *            the new lang id
	 */
	@JsonProperty("langId")
	public void setLangId(String langId) {
		this.langId = langId;
	}

	/**
	 * Gets the template code.
	 *
	 * @return the template code
	 */
	@JsonProperty("templateCode")
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * Sets the template code.
	 *
	 * @param templateCode
	 *            the new template code
	 */
	@JsonProperty("templateCode")
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */
	@JsonProperty("frequency")
	public String getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency.
	 *
	 * @param frequency
	 *            the new frequency
	 */
	@JsonProperty("frequency")
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the custom class name.
	 *
	 * @return the custom class name
	 */
	@JsonProperty("customClassName")
	public String getCustomClassName() {
		return customClassName;
	}

	/**
	 * Sets the custom class name.
	 *
	 * @param customClassName
	 *            the new custom class name
	 */
	@JsonProperty("customClassName")
	public void setCustomClassName(String customClassName) {
		this.customClassName = customClassName;
	}

	/**
	 * Gets the masking regex.
	 *
	 * @return the masking regex
	 */
	@JsonProperty("maskingRegex")
	public String getMaskingRegex() {
		return maskingRegex;
	}

	/**
	 * Sets the masking regex.
	 *
	 * @param maskingRegex
	 *            the new masking regex
	 */
	@JsonProperty("maskingRegex")
	public void setMaskingRegex(String maskingRegex) {
		this.maskingRegex = maskingRegex;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the new subject
	 */
	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category
	 *            the new category
	 */
	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	@JsonProperty("index")
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index
	 *            the new index
	 */
	@JsonProperty("index")
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content
	 *            the new content
	 */
	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the max retry count.
	 *
	 * @return the max retry count
	 */
	@JsonProperty("maxRetryCount")
	public Integer getMaxRetryCount() {
		return maxRetryCount;
	}

	/**
	 * Sets the max retry count.
	 *
	 * @param maxRetryCount
	 *            the new max retry count
	 */
	@JsonProperty("maxRetryCount")
	public void setMaxRetryCount(Integer maxRetryCount) {
		this.maxRetryCount = maxRetryCount;
	}

	/**
	 * Gets the attachment.
	 *
	 * @return the attachment
	 */
	@JsonProperty("attachment")
	public String getAttachment() {
		return attachment;
	}

	/**
	 * Sets the attachment.
	 *
	 * @param attachment
	 *            the new attachment
	 */
	@JsonProperty("attachment")
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * Gets the black out end time.
	 *
	 * @return the black out end time
	 */
	@JsonProperty("blackOutEndTime")
	public Object getBlackOutEndTime() {
		return blackOutEndTime;
	}

	/**
	 * Sets the black out end time.
	 *
	 * @param blackOutEndTime
	 *            the new black out end time
	 */
	@JsonProperty("blackOutEndTime")
	public void setBlackOutEndTime(Object blackOutEndTime) {
		this.blackOutEndTime = blackOutEndTime;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the tamplate data model.
	 *
	 * @return the tamplate data model
	 */
	@JsonProperty("tamplateDataModel")
	public String getTamplateDataModel() {
		return tamplateDataModel;
	}

	/**
	 * Sets the tamplate data model.
	 *
	 * @param tamplateDataModel
	 *            the new tamplate data model
	 */
	@JsonProperty("tamplateDataModel")
	public void setTamplateDataModel(String tamplateDataModel) {
		this.tamplateDataModel = tamplateDataModel;
	}

	/**
	 * Gets the ref 1.
	 *
	 * @return the ref 1
	 */
	@JsonProperty("ref1")
	public Object getRef1() {
		return ref1;
	}

	/**
	 * Sets the ref 1.
	 *
	 * @param ref1
	 *            the new ref 1
	 */
	@JsonProperty("ref1")
	public void setRef1(Object ref1) {
		this.ref1 = ref1;
	}

	/**
	 * Gets the max.
	 *
	 * @return the max
	 */
	@JsonProperty("max")
	public String getMax() {
		return max;
	}

	/**
	 * Sets the max.
	 *
	 * @param max
	 *            the new max
	 */
	@JsonProperty("max")
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets the ref 3.
	 *
	 * @return the ref 3
	 */
	@JsonProperty("ref3")
	public Object getRef3() {
		return ref3;
	}

	/**
	 * Sets the ref 3.
	 *
	 * @param ref3
	 *            the new ref 3
	 */
	@JsonProperty("ref3")
	public void setRef3(Object ref3) {
		this.ref3 = ref3;
	}

	/**
	 * Gets the ref 2.
	 *
	 * @return the ref 2
	 */
	@JsonProperty("ref2")
	public Object getRef2() {
		return ref2;
	}

	/**
	 * Sets the ref 2.
	 *
	 * @param ref2
	 *            the new ref 2
	 */
	@JsonProperty("ref2")
	public void setRef2(Object ref2) {
		this.ref2 = ref2;
	}

	/**
	 * Gets the ref 5.
	 *
	 * @return the ref 5
	 */
	@JsonProperty("ref5")
	public Object getRef5() {
		return ref5;
	}

	/**
	 * Sets the ref 5.
	 *
	 * @param ref5
	 *            the new ref 5
	 */
	@JsonProperty("ref5")
	public void setRef5(Object ref5) {
		this.ref5 = ref5;
	}

	/**
	 * Gets the ref 4.
	 *
	 * @return the ref 4
	 */
	@JsonProperty("ref4")
	public Object getRef4() {
		return ref4;
	}

	/**
	 * Sets the ref 4.
	 *
	 * @param ref4
	 *            the new ref 4
	 */
	@JsonProperty("ref4")
	public void setRef4(Object ref4) {
		this.ref4 = ref4;
	}

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	@JsonProperty("recipient")
	public List<String> getRecipient() {
		return recipient;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient
	 *            the new recipient
	 */
	@JsonProperty("recipient")
	public void setRecipient(List<String> recipient) {
		this.recipient = recipient;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	@JsonProperty("sender")
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the new sender
	 */
	@JsonProperty("sender")
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the application key.
	 *
	 * @return the application key
	 */
	@JsonProperty("applicationKey")
	public String getApplicationKey() {
		return applicationKey;
	}

	/**
	 * Sets the application key.
	 *
	 * @param applicationKey
	 *            the new application key
	 */
	@JsonProperty("applicationKey")
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	/**
	 * Gets the delivery report required.
	 *
	 * @return the delivery report required
	 */
	@JsonProperty("deliveryReportRequired")
	public Boolean getDeliveryReportRequired() {
		return deliveryReportRequired;
	}

	/**
	 * Sets the delivery report required.
	 *
	 * @param deliveryReportRequired
	 *            the new delivery report required
	 */
	@JsonProperty("deliveryReportRequired")
	public void setDeliveryReportRequired(Boolean deliveryReportRequired) {
		this.deliveryReportRequired = deliveryReportRequired;
	}

	/**
	 * Gets the external ref no.
	 *
	 * @return the external ref no
	 */
	@JsonProperty("externalRefNo")
	public String getExternalRefNo() {
		return externalRefNo;
	}

	/**
	 * Sets the external ref no.
	 *
	 * @param externalRefNo
	 *            the new external ref no
	 */
	@JsonProperty("externalRefNo")
	public void setExternalRefNo(String externalRefNo) {
		this.externalRefNo = externalRefNo;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	@JsonProperty("mode")
	public String getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode
	 *            the new mode
	 */
	@JsonProperty("mode")
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Gets the black out start time.
	 *
	 * @return the black out start time
	 */
	@JsonProperty("blackOutStartTime")
	public Object getBlackOutStartTime() {
		return blackOutStartTime;
	}

	/**
	 * Sets the black out start time.
	 *
	 * @param blackOutStartTime
	 *            the new black out start time
	 */
	@JsonProperty("blackOutStartTime")
	public void setBlackOutStartTime(Object blackOutStartTime) {
		this.blackOutStartTime = blackOutStartTime;
	}

	/**
	 * Gets the application code.
	 *
	 * @return the application code
	 */
	@JsonProperty("applicationCode")
	public String getApplicationCode() {
		return applicationCode;
	}

	/**
	 * Sets the application code.
	 *
	 * @param applicationCode
	 *            the new application code
	 */
	@JsonProperty("applicationCode")
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	/**
	 * Gets the masking required.
	 *
	 * @return the masking required
	 */
	@JsonProperty("maskingRequired")
	public Boolean getMaskingRequired() {
		return maskingRequired;
	}

	/**
	 * Sets the masking required.
	 *
	 * @param maskingRequired
	 *            the new masking required
	 */
	@JsonProperty("maskingRequired")
	public void setMaskingRequired(Boolean maskingRequired) {
		this.maskingRequired = maskingRequired;
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
		return new ToStringBuilder(this).append("startDate", startDate).append("endDate", endDate)
				.append("langId", langId).append("templateCode", templateCode).append("frequency", frequency)
				.append("customClassName", customClassName).append("maskingRegex", maskingRegex)
				.append("subject", subject).append("category", category).append("index", index)
				.append("content", content).append("maxRetryCount", maxRetryCount).append("attachment", attachment)
				.append("blackOutEndTime", blackOutEndTime).append("type", type)
				.append("tamplateDataModel", tamplateDataModel).append("ref1", ref1).append("max", max)
				.append("ref3", ref3).append("ref2", ref2).append("ref5", ref5).append("ref4", ref4)
				.append("recipient", recipient).append("sender", sender).append("applicationKey", applicationKey)
				.append("deliveryReportRequired", deliveryReportRequired).append("externalRefNo", externalRefNo)
				.append("mode", mode).append("blackOutStartTime", blackOutStartTime)
				.append("applicationCode", applicationCode).append("maskingRequired", maskingRequired)
				.append("additionalProperties", additionalProperties).toString();
	}

}
