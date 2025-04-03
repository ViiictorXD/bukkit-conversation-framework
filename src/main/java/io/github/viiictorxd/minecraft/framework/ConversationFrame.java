package io.github.viiictorxd.minecraft.framework;

import io.github.viiictorxd.minecraft.framework.api.conversation.Conversation;
import io.github.viiictorxd.minecraft.framework.exeception.ConversationAlreadyRegisteredException;
import io.github.viiictorxd.minecraft.framework.exeception.ConversationNotFoundException;
import io.github.viiictorxd.minecraft.framework.exeception.ConversationStepsAreEmptyException;
import io.github.viiictorxd.minecraft.framework.exeception.PlayerAlreadyInConversationException;
import io.github.viiictorxd.minecraft.framework.listener.ConversationListener;
import io.github.viiictorxd.minecraft.framework.registry.ConversationRegistry;
import io.github.viiictorxd.minecraft.framework.controller.ConversationController;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ConversationFrame {

  private final JavaPlugin plugin;

  private final ConversationRegistry registry;
  private final ConversationController controller;

  public ConversationFrame(JavaPlugin plugin) {
    this.plugin = plugin;

    this.registry = new ConversationRegistry();
    this.controller = new ConversationController();

    Bukkit.getPluginManager().registerEvents(new ConversationListener(controller), plugin);
  }

  public void start(Player player, Conversation conversation) {
    if (controller.has(player.getUniqueId()))
      throw new PlayerAlreadyInConversationException(player, conversation.getId());

    if (conversation.getSteps().isEmpty())
      throw new ConversationStepsAreEmptyException(conversation.getId());

    controller.register(player.getUniqueId(), conversation);
    conversation.advanceStep(player);
  }

  public void start(Player player, String conversationId) {
    if (!registry.has(conversationId))
      throw new ConversationNotFoundException(conversationId);

    start(player, registry.get(conversationId));
  }

  public void end(Player player) {
    if (!controller.has(player.getUniqueId()))
      return;

    controller.unregister(player.getUniqueId());
  }

  public void register(Conversation... conversations) {
    for (Conversation conversation : conversations) {
      if (registry.has(conversation.getId())) {
        throw new ConversationAlreadyRegisteredException(conversation.getId());
      }

      registry.register(conversation.getId(), conversation);
    }
  }
}
