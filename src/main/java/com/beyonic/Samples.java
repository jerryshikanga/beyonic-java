package com.beyonic;

import com.beyonic.exceptions.BeyonicException;
import com.beyonic.models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by jerryshikanga on  2019-07-08
 */
public class Samples {
    public static void main(String[] args) throws BeyonicException {
        Beyonic.API_KEY = "ab594c14986612f6167a975e1c369e71edab6900";

        String response = null;

        // Collection Requests
        // CREATE
        HashMap<String, Object> crCreateData = new HashMap<>();
        crCreateData.put("amount", "1200");
        crCreateData.put("currency", "KES");
        crCreateData.put("description", "Test  Java Client");
        crCreateData.put("phonenumber", "+254727447101");
        response = new CollectionRequest().create(crCreateData, null);

        System.out.println(response);

        // LIST
        response = new CollectionRequest().list(null, null);
        System.out.println(response);

        // Filter
        HashMap<String, String> crFilter = new HashMap<>();
        crFilter.put("amount", "155");
        response = new CollectionRequest().filter(crFilter, null);
        System.out.println(response);

        // GET
        response = new CollectionRequest().get(123);
        System.out.println(response);

        // Collections
        // List
        response = new Collection().list(null, null);
        System.out.println(response);

        // Filter
        HashMap<String, String> filterValues = new HashMap<>();
        filterValues.put("currency", "KES");
        filterValues.put("amount", "1000");
        response = new Collection().filter(filterValues, null);
        System.out.println(response);

        // Get
        response = new Collection().get(123);
        System.out.println(response);


        // Payments
        // Create
        HashMap<String, Object> paymentCreate = new HashMap<>();
        paymentCreate.put("phonenumber", "+80000000001");
        paymentCreate.put("first_name", "Kennedy");
        paymentCreate.put("last_name", "Amani");
        paymentCreate.put("currency", "BXC");
        paymentCreate.put("amount", "30");
        paymentCreate.put("description", "Per diem payment");
        paymentCreate.put("payment_type", "money");
        response = new Payment().create(paymentCreate, null);
        System.out.println(response);

        // Payment with duplicate check
        response = new Payment().create(paymentCreate, null, "duplicate.check.key");
        System.out.println(response);

        // Multiple payments
        JSONObject recipient1 = new JSONObject();
        recipient1.put("amount", 15);
        recipient1.put("phonenumber", "+80000000001");
        JSONObject recipient2 = new JSONObject();
        recipient2.put("amount", 25);
        recipient2.put("phonenumber", "80000000005");
        recipient2.put("description", "Cool guy benefits");
        JSONArray recipientsArray = new JSONArray();
        recipientsArray.put(recipient1);
        recipientsArray.put(recipient2);

        HashMap<String, Object> mutiplePayments = new HashMap<>();
        mutiplePayments.put("recipient_data", recipientsArray.toString());
        mutiplePayments.put("currency", "BXC");
        mutiplePayments.put("description", "Per diem payment");
        mutiplePayments.put("payment_type", "airtime");
        response = new Payment().create(mutiplePayments, null);
        System.out.println(response);

        // List
        response = new Payment().list(null, null);
        System.out.println(response);

        // filter
        HashMap<String, String> paymentFilters = new HashMap<>();
        paymentFilters.put("currency", "KES");
        paymentFilters.put("amount", "1000");
        response = new Payment().filter(paymentFilters, null);
        System.out.println(response);

        // Get
        response = new Payment().get(123);
        System.out.println(response);


        //Currencies
        // List
        response = new Currency().list(null, null);
        System.out.println(response);


        // Get
        response = new Currency().get(7);
        System.out.println(response);


        //Networks
        // List
        response = new Network().list(null, null);
        System.out.println(response);


        // Get
        response = new Network().get(7);
        System.out.println(response);


        //Accounts
        // List
        response = new Account().list(null, null);
        System.out.println(response);


        // Get
        response = new Account().get(123);
        System.out.println(response);

        // Filter
        HashMap<String, String> accountFilter = new HashMap<>();
        accountFilter.put("currrency", "BXC");
        response = new Account().filter(accountFilter, null);
        System.out.println(response);


        //Transactions
        // List
        response = new Transaction().list(null, null);
        System.out.println(response);


        // Get
        response = new Transaction().get(123);
        System.out.println(response);

        // Filter
        HashMap<String, String> transactionFilter = new HashMap<>();
        transactionFilter.put("currrency", "BXC");
        transactionFilter.put("account", "123");
        response = new Transaction().filter(transactionFilter, null);
        System.out.println(response);


        // Contacts
        // CREATE
        HashMap<String, Object> contactData = new HashMap<>();
        contactData.put("first_name", "Keneddy");
        contactData.put("last_name", "Amani");
        contactData.put("phone_number", "+80000000001");
        contactData.put("email", "john.doe@beyonic.com");
        response = new Contact().create(contactData, null);
        System.out.println(response);

        // LIST
        response = new Contact().list(null, null);
        System.out.println(response);

        // Filter
        HashMap<String, String> contactFilter = new HashMap<>();
        contactFilter.put("created_after", "2017-01-01 00:00");
        response = new Contact().filter(contactFilter, null);
        System.out.println(response);

        // GET
        response = new Contact().get(123);
        System.out.println(response);
    }
}
