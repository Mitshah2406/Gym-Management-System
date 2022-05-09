package com.company;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;
import java.util.Scanner;
// Classes
class MongoConnection{
 // DataBase
        MongoClient client = MongoClients.create("mongodb+srv://Java:Java@cluster0.0ksdj.mongodb.net/GymManagementSystem?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("GymManagementSystem");
        MongoCollection<Document> coll = db.getCollection("Members");
}
class Menu{
    public int displayOptions(){
        int choice=0;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("1. About Our Gym");
        System.out.println("2. Add New Member");
        System.out.println("3. Remove Member");
        System.out.println("4. Update Member");
        System.out.println("5. Display Member List");
        System.out.println("6. Exit The Application");
//            Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter Your Choice: ");
        choice = sc.nextInt();
        System.out.println();
        System.out.println();
        return choice;
    }
}
class takeInput{
    int membershipId,age,deleteId,updateId,updateChoice;
    long mobileNumber;
    String firstName,lastName,updateField,updateValue;
    String startDate,endDate;
    Scanner sc = new Scanner(System.in);
    public void addMemberInput(){
        System.out.println("------------ Add Member ----------------");
        System.out.print("MembershipId: ");
         membershipId = sc.nextInt();
        System.out.print("First Name: ");
         firstName = sc.next();
        System.out.print("Last Name: ");
         lastName = sc.next();
        System.out.print("Age: ");
         age = sc.nextInt();
        System.out.print("Gym Start Date: ");
        startDate = sc.next();
        System.out.print("Gym End Date: ");
        endDate = sc.next();
        System.out.print("Mobile Number: ");
         mobileNumber = sc.nextLong();
    }
    public void deleteMemberInput(){
        System.out.print("Enter The membership id of the member you want to delete: ");
        deleteId = sc.nextInt();
    }
    public void updateMemberInput(){
        System.out.print("Enter The membership id of the member you want to update: ");
        updateId = sc.nextInt();
        updateChoice = 0;
        updateField = "";
        updateValue = "";
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Age");
        System.out.println("4. Gym Start Date");
        System.out.println("5. Gym End Date");
        System.out.println("6. Mobile Number");
        System.out.print("Enter The field number you wanna update: ");
        updateChoice = sc.nextInt();
    }
}
class GymOperations extends takeInput{
    Scanner sc = new Scanner(System.in);
    MongoConnection conn = new MongoConnection();
    public void aboutGym(){
        System.out.println("               About Our Gym:             ");
        System.out.println("------------------------------------------");
        System.out.println("Gold's Gym Has Been The Authority In Fitness Since 1965 Dating Back To The Original Gold's Gym In Venice, California. It Was The Place For Serious Fitness. Opened Long Before The Modern-Day Health Club Existed, The Original Gold's Gym Featured Homemade Equipment And A Dedication To Getting Results. It Was An Instant Hit. Gold's Gym Quickly Became Known As “The Mecca Of Bodybuilding.''\n" +
                "\n" +
                "In 1977, Gold's Gym Received International Attention When It Was Featured In The Movie Pumping Iron That Starred Arnold Schwarzenegger And Lou Ferrigno. From That First Gym In Venice, Gold's Gym Has Become The Largest Co-Ed Gym Chain In The World With Over 700+ Clubs In 27 Countries. Today, Gold's Gym Has Expanded Its Fitness Profile To Offer All Of The Latest Equipment And Services, Including Group Exercise, Personal Training, Cardiovascular Equipment, Spinning, And Yoga, While Maintaining Its Core Weight Lifting Tradition.\n" +
                "\n" +
                "Gold's Gym Has Become The Preferred Gym Of Celebrities, Athletes, Bodybuilders, The Military, And Fitness Enthusiasts All Over The World. Most Importantly, Gold's Gym Continues To Change Lives By Helping People Achieve Their Individual Potential.");
    }
    public void addMember(){
        Document addMember;
        try {
            addMemberInput();
            addMember = new Document("membershipId", membershipId)
                    .append("firstName", firstName)
                    .append("lastName", lastName)
                    .append("age",age)
                    .append("mobileNumber", mobileNumber)
                    .append("startDate", startDate)
                    .append("endDate", endDate);
            conn.coll.insertOne(addMember);
            System.out.println("Added Member Successfully...");
        } catch (Exception e) {
            System.out.println("Failed To Add Member...");
        }
    }
    public void removeMember(){
        deleteMemberInput();
        conn.coll.deleteOne(Filters.eq("membershipId", deleteId));
        System.out.println("Document deleted successfully...");
    }
    public void updateMember(){
        updateMemberInput();
        switch (updateChoice) {
            case 1 -> {
                updateField = "firstName";
                System.out.print("Enter the new value for first name: ");
                updateValue = sc.next();
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }
            case 2 -> {
                updateField = "lastName";
                System.out.print("Enter the new value for last name: ");
                updateValue = sc.next();
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }
            case 3 -> {
                updateField = "age";
                System.out.print("Enter the new value for age: ");
                updateValue = String.valueOf(Integer.parseInt(sc.next()));
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }

            case 4 ->{
                updateField = "startDate";
                System.out.print("Enter the new value for startDate: ");
                updateValue = String.valueOf(sc.next());
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }
            case 5 ->{
                updateField = "endDate";
                System.out.print("Enter the new value for endDate: ");
                updateValue = String.valueOf(sc.next());
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }
            case 6 -> {
                updateField = "mobileNumber";
                System.out.print("Enter the new value for mobile number: ");
                updateValue = String.valueOf(Long.parseLong(sc.next()));
                System.out.println(updateValue);
                conn.coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                System.out.println("Update Success");
            }
            default -> System.out.println("Invalid update choice...");
        }
        System.out.println("Document updated successfully...");
    }
    public void displayMember(){
        //Listing All Mongo Documents in Collection
        FindIterable<Document> iterobj
                = conn.coll.find();

        // Print the documents using iterators
        Iterator itr = iterobj.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
class SwitchStatements{
    public SwitchStatements(int choice){
        Scanner sc = new Scanner(System.in);
        MongoConnection conn = new MongoConnection();
        GymOperations gym = new GymOperations();
        switch (choice) {
            case 1 -> {
                gym.aboutGym();
            }
            case 2 -> {
                gym.addMember();
            }
            case 3 -> {
                gym.removeMember();
            }
            case 4 -> {
                gym.updateMember();
            }
            case 5 -> {
                gym.displayMember();
            }
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid Choice !!!!");
        }
    }
}



public class Gym{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------Welcome To Gym Management System---------------------");
        while(true) {
            Menu menu = new Menu();
            int choice = menu.displayOptions();
            SwitchStatements ss = new SwitchStatements(choice);
        }
    }
}