package me.azarex.commonapi;

import me.azarex.commonapi.item.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CommonAPI {

    private static CommonAPI instance;

    public static CommonAPI getInstance() {
        final ItemStack sword = ItemBuilder.of(Material.DIAMOND_SWORD)
                .setAmount(1)
                .setDisplayName("The noob slayer!")
                .setLore("This thing will kill all noobs", "every last one.")
                .getItem();


        if (instance == null) {
            instance = new CommonAPI();
        }
        return instance;
    }

}
