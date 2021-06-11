package com.meme;
import Users.Player;
import Users.PlaygroundOwner;
import Users.Role;
import Users.User;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramInterface {
    private User user;
    SystemGoFo system = new SystemGoFo();

    public void displayPlaygroundRegisterationForm(){
        Scanner sc= new Scanner(System.in);

        System.out.println("Please enter playground name: ");
        String playgroundName = sc.nextLine();

        System.out.println("Please enter playground location: ");
        String location = sc.nextLine();

        System.out.println("Please enter playground type: ");
        String playgroundType = sc.nextLine();

        System.out.println("Please enter playground length: ");
        String lengthStr = sc.nextLine();
        double length = Double.parseDouble(lengthStr);

        System.out.println("Please enter playground width: ");
        String widthStr = sc.nextLine();
        double width = Double.parseDouble(widthStr);

        Size playgroundSize = new Size(width,length);

        System.out.println("Enter time available | time from: ");
        String timeFrom = sc.nextLine();

        System.out.println("Enter time available | time to: ");
        String timeTo = sc.nextLine();

        TimeSlot timeSlot = new TimeSlot(timeFrom,timeTo);
        System.out.println("Please enter price per hour: ");
        String pricePerHourStr = sc.nextLine();
        double pricePerHour = Double.parseDouble(pricePerHourStr);

        System.out.println("Please enter cancellation period");

        System.out.println("Enter days ");
        String daysStr = sc.nextLine();
        int days = Integer.parseInt(daysStr);

        System.out.println("Enter hours ");
        String hoursStr = sc.nextLine();
        int hours = Integer.parseInt(hoursStr);

        System.out.println("Enter minutes ");
        String minutesStr = sc.nextLine();
        int minutes = Integer.parseInt(minutesStr);

        TimePeriod cancelationPeriod = new TimePeriod(days,hours,minutes);

        Playground playground = new Playground(playgroundName,location,playgroundType,playgroundSize,timeSlot,pricePerHour,cancelationPeriod);
        ((PlaygroundOwner) user.getConcreteRole()).addPlayground(playground);
        system.addPlayground(playground);
        System.out.println("Playground has been added successfully");
    }
    public void userRegisterationForm(){
        Scanner sc = new Scanner(System.in);
        boolean Success = false;
        String userName = new String();
        String userId = new String();
        String userEmail = new String();
        String userPassword = new String();
        String userPhone = new String();
        String userLocation = new String();
        Role userRole = null;
        while(true) {
            System.out.println("Are you a playground owner or a player ?");
            System.out.println("Enter 0 if your are a playground owner enter 1 if you are a player");
            String choiceStr = sc.nextLine();
            int choice = Integer.parseInt(choiceStr );
            if (choice == 0) {
                userRole = new PlaygroundOwner("PlaygroundOwner");
            } else if (choice == 1) {
                userRole = new Player("Player");
            }
            else {
                continue;
            }
            System.out.println("Please enter your name: ");
            userName = sc.nextLine();

            while (true) {
                System.out.println("Please enter your Id: ");
                userId = sc.nextLine();
                if (system.IdExists(userId)) {
                    System.out.println("This ID already exists please enter another ID");
                    continue;
                }
                break;
            }
            System.out.println("Please enter your password: ");
            userPassword = sc.nextLine();

            System.out.println("Please enter your email: ");
            userEmail = sc.nextLine();

            System.out.println("Please enter your phone: ");
            userPhone = sc.nextLine();

            System.out.println("Please enter your location: ");
            userLocation = sc.nextLine();

            break;
        }
        User newUser =new User(userName,userId,userPassword,userEmail,userPhone,userLocation, userRole);
        system.addUser(newUser);
        user = newUser;


    }
        public void displayBookingForm(Playground playground){

        System.out.println("enter your booking date in \"yyyy:mm:dd\" format");
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();

        System.out.println("enter your booking start time  in \"hh:mm:ss\" format");
        String startTime = sc.nextLine();

        System.out.println("enter your booking end time  in \"hh:mm:ss\" format");
        String endTime = sc.nextLine();

        WorkDay Wday = new WorkDay(date, startTime, endTime);

        Booking booking =new Booking(Wday,playground);
        ((Player) user.getConcreteRole()).addBooking(booking);
        system.addBooking(booking);

        System.out.println("Playground is booked successfully");

    }
    public void displayPlayground(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Playground> playgrounds = system.getPlaygrounds();
        for(int i = 0; i< playgrounds.size(); i++){
            System.out.println((playgrounds.get(i)));
        }
        System.out.println("Enter 0 to return to main menu or 1 to book a playground");
        String choiceStr = sc.nextLine();
        int choice = Integer.parseInt(choiceStr);
        if(choice == 0)
            return;
        else if(choice == 1) {
            System.out.println("Enter the id of the playground you want to book: ");
            String playgroundId = sc.nextLine();
            for (int i = 0; i < playgrounds.size(); i++) {
                if (playgrounds.get(i).getPlaygroundID().equals(playgroundId)) {
                    displayBookingForm(playgrounds.get(i));
                    break;
                }
            }
            System.out.println("No such playground exists !!");
        }
    }
    public void loginForm() {

            user = null;
            do {
                System.out.println("Enter ID");
                Scanner sc = new Scanner(System.in);
                String ID = sc.nextLine();
                System.out.println("Enter Password");

                String Pass = sc.nextLine();
                user = system.verfiyUser(ID,Pass);
                if(user == null) {
                    System.out.println("WRONG ID OR PASSWORD !!");
                }
            } while (user == null);
    }
    public void displayUserMenu()
    {
        Scanner sc = new Scanner(System.in);
        if(user.getRole().equals("Player"))
        {
            System.out.println("Hi player");
            System.out.println("Enter 0 to logout");
            System.out.println("Enter 1 to view/book Playgrounds");
            System.out.println("Enter 2 to display my bookings");
            String choiceStr = sc.nextLine();
            int choice = Integer.parseInt(choiceStr);
            if(choice == 0) {
                user = null;
                return;
            }
            if(choice == 1)
            {
                displayPlayground();
            }
            else if(choice == 2)
            {
                ArrayList<Booking> bookings = ((Player) user.getConcreteRole()).getBookings();
                for(int  i= 0;i < bookings.size(); i++)
                {
                    System.out.println(bookings.get(i));
                }
            }
        }

        else if(user.getRole().equals("PlaygroundOwner"))
        {
            System.out.println("Hi playgroudOwner");
            System.out.println("Enter 0 to logout");
            System.out.println("Enter 1 to view your Playgrounds (playgrounds info and when they are available and who booked them and when");
            System.out.println("Enter 2 to register a Playground");
            String choiceStr = sc.nextLine();
            int choice = Integer.parseInt(choiceStr);
            if(choice == 0) {
                user = null;
                return;
            }
            else if(choice == 1)
            {

            }
            else if(choice == 2)
            {
                displayPlaygroundRegisterationForm();
            }
        }
    }
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("type 0 if you want to register, type 1 if you want to login");
            String choiceStr = sc.nextLine();
            int choice = Integer.parseInt(choiceStr);
            if(choice == 0) userRegisterationForm();
            else if(choice == 1) loginForm();
            displayUserMenu();
        }
    }



}