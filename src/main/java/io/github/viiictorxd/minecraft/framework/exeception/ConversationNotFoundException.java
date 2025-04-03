package io.github.viiictorxd.minecraft.framework.exeception;

public class ConversationNotFoundException extends RuntimeException {

  public ConversationNotFoundException(String id) {
    super("Conversation with id '" + id + "' not found.");
  }
}
