// Home.java
package com.example.swapspot;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView categoriesRecyclerView;
    private RecyclerView hotDealsRecyclerView;
    private RecyclerView newArrivalsRecyclerView;
    private BottomNavigationView bottomNavigationView;
    private ImageView discountImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Set up discount banner image
        discountImage = findViewById(R.id.discountImage);
        
        // Set a colored background until the real image is added
        try {
            discountImage.setImageResource(R.drawable.discount_banner);
        } catch (Exception e) {
            // Fallback if image resource isn't available yet
            discountImage.setImageDrawable(new ColorDrawable(getResources().getColor(R.color.yellow)));
        }

        // Set up bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        
        // Enlarge the profile icon programmatically
        enlargeProfileIcon();

        // Initialize Categories Section
        setupCategories();

        // Initialize Hot Deals Section
        setupHotDeals();

        // Initialize New Arrivals Section
        setupNewArrivals();
    }

    private void enlargeProfileIcon() {
        // Wait for the bottom navigation to be fully laid out
        bottomNavigationView.post(() -> {
            try {
                MenuItem profileItem = bottomNavigationView.getMenu().findItem(R.id.navigation_profile);
                if (profileItem != null && profileItem.getIcon() != null) {
                    // Create a scaled version of the drawable with a smaller scale factor
                    Drawable originalIcon = profileItem.getIcon();
                    LargeIconDrawable largeIcon = new LargeIconDrawable(originalIcon, 1f);
                    
                    // Set the new scaled drawable as the icon
                    profileItem.setIcon(largeIcon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupCategories() {
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Category> categories = new ArrayList<>();
        // Use a safe way to reference resources that might not exist yet
        categories.add(new Category(getDrawableResourceId("category_stationery", R.drawable.logo_swap_spot), "Stationery"));
        categories.add(new Category(getDrawableResourceId("category_culinary", R.drawable.logo_swap_spot), "Culinary"));
        categories.add(new Category(getDrawableResourceId("category_electronics", R.drawable.logo_swap_spot), "Electronics"));
        categories.add(new Category(getDrawableResourceId("category_clothing", R.drawable.logo_swap_spot), "Clothing"));
        categories.add(new Category(getDrawableResourceId("category_furniture", R.drawable.logo_swap_spot), "Furniture"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }

    private void setupHotDeals() {
        hotDealsRecyclerView = findViewById(R.id.hotDealsRecyclerView);
        hotDealsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Product> hotDeals = new ArrayList<>();
        // Use a safe way to reference resources that might not exist yet
        hotDeals.add(new Product(getDrawableResourceId("product_waterbottle", R.drawable.handshake_banner), "Waterbottle", "$ 20.00"));
        hotDeals.add(new Product(getDrawableResourceId("product_phone_cover", R.drawable.handshake_banner), "Phone Cover", "$ 45.00"));
        hotDeals.add(new Product(getDrawableResourceId("product_wooden_chair", R.drawable.handshake_banner), "Wooden-Chair", "$ 49.00"));
        hotDeals.add(new Product(getDrawableResourceId("product_refrigerator", R.drawable.handshake_banner), "Refrigerator", "$ 39.00"));

        ProductAdapter hotDealAdapter = new ProductAdapter(hotDeals);
        hotDealsRecyclerView.setAdapter(hotDealAdapter);
    }

    private void setupNewArrivals() {
        newArrivalsRecyclerView = findViewById(R.id.newArrivalsRecyclerView);
        newArrivalsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Product> newArrivals = new ArrayList<>();
        // Use a safe way to reference resources that might not exist yet
        newArrivals.add(new Product(getDrawableResourceId("product_cotton_dress", R.drawable.handshake_banner), "Cotton Dress", "$ 49.00"));
        newArrivals.add(new Product(getDrawableResourceId("product_leather_sneakers", R.drawable.handshake_banner), "Leather Sneakers", "$ 49.00"));
        newArrivals.add(new Product(getDrawableResourceId("product_macbook", R.drawable.handshake_banner), "Macbook Air", "$ 49.00"));

        ProductAdapter newArrivalAdapter = new ProductAdapter(newArrivals);
        newArrivalsRecyclerView.setAdapter(newArrivalAdapter);
    }

    // Helper method to safely get drawable resource ID
    private int getDrawableResourceId(String resourceName, int fallbackDrawable) {
        int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
        return resourceId != 0 ? resourceId : fallbackDrawable;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        // Apply animation to the selected item
        View view = bottomNavigationView.findViewById(itemId);
        if (view != null) {
            // Apply scale animation
            view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200)
                .withEndAction(() -> view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start())
                .start();
        }
        
        if (itemId == R.id.navigation_home) {
            // Already on home
            return true;
        } else if (itemId == R.id.navigation_compass) {
            Toast.makeText(this, "Explore clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.navigation_cart) {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.navigation_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.navigation_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return false;
    }

    // Inner classes for data models
    static class Category {
        int iconResId;
        String name;

        Category(int iconResId, String name) {
            this.iconResId = iconResId;
            this.name = name;
        }
    }

    static class Product {
        int imageResId;
        String name;
        String price;

        Product(int imageResId, String name, String price) {
            this.imageResId = imageResId;
            this.name = name;
            this.price = price;
        }
    }

    // Adapter classes would be implemented as separate files in a real app
    // They're included here for simplicity
}