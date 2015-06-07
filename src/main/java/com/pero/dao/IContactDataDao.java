package com.pero.dao;

import java.util.List;

import org.hibernate.Session;

import com.pero.model.Contact;

public interface IContactDataDao {
    
    public boolean addContact(Contact contact, Session session) throws Exception;
    
    public boolean updateContact(Contact contact, Session session) throws Exception;
    
    public Contact getContactById(long id, Session session) throws Exception;
    
    public List<Contact> getContactsList(String query, Session session) throws Exception;
    
    public boolean deleteContact(long id, Session session) throws Exception;

}
