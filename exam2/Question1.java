/*
Develop a java application for data management about sales of different products in a company X. The company X has different stores, at each store it manitains
different product and their prices. For efficient data management the company maintains product and stores mapping in the form of records. It maintains a data 
for efficient retrieval in the form of store id as key and collection of product id and price of key-value pairs.
Example: {"Store A":{"Apple":25, "Banana":100}}
Once data is stored in database run the following queries:
1. If a store id is given retrieve and print all the products and their prices.
2. If a store id is given find product has highest priced.
3. If product name is given find and display all the stores which contains this product.
Develop a menu driven program.
*/
import java.util.*;
class Question1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        System.out.println("Enter number of stores");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter store name:");
            String StoreName = sc.nextLine().toLowerCase();
            System.out.println("Enter number of products:");
            int numProducts = sc.nextInt();
            sc.nextLine();
            HashMap<String, Integer> products = new HashMap<>();
            for (int j = 0; j < numProducts; j++) {
                System.out.println("Enter product name:");
                String product = sc.nextLine().toLowerCase();
                System.out.println("Enter price of the product");
                int price = sc.nextInt();
                sc.nextLine();
                products.put(product, price);
            }
            map.put(StoreName, products);
        }
        System.out.println(map);

        while(true){
            System.out.println("-----MENU-----");
            System.out.println("1. Find Products and Prices of a Store");
            System.out.println("2. Find Highest priced product in a Store");
            System.out.println("3. Find Stores with a product name");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){
            	System.out.println("--------------------");
                System.out.println("Enter store name:");
                String storeName = sc.nextLine().toLowerCase();
                findProductsAndPrices(map,storeName);
                System.out.println("--------------------");
            }
            else if(choice == 2){
            	System.out.println("--------------------");
                System.out.println("Enter store name:");
                String storeName = sc.nextLine().toLowerCase();
                findHighestPricedProduct(map,storeName);
                System.out.println("--------------------");
            }
            else if(choice == 3){
            	System.out.println("--------------------");
                System.out.println("Enter product name:");
                String productName = sc.nextLine().toLowerCase();
                findStoresWithProduct(map,productName);
                System.out.println("--------------------");
            }
            else if(choice == 4){
                break;
            }
            else{
                System.out.println("Invalid choice");
            }
        }
        sc.close();
    }



    static void findProductsAndPrices(HashMap<String, HashMap<String, Integer>> map, String storeName){
        
        if(map.containsKey(storeName)){
            HashMap<String, Integer> products = map.get(storeName);
            if(products.size() == 0){
                System.out.println("Store has no products");
            }
            else{
                System.out.println("Products and Prices in " + storeName + ":");
                for(Map.Entry<String, Integer> entry : products.entrySet()){
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        }
        else{
            System.out.println("Store not found...");
        }
    }

    static void findHighestPricedProduct(HashMap<String, HashMap<String, Integer>> map, String storeName) {
    
    	if(map.containsKey(storeName)) {
    		HashMap<String, Integer> products = map.get(storeName);
            if(products.size() == 0){
                System.out.println("Store has no products");
            }
            else{
                System.out.println("Highest priced prodcut in "+storeName+":");
                int highest = Integer.MIN_VALUE;
                String highestProduct = "";
                for(String productName : products.keySet()) {
                    if(products.get(productName) > highest) {
                        highest = products.get(productName);
                        highestProduct = productName;
                    }
                }

                System.out.println(highestProduct+" "+highest);
            }
    	}
    	else {
    		System.out.println("Store not found...");
    	}
    }
    
    static void findStoresWithProduct(HashMap<String, HashMap<String, Integer>> map, String productName) {
    	List<String> stores = new ArrayList<>();
    	for(String storeName : map.keySet()) {
    		HashMap<String, Integer> products = map.get(storeName);
    		if(products.containsKey(productName)) {
    			stores.add(storeName);
    		}
    	}
    	
        if(stores.size() == 0)
            System.out.println(productName+" "+ "is not available");
    	for(String store : stores) {
    		System.out.println(store);
    	}
    }
}
