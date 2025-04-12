# Using Actual Images in SwapSpot App

## Overview

This guide explains how to replace the vector drawables in the SwapSpot app with actual image resources. The app has been pre-configured to use real images, but you need to provide these images and place them in the correct directories.

## Required Images

### Category Icons
These should be PNG files with transparency, ideally 96x96px:
- `category_stationery.png`
- `category_culinary.png`
- `category_electronics.png`
- `category_clothing.png`
- `category_furniture.png`

### Hot Deal Products
These should be high-quality product photos, ideally 400x300px:
- `product_waterbottle.jpg`
- `product_phone_cover.jpg`
- `product_wooden_chair.jpg`
- `product_refrigerator.jpg`

### New Arrival Products
These should be high-quality product photos, ideally 400x300px:
- `product_cotton_dress.jpg`
- `product_leather_sneakers.jpg`
- `product_macbook.jpg`

### Discount Banner
This should be a wide banner image, ideally 800x400px:
- `discount_banner.jpg`

## Steps to Add Images

1. **Prepare your images** according to the specifications above
2. **Place all images** in the `app/src/main/res/drawable-xxhdpi` directory
3. **Build and run** the app

## Code Implementation

The app is already set up to use these image resources. The key sections of code that handle the images are:

### Setting up Category Images
```java
private void setupCategories() {
    // ... existing code
    List<Category> categories = new ArrayList<>();
    categories.add(new Category(R.drawable.category_stationery, "Stationery"));
    categories.add(new Category(R.drawable.category_culinary, "Culinary"));
    categories.add(new Category(R.drawable.category_electronics, "Electronics"));
    categories.add(new Category(R.drawable.category_clothing, "Clothing"));
    categories.add(new Category(R.drawable.category_furniture, "Furniture"));
    // ... existing code
}
```

### Setting up Product Images for Hot Deals
```java
private void setupHotDeals() {
    // ... existing code
    List<Product> hotDeals = new ArrayList<>();
    hotDeals.add(new Product(R.drawable.product_waterbottle, "Waterbottle", "$ 20.00"));
    hotDeals.add(new Product(R.drawable.product_phone_cover, "Phone Cover", "$ 45.00"));
    hotDeals.add(new Product(R.drawable.product_wooden_chair, "Wooden-Chair", "$ 49.00"));
    hotDeals.add(new Product(R.drawable.product_refrigerator, "Refrigerator", "$ 39.00"));
    // ... existing code
}
```

### Setting up Discount Banner
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    // ... existing code
    discountImage = findViewById(R.id.discountImage);
    discountImage.setImageResource(R.drawable.discount_banner);
    // ... existing code
}
```

## Tips for Best Results

- Use consistent image sizes for a uniform look
- Ensure category icons have transparent backgrounds
- Product images should be clear and professional looking
- For the discount banner, use an image with a person similar to the reference design
- Test on multiple device sizes to ensure proper display

## Need Help?

If you encounter any issues or have questions about implementing images, refer to the Android documentation on [Working with Drawables](https://developer.android.com/guide/topics/resources/drawable-resource) or contact the SwapSpot development team. 