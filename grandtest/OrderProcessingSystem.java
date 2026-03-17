import java.time.LocalDate;
import java.util.*;
class OrderProcessingSystem{
    public static void main(String args[]){
        Item item1 = new Item("Brush", 15, 10, 2);
        Item item2 = new Item("Colgate", 20, 15, 5);
        Item item3 = new Item("Oreo", 5, 5, 2);
        Customer cust1 = new Customer("Abdul", "Tanuku", "1234567890","abdul@gmail.com");
        Customer cust2 = new Customer("Maxwell", "Tadepalligudem", "987654321", "maxwell@gmail.com");
        OrderService orderService = new OrderService();
        orderService.addItem(item1);
        orderService.addItem(item2);
        orderService.addItem(item3);
        orderService.addCustomer(cust1);
        orderService.addCustomer(cust2);

        System.out.println("Item Name: "+orderService.getItemByname("Brush").getName());
        List<Item> itemRes = orderService.getItemsByPrice(20);
        if(itemRes.size() > 0){
            for(Item item : itemRes){
            System.out.println("Item Name: "+item.getName());
            }
        }
        else{
            System.out.println("No items found");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem(item1, 2);
        OrderItem orderItem2 = new OrderItem(item3, 3);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2); 
        orderService.placeOrder(cust1, orderItems);

        Order orderRes = orderService.getOrderById(1);
        if(orderRes != null){
            System.out.println("Customer Name: "+orderRes.getCustomerName());
            System.out.println("Total items orders: "+orderRes.getOrderItems().size());
        }
        else{
            System.out.println("Order not found");
        }

        System.out.println("Highest order transaction: "+orderService.getHighestOrderTransaction());
        System.out.println("Lowest order transaction: "+orderService.getLowestOrderTransaction());
        List<Order> orders = orderService.getOrdersFromLastWeek();
        if(orders.size() > 0){
            for(Order order : orders){
                System.out.println("Order Id: "+order.getOrderId());
            }
        }
        else{
            System.out.println("No orders found"); 
        } 
    }
}
class OrderService{
    private List<Order> orders = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void placeOrder(Customer customer, List<OrderItem> orderItems){
        for(OrderItem orderItem : orderItems){
            if(orderItem.getItem().getQuantity() <= orderItem.getItem().getReOrderLevel()){
                System.out.println("Order should not be placed, item below reorder level "+ orderItem.getItem().getName());
                return;
            }
            else if(orderItem.getQuantity() > orderItem.getItem().getQuantity()){
                System.out.println("Order should not be placed, item out of stock");
                return;
            }
        }

        for(OrderItem orderItem : orderItems){
            orderItem.getItem().updateQuantity(orderItem.getQuantity());
        }
        Order order = new Order(customer, orderItems);
        orders.add(order);
        System.out.println("Order successfully placed, order id "+order.getOrderId());
    }

    public Item getItemByname(String name){
        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }

    public List<Item> getItemsByPrice(double price){
        List<Item> itemsByPrice = new ArrayList<>();
        for(Item item : items){
            if(item.getPrice() == price){
                itemsByPrice.add(item);
            }
        }
        return itemsByPrice;
    }

    public Order getOrderById(int id){
        for(Order order : orders){
            if(order.getOrderId() == id){
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrdersByCustomer(String name){
        List<Order> getOrdersByCustomer = new ArrayList<>();
        for(Order order : orders){
            if(order.getCustomerName().equalsIgnoreCase(name)){
                getOrdersByCustomer.add(order);
            }
        }
        return getOrdersByCustomer;
    }

    public double getHighestOrderTransaction(){
        List<Order> temp = new ArrayList<>(orders);
        Collections.sort(temp, new Comparator<Order>(){
            public int compare(Order o1, Order o2){
                return Double.compare(o2.getTotalPrice(), o1.getTotalPrice());
            }
        });
        return temp.get(0).getTotalPrice();
    }

    public double getLowestOrderTransaction(){
        List<Order> temp = new ArrayList<>(orders);
        Collections.sort(temp, new Comparator<Order>() {
            public int compare(Order o1, Order o2){
                return Double.compare(o1.getTotalPrice(), o2.getTotalPrice());
            }
        });
        return temp.get(0).getTotalPrice();
    }

    public List<Order> getOrdersFromLastWeek(){
        List<Order> ordersLastWeek = new ArrayList<>();
        LocalDate date = LocalDate.now().minusDays(7);
        for(Order order : orders){
            if(order.getDate().isAfter(date)){
                ordersLastWeek.add(order);
            }
        }
        return ordersLastWeek;
    }
}

class Item{
    private String name;
    private double price;
    private int quantity;
    private int reOrderLevel;

    public Item(String name, double price, int quantity, int reOrderLevel){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.reOrderLevel = reOrderLevel;
    } 
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getReOrderLevel(){
        return reOrderLevel;
    }
    public void updateQuantity(int quantity){
        this.quantity -= quantity;
    }
    public void setReOrderLevel(int reOrderLevel){
        this.reOrderLevel = reOrderLevel;
    }
}

class Customer{
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer(String name, String address, String phoneNumber, String email){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail(){
        return email;
    }
}

class OrderItem{
    private Item item;
    private int quantity;

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

}

class Order{
    private static int orderCount = 0;
    private int orderId;
    private Customer customer;
    private List<OrderItem> orderItems;
    private LocalDate date;
    private double totalPrice;

    public Order(Customer customer, List<OrderItem> orderItems) {
        this.orderId = ++orderCount;
        this.customer = customer;
        this.orderItems = orderItems;
        this.date = LocalDate.now();
        this.totalPrice = getTotalPrice();
    }

    public LocalDate getDate(){
        return date;
    }
    public int getOrderId(){
        return orderId;
    }
    public String getCustomerName(){
        return customer.getName();
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }    
}