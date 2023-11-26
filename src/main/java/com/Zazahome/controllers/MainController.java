package com.Zazahome.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.CommentRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UnbanRequestRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.Category;
import com.Zazahome.entities.Comment;
import com.Zazahome.entities.Product;
import com.Zazahome.entities.UnbanRequest;
import com.Zazahome.entities.User;

@Controller
public class MainController {

	/* Autowired */

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UnbanRequestRepository unbanRequestRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/* ==================== */

	// home page view.

	@GetMapping(value = { "/", "/home" })
	public String firstHomeView(Model m, Principal principal) {

		if (principal != null) {
			m.addAttribute("user", this.userRepo.loadUserByUserName(principal.getName()));
		}

		List<Product> products = this.productRepo.getProducts();
		List<Category> categories = this.categoryRepo.getCategories();

		m.addAttribute("title", "ZazaHome | Start Shopping Now!");
		m.addAttribute("categories", categories);
		m.addAttribute("products", products);
		return "index.html";
	}

	/* Register page view */
	@GetMapping("/register")
	public String registerPage(Model m) {

		m.addAttribute("title", "Register | StoreWala");
		m.addAttribute("user", new User());
		return "register";
	}


	@PostMapping("/process-registration")
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redir,
							 @RequestParam("confirm_password") String confirmPassword, @RequestParam("user_role") String role, Model m,
							 HttpSession httpSession) {

		if (result.hasErrors()) {
			m.addAttribute("user", user);
			return "register";
		}

		if (role.equals("non-selected")) {
			m.addAttribute("user", user);
			httpSession.setAttribute("status", "role-not-select");
			return "redirect:/register";
		}

		if (confirmPassword.equals("") || confirmPassword == null) {
			m.addAttribute("user", user);
			httpSession.setAttribute("status", "cp-empty");
			return "redirect:/register";
		}

		if (!user.getPassword().equals(confirmPassword)) {
			m.addAttribute("user", user);
			httpSession.setAttribute("status", "cp-not-match");
			return "redirect:/register";
		}

		try {
			user.setRole(role.equals("customer") ? "ROLE_CUSTOMER" : "ROLE_SELLER");

			user.setEnable(true);
			user.setProfile("user.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setDate(new Date());

			this.userRepo.save(user);

		} catch (DataIntegrityViolationException e) {
			httpSession.setAttribute("status", "email-exist");
			m.addAttribute("user", user);
			return "redirect:/register";

		} catch (Exception e) {
			httpSession.setAttribute("status", "went-wrong");
			e.printStackTrace();
		}

		httpSession.setAttribute("status", "registered-success");
		return "redirect:/register";
	}



	@GetMapping("/login")
	public String loginPage(Model m) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			User user = this.userRepo.loadUserByUserName(auth.getName());

			if (user.getRole().equals("ROLE_CUSTOMER")) {
				return "redirect:/customer/home";
			}
			if (user.getRole().equals("ROLE_ADMIN")) {
				return "redirect:/admin/home";
			}
			if (user.getRole().equals("ROLE_SELLER")) {
				return "redirect:/seller/home";
			}

		}

		m.addAttribute("title", "Login | StoreWala");
		return "login";
	}

	/*
	 * The above function is a method that handles requests to the /login URL. If
	 * the user is logged in, the function redirects them to a different URL
	 * depending on their role (e.g. /customer/home, /admin/home, or /seller/home).
	 * If the user is not logged in, the function adds an attribute to the Model
	 * object and returns a view that will likely render a login page for the user.
	 */

	@GetMapping("/search")
	public String searchProducts(@RequestParam(value = "category", required = false) Integer categoryType,
								 @RequestParam(name = "query", required = false) String query) {

		return "search_product";
	}

	/*
	 *                         EXPLANATION OF SEARCH METHOD
	 *
	 *  the above function is a method that handles request to the /search URL.
	 *  it displays all of the products related to the query and category.
	 */

	@GetMapping("/unban-request")
	public String unbanRequestView(Model m) {
		m.addAttribute("title", "Unban Request | StoreWala");
		m.addAttribute("unbanRequest", new UnbanRequest());
		return "unban";
	}

	// unban request processing logic.

	@PostMapping("/processing-unban-request")
	public String processUnbanRequest(@Valid @ModelAttribute("unbanRequest") UnbanRequest unbanRequest,
									  BindingResult result, HttpSession httpSession) {

		if (result.hasErrors()) {
			return "unban";
		}

		boolean flag = false;

		User user = this.userRepo.loadUserByUserName(unbanRequest.getEmail());

		if (user == null) {
			httpSession.setAttribute("status", "user-not-exist");
			return "redirect:/unban-request?emailNotValid";
		}

		if (user.isEnable()) {
			httpSession.setAttribute("status", "user-not-suspend");
			return "redirect:/unban-request?userIsNotBanned";
		}

		this.unbanRequestRepo.save(unbanRequest);
		flag = true;

		if (flag) {
			httpSession.setAttribute("status", "message-send-successfully");
		}

		return "redirect:/unban-request";
	}


	/*            EXPLANATION OF PROCESS UNBAN REQUEST METHOD
	  This function is a controller method that handles a POST request to the "/processing-unban-request" URL.
	  It is used to submit an unban request for a suspended user in the application.

	  If the user input is not valid, return the user to the unban request page.

	  Check if the user exists in the database. If they do not exist, set a status message
	  and redirect the user to the unban request page.

	  Check if the user is suspended. If they are not suspended, set a status message
	  and redirect the user to the unban request page.

	  Save the unban request to the database.

	  If the unban request was saved successfully, set a status message to indicate success.

	  Redirect the user to the unban request page.
	*/

	@GetMapping("/profile")
	public String showProfile(Model m, Principal principal, HttpSession httpSession) {

		if (principal == null) {
			httpSession.setAttribute("status", "not-login");
			return "redirect:/home";
		}

		User user = this.userRepo.loadUserByUserName(principal.getName());

		m.addAttribute("title", user.getName() + " | ZazaHome");
		m.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/checkout")
	public String checkOut(Model m, Principal principal, HttpSession httpSession) {

		if (principal == null) {
			httpSession.setAttribute("status", "not-login");
			return "redirect:/home";
		}

		int pinCode = new Random().nextInt(999999);

		User user = this.userRepo.loadUserByUserName(principal.getName());

		if (user.getRole().equals("ROLE_SELLER") || user.getRole().equals("ROLE_ADMIN")) {
			httpSession.setAttribute("status",
					user.getRole().equals("ROLE_SELLER") ? "seller-not-allow" : "admin-not-allow");
			return user.getRole().equals("ROLE_SELLER") ? "redirect:/seller/home" : "redirect:/admin/home";
		}

		m.addAttribute("user", user);
		m.addAttribute("pincode", String.format("%06d", pinCode));
		m.addAttribute("title", "Checkout | StoreWala");
		return "checkout";
	}


	@GetMapping("/showProduct")
	public String productDetail(@RequestParam(name = "product_id", required = false) Integer id, Model m,
								Principal principal) {

		List<Comment> comments = this.commentRepo.getAllComments(id);

		if (principal != null) {
			User user = this.userRepo.loadUserByUserName(principal.getName());
			m.addAttribute("user", user);
		}

		Product product = this.productRepo.getById(id);

		m.addAttribute("comments", comments);
		m.addAttribute("product", product);
		return "show_product";
	}


	@PostMapping("/processingComment")
	public String processComment(HttpSession httpSession,
								 @RequestParam(value = "comment", required = false) String comment,
								 @RequestParam(name = "user_id", required = false) Integer userId,
								 @RequestParam(value = "product_id", required = false) Integer productId) {

		User user = this.userRepo.getById(userId);

		boolean flag = false;

		Comment cmnt = new Comment();

		cmnt.setComment(comment);
		cmnt.setCommentRelatedTo(productId);
		cmnt.setUser(user);
		cmnt.setDate(new Date());

		this.commentRepo.save(cmnt);
		flag = true;

		if (flag) {
			httpSession.setAttribute("status", "commented-successfully");
			return "redirect:/showProduct?product_id=" + productId;
		}

		httpSession.setAttribute("status", "went-wrong");
		return "redirect:/showProduct?product_id=" + productId;
	}



	@GetMapping("/MyOrders")
	public String orderStatus() {
		return "order_status.html";
	}

}
