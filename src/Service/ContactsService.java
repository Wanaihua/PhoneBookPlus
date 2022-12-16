package Service;

import java.util.List;

import javax.annotation.Resource;

import Bean.User;
import org.springframework.stereotype.Component;


import Bean.Contacts;
import Bean.Kind;
import Bean.Page;
import Dao.ContactsDao;

@Component
public class ContactsService {
	ContactsDao contactsDao;

	public ContactsDao getContactsDao() {
		return contactsDao;
	}
	@Resource
	public void setContactsDao(ContactsDao contactsDao) {
		this.contactsDao = contactsDao;
	}
	public int registerContacts(Contacts contacts,int kindId){
		return contactsDao.registerContacts(contacts,kindId);
	}
	public int registerKind(Kind kind){
		return contactsDao.registerKind(kind);
	}
	public List findKind(){
		return contactsDao.findKind();
	}	
	public List findContacts(){
		return contactsDao.findContacts();
	}
	public Page getContactsPageOfSome(int pageNum,int id){
		 return contactsDao.getContactsPageOfSome(pageNum,id);
	 }
	public Page getContactsPage(int pageNum,int who){
		 return contactsDao.getContactsPage(pageNum,who);
	 }
	public Page getKindPage(int pageNum,int who){
		 return contactsDao.getKindPage(pageNum,who);
	 }
	public  int updateContacts(Contacts contacts) {
		return contactsDao.updateContacts(contacts);
	}
	public boolean deleteContacts(int id) {
		return contactsDao.deleteContacts(id);
	}
	public List queryById(int id) {
		return contactsDao.queryById(id);
	}
	public boolean deleteKind(int id,int who) {
		return contactsDao.deleteKind(id,who);
	}
	public List queryContactsById(int id) {
		return contactsDao.queryContactsById(id);
	}
	public Page getContactsOfSome(int pageNum,String PersonName) {
		return contactsDao.getContactsOfSome(pageNum, PersonName);
	}
	public Page getSomeKind(int pageNum, String kindName) {
		return contactsDao.getSomeKind(pageNum,kindName);
	}
	public int registerContacts(Contacts contacts) {
		return contactsDao.registerContacts(contacts);
	}
	public List findkind(int who) {
		return contactsDao.findkind(who);
	}

	public int login(User user) {
		return contactsDao.login(user);
	}

	public boolean register(User user) {
		return contactsDao.register(user);
	}

    public List queryContacts(int who) {
		return contactsDao.queryContacts(who);
    }
}
