package io.github.viiictorxd.minecraft.framework.exeception;

public class ConversationStepsAreEmptyException extends RuntimeException {

  public ConversationStepsAreEmptyException(String id) {
    super("A conversation with id '" + id + "' has no steps.");
  }
}
