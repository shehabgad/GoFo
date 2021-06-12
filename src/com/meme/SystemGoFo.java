package com.meme;

import Users.PlaygroundOwner;
import Users.Role;
import Users.User;

import java.awt.print.Book;
import java.util.ArrayList;

public class SystemGoFo {
    private ArrayList<User> users;
    private ArrayList<Playground> playgrounds;
    private ArrayList<Booking> bookings;
    long PlaygroundId;
    long BookingId;
    public SystemGoFo()
    {
        PlaygroundId = 1;
        BookingId = 1;
        users = new ArrayList<User>();
        playgrounds = new ArrayList<Playground>();
        bookings = new ArrayList<Booking>();
    }

    public void addPlayground(Playground playground)
    {
        String playgroundId = Long.toString(PlaygroundId);
        playground.setId(playgroundId);
        playgrounds.add(playground);
        PlaygroundId++;
    }
    public void addUser(User user){
         users.add(user);
    }
    public void addBooking(Booking booking)
    {
        booking.setId(Long.toString(BookingId));
        Role playgroundOwner = getPlaygroundOwner(booking);
        ((PlaygroundOwner) playgroundOwner).addBooking(booking);
        bookings.add(booking);
        BookingId++;

    }
    public ArrayList<Playground> getPlaygrounds()
    {
        return playgrounds;
    }
    public User verfiyUser(String ID, String password)
    {
          for(int i = 0; i < users.size(); i++)
          {
                    if(users.get(i).getId().equals(ID) && users.get(i).getPassword().equals(password))
                    {
                        return users.get(i);
                    }
          }
          return null;
    }
    public Role getPlaygroundOwner(Booking booking)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getRole().equals("PlaygroundOwner"))
            {
                User user = users.get(i);
                if(user.getId().equals(booking.getPlaygroundOwnerId()))
                {
                     return user.getConcreteRole();
                }

            }
        }
        return null;
    }

    public boolean IdExists(String id)
    {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(id))
                    return true;


            }
        return false;
    }

}
