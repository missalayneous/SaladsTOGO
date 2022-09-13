import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderingSystem {

    //Main Method - called when the program runs
    public static void main (String[] args){

        //Declare a variable to hold the STG greeting
        System.out.print("---------------------------------------------------------------------");
        String greeting = "\nWelcome to Salads to Go!";

        //Declare a variable to hold the slogan
        String slogan = "Build your own salads, your way. " +
                "\n---------------------------------------------------------------------" +
                "\nTime to build a salad!";

        //Declare boolean variable to track if the saladOrder is vegan or not
        boolean isVegan;

        //Declare three arraylists to hold the greens, proteins and toppings
        ArrayList<String> greens = new ArrayList<String>();
        ArrayList<String> proteins = new ArrayList<String>();
        ArrayList<String> toppings = new ArrayList<String>();

        //Add the values to the arraylists
        greens.add("Kale");
        greens.add("Romaine");
        greens.add("Iceberg");

        proteins.add("Tuna");
        proteins.add("Chicken");
        proteins.add("BlackBeans");

        toppings.add("Cheese");
        toppings.add("Croutons");
        toppings.add("Tomatoes");
        toppings.add("Onions");
        toppings.add("Bacon");
        toppings.add("Olives");

        //Display the greeting and slogan to the customer
        System.out.println(greeting);
        System.out.println(slogan);

        //Call the method to display the greens types to the customer
        System.out.println("\nFirst, we'll start with the base of your salad: the Greens! ~" +
                "\nWe have:");
        displayIngredients(greens);

        //Prompt the user to choose a type of salad greens
        System.out.println("Which type of greens would you like?");

        //Get the type of greens as input from the user using the Scanner class
        Scanner input = new Scanner(System.in);
        String userGreens = input.next();

        //Create a new salad object with the greens
        Salad customerSalad = new Salad();
        customerSalad.setGreens(userGreens);

        //Call the method to display the proteins to the customer
        System.out.println("Next, we will select a protein for your salad. +\n" +
                "The types of proteins that we have are:");
        displayIngredients(proteins);

        //Prompt the user to choose the type of protein
        System.out.println("Which type of protein would you like?");
        String userProtein = input.next();
        customerSalad.setProtein(userProtein);

        //Determine if the selected protein is Vegan
        if(userProtein.equalsIgnoreCase("tuna") || userProtein.equalsIgnoreCase("chicken")){
            isVegan = false;
        }else{
            isVegan = true;
        }//end if

        //Create an arraylist to hold the customers toppings
        ArrayList<String> userToppings = new ArrayList<String>();

        System.out.println("Finally, let's choose our toppings." +
                "\nPick as many as you like!");

        //Use a while loop to keep prompting the user to
        //choose toppings until they are finished
        boolean wantsMoreToppings = true;
        while(wantsMoreToppings){

            //Prompt the user to choose a topping & display to the customer

            displayIngredients(toppings);
            System.out.println("What type of topping do you want?");

            //Get the topping from the user and add it to the
            //customers list of toppings on the same line
            String userTopping = input.next();
            userToppings.add(userTopping);

            //Determine if the selected topping is Vegan
            if(userTopping.equalsIgnoreCase("cheese") || userTopping.equalsIgnoreCase("bacon")){
                isVegan = false;
            }//end if

            //Ask the user if they want another topping
            System.out.println("Do you want to add another topping to your salad? [Yes or No]");

            //If the user answers Yes, stop looping, else keep going
            String userResponse = input.next();

            if(userResponse.equalsIgnoreCase("yes")){
                wantsMoreToppings = true;
            } else {
                wantsMoreToppings = false;
            }//end if statement

        }//end while loop

        //Add the customers toppings to the salad
        customerSalad.setToppings(toppings);

        //Display a message to the user showing the type of salad they ordered
        System.out.print("---------------------------------------------------------------------");
        System.out.println("\nYou ordered a " + customerSalad.getProtein() + " salad on " + customerSalad.getGreens() + ".");

        //Use a loop to display the toppings the user ordered on their salad
        if(userToppings.size() > 0) {   //see if the user chose a topping

            //Print a message about the topping list
            System.out.println("You chose the following toppings: ");
            for (String topping : userToppings) {
                System.out.print(topping + " ");
            }//end for each
        }//end if

        System.out.print("\n---------------------------------------------------------------------");

        //If the salad is Vegan, display a message indicating as much, otherwise thank them for the order
        if(isVegan){
            System.out.println("\n*Your custom salad is Vegan!*");
            System.out.println("\nThank you for ordering a salad from Salads to Go!");
        } else {
            System.out.println("\nThank you for ordering a salad from Salads to Go!");
        }//end if

        //Open a file called SaladOrder.txt and write the info about the salad to the file, including
        //the greens type, protein type, and toppings

        try { //handles IO Exceptions thrown by writing to a file
            //create a file writer
            FileWriter file = new FileWriter("SaladOrder.txt");
            //create BufferedWriter
            BufferedWriter buffer = new BufferedWriter(file);

            //Write the salad info to the file
            buffer.write("The salad greens are: " + customerSalad.getGreens());
            buffer.write("\nThe protein is: " + customerSalad.getProtein());

            //Loop through the toppings to add them to the file
            for(String topping : userToppings){
                buffer.write("\nTopping: " + topping);
            }//end for loop

            //close the writer
            buffer.close();

        }catch (IOException e){
            e.printStackTrace();
        }//end try catch

    }//end main

//=====================================================================================================================
    //Method to display the salad ingredients to the customer
    //This method will read each array (protein, toppings, etc) and display each item in the array
    private static void displayIngredients(ArrayList<String> ingredients){

        //Use a loop to iterate over the items in the ArrayList and display them to the customer
        for(String ingredient : ingredients){
            System.out.print(ingredient + "\n");
        }//end for each loop

    }//end method displayIngredients
//=====================================================================================================================
}//end class
