package Action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import Bean.Contacts;
import Bean.Kind;
import Bean.Page;
import Service.ContactsService;

@Controller
public class CantactsAction {
	ContactsService contactsService;
	Page page;
	
	public ContactsService getContactsService() {
		return contactsService;
	}
	@Resource
	public void setContactsService(ContactsService contactsService) {
		this.contactsService = contactsService;
	}

	//��¼
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpSession session, User user) {
		int who=contactsService.login(user);
		if(who!=0) {
			request.setAttribute("who", who);
			return findkind(session,request);
		}
		request.setAttribute("who", who);
		return "user_login";
	}

	//ע��
	@RequestMapping("/register.do")
	public String register(HttpServletRequest request, HttpSession session, User user) {
		boolean flag=contactsService.register(user);
		if(flag) {
			request.setAttribute("ac",1);
		}else {
			request.setAttribute("have",0);
		}
		return "user_register";
	}


	//��ѯ������
	@RequestMapping("findkind.do")
	public String findkind(HttpSession session,HttpServletRequest request){
		int who=0;
		if(request.getAttribute("who")!=null) {
			who=Integer.parseInt(request.getAttribute("who").toString());
		}else {
			who=Integer.parseInt(request.getParameter("who"));
		}
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getKindPage(Integer.valueOf(pageNum),who));
		request.setAttribute("who", who);
	    request.setAttribute("y", 1);
		return "show";
	}
	//��ѯ������ϵ��
	@RequestMapping("findAll.do")
	public String getContactsPage(HttpSession session,HttpServletRequest request){
		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsPage(Integer.valueOf(pageNum),who));
		return "allShow";
	}
	//��ѯ������ϵ��
	@RequestMapping("findcontacts.do")
	public String findcontacts(HttpSession session,HttpServletRequest request,Contacts contacts){
		String PersonName=contacts.getPersonName();
		int kindId=Integer.parseInt(request.getParameter("kindId").toString());
		request.setAttribute("kindid",kindId);
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsOfSome(Integer.valueOf(pageNum),PersonName));
		return "contactsShow";
	}
	//��������ϵ���в�ѯ
	@RequestMapping("findcontactss.do")
	public String findcontactss(HttpSession session,HttpServletRequest request,Contacts contacts){
		String PersonName=contacts.getPersonName();
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsOfSome(Integer.valueOf(pageNum),PersonName));
		return "allShow";
	}
	//�������в�ѯ��
	@RequestMapping("findSomeKind.do")
	public String findSomeKind(HttpSession session,HttpServletRequest request,Kind kind){
		String kindName=kind.getKindName();
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getSomeKind(Integer.valueOf(pageNum),kindName));
		return "show";
	}
	//�����½���ϵ��,�û�������Ҫ������
	@RequestMapping("registerContacts.do")
	public String registerContacts(@Valid@ModelAttribute("command") Contacts contacts,BindingResult bindingResult,HttpSession session,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			request.setAttribute("id",contacts.getKindId());
			return "registerContacts";
		}
		int n=contactsService.registerContacts(contacts);
		if(n>0){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPageOfSome(Integer.valueOf(pageNum),contacts.getKindId()));
		    request.setAttribute("kindid",contacts.getKindId());
			return "contactsShow";
		}else{
			return "registerContactsFail";
		}
	}
	//�½���ϵ��
	@RequestMapping("registerContactss.do")
	public String registerContactss(@Valid@ModelAttribute("command") Contacts contacts,BindingResult bindingResult,HttpSession session,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			request.setAttribute("list",contactsService.findkind());
			return "registerContacts1";
		}
		int n=contactsService.registerContacts(contacts);
		int who=contacts.getWho();
		if(n>0){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPage(Integer.valueOf(pageNum),who));
			return "allShow";
		}else{
			return "registerContactsFail";
		}
	}
	//�½���
	@RequestMapping("registerKind.do")
	public String registerKind(@Valid@ModelAttribute("command") Kind kind,BindingResult bindingResult,HttpSession session,HttpServletRequest request){
		int who=kind.getWho();
		if(bindingResult.hasErrors()){
			return "registerKind";
		}
		int n=contactsService.registerKind(kind);
		if(n>0){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getKindPage(Integer.valueOf(pageNum),who));
			request.setAttribute("who", who);
			return "show";
		}else{
			return "registerKindFail";
		}
	}
	//��ȡ��,�½���ϵ��
	@RequestMapping("registerContacts1.do")
	public String findkind(HttpServletRequest request,HttpSession session){
		request.setAttribute("list",contactsService.findkind());
		return "registerContacts1";
	}
	//����ʾ���������ϵ��
	@RequestMapping("updateContacts.do")
	public String updateContacts(@Valid@ModelAttribute("command") Contacts contacts,BindingResult bindingResult,HttpSession session,HttpServletRequest request){
	    int n=contactsService.updateContacts(contacts);
	    if(n>0){
	    	int id=Integer.parseInt(request.getParameter("kindId").toString());
			int who=contacts.getWho();
	    	String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPageOfSome(Integer.valueOf(pageNum),id));
		    request.setAttribute("kindid",id);
			request.setAttribute("who",who);
			return "contactsShow";
	    }
		return "updateContactsFail";
	}
	//ȫ����ϵ����ʾ���������ϵ��
	@RequestMapping("updateContactss.do")
	public String updateContactss(@Valid@ModelAttribute("command") Contacts contacts,BindingResult bindingResult,HttpSession session,HttpServletRequest request){
		int who=contacts.getWho();
	    int n=contactsService.updateContacts(contacts);
	    if(n>0){
	    	String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPage(Integer.valueOf(pageNum),who));
			return "allShow";
	    }
		return "updateContactsFail";
	}
	//�����ɾ����ϵ��
	@RequestMapping("deleteContacts.do")
	public String deleteContacts(HttpSession session,HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id").toString());
		int kindId=Integer.parseInt(request.getParameter("kindId").toString());
		boolean n=contactsService.deleteContacts(id);
		if(n){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPageOfSome(Integer.valueOf(pageNum),kindId));
		    request.setAttribute("kindid",kindId);
			request.setAttribute("who",request.getParameter("who"));
			return "contactsShow";
		}else{			
			return "fail";
		}
	}
	//ȫ����ϵ�˽���ɾ����ϵ��
	@RequestMapping("deleteContactss.do")
	public String deleteContactss(HttpSession session,HttpServletRequest request){
		int who=Integer.parseInt(request.getParameter("who").toString());
		int id=Integer.parseInt(request.getParameter("id").toString());
		boolean n=contactsService.deleteContacts(id);
		if(n){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
		    request.setAttribute("page", contactsService.getContactsPage(Integer.valueOf(pageNum),who));
			request.setAttribute("who",who);
			return "allShow";
		}else{
			return "fail";
		}
	}
	//ɾ����
	@RequestMapping("deleteKind.do")
	public String deleteKind(HttpSession session,HttpServletRequest request){
		int kindId=Integer.parseInt(request.getParameter("id"));
		int who=Integer.parseInt(request.getParameter("who"));
		boolean n=contactsService.deleteKind(kindId,who);
		if(n){
			String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
			request.setAttribute("page", contactsService.getKindPage(Integer.valueOf(pageNum),who));
			request.setAttribute("who",who);
			return "show";
		}else{
			return "fail";
		}	
	}
	//��ѯ�����µ���ϵ��
	@RequestMapping("queryById.do")
	public String queryById(HttpSession session,HttpServletRequest request){
		int who=Integer.parseInt(request.getParameter("who"));
	    int id=Integer.parseInt(request.getParameter("id"));
	    String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsPageOfSome(Integer.valueOf(pageNum),id));
	    request.setAttribute("kindid",id);
		request.setAttribute("who",who);
	    return "contactsShow";
	}
	//����Id��ѯ��ϵ����Ϣ��ת�޸�ҳ
	@RequestMapping("queryContactsById.do")
	public String queryContactsById(HttpSession session,HttpServletRequest request){
	    int id=Integer.parseInt(request.getParameter("id"));
	    List list=contactsService.queryContactsById(id);
	    request.setAttribute("listt",contactsService.findkind());
	    session.setAttribute("list", list);
	    return "updateContacts";
	}
	//����Id��ѯ��ϵ����Ϣ��ת�޸�ҳ
	@RequestMapping("queryContactsByIdd.do")
	public String queryContactsByIdd(HttpSession session,HttpServletRequest request){
	    int id=Integer.parseInt(request.getParameter("id"));
	    List list=contactsService.queryContactsById(id);
	    request.setAttribute("listt",contactsService.findkind());
	    session.setAttribute("list", list);
	    return "updateContactss";
	}
	//����ʾ�ķ�ҳ
	 @RequestMapping("list.do")
	    public String list(HttpServletRequest request,Contacts contacts,HttpSession session) {
		 		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
				String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
				request.setAttribute("page",contactsService.getKindPage(Integer.valueOf(pageNum),who));
				request.setAttribute("who",who);
	        	return "show";
	 }
	 //ȫ����ϵ����ʾ�ķ�ҳ
	 @RequestMapping("listt.do")
	    public String listt(HttpServletRequest request,Contacts contacts,HttpSession session) {
			int kindId=Integer.parseInt(request.getParameter("kindId").toString());
		 int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
				String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
				request.setAttribute("page",contactsService.getContactsPageOfSome(Integer.valueOf(pageNum),kindId));
				request.setAttribute("kindid",kindId);
				request.setAttribute("who",who);
	        	return "contactsShow";
	 }
	 //������ϵ����ʾ�ķ�ҳ
	 @RequestMapping("listtt.do")
	    public String listtt(HttpServletRequest request,Contacts contacts,HttpSession session) {
		 		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
				String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
				request.setAttribute("page",contactsService.getContactsPage(Integer.valueOf(pageNum),who));
	        	return "allShow";
	 }
	
}
