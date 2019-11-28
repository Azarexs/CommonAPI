package me.azarex.commonapi.item.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface ItemBuilder {

    ItemStack getItem();

    default ItemBuilder getType(Material type) {
        getItem().setType(type);
        return this;
    }
}
