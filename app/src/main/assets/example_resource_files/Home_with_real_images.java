package com.example.swapspot;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an example of how to implement the Home activity with real images
 * Replace the vectors with actual image resources placed in drawable-xxhdpi folder
 */
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
        // Use an actual image resource here
        discountImage.setImageResource(R.drawable.discount_banner);

        // Set up bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Initialize Categories Section
        setupCategories();

        // Initialize Hot Deals Section
        setupHotDeals();

        // Initialize New Arrivals Section
        setupNewArrivals();
    }

    private void setupCategories() {
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Category> categories = new ArrayList<>();
        // Using real image resources from drawable-xxhdpi folder
        categories.add(new Category(R.drawable.category_stationery, "Stationery"));
        categories.add(new Category(R.drawable.category_culinary, "Culinary"));
        categories.add(new Category(R.drawable.category_electronics, "Electronics"));
        categories.add(new Category(R.drawable.category_clothing, "Clothing"));
        categories.add(new Category(R.drawable.category_furniture, "Furniture"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }

    private void setupHotDeals() {
        hotDealsRecyclerView = findViewById(R.id.hotDealsRecyclerView);
        hotDealsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Product> hotDeals = new ArrayList<>();
        // Using real image resources from drawable-xxhdpi folder
        hotDeals.add(new Product(R.drawable.product_waterbottle, "Waterbottle", "$ 20.00"));
        hotDeals.add(new Product(R.drawable.product_phone_cover, "Phone Cover", "$ 45.00"));
        hotDeals.add(new Product(R.drawable.product_wooden_chair, "Wooden-Chair", "$ 49.00"));
        hotDeals.add(new Product(R.drawable.product_refrigerator, "Refrigerator", "$ 39.00"));

        ProductAdapter hotDealAdapter = new ProductAdapter(hotDeals);
        hotDealsRecyclerView.setAdapter(hotDealAdapter);
    }

    private void setupNewArrivals() {
        newArrivalsRecyclerView = findViewById(R.id.newArrivalsRecyclerView);
        newArrivalsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Product> newArrivals = new ArrayList<>();
        // Using real image resources from drawable-xxhdpi folder
        newArrivals.add(new Product(R.drawable.product_cotton_dress, "Cotton Dress", "$ 49.00"));
        newArrivals.add(new Product(R.drawable.product_leather_sneakers, "Leather Sneakers", "$ 49.00"));
        newArrivals.add(new Product(R.drawable.product_macbook, "Macbook Air", "$ 49.00"));

        ProductAdapter newArrivalAdapter = new ProductAdapter(newArrivals);
        newArrivalsRecyclerView.setAdapter(newArrivalAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
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
} 