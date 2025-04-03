package io.github.viiictorxd.minecraft.framework.exeception;

public class ConversationAlreadyRegisteredException extends RuntimeException {

  public ConversationAlreadyRegisteredException(String id) {
    super("A conversation with id '" + id + "' is already registered.");
  }
}
