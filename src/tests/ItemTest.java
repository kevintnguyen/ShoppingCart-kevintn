/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Item class.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public class ItemTest {
    /**
     * Tolerance is used when comparing two doubles for equality.
     */
    private static final double TOLERANCE = .000001;
    /**
     * Sample items to use for tests. myItem will be the testing Item.
     */
    private Item myItem;
    /**
     * mySecondItem will be the testing Item with bulk price and quantity.
     */
    private Item mySecondItem;

    /**
     * setUp method initialized before each test is compiled.
     */
    @Before
    public void setUp() {
        myItem = new Item("Burger Combo", new BigDecimal("6.50"));
        mySecondItem = new Item("Hot Wings", new BigDecimal("5.99"), 20,
                                new BigDecimal("11.99"));
    }

    /**
     * Test method for {@link Item#Item(String, BigDecimal)} .
     */
    @Test
    public void testItem() {
        assertEquals("Constructor Failed!", false, myItem.isBulk());
        assertEquals("Constructor Failed!", 1879952566, myItem.hashCode());
        assertEquals("Constructor Failed!", "Burger Combo",
                     myItem.toString().substring(0, 12));
        assertEquals("Constructor Failed!", 6.50 * 3,
                     myItem.calculateItemTotal(3).doubleValue(), TOLERANCE);
    }

    /**
     * Test method for {@link Item#ItemEmptyStringException()} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemEmptyStringException() {
        myItem = new Item("", new BigDecimal(5));
    }

    /**
     * Test method for {@link Item#ItemNegativeBDecimalException()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemNegativeBDecimalException() {
        myItem = new Item("Hot Dogs", new BigDecimal(-1));
    }

    /**
     * Test method for {@link Item#ItemNullException()}.
     */
    @Test(expected = NullPointerException.class)
    public void testItemNullException() {
        myItem = new Item(null, new BigDecimal(5));
    }

    /**
     * Test method for {@link Item#Item(String, BigDecimal, int, BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("Constructor Failed!", true, mySecondItem.isBulk());
        assertEquals("Constructor Failed!", 1200934647, mySecondItem.hashCode());
        assertEquals("Constructor Failed!", "Hot Wings",
                     mySecondItem.toString().substring(0, 9));
        assertEquals("Constructor Failed!", 11.99 + 5.99 + 5.99,
                     mySecondItem.calculateItemTotal(22).doubleValue(), TOLERANCE);

    }

    /**
     * Test method for {@link Item#SecondItemEmptyStringException()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondItemEmptyStringException() {
        mySecondItem = new Item("", new BigDecimal("2.00"), 5, new BigDecimal("5.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNegativeBDecimalException()).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondItemNegativeBDecimalException() {
        mySecondItem = new Item("Laffy Taffy", new BigDecimal("-6.99"), 5,
                                new BigDecimal("5.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNegativeBulkPriceException()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondItemNegativeBulkPriceException() {
        mySecondItem = new Item("Pencils", new BigDecimal("1.00"), 5, new BigDecimal("-5.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNegativeBulkQuantityException()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondItemNegativeBulkQuantityException() {
        mySecondItem = new Item("Flashlight", new BigDecimal("2.00"), -2,
                                new BigDecimal("5.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNullNameException()}.
     */
    @Test(expected = NullPointerException.class)
    public void testSecondItemNullNameException() {
        mySecondItem = new Item(null, new BigDecimal("3.00"), 5, new BigDecimal("6.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNullPriceException()}.
     */
    @Test(expected = NullPointerException.class)
    public void testSecondItemNullPriceException() {
        mySecondItem = new Item("Eraser", null, 5, new BigDecimal("1.99"));
    }

    /**
     * Test method for {@link Item#SecondItemNullBulkPriceException()}.
     */
    @Test(expected = NullPointerException.class)
    public void testSecondItemNullBulkPriceException() {
        mySecondItem = new Item("Cups", new BigDecimal(".50"), 5, null);
    }

    /**
     * Test method for {@link Item#calculateItemTotalException()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateItemTotalException() {
        myItem.calculateItemTotal(-1);
    }

    /**
     * Test method for {@link Item#calculateItemTotal()}.
     */
    @Test
    public void testCalculateItemTotal() {
        assertEquals("CalculateItemTotal Failed!", 6.50 * 20,
                     myItem.calculateItemTotal(20).doubleValue(), TOLERANCE);
        assertEquals("CalculateItemTotal Failed!", 5.99 * 18,
                     mySecondItem.calculateItemTotal(18).doubleValue(), TOLERANCE);
    }

    /**
     * Test method for {@link Item#isBulk()}.
     */
    @Test
    public void testIsBulk() {
        assertEquals("IsBulk failed!", false, myItem.isBulk());
        assertEquals("IsBulk failed!", true, mySecondItem.isBulk());
    }

    /**
     * Test method for {@link Item#toString()}.
     */
    @Test
    public void testStringToString() {
        assertEquals("ToString failed!", "Burger Combo, $6.50",
                     myItem.toString());
        assertEquals("ToString failed!",
                     "Hot Wings, $5.99 (20 for $11.99)", mySecondItem.toString());
    }

    /**
     * Test method for {@link Item#equals(Object)}.
     */
    @Test
    public void testEquals() {
        final Item test1 = new Item("Burger Combo", new BigDecimal("6.50"));
        final Item test2 = new Item("Hot Wings", new BigDecimal("5.99"), 20,
                                    new BigDecimal("11.99"));
        assertEquals("Equals failed!", true, myItem.equals(test1));
        assertEquals("Equals failed!", true, mySecondItem.equals(test2));
    }
    /**
     * Test method for {@link Item#equalsNull()}.
     */
    @Test
    public void testEqualsNull() {
        final Item test1 = null;
        assertEquals("EqualsNull failed!", false, myItem.equals(test1));
    }
    /**
     * Test method for {@link Item#equalsDifClass()}.
     */
    @Test
    public void testEqualsDifClass() {
        final Object test1 = new Object();
        assertEquals("EqualsDifClass failed!", false, myItem.equals(test1));
    }
    /**
     * Test method for {@link Item#equalsSame()}.
     */
    @Test
    public void testEqualsSame() {
        assertEquals("EqualsSame failed!", true, myItem.equals(myItem));
    }
    /**
     * Test method for {@link Item#equalsDifferentName()}.
     */
    @Test
    public void testEqualsDifferentName() {
        final Item test1 = new Item("Burger Combos", new BigDecimal("6.50"));
        assertEquals("EqualsDifferentName failed!", false, myItem.equals(test1));
    }
    /**
     * Test method for {@link Item#equalsDifferentPrice()}.
     */
    @Test
    public void testEqualsDifferentPrice() {
        final Item test1 = new Item("Burger Combo", new BigDecimal("6.55"));
        assertEquals("EqualsDifferentPrice failed!", false, myItem.equals(test1));
    }

    /**
     * Test method for {@link Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals("Hashcode failed!", 1879952566, myItem.hashCode());
        assertEquals("Hashcode failed!", 1200934647, mySecondItem.hashCode());
    }

}
