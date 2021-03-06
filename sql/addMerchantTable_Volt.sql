CREATE TABLE "MERCHANT_REGISTER_BATCH_STATS" 
 (	
    "CUSTOMER_ID" NUMBER(20,0), 
    "PROCESSED_ON" DATE,
    "BATCH_ID" NUMBER(10,0),
    "ACTIVATION_DATE_TIME" DATE,
    "EMAIL" VARCHAR2(255 BYTE),
    "ORG_NAME" VARCHAR2(255 BYTE),
    "MCC_CODE" VARCHAR2(255 BYTE),
    "PAN_NUMBER" VARCHAR2(255 BYTE),
    "AADHAR" VARCHAR2(255 BYTE),
    "MANAGER_NAME" VARCHAR2(255 BYTE),
    "ADDRESS" VARCHAR2(255 BYTE),
    "MOBILE" VARCHAR2(255 BYTE),
    "ORG_CODE" VARCHAR2(255 BYTE),
    "CALLBACK_URL" VARCHAR2(255 BYTE),
    "SETTLEMENT_BANK" VARCHAR2(255 BYTE),
    "BANK_ACCOUNT_NO" VARCHAR2(255 BYTE),
    "IFSC_CODE" VARCHAR2(255 BYTE),
    "API_BANK" VARCHAR2(255 BYTE),
    "CONTACT_NAME" VARCHAR2(255 BYTE),
    "OPERATOR_MOBILE_NO" VARCHAR2(255 BYTE),
    "OPERATOR_EMAIL" VARCHAR2(255 BYTE), 
    "MERCHANT_REGISTER_STATUS" VARCHAR2(255 BYTE),
    "MERCHANT_REGISTER_ERROR" VARCHAR2(2000),
    "UNIQUE_MID" VARCHAR2(255),  
    "MERCHANT_PRIVATE_KEY" VARCHAR2(255),
    "MERCHANT_PUBLIC_KEY" VARCHAR2(255), 
    "LINK_QR_STATUS" VARCHAR2(255),
    "LINK_QR_ERROR_CODE" VARCHAR2(255),
    "LINK_QR_MESSAGE_TEXT" VARCHAR2(255),
    "QR_STRING" VARCHAR2(255),
    "UPI_STRING" VARCHAR2(255),
    "VPA" VARCHAR2(255 BYTE)
 );