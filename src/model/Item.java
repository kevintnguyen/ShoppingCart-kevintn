/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * The Item class stores information about an individual item.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public final class Item {
    /**
     * myItemName stores the item's name as a String.
     */
    private final String myItemName;
    /**
     * myIndiviualPrice stores the item's individual price, not bulk.
     */
    private final BigDecimal myIndiviualPrice;
    /**
     * myBulkPrice stores the item's bulk pricing.
     */
    private BigDecimal myBulkPrice;
    /**
     * myBulkQuantity stores the item's bulk quantity, so it'll know when a
     * specific quantity is considered a bulk.
     */
    private int myBulkQuantity;

    /**
     * Constructor class Item stores the name and price of an item into instance
     * fields.
     * 
     * @param theName is stored to myItemName as a String.
     * @param thePrice is stored to myIndiviualPrice as a BigDecimal.
     * @throws IllegalArgumentException if String theName is empty or thePrice <0.
     * @throws NullPointerException if theName is passed as a null.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if ("".equals(theName) || thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        myItemName = Objects.requireNonNull(theName);
        myIndiviualPrice = thePrice;
    }

    /**
     * Constructor class Item stores the name, price, bulk quantity, and bulk
     * price of an item into instance fields if it has a bulk.
     * 
     * @param theName is stored to myItemName as a String.
     * @param thePrice is stored to myIndiviualPrice as a BigDecimal.
     * @param theBulkQuantity is stored to myBulkQuantity as a integer.
     * @param theBulkPrice is stored to myBulkPrice as a BigDecimal.
     * @throws IllegalArgumentException if thePrice or BulkPrice or BulkQuantity
     *             <0. Also throws IllegalArgumentException if theName parameter
     *             is passed as a empty string.
     * @throws NullPointerException if myitemName or myIndiviualPrice or
     *             myBulkPrice are passed with nulls.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        if (thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        if (theBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException();
        }
        if ("".equals(theName)) {
            throw new IllegalArgumentException();
        }

        myItemName = Objects.requireNonNull(theName);
        myIndiviualPrice = Objects.requireNonNull(thePrice);
        myBulkPrice = Objects.requireNonNull(theBulkPrice);
        myBulkQuantity = theBulkQuantity;
    }

    /**
     * calculateItemTotal() calculates the total cost of a Item.
     * 
     * @param theQuantity is used as a parameter to determine the total cost of
     *            an item.
     * @return the total cost in BigDecimal form.
     * @throws IllegalArgumentException if theQuantity is less than 0.
     */
    public BigDecimal calculateItemTotal(final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        int bulkpricing = 0;
        int localquantity = theQuantity;
        int indiviualpricing = 0;
        BigDecimal finalcalculation = BigDecimal.ZERO;
        if (isBulk()) {
            if (localquantity >= myBulkQuantity) {
                indiviualpricing = localquantity % myBulkQuantity;
                bulkpricing = (localquantity - indiviualpricing) / myBulkQuantity;
                localquantity = indiviualpricing;
            }

            final BigDecimal totalbulkcost = myBulkPrice.multiply(new BigDecimal(bulkpricing));
            final BigDecimal totalindiviualcost =
                            myIndiviualPrice.multiply(new BigDecimal(localquantity));

            finalcalculation = totalbulkcost.add(totalindiviualcost);
        } else {
            finalcalculation = myIndiviualPrice.multiply(new BigDecimal(theQuantity));
        }
        return finalcalculation;
    }

    /**
     * isBulk() returns a boolean, true if it's a item that has bulk
     * pricing, and false if it's doesn't.
     * 
     * @return a boolean determining if the current item is bulk.
     */
    public boolean isBulk() {
        boolean isbulk;
        if (this.myBulkPrice == null) {
            isbulk = false;
        } else {
            isbulk = true;
        }
        return isbulk;
    }

    /**
     * String toString() returns a string for item in "X, $19.99" format, and
     * "X, $19.99 (5 for $89.99)" if the item has bulk pricing.
     * 
     * @return a String in a certain format.
     */
    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String output = "";

        if (this.isBulk()) {

            output = myItemName + ',' + ' ' + nf.format(myIndiviualPrice) + " ("
                     + this.myBulkQuantity + " for " + nf.format(this.myBulkPrice) + ")";
        } else {

            output = myItemName + ", " + nf.format(myIndiviualPrice);
        }
        return output;
    }

    /**
     * equals() is a override method. Returns a boolean determining whether the
     * given object equals to the item that is being compared.
     * 
     * @return a boolean false or true whether two objects are identical.
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }
        if (theOther == null) {
            return false;
        }
        if (getClass() != theOther.getClass()) {
            return false;
        }

        final Item other = (Item) theOther;

        return Objects.equals(myItemName, other.myItemName) 
                        && myIndiviualPrice.equals(other.myIndiviualPrice);

    }

    /**
     * hashCode() is a override method.
     * 
     * @return an integer hash code for this item.
     */
    @Override
    public int hashCode() {

        return Objects.hash(myItemName, myIndiviualPrice);
    }

}
