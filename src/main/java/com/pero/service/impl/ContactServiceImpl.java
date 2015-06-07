package com.pero.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pero.dao.IContactDataDao;
import com.pero.model.Contact;
import com.pero.service.IContactService;

public class ContactServiceImpl implements IContactService{
	
	@Autowired
	IContactDataDao dataDao;

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addContact(Contact contact)
			throws Exception {
		boolean result = false;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {


			result = dataDao.addContact(contact, session);

			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {

				session.close();

			}
		}
		return result;
	}

	@Override
	public boolean updateContact(Contact contact)
			throws Exception {
		boolean result = false;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			

			result = dataDao.updateContact(contact, session);

			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {

				session.close();

			}
		}
		return result;
	}

	@Override
	public Contact getContactById(long id) throws Exception {
		Contact result = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			result = dataDao.getContactById(id, session);

			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {

				session.close();

			}
		}
		return result;
	}

	@Override
	public List<Contact> getContactsList(String query)
			throws Exception {
		List<Contact> result = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			result = dataDao.getContactsList(query, session);
			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {

				session.close();

			}
		}
		return result;
	}


	@Override
	public boolean deleteContact(long id) throws Exception {
		boolean result = false;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			result = dataDao.deleteContact(id, session);
			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {

				session.close();

			}
		}
		return result;
	}

}
