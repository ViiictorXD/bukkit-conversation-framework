package io.github.viiictorxd.minecraft.framework;

import io.github.viiictorxd.minecraft.framework.api.conversation.step.ConversationStepType;

import java.util.HashMap;
import java.util.Map;

public class ConversationSetting {

  private final Map<ConversationStepType, Type> typeMap = new HashMap<>();

  public void set(ConversationStepType stepType, Type type) {
    typeMap.put(stepType, type);
  }

  public Type get(ConversationStepType stepType) {
    return typeMap.get(stepType);
  }

  public void defaultValues() {
    typeMap.put(ConversationStepType.TYPE_INPUT, Type.BOTH_TRUE);
    typeMap.put(ConversationStepType.BREAK_BLOCK, Type.FALSE_TRUE);
    typeMap.put(ConversationStepType.PLACE_BLOCK, Type.FALSE_TRUE);
    typeMap.put(ConversationStepType.INTERACT_BLOCK, Type.FALSE_TRUE);
  }

  public enum Type {

    BOTH_TRUE,
    BOTH_FALSE,
    TRUE_FALSE,
    FALSE_TRUE
  }
}
