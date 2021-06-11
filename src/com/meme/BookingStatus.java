package com.meme;

/**
 * @author Peter Essam Maguid Isaac
 * Booking Status that can be used
 * {@link #Accepted
 * @link #Canceled
 * @link #Pending
 * @link #Denied}
 */

public enum BookingStatus {
    /**
     * booking is accepted
     */
    Accepted,

    /**
     * booking is still Pending
     */
    Pending,

    /**
     * booking is Denied
     */
    Denied,

    /**
     * booking is Canceled
     */
    Canceled
}
