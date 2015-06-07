package com.pero.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pero.dao.IContactDataDao;
import com.pero.model.Contact;

public class ContactDataDaoImpl implements IContactDataDao {

	@Override
	public boolean addContact(Contact contact, Session session)
			throws Exception {

		session.save(contact);
		return false;
	}

	@Override
	public boolean updateContact(Contact contact, Session session)
			throws Exception {
		session.update(contact);
		return false;
	}

	@Override
	public Contact getContactById(long id, Session session) throws Exception {

		Contact contact = (Contact) session.get(Contact.class, new Long(id));

		return contact;
	}

	@Override
	public List<Contact> getContactsList(String input, Session session)
			throws Exception {

		Query query = null;

		if (input != null && !"".equals(input)) {
			query = session
					.createQuery("from Contact where first_name like (:input) or last_name like (:input)");
			query.setParameter("input", input+"%");
		} else {
			query = session.createQuery("from Contact");
		}

		@SuppressWarnings("unchecked")
		List<Contact> contacts = (List<Contact>) query.list();

		return contacts;
	}

	@Override
	public boolean deleteContact(long id, Session session) throws Exception {
		Object o = session.get(Contact.class, id);

		session.delete(o);

		return false;
	}

}
