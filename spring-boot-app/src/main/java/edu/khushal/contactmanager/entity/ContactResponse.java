package edu.khushal.contactmanager.entity;

import lombok.Data;

@Data
public class ContactResponse<T> {

	private String massage;
	private T data;
	private int httpStatusCode;
}
