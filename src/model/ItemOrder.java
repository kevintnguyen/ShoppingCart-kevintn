/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ItemOrder class stores item objects and integer quantity, for an overall
 * itemOrder.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public final class ItemOrder {
    /**
     * myItem stores the item that is passed by constructor as an item object.
     */
    private final Item myItem;
    /**
     * myQuantity stores the quantity that is passed by the constructor as an
     * integer.
     */
    private final int myQuantity;

    /**
     * Constructor class ItemOrder creates a item order.
     * 
     * @param theItem is stored to myItem.
     * @param theQuantity is stored to myQuantity.
     * @throws IllegalArgumentException if theQuantity is less than zero.
     * @throws NullPointerException if myItem is null.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        myItem = Objects.requireNonNull(theItem);
        myQuantity = theQuantity;
    }

    /**
     * calculateOrderTotal() calculates the total cost of an item given the
     * quantity as an integer.
     * 
     * @return a BigDecimal.
     */
    public BigDecimal calculateOrderTotal() {
        return myItem.calculateItemTotal(myQuantity);
    }

    /**
     * getItem() returns the item stored in itemOrder.
     * @return a reference to the item from itemOrder.
     */
    public Item getItem() {

        return myItem;
    }

    /**
     * getQuantity() returns the quantity stored in itemOrder.
     * @return the quantity for ItemOrder as an integer.
     */
    public int getQuantity() {

        return myQuantity;
    }

    /**
     * String toString() returns a string representing the ItemOrder.
     * @return in "Item Order: 4 of 'Java Rules!' bumper sticker, $0.99 (20 for
     *         $8.95) format.
     */
    @Override
    public String toString() {
        return "Item Order: " + myQuantity + " of " + myItem;
    }

}
