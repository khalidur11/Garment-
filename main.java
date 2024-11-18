import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class GarmentManagement {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Fabric input
        System.out.print("Enter Fabric Code: ");
        String fabricCode = input.nextLine();
        System.out.print("Enter Fabric Category: ");
        String fabricCategory = input.nextLine();
        System.out.print("Enter Fabric Shade: ");
        String fabricShade = input.nextLine();
        System.out.print("Enter Cost Per Meter of Fabric: ");
        double costPerMeter = input.nextDouble();
        System.out.println("================================");
        input.nextLine();

        // Create Fabric instance
        Material material = new Material(fabricCode, fabricCategory, fabricShade, costPerMeter);

        // Garment input
        System.out.print("Enter Garment Code: ");
        String garmentCode = input.nextLine();
        System.out.print("Enter Garment Title: ");
        String garmentTitle = input.nextLine();
        System.out.print("Enter Garment Details: ");
        String garmentDetails = input.nextLine();
        System.out.print("Enter Garment Size: ");
        String garmentSize = input.nextLine();
        System.out.print("Enter Garment Color: ");
        String garmentColor = input.nextLine();
        System.out.print("Enter Garment Price: ");
        double garmentPrice = input.nextDouble();
        System.out.print("Enter Available Stock: ");
        int availableStock = input.nextInt();
        System.out.println("================================");
        input.nextLine();

        // Create Garment instance
        Apparel apparel = new Apparel(garmentCode, garmentTitle, garmentDetails, garmentSize, garmentColor, garmentPrice, availableStock, material);

        // Customer input
        System.out.print("Enter Buyer ID: ");
        String buyerId = input.nextLine();
        System.out.print("Enter Buyer Name: ");
        String buyerName = input.nextLine();
        System.out.print("Enter Buyer Email: ");
        String buyerEmail = input.nextLine();
        System.out.print("Enter Buyer Phone Number: ");
        String buyerPhone = input.nextLine();
        System.out.println("================================");

        // Create Customer instance
        Buyer buyer = new Buyer(buyerId, buyerName, buyerEmail, buyerPhone);

        // Order processing
        System.out.print("Enter Transaction ID: ");
        String transactionId = input.nextLine();
        Date transactionDate = new Date();

        Purchase purchase = new Purchase(transactionId, transactionDate);
        purchase.addItem(apparel);
        buyer.completePurchase(purchase);
        purchase.displayPurchaseSummary();

        input.close();
    }
}

// Apparel class
class Apparel {
    public String code;
    public String title;
    public String details;
    public String size;
    public String color;
    public double price;
    public int stock;
    public Material material;

    public Apparel(String code, String title, String details, String size, String color, double price, int stock, Material material) {
        this.code = code;
        this.title = title;
        this.details = details;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.material = material;
    }

    void modifyStock(int newStock) {
        this.stock = newStock;
    }

    double computeDiscountedPrice(double discountRate) {
        return price - (price * discountRate / 100);
    }
}

// Material class
class Material {
    public String code;
    public String category;
    public String shade;
    public double costPerUnit;

    public Material(String code, String category, String shade, double costPerUnit) {
        this.code = code;
        this.category = category;
        this.shade = shade;
        this.costPerUnit = costPerUnit;
    }
  double calculateTotalCost(double length) {
        return length * costPerUnit;
    }
}

// Buyer class
class Buyer {
    public String id;
    public String name;
    public String email;
    public String phone;
    public List<Purchase> purchaseHistory;

    public Buyer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.purchaseHistory = new ArrayList<>();
    }

    void completePurchase(Purchase purchase) {
        purchaseHistory.add(purchase);
        System.out.println("Purchase successful!");
    }

    List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }
}

// Purchase class
class Purchase {
    public String transactionId;
    public Date transactionDate;
    public List<Apparel> purchasedItems;
    private double totalPrice;

    public Purchase(String transactionId, Date transactionDate) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.purchasedItems = new ArrayList<>();
    }

    void addItem(Apparel item) {
        purchasedItems.add(item);
    }

    double computeTotalPrice() {
        totalPrice = 0; // Reset total before calculating
        for (Apparel item : purchasedItems) {
            totalPrice += item.price;
        }
        return totalPrice;
    }

    void displayPurchaseSummary() {
        System.out.println("====== Purchase Details ======");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Transaction Date: " + transactionDate);
        for (Apparel item : purchasedItems) {
            System.out.println("Item: " + item.title);
            System.out.println("Price: $" + item.price);
            System.out.println("Details: " + item.details);
        }
        System.out.println("Total Amount: $" + computeTotalPrice());
    }
}
