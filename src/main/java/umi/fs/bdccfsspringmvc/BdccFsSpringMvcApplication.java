package umi.fs.bdccfsspringmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import umi.fs.bdccfsspringmvc.entities.Product;
import umi.fs.bdccfsspringmvc.repository.ProductRepository;
@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BdccFsSpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(BdccFsSpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(5400.0)
                    .quantity(12.0)
                    .build());
            productRepository.save(Product.builder()
                    .name("printer")
                    .price(1200.0)
                    .quantity(11.0)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smart Phone ")
                    .price(12000.0)
                    .quantity(33.0)
                    .build());
            productRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });

        };
    }
}