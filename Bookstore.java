import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//creating book class 
class Book{
    private String title;
    private String author;     // ENCAPSULATION 
    private double price;     // the class encapsulates the properties of book
    private int quantity;    // fields are marked private to restrict direct access
    
    public Book (String title, String author, double price, int quantity){
    this.title = title;
    this.author = author;
    this.price = price;
    this.quantity = quantity;
}

// getter methods for title, author, price and quantity 
public String getTitle(){
    return title;
}
public String getauthor(){
    return author;
}
public double getprice(){
    return price;
}
public int getquantity(){
    return quantity;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}
}

// class to represent item in the shopping cart 
class ShoppingCartItem{
    private Book book;
    private int quantity;

    public ShoppingCartItem(Book book, int quantity){
        this.book=book;
        this.quantity=quantity;
    }

// getter methods
public Book getBook(){
    return book;
}

public int getQuantity(){
    return quantity;
}
}

//class to represent shopping cart 
class ShoppingCart{
    private List<ShoppingCartItem> items;
    
    public ShoppingCart(){
        this.items=new ArrayList<>();  //initializes items as an empty Array list
    }
    
    // methods to add/ remove and return the items in and from the cart
    public void addItem (Book book, int quantity){
        items.add(new ShoppingCartItem(book, quantity));
    }

    public void removeItem (ShoppingCartItem item){
        items.remove(item);
    }

    public List <ShoppingCartItem> getItems(){
        return items;
    }

//class to calculate total price of items
    public double calculateTotalPrice(){
        double totalPrice=0.0;
        for (ShoppingCartItem item: items){
           totalPrice = item.getBook().getprice()*item.getQuantity();
        }
        return totalPrice;
    }
}

//main bookstore class 
public class Bookstore{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        // Creating / adding books
        List<Book> books = new ArrayList <>();
        books.add(new Book("Clean Code", "Robert Martin", 29.99, 10));
        books.add(new Book("Python Basics", "Jane Smith", 24.99, 15));
    
    //an instance of shopping cart 
    ShoppingCart cart= new ShoppingCart();
    // Loop for Menu choice / displayed to the user 
    boolean exit=false;
    while (!exit) {
        displayMenu();
        int choice = getChoice();
        switch(choice){
            case 1:
            browsebooks (books);
            break;

            case 2:
            addtocart(books, cart);
            break;

            case 3:
            removefromcart(cart);
            break;

            case 4:
            viewcart(cart);
            break;

            case 5:
            checkout();
            break;

            case 6:
            exit=true;
            
            default:
            System.out.println("Invalid Choice, Try again!");
        }
    }
    scanner.close();
}

//Menu Options
private static void displayMenu(){
    System.out.println("Menu Options:");
    System.out.println("1. Browse Books");
    System.out.println("2. Add To Cart");
    System.out.println("3. Remove From Cart");
    System.out.println("4. View Cart");
    System.out.println("5. Checkout");    
    System.out.println("6. Exit");
}

//User Choice
private static int getChoice(){
    System.out.println("Enter Choice");
    return scanner.nextInt();
}

//Browse Books
private static void browsebooks(List<Book>books){
    System.out.println("Available Books");
    for(int i=0; i<books.size();i++){
        Book book=books.get(i);
        System.out.println((i+1)+"."+book.getTitle()+"-Rs."+book.getprice());
    }
}

//Add to cart 
private static void addtocart(List<Book>books, ShoppingCart cart){
    browsebooks(books);
    System.out.println("Enter Book Number:");
    int bookIndex=scanner.nextInt();
    System.out.println("Enter Quantity");
    int quantity=scanner.nextInt();
    scanner.nextLine();

//Adjusting index to be zero-based
    bookIndex--;
    if(bookIndex>=0 && bookIndex<books.size()){
        Book selectedBook=books.get(bookIndex);
        if (selectedBook.getquantity()>=quantity) {
           cart.addItem(selectedBook, quantity);
           System.out.println("Books added to cart");
        }
        else{
          System.out.println("Insufficient quantity available");
        }

    
    }
}

//Remove from cart
private static void removefromcart(ShoppingCart cart){
    List<ShoppingCartItem> items=cart.getItems();
    if(items.isEmpty()){
        System.out.println("Cart is Empty");
        return;
    }
    System.out.println("Cart Contents");
    for(int i=0; i<items.size();i++){
        ShoppingCartItem=items.get(i);
        System.out.println((i+1)+ "." +items.getBook().getTitle()+"x"+items.getQuantity());
    }
    System.out.println("Enter Item number to remove:");
    int itemIndex=scanner.nextInt()-1; //adjustment for zero based index
    scanner.nextLine();

    if (itemIndex>=0 && itemIndex <items.size()){
    cart.removeItem(items.get(itemIndex));
    System.out.println("~~~~Item Removed~~~~");
    } 
    else{
    System.out.println("invalid Item Number");
    }
}

//View cart contents
private static void viewcart(ShoppingCart cart){
    List<ShoppingCartItem> items= cart.getItems();
    if(items.isEmpty()){
        System.out.println("Cart is empty");
        return;
    }
    System.out.println("Cart contents:");
    for (int i=0; i<items.size();i++){
        ShoppingCartItem item=items.get(i);
        System.out.println(i+1+ "."+item.getBook().getTitle()+ " " +"x"+ " " +item.getQuantity());
    }
    System.out.println("Total Price:"+ cart.calculateTotalPrice());
}

//Placeholder method
private static void checkout() {

// Placeholder message for future implementation
    System.out.println("Checkout feature coming soon.");
}
}

