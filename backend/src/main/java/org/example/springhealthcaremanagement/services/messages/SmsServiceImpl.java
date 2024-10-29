package org.example.springhealthcaremanagement.services.messages;

import ClickSend.Api.ContactApi;
import ClickSend.Api.ContactListApi;
import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.*;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class SmsServiceImpl implements SmsService {

    private static final String USERNAME = "alexiailiescu";
    private static final String API_KEY = "6DD6F5E9-2026-5515-F4C5-5155616D4434";
    private static final int LIST_ID = 2605397;
    @Override
    public void importContacts(String fileUrl) {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername(USERNAME);
        defaultClient.setPassword(API_KEY);
        ContactListApi apiInstance = new ContactListApi(defaultClient);

        List<String> fieldList = Arrays.asList(
                "phone", "first_name", "last_name"
        );

        ContactListImport contactListImport = new ContactListImport();
        contactListImport.fileUrl(fileUrl);
        contactListImport.fieldOrder(fieldList);

        try {
            String result = apiInstance.listsImportByListIdPost(LIST_ID, contactListImport);
            apiInstance.listsRemoveDuplicatesByListIdPut(LIST_ID, (Fields) fieldList);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ContactListApi#listsImportByListIdPost");
            System.out.println(e.getCode());
            System.out.println(e.getResponseBody());
            e.printStackTrace();
        }
    }
    @Override
    public boolean createNewContact(Patient patient) {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername(USERNAME);
        defaultClient.setPassword(API_KEY);
        ContactApi apiInstance = new ContactApi(defaultClient);

        Contact contact = new Contact();
        contact.setPhoneNumber(patient.getPhoneNumber());
        contact.setFirstName(patient.getFirstName());
        contact.setLastName(patient.getLastName());

        try {
            apiInstance.listsContactsByListIdPost(contact, LIST_ID);
            return true;
        } catch (ApiException e) {
            System.err.println("Exception when calling ContactApi#contactsPost");
            System.out.println(e.getCode());
            System.out.println(e.getResponseBody());
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean sendSms(Appointment appointment) {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername(USERNAME);
        defaultClient.setPassword(API_KEY);
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage=new SmsMessage();
        smsMessage.body("Hello " + appointment.getPatient().getFirstName()+" "+appointment.getPatient().getLastName()
                + ", this is a reminder for your appointment on " + appointment.getDateTime() + " with Dr. " + appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName());
        smsMessage.to(appointment.getPatient().getPhoneNumber());
        smsMessage.source("Healthcare Management System");

        List<SmsMessage> smsMessageList= Arrays.asList(smsMessage);
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
            return true;
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            System.out.println(e.getCode());
            System.out.println(e.getResponseBody());
            e.printStackTrace();
            return false;
        }
    }
}