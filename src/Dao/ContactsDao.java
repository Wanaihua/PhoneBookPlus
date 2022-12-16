package Dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import Bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import Bean.Contacts;
import Bean.Kind;
import Bean.Page;

@Component
public class ContactsDao {
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List findKind(){
		String sql="select * from kind_message";
		List list=jdbcTemplate.queryForList(sql);
		return list;
	}
	public List findContacts(){
		String sql="select * from contacts_message";
		List list=jdbcTemplate.queryForList(sql);
		return list;
	}
	public int getTotalRecord(String sql, Object... arrayParameters) {
        int totalRecord = jdbcTemplate.queryForInt(sql, arrayParameters);
        return totalRecord;
    }
	public Page getPage(int pageNum, Class clazz, String sql, int totalRecord, Object... parameters) {
        Page page = new Page(pageNum, totalRecord);
        sql = sql+" limit "+page.getStartIndex()+","+page.getPageSize();
        List list=jdbcTemplate.query(sql, parameters, ParameterizedBeanPropertyRowMapper.newInstance(clazz));    
        page.setAlllist(list);
        return page;
    }
	public Page getKindPage(int pageNum,int who) {
		String sql = "select count(*) from kind_message where who=?";
		int totalRecord = getTotalRecord(sql,who);
		sql = "select * from kind_message where who="+who+"";
		Page page = getPage(pageNum, Kind.class, sql, totalRecord);
		return page;
	}
	public Page getContactsPage(int pageNum, Class clazz, String sql, int totalRecord, Object... parameters) {
        Page page = new Page(pageNum, totalRecord);
        sql = sql+" limit "+page.getStartIndex()+","+page.getPageSize();
        List list=jdbcTemplate.query(sql, parameters, ParameterizedBeanPropertyRowMapper.newInstance(clazz));    
        page.setContactslist(list);
        if(list.size()<=0){
        	page=null;
        }
        return page;
    }
	public Page getKindsPage(int pageNum, Class clazz, String sql, int totalRecord, Object... parameters) {
        Page page = new Page(pageNum, totalRecord);
        sql = sql+" limit "+page.getStartIndex()+","+page.getPageSize();
        List list=jdbcTemplate.query(sql, parameters, ParameterizedBeanPropertyRowMapper.newInstance(clazz));
        page.setAlllist(list);
        if(list.size()<=0){
        	page=null;
        }
        return page;
    }
	public Page getContactsPageOfSome(int pageNum,int id) {
        String sql = "select count(*) from contacts_message where kindId='"+id+"'";
        int totalRecord = getTotalRecord(sql);
        sql = "select * from contacts_message where kindId='"+id+"' ";
        Page page = getContactsPage(pageNum, Contacts.class, sql, totalRecord);
        return page;
    }
	public Page getSomeKind(int pageNum, String kindName) {
		String sql = "select count(*) from kind_message where kindName like '%"+kindName+"%'";
        int totalRecord = getTotalRecord(sql);
        sql = "select * from kind_message where kindName like '%"+kindName+"%'";
        Page page = getKindsPage(pageNum, Kind.class, sql, totalRecord);
        return page;
	}
	public Page getContactsOfSome(int pageNum,String PersonName) {
        String sql = "select count(*) from contacts_message where PersonName like '%"+PersonName+"%'";
        int totalRecord = getTotalRecord(sql);
        sql = "select * from contacts_message where PersonName like '%"+PersonName+"%'";
        Page page = getContactsPage(pageNum, Contacts.class, sql, totalRecord);
        return page;
    }
	public Page getContactsPage(int pageNum,int who) {
        String sql = "select count(*) from contacts_message where who='"+who+"'";
        int totalRecord = getTotalRecord(sql);
        sql = "select * from contacts_message where who='"+who+"'";
        Page page = getContactsPage(pageNum, Contacts.class, sql, totalRecord);
        return page;
    }
	public int registerContacts(Contacts contacts,int kindId) {
		String sql="select * from contacts_message where personName=? and who=?";
		List list=jdbcTemplate.queryForList(sql,contacts.getPersonName(),contacts.getWho());
		if(list.size()>0){
			return 0;
		}else{
			String sql1="insert into contacts_message(personName,personNickName,personSex,personBirthday,kindId,personTelephone,personQQ,personEmail,personAddress,personMSN,personInfo,who) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			return jdbcTemplate.update(sql1,contacts.getPersonName(),contacts.getPersonNickName(),contacts.getPersonSex(),contacts.getPersonBirthday(),kindId,contacts.getPersonTelephone(),contacts.getPersonQQ(),contacts.getPersonEmail(),contacts.getPersonAddress(),contacts.getPersonMSN(),contacts.getPersonInfo(),contacts.getWho());
		}
	}
	public int registerContacts(Contacts contacts) {
		String sql="select * from contacts_message where personName=? and who=?";
		List list=jdbcTemplate.queryForList(sql,contacts.getPersonName(),contacts.getWho());
		if(list.size()>0){
			return 0;
		}else{
			String sql1="insert into contacts_message(personName,personNickName,personSex,personBirthday,kindId,personTelephone,personQQ,personEmail,personAddress,personMSN,personInfo,who) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			return jdbcTemplate.update(sql1,contacts.getPersonName(),contacts.getPersonNickName(),contacts.getPersonSex(),contacts.getPersonBirthday(),contacts.getKindId(),contacts.getPersonTelephone(),contacts.getPersonQQ(),contacts.getPersonEmail(),contacts.getPersonAddress(),contacts.getPersonMSN(),contacts.getPersonInfo(),contacts.getWho());
		}
	}
	public int registerKind(Kind kind) {
		String sql="select * from kind_message where kindName=? and who=?";
		List list=jdbcTemplate.queryForList(sql,kind.getKindName(),kind.getWho());
		if(list.size()>0){
			return 0;
		}else{
			String sql1="insert into kind_message(kindName,kindInfo,who) values(?,?,?)";
			return jdbcTemplate.update(sql1,kind.getKindName(),kind.getKindInfo(),kind.getWho());
		}
	}
	public int updateContacts(Contacts contacts) {
		String sql="update contacts_message set personName=?,personNickName=?,personSex=?,personBirthday=?,kindId=?,personTelephone=?,personQQ=?,personEmail=?,personAddress=?,personMSN=?,personInfo=? where personId=?";
		return jdbcTemplate.update(sql,contacts.getPersonName(),contacts.getPersonNickName(),contacts.getPersonSex(),contacts.getPersonBirthday(),contacts.getKindId(),contacts.getPersonTelephone(),contacts.getPersonQQ(),contacts.getPersonEmail(),contacts.getPersonAddress(),contacts.getPersonMSN(),contacts.getPersonInfo(),contacts.getPersonId());
	}
	public int updateKind(Kind kind){
		String sql="update kind_messgae set kindName=? kindInfo=? where Kindid=?";
		return  jdbcTemplate.update(sql,kind.getKindName(),kind.getKindInfo(),kind.getKindId());
	}
	public boolean deleteContacts(int id){
		String sql="delete from contacts_message where personId=?";
		int list=jdbcTemplate.update(sql,id);
		if(list>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean deleteKind(int id,int who) {
		String sql="delete from kind_message where kindId=? and who=?";
		String sqll="delete from contacts_message where kindId=? and who=?";
		jdbcTemplate.update(sqll,id,who);
		int n=jdbcTemplate.update(sql,id,who);
		if(n>0){
			return true;
		}else{
			return false;
		}
	}
	public List queryById(int id) {
		String sql="select * from contacts_message where kindId=?";
		return jdbcTemplate.queryForList(sql,id);
	}
	public List queryContactsById(int id) {
		String sql="select * from contacts_message where personId=?";
		return jdbcTemplate.queryForList(sql,id);
	}
	public List findkind(int who) {
		 String sql = "select * from kind_message where who='"+who+"'";
	        List list=jdbcTemplate.queryForList(sql);
	        return list;
	}

	public int login(User user) {
		String sql="select * from user where userName=? and userPass=?";
		List list=jdbcTemplate.queryForList(sql,user.getUserName(),user.getUserPass());
		if(list.size()>0) {
			int id=(Integer) ((Map)list.get(0)).get("id");
			return id;
		}
		return 0;
	}

	public boolean register(User user) {
		int id=login(user);
		if(id==0) {
			String sql="insert into user(userName,userPass) values(?,?)";
			int n=jdbcTemplate.update(sql,user.getUserName(),user.getUserPass());
			if(n>0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

    public List queryContacts(int who) {
		String sql="select  personName,personNickName,personSex,personBirthday,personTelephone,personQQ,personEmail,personAddress,personMSN,personInfo from contacts_message where who=?";
		return jdbcTemplate.queryForList(sql,who);
    }
}
