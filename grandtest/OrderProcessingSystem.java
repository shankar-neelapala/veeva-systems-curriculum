import java.time.LocalDate;
import java.util.*;
class OrderProcessingSystem{
    public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    OrderService orderService = new OrderService();

    while (true) {
        System.out.println("\n===== Order Processing Menu =====");
        System.out.println("1. Add Item");
        System.out.println("2. Add Customer");
        System.out.println("3. Get Item By Name");
        System.out.println("4. Get Items By Price");
        System.out.println("5. Place Order");
        System.out.println("6. Get Order By Id");
        System.out.println("7. Get Orders By Customer");
        System.out.println("8. Highest Order Transaction");
        System.out.println("9. Lowest Order Transaction");
        System.out.println("10. Orders From Last Week");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter item name: ");
                String name = sc.nextLine();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                System.out.print("Enter reorder level: ");
                int reorder = sc.nextInt();
                orderService.addItem(new Item(name, price, quantity, reorder));
                System.out.println("Item added");
                break;

            case 2:
                System.out.print("Enter customer name: ");
                String cname = sc.nextLine();
                System.out.print("Enter address: ");
                String address = sc.nextLine();
                System.out.print("Enter phone: ");
                String phone = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                orderService.addCustomer(new Customer(cname, address, phone, email));
                System.out.println("Customer added");
                break;

            case 3:
                System.out.print("Enter item name: ");
                String searchName = sc.nextLine();
                Item item = orderService.getItemByname(searchName);
                if (item != null)
                    System.out.println("Found: " + item.getName());
                else
                    System.out.println("Item not found");
                break;

            case 4:
                System.out.print("Enter price: ");
                double p = sc.nextDouble();
                List<Item> items = orderService.getItemsByPrice(p);
                if (items.size() > 0) {
                    for (Item i : items)
                        System.out.println(i.getName());
                } else {
                    System.out.println("No items found");
                }
                break;

            case 5:
                System.out.print("Enter customer name: ");
                String custName = sc.nextLine();
                Customer foundCustomer = orderService.findCustomerByName(custName);
                if (foundCustomer == null) {
                    System.out.println("Customer not found (use same object reference in real case)");
                    break;
                }

                List<OrderItem> orderItems = new ArrayList<>();
                System.out.print("Enter number of items: ");
                int n = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < n; i++) {
                    System.out.print("Enter item name: ");
                    String iname = sc.nextLine();
                    Item it = orderService.getItemByname(iname);
                    if (it == null) {
                        System.out.println("Item not found");
                        i--;
                        continue;
                    }
                    System.out.print("Enter quantity: ");
                    int q = sc.nextInt();
                    sc.nextLine();
                    orderItems.add(new OrderItem(it, q));
                }

                orderService.placeOrder(foundCustomer, orderItems);
                break;

            case 6:
                System.out.print("Enter order id: ");
                int id = sc.nextInt();
                Order order = orderService.getOrderById(id);
                if (order != null) {
                    System.out.println("Customer: " + order.getCustomerName());
                    System.out.println("Total Items: " + order.getOrderItems().size());
                } else {
                    System.out.println("Order not found");
                }
                break;

            case 7:
                System.out.print("Enter customer name: ");
                String cnameSearch = sc.nextLine();
                List<Order> orders = orderService.getOrdersByCustomer(cnameSearch);
                for (Order o : orders) {
                    System.out.println("Order ID: " + o.getOrderId());
                }
                break;

            case 8:
                System.out.println("Highest Transaction: " + orderService.getHighestOrderTransaction());
                break;

            case 9:
                System.out.println("Lowest Transaction: " + orderService.getLowestOrderTransaction());
                break;

            case 10:
                List<Order> lastWeek = orderService.getOrdersFromLastWeek();
                for (Order o : lastWeek) {
                    System.out.println("Order ID: " + o.getOrderId());
                }
                break;

            case 0:
                System.out.println("Exiting...");
                return;

            default:
                System.out.println("Invalid choice");
        }
    }
}
}
class OrderService{
    private List<Order> orders = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Customer findCustomerByName(String name){
        for(Customer customer : customers){
            if(customer.getName().equalsIgnoreCase(name)){
                return customer;
            }
        }
        return null;
    }
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
