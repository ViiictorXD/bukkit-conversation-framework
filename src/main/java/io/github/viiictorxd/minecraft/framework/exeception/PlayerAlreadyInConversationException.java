package io.github.viiictorxd.minecraft.framework.exeception;

import org.bukkit.entity.Player;

public class PlayerAlreadyInConversationException extends RuntimeException {

  public PlayerAlreadyInConversationException(Player player, String id) {
    super("Player '" + player.getName() + "' is already in conversation '" + id + "'.");
  }
}
