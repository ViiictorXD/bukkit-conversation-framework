package io.github.viiictorxd.minecraft.framework.api.conversation.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.BiPredicate;

@AllArgsConstructor
@Getter
public class ConversationStep {

  private String[] messages;
  private ConversationStepType type;
  private BiPredicate<Object, Player> handler;
}
