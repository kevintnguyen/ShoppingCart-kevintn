/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ShoppingCart class stores ItemOrders in an ArrayList.
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public class ShoppingCart {
    /**
     * myShoppingCart uses the collection List to create a list of itemOrder. It
     * is not initialized yet.
     */
    private final List<ItemOrder> myShoppingCart;
    /**
     * myMembership is a boolean, true if the customer has a membership and
     * false otherwise. 
     */
    private boolean myMembership;

    /**
     * Constructor class ShoppingCart creates a new ArrayList.
     */
    public ShoppingCart() {
        myShoppingCart = new ArrayList<>();

    }

    /**
     * add() adds itemOrders into the ArrayList. Also replaces any previous
     * order for a new order that is equivalent with an updated quantity.
     * 
     * @param theOrder is the itemOrder passed to the add method.
     * @throws NullPointerException if theOrder is null.
     */
    public void add(final ItemOrder theOrder) {
        if (myShoppingCart.isEmpty()) {
            myShoppingCart.add(Objects.requireNonNull(theOrder));
        } else {
            int changes = 0;
            for (int i = 0; i < myShoppingCart.size(); i++) { // goes through
                                                              // the cart
                if (myShoppingCart.get(i).getItem().equals(theOrder.getItem())) {
                    // if two items are identical then replace existing order
                    myShoppingCart.remove(i);
                    myShoppingCart.add(Objects.requireNonNull(theOrder));
                    changes++;
                }
            }
            if (changes == 0) { // if nothing was changed, add order
                myShoppingCart.add(Objects.requireNonNull(theOrder));
            }
        }
    }

    /**
     * setMembership() sets whether or not the customer has pressed on the
     * membership icon.
     * 
     * @param theMembership sets to true if the customer is a member, false
     *            otherwise.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * calculateTotal() calculates the total cost of the shopping cart.
     * 
     * @return a BigDecimal that is scale to 2 and uses the ROUND_HALF_EVEN
     *         rounding rule.
     */
    public BigDecimal calculateTotal() {
        BigDecimal totalcost = BigDecimal.ZERO;
        BigDecimal totalcostfors = BigDecimal.ZERO;
        final BigDecimal discount = new BigDecimal("0.9");

        for (int i = 0; i < myShoppingCart.size(); i++) {
            if (myMembership) {
                if (myShoppingCart.get(i).getItem().isBulk()) {
                    final BigDecimal totalcal = myShoppingCart.get(i).calculateOrderTotal();
                    totalcost = totalcost.add(totalcal);
                } else {
                    final BigDecimal totalcal = myShoppingCart.get(i).calculateOrderTotal();
                    totalcostfors = totalcostfors.add(totalcal.multiply(discount));

                }
            } else {
                final BigDecimal totalcal = myShoppingCart.get(i).calculateOrderTotal();
                totalcost = totalcost.add(totalcal);

            }

        }
        return totalcost.add(totalcostfors).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * clear() removes all order from shopping cart. Clears the ArrayList.
     */
    public void clear() {
        myShoppingCart.clear();
    }

    /**
     * String toString() creates then returns a string in
     * "[Shopping Cart: 4 of 'Java Rules!' bumper sticker, $0.99 (20 for $8.95)]"
     * format.
     * 
     * @return a String in a format String.
     */
    @Override
    public String toString() {
        // 17 strings were appended, which max the default StringBuilder.
        final int stringbuilderspace = 18 + myShoppingCart.size();
        final StringBuilder totalstring = new StringBuilder(stringbuilderspace);
        totalstring.append("[Shopping Cart: ");
        for (int i = 0; i < myShoppingCart.size(); i++) {
            totalstring.append(myShoppingCart.get(i));
        }
        totalstring.append(']');

        return totalstring.toString();
    }

}
