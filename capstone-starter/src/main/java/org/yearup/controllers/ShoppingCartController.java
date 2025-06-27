package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class ShoppingCartController
{
    private final ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao)
    {
        this.shoppingCartDao = shoppingCartDao;
    }

    // Get all items in the user's cart
    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart getCart(@PathVariable int userId)
    {
        return shoppingCartDao.getByUserId(userId);
    }

    // Get all items in the user's cart as a list (optional convenience)
    @GetMapping("/{userId}/items")
    @PreAuthorize("isAuthenticated()")
    public List<ShoppingCartItem> getCartItems(@PathVariable int userId)
    {
        return shoppingCartDao.getCartItems(userId);
    }

    // Add a product to the cart
    @PostMapping("/{userId}/add")
    @PreAuthorize("isAuthenticated()")
    public void addProduct(@PathVariable int userId,
                           @RequestParam int productId,
                           @RequestParam int quantity)
    {
        shoppingCartDao.addProduct(userId, productId, quantity);
    }

    // Update product quantity in cart
    @PutMapping("/{userId}/update")
    @PreAuthorize("isAuthenticated()")
    public void updateQuantity(@PathVariable int userId,
                               @RequestParam int productId,
                               @RequestParam int quantity)
    {
        shoppingCartDao.updateQuantity(userId, productId, quantity);
    }

    // Remove product from cart
    @DeleteMapping("/{userId}/remove")
    @PreAuthorize("isAuthenticated()")
    public void removeProduct(@PathVariable int userId,
                              @RequestParam int productId)
    {
        shoppingCartDao.removeProduct(userId, productId);
    }

    // Clear the user's entire cart
    @DeleteMapping("/{userId}/clear")
    @PreAuthorize("isAuthenticated()")
    public void clearCart(@PathVariable int userId)
    {
        shoppingCartDao.clearCart(userId);
    }
}
