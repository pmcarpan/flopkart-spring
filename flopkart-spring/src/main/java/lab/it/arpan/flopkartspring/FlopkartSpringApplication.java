package lab.it.arpan.flopkartspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import lab.it.arpan.flopkartspring.converter.Converter;
import lab.it.arpan.flopkartspring.converter.ProductToProductResponseConverter;
import lab.it.arpan.flopkartspring.datastore.DropOffPointRepository;
import lab.it.arpan.flopkartspring.datastore.JdbcDropOffPointRepository;
import lab.it.arpan.flopkartspring.datastore.JdbcOrderRepository;
import lab.it.arpan.flopkartspring.datastore.JdbcProductRepository;
import lab.it.arpan.flopkartspring.datastore.OrderRepository;
import lab.it.arpan.flopkartspring.datastore.ProductRepository;
import lab.it.arpan.flopkartspring.filter.OrderControllerAuthFilter;
import lab.it.arpan.flopkartspring.model.Product;
import lab.it.arpan.flopkartspring.model.response.ProductResponse;

@SpringBootApplication
public class FlopkartSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlopkartSpringApplication.class, args);
	}

	@Bean
	@Autowired
	public ProductRepository productRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcProductRepository(jdbcTemplate);
	}

	@Bean
	@Autowired
	public OrderRepository orderRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository) {
		return new JdbcOrderRepository(jdbcTemplate, productRepository);
	}

	@Bean
	@Autowired
	public DropOffPointRepository dropOffPointRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcDropOffPointRepository(jdbcTemplate);
	}

	@Bean
	public Converter<Product, ProductResponse> productToProductResponseConverter() {
		return new ProductToProductResponseConverter();
	}

	@Bean
	public FilterRegistrationBean<OrderControllerAuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<OrderControllerAuthFilter> registrationBean = new FilterRegistrationBean<>();
		OrderControllerAuthFilter orderControllerAuthFilter = new OrderControllerAuthFilter();

		registrationBean.setFilter(orderControllerAuthFilter);
		registrationBean.addUrlPatterns("/orders");
		registrationBean.setOrder(2);
		return registrationBean;
	}

}
