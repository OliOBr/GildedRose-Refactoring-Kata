package com.gildedrose

import java.lang.Integer.min
import java.lang.Integer.max

class GildedRose(var items: Array<Item>) {

    fun backstageUpdateQuality(item: Item): Int {
        return when{
            item.sellIn <=0 -> 0
            item.sellIn <=5 -> min(item.quality + 3,50)
            item.sellIn <=10 -> min(item.quality + 2,50)
            else -> item.quality + 1
        }
    }

    fun standardUpdateQuality(item: Item): Int {
        return max(item.quality - when{
            item.sellIn <=0 -> 2
            else -> 1
        },0)
    }

    fun updateQuality() {
        for (item in items) {

            item.quality = when(item.name){
                "Aged Brie" -> min(item.quality + 1, 50)
                "Backstage passes to a TAFKAL80ETC concert" ->  backstageUpdateQuality(item)
                "Sulfuras, Hand of Ragnaros" -> 80
                else -> standardUpdateQuality(item)
            }
            item.sellIn = item.sellIn - 1
        }
    }

}

