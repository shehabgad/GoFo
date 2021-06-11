package com.meme;
import java.awt.print.Book;
import java.security.PublicKey;
import java.util.Scanner;
import Users.Player;

import java.util.ArrayList;

/**
 * @author shehab eldin khaled mohamed
 * this class reperesents the Booking in the GoFo System
 */
public class Booking {
    private WorkDay bookingWorkDay;
    private Playground playground;
    private String playerID;
    ArrayList<Player> playerTeam;
    private boolean teamCompleted;
    private BookingStatus bookingStatus;
    private String bookingID;
    private boolean lookingForATeam;

    public Booking(WorkDay wday, Playground playground) {
        bookingWorkDay = wday;
        this.playground = playground;
        bookingStatus = BookingStatus.Pending;
    }
    public void setBookingStatus(BookingStatus bookingStatus)
    {
        this.bookingStatus = bookingStatus;
    }
    public String getBookingID()
    {
        return bookingID;
    }
    /**
     * getBookingStatus method returns the status of this booking
     * @return bookingStatus the status of this booking
     */
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    /**
     *
     * @return String that represents meaningful data about the booking
     */
    public String toString()
    {
        String bookingInfo = new String("");
        bookingInfo = bookingInfo.concat("Booking Status: ");
        if(bookingStatus == BookingStatus.Accepted) bookingInfo = bookingInfo.concat("Accepted");
        else if(bookingStatus == BookingStatus.Pending) bookingInfo = bookingInfo.concat("Pending");
        else if(bookingStatus == BookingStatus.Denied) bookingInfo = bookingInfo.concat("Denied");
        else if(bookingStatus == BookingStatus.Canceled) bookingInfo = bookingInfo.concat("Cancelled");
        bookingInfo = bookingInfo.concat("\n");

        bookingInfo = bookingInfo.concat("Playground name: ");
        bookingInfo = bookingID.concat(playground.getName() + "\n");
        bookingInfo = bookingInfo.concat("Player ID: " + playerID + "\n");
        bookingInfo = bookingID.concat("Team completed: " + teamCompleted + "\n");
        bookingInfo = bookingID.concat("Booking status: " + bookingStatus + "\n");
        bookingInfo = bookingID.concat("Booking ID: " + bookingID + "\n");
        bookingInfo = bookingID.concat("looking for a team: " + lookingForATeam + "\n");



        return bookingInfo;
    }

    /**
     *
     * @return Workday returns the workDay information about the booking
     */
    public WorkDay getBookingWorkDay() {
        return bookingWorkDay;
    }

    /**
     *
     * @return playerId the playgroundOwner ID
     */
    public String getPlaygroundOwnerId()
    {
        return playerID;
    }
    /**
     *
     * @return boolean true if the team is completed and false if it is not completed
     */
    public boolean isTeamCompleted()
    {
        return teamCompleted;
    }

    public void setId(String id)
    {
        bookingID = id;
    }
}
