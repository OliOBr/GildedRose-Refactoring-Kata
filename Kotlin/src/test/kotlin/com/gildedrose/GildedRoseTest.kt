package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

    @ParameterizedTest
    @CsvSource(
            "2, 50, 50",
            "2, 5, 8",
            "10, 5, 7",
            "11, 5, 6"
    )
    fun testBackstageUpdateQuality(sellIn: Int, quality: Int, expected: Int) {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality))
        val app = GildedRose(items)
        val newQuality = app.backstageUpdateQuality(items[0])
        assertEquals(expected,newQuality)
    }

    @ParameterizedTest
    @CsvSource(
            "2, 0, 0",
            "-2, 1, 0",
            "2, 5, 4",
            "-2, 5, 3",
            "0, 5, 3"
    )
    fun testStandardUpdateQuality(sellIn: Int, quality: Int, expected: Int) {
        val items = arrayOf<Item>(
                Item("foo", sellIn, quality)
        )
        val app = GildedRose(items)
        var newQuality = app.standardUpdateQuality(items[0])
        assertEquals(expected,newQuality)
    }

    @ParameterizedTest
    @CsvSource(
            "2, 0, 0",
            "-2, 1, 0",
            "2, 5, 3",
            "-2, 5, 1",
            "0, 5, 1"
    )
    fun testConjuredUpdateQuality(sellIn: Int, quality: Int, expected: Int) {
        val items = arrayOf<Item>(
                Item("foo", sellIn, quality)
        )
        val app = GildedRose(items)
        var newQuality = app.conjuredUpdateQuality(items[0])
        assertEquals(expected,newQuality)
    }
}


