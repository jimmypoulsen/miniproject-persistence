package ctrl;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Product;

public class ProductsControllerTests {
	private ProductsController pCtrl;
	
	@Before
	public void setUp() throws DataAccessException {
		pCtrl = new ProductsController();
	}
	
	@Test
	public void getProductThatDoesntExist() throws DataAccessException {
		Product p = pCtrl.findById(91231231);
		assertNull(p);
	}
	
	@Test
	public void getProductThatExists() throws DataAccessException {
		Product p = pCtrl.findById(1);
		String expectedName = "Lego Mindstorm";
		
		assertEquals(expectedName, p.getName());
	}
	
	@Test
	public void getAllProducts() throws DataAccessException {
		List<Product> res = pCtrl.findAll();
		
		assertThat(res, instanceOf(List.class));
	}
}
