package com.gildedrose

import java.lang.Integer.min
import java.lang.Integer.max

class GildedRose(var items: Array<Item>) {

    fun backstageUpdateQuality(item: Item): Int {
        return when{
            item.sellIn <=0 -> 0
            item.sellIn <=5 -> min(item.quality + 3,50)
            item.sellIn <=10 -> min(item.quality + 2,50)
            else -> min(item.quality + 1,50)
        }
    }

    fun standardUpdateQuality(item: Item): Int {
        return max(item.quality - if (item.sellIn <= 0) 2 else 1,0)
    }

    fun conjuredUpdateQuality(item: Item): Int {
        return max(item.quality - if (item.sellIn <= 0) 4 else 2,0)
    }

    fun updateQuality() {
        for (item in items) {
            item.quality = when(item.name){
                "Aged Brie" -> min(item.quality + 1, 50)
                "Backstage passes to a TAFKAL80ETC concert" ->  backstageUpdateQuality(item)
                "Sulfuras, Hand of Ragnaros" -> 80
                "Conjured" -> conjuredUpdateQuality(item)
                else -> standardUpdateQuality(item)
            }
            item.sellIn = item.sellIn - 1
        }
    }

}

