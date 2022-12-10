package Bean;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class Contacts {
	int personId;
	@NotBlank
	String personName;
	String personNickName;
	String personSex;
	@Length(min=0,max=10)
	String personBirthday;
	int kindId;
	@NotBlank
	@Length(max=11)
	String personTelephone;
	@Length(max=11)
	String personQQ;
	String personEmail;
	String personAddress;
	String personMSN;
	String personInfo;
	int who;
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonNickName() {
		return personNickName;
	}
	public void setPersonNickName(String personNickName) {
		this.personNickName = personNickName;
	}
	public String getPersonSex() {
		return personSex;
	}
	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}
	public String getPersonBirthday() {
		return personBirthday;
	}
	public void setPersonBirthday(String personBirthday) {
		this.personBirthday = personBirthday;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getPersonTelephone() {
		return personTelephone;
	}
	public void setPersonTelephone(String personTelephone) {
		this.personTelephone = personTelephone;
	}
	public String getPersonQQ() {
		return personQQ;
	}
	public void setPersonQQ(String personQQ) {
		this.personQQ = personQQ;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	public String getPersonMSN() {
		return personMSN;
	}
	public void setPersonMSN(String personMSN) {
		this.personMSN = personMSN;
	}
	public String getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	public int getWho() {
		return who;
	}

	public void setWho(int who) {
		this.who = who;
	}
}
