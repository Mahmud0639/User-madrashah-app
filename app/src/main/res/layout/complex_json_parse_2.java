RequestQueue requestQueue = Volley.newRequestQueue(context);
String url = "YOUR_JSON_API_URL";

JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
    new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            // Parse the JSON response here
            parseJSON(response);
        }
    },
    new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // Handle error
        }
    });

// Add the request to the RequestQueue
requestQueue.add(jsonObjectRequest);
------------------------------------------------------
//calling method of parseJSON
private void parseJSON(JSONObject response) {
    try {
        // Get the data object
        JSONObject data = response.getJSONObject("data");

        // Get the products array
        JSONArray productsArray = data.getJSONArray("products");

        // Iterate through each product
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productsArray.length(); i++) {
            // Get the product object
            JSONObject productObject = productsArray.getJSONObject(i);

            // Extract product details
            int id = productObject.getInt("id");
            String name = productObject.getString("name");
            double price = productObject.getDouble("price");
            String description = productObject.getString("description");

            // Extract categories array
            JSONArray categoriesArray = productObject.getJSONArray("categories");
            List<Category> categories = new ArrayList<>();
            for (int j = 0; j < categoriesArray.length(); j++) {
                String categoryName = categoriesArray.getString(j);
                Category category = new Category(categoryName);
                categories.add(category);
            }

            // Extract reviews array
            JSONArray reviewsArray = productObject.getJSONArray("reviews");
            List<Review> reviews = new ArrayList<>();
            for (int k = 0; k < reviewsArray.length(); k++) {
                JSONObject reviewObject = reviewsArray.getJSONObject(k);
                int reviewId = reviewObject.getInt("id");
                double rating = reviewObject.getDouble("rating");
                String comment = reviewObject.getString("comment");

                Review review = new Review(reviewId, rating, comment);
                reviews.add(review);
            }

            // Create a Product object with the extracted data
            Product product = new Product(id, name, price, description, categories, reviews);
            products.add(product);
        }

        // Process the extracted product list as needed
        // e.g., pass it to an adapter for displaying in a RecyclerView

    } catch (JSONException e) {
        e.printStackTrace();
    }
}
