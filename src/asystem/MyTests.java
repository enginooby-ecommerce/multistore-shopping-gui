package asystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import main.Admin;
import main.Customer;
import main.Dislike;
import main.Inbox;
import main.Item;
import main.ItemInCart;
import main.Like;
import main.Message;
import main.Purchase;
import main.Retailer;
import main.Review;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class MyTests {
	// private static final int DEFAULT_TIMEOUT = 2000;
	private Admin admin = new Admin();
	private Customer customer, customer2 = new Customer();
	private Retailer retailer = new Retailer();
	private Item pc = new Item();
	private ItemInCart pcInCart, pcInCart2 = new ItemInCart();
	private Inbox newInbox = new Inbox();
	private Message newMessage = new Message();
	List<String> classList = new ArrayList<>();

	// helper to generate tests for getters and setters of a class using Pojo API
	private void testGetterSetterHelper(String myClass) {
		try {
			System.out.println("Testing getters and setters of class: " + myClass);
			PojoClass pojoclass = PojoClassFactory.getPojoClass(Class.forName(myClass));
			Validator validator = ValidatorBuilder.create().with(new SetterMustExistRule())
					.with(new GetterMustExistRule()).with(new SetterTester()).with(new GetterTester()).build();
			validator.validate(pojoclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to get all classes in a package using ClassGraph API
	private List<String> getClasses(String packageName) throws ClassNotFoundException, IOException {
		List<String> list = new ArrayList<>();
		try (ScanResult scanResult = new ClassGraph().whitelistPackages(packageName).enableClassInfo().scan()) {
			list.addAll(scanResult.getAllClasses().getNames());
		}
		return list;
	}

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);

	@BeforeEach
	public void beforeTest() {
		// account
		admin = new Admin("admin", "admin");
		retailer = new Retailer("reName", "rePass", 0);
		customer = new Customer("cusName", "cusPass", 1);
		customer2 = new Customer("cusName", "cusPass", 2, "avatar");
		Database.retailer_list.add(retailer);
		Database.customer_list.add(customer);
		Database.customer_list.add(customer2);

		// item
		pc = new Item(1, 1, "This is a description of PC1", "PC1", "PC", 12, 15);
		Database.super_stock.add(pc);

		// item in cart
		pcInCart = new ItemInCart(1, 6);
		pcInCart2 = new ItemInCart(2, 1, 3, 2);

		// inbox and message
		newInbox = new Inbox(1, 0);
		newMessage = new Message(0, "Hi customer!");
		newInbox.setMessages(Arrays.asList(newMessage));
		customer.setMy_inbox(Arrays.asList(newInbox));

		// favorite
		customer.getMy_favorite_id().add((Integer) 1);
	}

	@AfterEach
	public void afterTest() {
		// reset database
		Database.retailer_list.removeAll(Database.retailer_list);
		Database.customer_list.removeAll(Database.customer_list);
		Database.super_stock.removeAll(Database.super_stock);
	}

	@Test // (timeout = DEFAULT_TIMEOUT)
	public void testGetterSetter() throws ClassNotFoundException, IOException {
		classList = getClasses("main");	//get all classes in package main
		for (String myClass : classList) {
			testGetterSetterHelper(myClass);
		}
	}

	@Test
	public void getFavoriteList() {
		// when
		List<Item> expectedList = Arrays.asList(pc);
		List<Item> actualList = customer.getMyFavoriteList();

		// then
		assertEquals(expectedList, actualList);

	}

	@Test
	public void checkItemInFavorite() {
		assertTrue(customer.itemInFavoriteList(1));
	}

	@Test
	public void checkItemNotInFavorite() {
		assertFalse(customer.itemInFavoriteList(3));
	}

	@Test
	public void inbox() {
		assertEquals("Label does not match", "1&0", newInbox.getLabel());
		assertEquals("Customer ID does not match", 1, newInbox.getCustomerId());
		assertEquals("Retailer ID does not match", 0, newInbox.getRetailerId());
	}

	@Test
	public void checkInboxExists() {
		assertTrue(customer.inboxExist("1&0"));
	}

	@Test
	public void getInboxes() {
		// when
		Inbox actualInbox = customer.getInboxByLabel("1&0");
		Inbox expectedInbox = newInbox;

		// then
		assertEquals(expectedInbox, actualInbox);
	}

	@Test
	public void message() {
		// then
		assertEquals("Content does not match", "Hi customer!", newMessage.getContent());
		assertEquals("Sender ID does not match", 0, newMessage.getSenderId());
	}

	@Test
	public void getSubscriptions() {
		// when
		customer.getMy_subscription_id().add((Integer) 0);
		List<Retailer> expectedList = Arrays.asList(retailer);
		List<Retailer> actualList = customer.getMySubscriptionList();

		// then
		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
	}

	@Test
	public void getSubscribers() {
		// when
		retailer.getMy_subscriber_id_list().add((Integer) 1);
		List<Customer> expectedList = Arrays.asList(customer);
		List<Customer> actualList = retailer.getMySubsriberList();

		// then
		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
	}

	@Test
	public void increaseInteraction() {
		// when
		Like review1Like = new Like(1, Arrays.asList(customer));
		review1Like.increase();

		// then
		assertEquals(2, review1Like.getNumber());
	}

	@Test
	public void decreaseInteraction() {
		// when
		Dislike review1Dislike = new Dislike(1, Arrays.asList(customer2));
		review1Dislike.decrease();

		// then
		assertEquals(0, review1Dislike.getNumber());
	}

	@Test
	public void rating() {
		// when
		Review review1 = new Review(1, "Review 1", "not bad", 0, 1, 5);
		Review review2 = new Review(1, "Review 2", "good", 0, 1, 1);
		pc.getReviewList().add(review1);
		pc.getReviewList().add(review2);
		Item pc2 = new Item();

		// then
		assertEquals(3, pc.getRating()); // average of 5 and 1 is 3
		assertEquals(0, pc2.getRating()); // pc2 has no review so return 0
	}

	@Test
	public void purchase() {
		// when
		Purchase newPurchase = new Purchase(0, 1, 90);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// then
		assertEquals("Purchase ID does not match", 0, newPurchase.getPurchaseId());
		assertEquals("Customer ID does not match", 1, newPurchase.getCustomerId());
		assertEquals("Amount does not match", 90, newPurchase.getAmount());
		assertEquals("Time does not match", dtf.format(now), newPurchase.getTime());
	}

}
