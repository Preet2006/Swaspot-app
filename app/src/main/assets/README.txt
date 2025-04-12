HOW TO ADD REAL IMAGES TO THE SWAPSPOT APP

1. CATEGORY IMAGES:
   - Prepare 5 category icon images (recommended size: 96x96px)
   - Name them: category_stationery.png, category_culinary.png, category_electronics.png, category_clothing.png, category_furniture.png
   - Place these files in the app/src/main/res/drawable-xxhdpi folder
   - Update Home.java's setupCategories() method to use these images instead of vector drawables

2. PRODUCT IMAGES:
   - Prepare product images for Hot Deals (recommended size: 400x300px):
     * product_waterbottle.jpg
     * product_phone_cover.jpg
     * product_wooden_chair.jpg
     * product_refrigerator.jpg
   
   - Prepare product images for New Arrivals (recommended size: 400x300px):
     * product_cotton_dress.jpg
     * product_leather_sneakers.jpg
     * product_macbook.jpg
   
   - Place all these images in the app/src/main/res/drawable-xxhdpi folder
   - Update Home.java's setupHotDeals() and setupNewArrivals() methods to use these image resources

3. DISCOUNT BANNER IMAGE:
   - Prepare a discount banner image with a person (recommended size: 800x400px)
   - Name it discount_banner.jpg
   - Place it in the app/src/main/res/drawable-xxhdpi folder
   - Update Home.java to use this image for the discount banner

CODE CHANGES NEEDED:

1. In Home.java, update the setupCategories() method:
```java
private void setupCategories() {
    // Existing code...
    
    List<Category> categories = new ArrayList<>();
    categories.add(new Category(R.drawable.category_stationery, "Stationery"));
    categories.add(new Category(R.drawable.category_culinary, "Culinary"));
    categories.add(new Category(R.drawable.category_electronics, "Electronics"));
    categories.add(new Category(R.drawable.category_clothing, "Clothing"));
    categories.add(new Category(R.drawable.category_furniture, "Furniture"));
    
    // Existing code...
}
```

2. In Home.java, update the setupHotDeals() method:
```java
private void setupHotDeals() {
    // Existing code...
    
    List<Product> hotDeals = new ArrayList<>();
    hotDeals.add(new Product(R.drawable.product_waterbottle, "Waterbottle", "$ 20.00"));
    hotDeals.add(new Product(R.drawable.product_phone_cover, "Phone Cover", "$ 45.00"));
    hotDeals.add(new Product(R.drawable.product_wooden_chair, "Wooden-Chair", "$ 49.00"));
    hotDeals.add(new Product(R.drawable.product_refrigerator, "Refrigerator", "$ 39.00"));
    
    // Existing code...
}
```

3. In Home.java, update the setupNewArrivals() method:
```java
private void setupNewArrivals() {
    // Existing code...
    
    List<Product> newArrivals = new ArrayList<>();
    newArrivals.add(new Product(R.drawable.product_cotton_dress, "Cotton Dress", "$ 49.00"));
    newArrivals.add(new Product(R.drawable.product_leather_sneakers, "Leather Sneakers", "$ 49.00"));
    newArrivals.add(new Product(R.drawable.product_macbook, "Macbook Air", "$ 49.00"));
    
    // Existing code...
}
```

4. In Home.java, update the onCreate() method:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    // Existing code...
    
    // Set up discount banner image
    discountImage = findViewById(R.id.discountImage);
    discountImage.setImageResource(R.drawable.discount_banner);
    
    // Existing code...
}
``` 