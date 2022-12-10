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

	//登录
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

	//注册
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


	//查询所有类
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
	//查询所有联系人
	@RequestMapping("findAll.do")
	public String getContactsPage(HttpSession session,HttpServletRequest request){
		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsPage(Integer.valueOf(pageNum),who));
		return "allShow";
	}
	//查询类中联系人
	@RequestMapping("findcontacts.do")
	public String findcontacts(HttpSession session,HttpServletRequest request,Contacts contacts){
		String PersonName=contacts.getPersonName();
		int kindId=Integer.parseInt(request.getParameter("kindId").toString());
		request.setAttribute("kindid",kindId);
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsOfSome(Integer.valueOf(pageNum),PersonName));
		return "contactsShow";
	}
	//在所有联系人中查询
	@RequestMapping("findcontactss.do")
	public String findcontactss(HttpSession session,HttpServletRequest request,Contacts contacts){
		String PersonName=contacts.getPersonName();
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getContactsOfSome(Integer.valueOf(pageNum),PersonName));
		return "allShow";
	}
	//所有类中查询类
	@RequestMapping("findSomeKind.do")
	public String findSomeKind(HttpSession session,HttpServletRequest request,Kind kind){
		String kindName=kind.getKindName();
		String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
	    request.setAttribute("page", contactsService.getSomeKind(Integer.valueOf(pageNum),kindName));
		return "show";
	}
	//类中新建联系人,用户不再需要输入类
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
	//新建联系人
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
	//新建类
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
	//获取类,新建联系人
	@RequestMapping("registerContacts1.do")
	public String findkind(HttpServletRequest request,HttpSession session){
		request.setAttribute("list",contactsService.findkind());
		return "registerContacts1";
	}
	//类显示界面更改联系人
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
	//全部联系人显示界面更改联系人
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
	//类界面删除联系人
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
	//全部联系人界面删除联系人
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
	//删除类
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
	//查询分类下的联系人
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
	//根据Id查询联系人信息跳转修改页
	@RequestMapping("queryContactsById.do")
	public String queryContactsById(HttpSession session,HttpServletRequest request){
	    int id=Integer.parseInt(request.getParameter("id"));
	    List list=contactsService.queryContactsById(id);
	    request.setAttribute("listt",contactsService.findkind());
	    session.setAttribute("list", list);
	    return "updateContacts";
	}
	//根据Id查询联系人信息跳转修改页
	@RequestMapping("queryContactsByIdd.do")
	public String queryContactsByIdd(HttpSession session,HttpServletRequest request){
	    int id=Integer.parseInt(request.getParameter("id"));
	    List list=contactsService.queryContactsById(id);
	    request.setAttribute("listt",contactsService.findkind());
	    session.setAttribute("list", list);
	    return "updateContactss";
	}
	//类显示的翻页
	 @RequestMapping("list.do")
	    public String list(HttpServletRequest request,Contacts contacts,HttpSession session) {
		 		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
				String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
				request.setAttribute("page",contactsService.getKindPage(Integer.valueOf(pageNum),who));
				request.setAttribute("who",who);
	        	return "show";
	 }
	 //全部联系人显示的翻页
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
	 //类中联系人显示的翻页
	 @RequestMapping("listtt.do")
	    public String listtt(HttpServletRequest request,Contacts contacts,HttpSession session) {
		 		int who=request.getParameter("who")==null?0: Integer.parseInt(request.getParameter("who"));
				String pageNum=request.getParameter("p")==null?"1":request.getParameter("p");
				request.setAttribute("page",contactsService.getContactsPage(Integer.valueOf(pageNum),who));
	        	return "allShow";
	 }
	
}
