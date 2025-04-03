package io.github.viiictorxd.minecraft.framework.listener;

import io.github.viiictorxd.minecraft.framework.ConversationSetting;
import io.github.viiictorxd.minecraft.framework.api.conversation.Conversation;
import io.github.viiictorxd.minecraft.framework.api.conversation.step.ConversationStep;
import io.github.viiictorxd.minecraft.framework.api.conversation.step.ConversationStepType;
import io.github.viiictorxd.minecraft.framework.controller.ConversationController;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class ConversationListener implements Listener {

  private final ConversationController controller;

  @EventHandler(priority = EventPriority.MONITOR)
  private void handleChat(AsyncPlayerChatEvent event) {
    if (event.isCancelled()) return;

    conversationHandler(
     event.getPlayer(),
     ConversationStepType.TYPE_INPUT,
     event,
     true,
     event.getMessage()
    );
  }

  @EventHandler(priority = EventPriority.MONITOR)
  private void handleBreak(BlockBreakEvent event) {
    if (event.isCancelled()) return;

    conversationHandler(
     event.getPlayer(),
     ConversationStepType.BREAK_BLOCK,
     event,
     false,
     event.getBlock()
    );
  }

  @EventHandler(priority = EventPriority.MONITOR)
  private void handlePlace(BlockPlaceEvent event) {
    if (event.isCancelled()) return;

    conversationHandler(
     event.getPlayer(),
     ConversationStepType.PLACE_BLOCK,
     event,
     false,
     event.getBlock()
    );
  }

  @EventHandler(priority = EventPriority.MONITOR)
  private void handleInteract(PlayerInteractEvent event) {
    if (event.isCancelled()) return;

    conversationHandler(
     event.getPlayer(),
     ConversationStepType.INTERACT_BLOCK,
     event,
     false,
     event.getClickedBlock()
    );
  }

  private void conversationHandler(Player player, ConversationStepType stepType, Cancellable cancellable, boolean cancelBeforeTest, Object object) {
    Conversation conversation = controller.get(player.getUniqueId());
    if (conversation == null) return;

    ConversationStep currentStep = conversation.getCurrentStep();
    if (currentStep == null || currentStep.getType() != stepType) return;

    ConversationSetting.Type type = conversation.getSetting().get(stepType);

    if (type == ConversationSetting.Type.BOTH_TRUE || type == ConversationSetting.Type.TRUE_FALSE)
      cancellable.setCancelled(true);

    if (currentStep.getHandler().test(object, player)) {
      if (type == ConversationSetting.Type.BOTH_TRUE || type == ConversationSetting.Type.FALSE_TRUE)
        cancellable.setCancelled(true);

      conversation.advanceStep(player);

      if (conversation.isLastStep()) {
        controller.unregister(player.getUniqueId());
      }
    }
  }
}
