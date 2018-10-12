package com.journaldev.client;

import javax.ws.rs.core.MediaType;

import com.journaldev.model.EmpRequest;
import com.journaldev.model.EmpResponse;
import com.journaldev.model.ErrorResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EmpClient {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String uri = "http://localhost:8080/jersey-example/rest/employees/id";
		EmpRequest request = new EmpRequest();
		// set id as 1 for OK response
		int id = Integer.parseInt(args[0]);
		request.setId(id);
		request.setName("VP");
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(uri);
			ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, request);
			System.out.println("Response status: " + response.getStatus());
			if (response.getStatus() == 200) {
				EmpResponse empResponse = response.getEntity(EmpResponse.class);
				System.out.println("Response: " + empResponse.getId() + "::" + empResponse.getName());
			} else {
				ErrorResponse exc = response.getEntity(ErrorResponse.class);
				System.out.println("Error code: " + exc.getErrorCode());
				System.out.println("Error id: " + exc.getErrorId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}