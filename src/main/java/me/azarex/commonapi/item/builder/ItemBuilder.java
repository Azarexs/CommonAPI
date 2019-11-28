package me.azarex.commonapi.item.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public interface ItemBuilder {

    ItemStack getItem();

    default ItemBuilder setType(Material type) {
        getItem().setType(type);
        return this;
    }

    default ItemBuilder setAmount(int amount) {
        getItem().setAmount(amount);
        return this;
    }

    default ItemBuilder setDisplayName(String name) {
        return applyToMeta(meta -> meta.setDisplayName(name));
    }

    default ItemBuilder setLore(String... lore) {
        return applyToMeta(meta -> meta.setLore(Arrays.asList(lore)));
    }

    default ItemBuilder setLore(List<String> lore) {
        return applyToMeta(meta -> meta.setLore(lore));
    }

    default <T extends ItemMeta> ItemBuilder applyToMeta(Consumer<T> metaAction) {
        final ItemStack item = getItem();

        if (item.getType() == Material.AIR) return this;
        if (!item.hasItemMeta()) return this;

        final ItemMeta meta = item.getItemMeta();
        metaAction.accept((T)meta);
        return this;
    }

    static ItemBuilder of(Material type) {
        final ItemStack item = new ItemStack(type);
        return () -> item;
    }
}
