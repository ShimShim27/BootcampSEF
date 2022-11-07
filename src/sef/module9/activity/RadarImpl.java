package sef.module9.activity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a Radar
 * 
 *
 */
public class RadarImpl implements Radar {

	private final Map<String, RadarContact> contacts;

	/**
	 * Constructs a new Radar
	 */
	public RadarImpl() {
		contacts = new HashMap<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		this.contacts.put(contact.getContactID(), contact);
		return contact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		return this.contacts.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		return this.contacts.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {

		return this.contacts.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		List<RadarContact> contactList = new ArrayList<>();
		for(RadarContact contact: this.contacts.values()) {
			contactList.add(contact);
		}
		return contactList;
	}

	/*
	 * ; (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		List<RadarContact> sortedContacts = new ArrayList<>();
		for(RadarContact contact:this.returnContacts()) {
			sortedContacts.add(contact);
		}
		sortedContacts.sort(comparator);
		return sortedContacts;
	}

}
