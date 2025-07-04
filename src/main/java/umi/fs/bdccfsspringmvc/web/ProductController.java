package umi.fs.bdccfsspringmvc.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import umi.fs.bdccfsspringmvc.entities.Product;
import umi.fs.bdccfsspringmvc.repository.ProductRepository;
import javax.naming.Binding;

import javax.naming.Binding;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model) {
        List<Product> products= productRepository.findAll();
        model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }
    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/user/index";
    }


    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @PostMapping("/admin/saveProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/admin/newProduct";
    }


    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@RequestParam Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return "redirect:/user/index";
        model.addAttribute("product", product);
        return "new-product";
    }

    @GetMapping("/user/search")
    @PreAuthorize("hasRole('USER')")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("productList", products);
        model.addAttribute("searchKeyword", keyword);
        return "products";
    }




}
