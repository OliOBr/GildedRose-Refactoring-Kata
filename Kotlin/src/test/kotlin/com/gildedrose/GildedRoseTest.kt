package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun testItemName() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun testSellInUpdate() {
        val items = arrayOf<Item>(Item("foo", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun testStandardItemQuality() {
        val items = arrayOf<Item>(Item("foo", 1, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].quality)
    }

    @Test
    fun testStandardItemQualityAfterSellBy() {
        val items = arrayOf<Item>(Item("foo", 0, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items[0].quality)
    }

    @Test
    fun testAgedBrieQuality() {
        val items = arrayOf<Item>(Item("Aged Brie", 2, 5))
        val app = GildedRose(items)
        app.updateQuality()                       
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun testBackstageQualityLess5DaysLeft() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 2, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun testBackstageQualityLess10DaysLeft() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 7, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.items[0].quality)
    }

    @Test
    fun testBackstageQualityMore10DaysLeft() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 12, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun testBackstageQualityAfterSellBy() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 0, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testSulfurasQuality() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 2, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun testSulfurasQualityAfterSellBy() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", -2, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun testAgedBrieQualityAbove50() {
        val items = arrayOf<Item>(Item("Aged Brie", 2, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun testBackstageQualityAbove50() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 2, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun testStandardItemQualityNeverNegative() {
        val items = arrayOf<Item>(Item("foo", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testConjuredItemQualityNeverNegative() {
        val items = arrayOf<Item>(Item("Conjured", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testConjuredItemQuality() {
        val items = arrayOf<Item>(Item("Conjured", 1, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items[0].quality)
    }

    @Test
    fun testConjuredItemQualityAfterSellBy() {
        val items = arrayOf<Item>(Item("Conjured", -1, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, app.items[0].quality)
    }

    @Test
    fun testBackstageUpdateQuality() {
        val items = arrayOf<Item>(
                Item("Backstage passes to a TAFKAL80ETC concert", 2, 50),
                Item("Backstage passes to a TAFKAL80ETC concert", 2, 5),
                Item("Backstage passes to a TAFKAL80ETC concert", 10, 5),
                Item("Backstage passes to a TAFKAL80ETC concert", 11, 5)
                )
        val app = GildedRose(items)
        var newQuality = app.backstageUpdateQuality(items[0])
        assertEquals(50,newQuality)
        newQuality = app.backstageUpdateQuality(items[1])
        assertEquals(8,newQuality)
        newQuality = app.backstageUpdateQuality(items[2])
        assertEquals(7,newQuality)
        newQuality = app.backstageUpdateQuality(items[3])
        assertEquals(6,newQuality)
    }

    @Test
    fun testStandardUpdateQuality() {
        val items = arrayOf<Item>(
                Item("foo", 2, 0),
                Item("foo", -2, 1),
                Item("foo", 2, 5),
                Item("foo", -2, 5),
                Item("foo", 0, 5)
        )
        val app = GildedRose(items)
        var newQuality = app.standardUpdateQuality(items[0])
        assertEquals(0,newQuality)
        newQuality = app.standardUpdateQuality(items[1])
        assertEquals(0,newQuality)
        newQuality = app.standardUpdateQuality(items[2])
        assertEquals(4,newQuality)
        newQuality = app.standardUpdateQuality(items[3])
        assertEquals(3,newQuality)
        newQuality = app.standardUpdateQuality(items[4])
        assertEquals(3,newQuality)
    }

    @Test
    fun testConjuredUpdateQuality() {
        val items = arrayOf<Item>(
                Item("Conjured", 2, 0),
                Item("Conjured", -2, 1),
                Item("Conjured", 2, 5),
                Item("Conjured", -2, 5),
                Item("Conjured", 0, 5)
        )
        val app = GildedRose(items)
        var newQuality = app.conjuredUpdateQuality(items[0])
        assertEquals(0,newQuality)
        newQuality = app.conjuredUpdateQuality(items[1])
        assertEquals(0,newQuality)
        newQuality = app.conjuredUpdateQuality(items[2])
        assertEquals(3,newQuality)
        newQuality = app.conjuredUpdateQuality(items[3])
        assertEquals(1,newQuality)
        newQuality = app.conjuredUpdateQuality(items[4])
        assertEquals(1,newQuality)
    }
}


