package com.pero.service;

import java.util.List;

import com.pero.model.Contact;

public interface IContactService {
	
    public boolean addContact(Contact contact) throws Exception;
    
    public boolean updateContact(Contact contact) throws Exception;
    
    public Contact getContactById(long id) throws Exception;
    
    public List<Contact> getContactsList(String query)throws Exception;
    
    public boolean deleteContact(long id) throws Exception;

}
