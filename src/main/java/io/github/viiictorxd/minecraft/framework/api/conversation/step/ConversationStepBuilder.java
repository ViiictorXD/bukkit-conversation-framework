package io.github.viiictorxd.minecraft.framework.api.conversation.step;

import org.bukkit.entity.Player;

import java.util.function.BiPredicate;

public class ConversationStepBuilder {

  private String[] messages;
  private ConversationStepType type;
  private BiPredicate<Object, Player> handler;

  public ConversationStepBuilder withMessages(String... messages) {
    this.messages = messages;
    return this;
  }

  public ConversationStepBuilder withType(ConversationStepType type) {
    this.type = type;
    return this;
  }

  public ConversationStepBuilder withHandler(BiPredicate<Object, Player> handler) {
    this.handler = handler;
    return this;
  }

  public ConversationStep build() {
    return new ConversationStep(messages, type, handler);
  }
}
