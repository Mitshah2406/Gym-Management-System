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

public class Main {

    public static void main(String[] args) {
        // DataBase
        MongoClient client = MongoClients.create("mongodb+srv://Java:Java@cluster0.0ksdj.mongodb.net/GymManagementSystem?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("GymManagementSystem");
        MongoCollection<Document> coll = db.getCollection("Members");



        System.out.println("------------Welcome To Gym Management System---------------------");
        int choice=0;
        while(true){
            System.out.println("1. Add New Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Update Member");
            System.out.println("4. Display Member List");
            System.out.println("5. Exit The Application");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("------------ Add Member ----------------");
                    System.out.print("MembershipId: ");
                    int membershipId = sc.nextInt();
                    System.out.print("First Name: ");
                    String firstName = sc.next();
                    System.out.print("Last Name: ");
                    String lastName = sc.next();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.print("Mobile Number: ");
                    long mobileNumber = sc.nextLong();
//                    Document addMember = new Document("firstName", firstName "LastName", lastName)
                    Document addMember;
                    try {
                        addMember = new Document("membershipId", membershipId)
                                .append("firstName", firstName)
                                .append("lastName", lastName)
                                .append("age", age)
                                .append("mobileNumber", mobileNumber);
                        coll.insertOne(addMember);
                        System.out.println("Added Member Successfully...");
                    } catch (Exception e) {
                        System.out.println("Failed To Add Member...");
                    }
                    break;
                case 2:
                    System.out.print("Enter The membership id of the member you want to delete: ");
                    int deleteId = sc.nextInt();
                    coll.deleteOne(Filters.eq("membershipId", deleteId));
                    System.out.println("Document deleted successfully...");
                    break;
                case 3:
                    System.out.print("Enter The membership id of the member you want to update: ");
                    int updateId = sc.nextInt();
                    int updateChoice = 0;
                    String updateField = "";
                    String updateValue = "";
                        System.out.println("1. First Name");
                        System.out.println("2. Last Name");
                        System.out.println("3. Age");
                        System.out.println("4. Mobile Number");
                    System.out.print("Enter The field number you wanna update: ");
updateChoice = sc.nextInt();
                        switch (updateChoice) {

                            case 1:
                                updateField = "firstName";
                                System.out.print("Enter the new value for first name: ");
                                updateValue = sc.next();
                                System.out.println(updateValue);
                                coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                                System.out.println("Update Success");
                                break;
                            case 2:
                                updateField = "lastName";
                                System.out.print("Enter the new value for last name: ");
                                updateValue = sc.next();
                                System.out.println(updateValue);
                                coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                                System.out.println("Update Success");
                                break;
                            case 3:
                            updateField = "age";
                            System.out.print("Enter the new value for age: ");
                            updateValue = String.valueOf(Integer.parseInt(sc.next()));
                            System.out.println(updateValue);
                            coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                            System.out.println("Update Success");
                            break;
                            case 4:
                                updateField = "mobileNumber";
                                System.out.print("Enter the new value for mobile number: ");
                                updateValue = String.valueOf(Long.parseLong(sc.next()));
                                System.out.println(updateValue);
                                coll.updateOne(Filters.eq("membershipId", updateId), Updates.set(updateField, updateValue));
                                System.out.println("Update Success");
                                break;
                            default:
                                System.out.println("Invalid update choice...");
                        }

                    System.out.println("Document updated successfully...");
                    break;
                case 4:
                    //Listing All Mongo Documents in Collection
                    FindIterable<Document> iterobj
                            = coll.find();

                    // Print the documents using iterators
                    Iterator itr = iterobj.iterator();
                    while (itr.hasNext()) {
                        System.out.println(itr.next());
                    }
                    break;
                case 5:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid Choice !!!!");
            }
        }
    }
}
