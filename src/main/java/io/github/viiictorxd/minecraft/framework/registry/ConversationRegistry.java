package io.github.viiictorxd.minecraft.framework.registry;

import io.github.viiictorxd.minecraft.framework.api.conversation.Conversation;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ConversationRegistry {

  private final Map<String, Conversation> conversationMap = new HashMap<>();

  public void register(String id, Conversation conversation) {
    conversationMap.put(id, conversation);
  }

  public void unregister(String id) {
    conversationMap.remove(id);
  }

  public boolean has(String id) {
    return conversationMap.containsKey(id);
  }

  public Conversation get(String id) {
    return conversationMap.get(id);
  }
}
