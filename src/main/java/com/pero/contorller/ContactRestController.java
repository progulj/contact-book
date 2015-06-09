package com.pero.contorller;

import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pero.model.Contact;
import com.pero.model.Query;
import com.pero.model.Status;
import com.pero.service.IContactService;

@Controller
@RequestMapping("/contact")
public class ContactRestController {

	@Autowired
	IContactService dataServices;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<Status> addContact(@RequestBody Contact contact) {

		Status status = new Status();
		try {
			dataServices.addContact(contact);
			status.setMessage("Contact added successfully !");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {

			status.setMessage("Contact add failed !");
			return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<Status> editContact(@RequestBody Contact contact) {

		Status status = new Status();

		try {
			dataServices.updateContact(contact);

			status.setMessage("Contact edited successfully !");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {

			status.setMessage("Contact update failed !");
			return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<Contact> getContact(@PathVariable("id") long id) {
		Contact contact = null;

		try {
			contact = dataServices.getContactById(id);

		} catch (Exception e) {

			return new ResponseEntity<Contact>(contact, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<List<Contact>> queryContacts(@RequestBody  Query query) {

		List<Contact> contactList = null;

		
		
		try {
			contactList = dataServices.getContactsList(query.getQuery());

		} catch (Exception e) {

			return new ResponseEntity<List<Contact>>(contactList,
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<List<Contact>> getContacts() {

		List<Contact> contactList = null;
		
		String query = null;

		try {
			contactList = dataServices.getContactsList(query);

		} catch (Exception e) {

			return new ResponseEntity<List<Contact>>(contactList,
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<Status> deleteContact(@PathVariable("id") long id) {
		Status status = new Status();
		try {
			dataServices.deleteContact(id);
			status.setMessage("Contact deleted successfully !");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {

			status.setMessage("Contact delete failed !");
			return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
		}
	}

}
