package io.github.viiictorxd.minecraft.framework.api.conversation.storage;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked" })
public class ConversationStorage {

  private final Map<String, Object> storageMap = new HashMap<>();

  public void set(String key, Object value) {
    storageMap.put(key, value);
  }

  public <T> T get(String key) {
    return (T) storageMap.get(key);
  }
}
