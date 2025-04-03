package io.github.viiictorxd.minecraft.framework.controller;

import io.github.viiictorxd.minecraft.framework.api.conversation.Conversation;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ConversationController {

  private final Map<UUID, Conversation> conversationMap = new HashMap<>();

  public void register(UUID uuid, Conversation conversation) {
    conversationMap.put(uuid, conversation);
  }

  public void unregister(UUID uuid) {
    conversationMap.remove(uuid);
  }

  public boolean has(UUID uuid) {
    return conversationMap.containsKey(uuid);
  }

  public Conversation get(UUID uuid) {
    return conversationMap.get(uuid);
  }
}
