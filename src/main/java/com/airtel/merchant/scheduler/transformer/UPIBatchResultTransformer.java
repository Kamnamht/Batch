package com.airtel.merchant.scheduler.transformer;

import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.enums.Evaluater;
import com.airtel.merchant.scheduler.enums.RSFields;

/**
 * The Class UPIBatchResultTransformer will transform the DB object to required
 * type.
 */
public class UPIBatchResultTransformer implements ResultTransformer {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIBatchResultTransformer.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.transform.ResultTransformer#transformTuple(java.lang.Object[],
	 * java.lang.String[])
	 */
	@Override
	public Object transformTuple(Object[] objects, String[] strings) {

		LOGGER.debug("Generating MerchantDetailsCBS from resultset object.");
		MerchantDetailsCBS merchantDetailsCBS = new MerchantDetailsCBS();
		for (int i = 0; i < objects.length; i++) {
			Evaluater.valueOf(RSFields.enumString(strings[i])).evaluate(merchantDetailsCBS, objects[i]);
		}
		return merchantDetailsCBS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.transform.ResultTransformer#transformList(java.util.List)
	 */
	@Override
	public List<MerchantDetailsCBS> transformList(List list) {
		return list;
	}
}
