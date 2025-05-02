package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapspot.adapter.AlsoViewedAdapter;
import com.example.swapspot.adapter.CartAdapter;
import com.example.swapspot.model.AlsoViewedItem;
import com.example.swapspot.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartChangeListener {
    private RecyclerView cartRecyclerView, alsoViewedRecyclerView;
    private CartAdapter cartAdapter;
    private AlsoViewedAdapter alsoViewedAdapter;
    private TextView totalAmount;
    private Button checkoutButton;
    private ImageButton backButton;

    private List<CartItem> cartItems = new ArrayList<>();
    private List<AlsoViewedItem> alsoViewedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        alsoViewedRecyclerView = findViewById(R.id.alsoViewedRecyclerView);
        totalAmount = findViewById(R.id.totalAmount);
        checkoutButton = findViewById(R.id.checkoutButton);
        backButton = findViewById(R.id.backButton);

        // Dummy cart items (use your drawable resources)
        cartItems.add(new CartItem(R.drawable.product_phone_cover, "Basic T-shirt", 49.00, 2));
        cartItems.add(new CartItem(R.drawable.product_waterbottle, "Water Bootle", 69.00, 1));
        cartItems.add(new CartItem(R.drawable.product_phone_cover, "SmartPhone Cover", 69.00, 1));

        // Dummy also viewed items
        alsoViewedItems.add(new AlsoViewedItem(R.drawable.product_phone_cover, "Phone Cover", "$ 49.00"));
        alsoViewedItems.add(new AlsoViewedItem(R.drawable.product_leather_sneakers, "Leather Bag", "$ 129.00 - 89.00"));
        alsoViewedItems.add(new AlsoViewedItem(R.drawable.product_cotton_dress, "Embroidered T-shirt", "$ 39.00"));
        alsoViewedItems.add(new AlsoViewedItem(R.drawable.product_refrigerator, "Refrigerator", "$ 49.00 - 19.00"));

        cartAdapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        alsoViewedAdapter = new AlsoViewedAdapter(alsoViewedItems);
        alsoViewedRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        alsoViewedRecyclerView.setAdapter(alsoViewedAdapter);

        updateTotal();

        checkoutButton.setOnClickListener(v -> {
            // Proceed to checkout logic (for now, just show a message)
            // You can start a new activity here
            // Toast.makeText(this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onCartChanged() {
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        totalAmount.setText("$ " + String.format("%.2f", total));
    }
} 