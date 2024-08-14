package fr.maxlego08.essentials.api.worldedit;

import fr.maxlego08.essentials.api.user.User;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WorldeditManager {

    Optional<WorldEditItem> getWorldeditItem(String name);

    void give(CommandSender sender, Player player, String itemName);

    List<String> getWorldeditItems();

    List<String> getAllowedMaterials(Player player);

    boolean isBlacklist(Material material);

    void setBlocks(User user, List<MaterialPercent> materialPercents);

    void fillBlocks(User user, List<MaterialPercent> materialPercents);

    void cutBlocks(User user);

    BigDecimal getMaterialPrice(Material material);

    void confirmAction(User user);

    int getBlocksPerSecond(Player player);

    int getMaxBlocks(Player player);

    int getMaxDistance(Player player);

    void sendFinishMessage(User user);

    int getBatchSize();

}
