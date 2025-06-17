package umi.fs.bdccfsspringmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umi.fs.bdccfsspringmvc.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
}
