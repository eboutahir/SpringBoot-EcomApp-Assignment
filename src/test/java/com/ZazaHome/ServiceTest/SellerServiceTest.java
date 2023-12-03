package com.ZazaHome.ServiceTest;


import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.Category;
import com.Zazahome.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.Zazahome.entities.User;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SellerServiceTest {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private UserRepository userRepo;

    static String usingRandomUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("_", "");
    }

    public void prepareSellerHomeView(Model m, Principal principal) {
        User user = this.userRepo.loadUserByUserName(principal.getName());
        List<Product> sellerProducts = this.productRepo.getSellerAllProducts(user.getId());
        m.addAttribute("title", "Seller Panel | Zazahome");
        m.addAttribute("product", new Product());
        m.addAttribute("categories", categoryRepo.getCategories());
        m.addAttribute("user", user);
        m.addAttribute("products", sellerProducts);
    }

    public String addProduct(Product product, List<MultipartFile> images, Integer selectedProductCategory,
                             HttpSession httpSession, Principal principal, RedirectAttributes redirectAttribute) {
        User user = this.userRepo.loadUserByUserName(principal.getName());

        if (images.size() > 5) {
            httpSession.setAttribute("status", "images-exceed");
            return "redirect:/seller/home";
        }

        List<String> productImages = new ArrayList<>();

        try {
            for (MultipartFile image : images) {
                String imageName = usingRandomUUID() + "_" + image.getOriginalFilename();
                File savedFile = new ClassPathResource("static/product_upload").getFile();
                Path path = Paths.get(savedFile.getAbsolutePath() + File.separator + imageName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                productImages.add(imageName);
            }

            product.setSellerId(user.getId());
            product.setSellerName(user.getName());
            product.setPriceAfterDiscount(product.getDiscountPrice());
            product.setCategory(this.categoryRepo.getById(selectedProductCategory));
            product.setImages(productImages);
            this.productRepo.save(product);
            httpSession.setAttribute("status", "product-added");
            return "redirect:/seller/home?productAdded";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateProduct(Product product, List<MultipartFile> images, Integer selectedProductCategory,
                                HttpSession httpSession, Principal principal, RedirectAttributes redirectAttribute) {
        User user = this.userRepo.loadUserByUserName(principal.getName());

        if (images.size() > 5) {
            httpSession.setAttribute("status", "images-exceed");
            return "redirect:/seller/home";
        }

        List<String> productImages = new ArrayList<>();

        try {
            for (MultipartFile image : images) {
                String imageName = usingRandomUUID() + "_" + image.getOriginalFilename();
                File savedFile = new ClassPathResource("static/product_upload").getFile();
                Path path = Paths.get(savedFile.getAbsolutePath() + File.separator + imageName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                productImages.add(imageName);
            }

            Product existingProduct = this.productRepo.getById(product.getId());
            existingProduct.setTitle(product.getTitle());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setId(product.getBrand());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDiscountPrice(product.getDiscountPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setCategory(this.categoryRepo.getById(selectedProductCategory));
            existingProduct.setImages(productImages);

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-updated");
            return "redirect:/seller/home?productUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String deleteProduct(Integer productId, HttpSession httpSession) {
        try {
            Product product = this.productRepo.getById(productId);
            this.productRepo.delete(product);
            httpSession.setAttribute("status", "product-deleted");
            return "redirect:/seller/home?productDeleted";
        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public List<Product> getSellerProducts(Principal principal) {
        User user = this.userRepo.loadUserByUserName(principal.getName());
        return this.productRepo.getSellerAllProducts(user.getId());
    }

    public Product getProductById(Integer productId) {
        return this.productRepo.getById(productId);
    }

    public String updateProductDetails(Product product, Integer selectedProductCategory,
                                       HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            Product existingProduct = this.productRepo.getById(product.getId());
            existingProduct.setTitle(product.getTitle());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(this.categoryRepo.getById(selectedProductCategory));
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDiscountPrice(product.getDiscountPrice());
            existingProduct.setStock(product.getStock());

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-details-updated");
            return "redirect:/seller/home?productDetailsUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }
    public String updateProductImages(Product product, List<MultipartFile> images,
                                      HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            List<String> productImages = new ArrayList<>();

            for (MultipartFile image : images) {
                String imageName = usingRandomUUID() + "_" + image.getOriginalFilename();
                File savedFile = new ClassPathResource("static/product_upload").getFile();
                Path path = Paths.get(savedFile.getAbsolutePath() + File.separator + imageName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                productImages.add(imageName);
            }

            Product existingProduct = this.productRepo.getById(product.getId());
            existingProduct.setImages(productImages);

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-images-updated");
            return "redirect:/seller/home?productImagesUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateProductPrice(Product product, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            Product existingProduct = this.productRepo.getById(product.getId());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDiscountPrice(product.getDiscountPrice());

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-price-updated");
            return "redirect:/seller/home?productPriceUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateProductAvailability(Integer productId, boolean available, HttpSession httpSession) {
        try {
            Product existingProduct = this.productRepo.getById(productId);
            existingProduct.setAvailable(available);

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-availability-updated");
            return "redirect:/seller/home?productAvailabilityUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateProductDiscount(Product product, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            Product existingProduct = this.productRepo.getById(product.getId());
            existingProduct.setDiscountPrice(product.getDiscountPrice());

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-discount-updated");
            return "redirect:/seller/home?productDiscountUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }
    public String unsuspendProduct(Integer productId, HttpSession httpSession) {
        try {
            Product existingProduct = this.productRepo.getById(productId);
            existingProduct.setSuspended(false);

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-unsuspended");
            return "redirect:/seller/home?productUnsuspended";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public List<Category> getAllCategories() {
        return this.categoryRepo.getCategories();
    }

    public String addCategory(Category category, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            this.categoryRepo.save(category);
            httpSession.setAttribute("status", "category-added");
            return "redirect:/seller/home?categoryAdded";

        }catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateCategoryDetails(Category category, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            Category existingCategory = this.categoryRepo.getById(category.getId());
            existingCategory.setTitle(category.getTitle());

            this.categoryRepo.save(existingCategory);
            httpSession.setAttribute("status", "category-details-updated");
            return "redirect:/seller/home?categoryDetailsUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String deleteCategory(Integer categoryId, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            Category existingCategory = this.categoryRepo.getById(categoryId);
            this.categoryRepo.delete(existingCategory);

            httpSession.setAttribute("status", "category-deleted");
            return "redirect:/seller/home?categoryDeleted";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String suspendProduct(Integer productId, HttpSession httpSession) {
        try {
            Product existingProduct = this.productRepo.getById(productId);
            existingProduct.setSuspended(true);

            this.productRepo.save(existingProduct);
            httpSession.setAttribute("status", "product-suspended");
            return "redirect:/seller/home?productSuspended";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public List<User> getAllUsers() {
        return this.userRepo.getUsers();
    }

    public String deleteUser(Integer userId, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            User existingUser = this.userRepo.getById(userId);
            this.userRepo.delete(existingUser);

            httpSession.setAttribute("status", "user-deleted");
            return "redirect:/seller/home?userDeleted";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }

    public String updateUserDetails(User user, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            User existingUser = this.userRepo.getById(user.getId());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());

            this.userRepo.save(existingUser);
            httpSession.setAttribute("status", "user-details-updated");
            return "redirect:/seller/home?userDetailsUpdated";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }
}
