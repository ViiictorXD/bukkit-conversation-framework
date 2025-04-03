package io.github.viiictorxd.minecraft.framework.api.conversation;

import io.github.viiictorxd.minecraft.framework.ConversationSetting;
import io.github.viiictorxd.minecraft.framework.api.conversation.step.ConversationStep;
import io.github.viiictorxd.minecraft.framework.api.conversation.storage.ConversationStorage;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Conversation {

  protected final String id;

  protected final List<ConversationStep> steps;

  protected final ConversationStorage storage;
  protected final ConversationSetting setting;

  protected int currentIndex;

  public Conversation(String id) {
    this.id = id;

    this.steps = new ArrayList<>();

    this.storage = new ConversationStorage();
    this.setting = new ConversationSetting();

    this.setting.defaultValues();

    this.currentIndex = -1;
  }

  public ConversationStep getCurrentStep() {
    return getConversationOrNull(currentIndex);
  }

  private ConversationStep getConversationOrNull(int index) {
    try {
      return steps.get(index);
    } catch (IndexOutOfBoundsException ignored) {
      return null;
    }
  }

  public void advanceStep(Player player) {
    startStep(player, getConversationOrNull(++currentIndex));
  }

  public void backStep(Player player) {
    startStep(player, getConversationOrNull(--currentIndex));
  }

  public void startStep(Player player, ConversationStep step) {
    if (isLastStep()) {
      onFinish(player);
      return;
    }

    player.sendMessage(step.getMessages());
  }

  public void addStep(ConversationStep step) {
    steps.add(step);
  }

  public void removeStep(ConversationStep step) {
    steps.remove(step);
  }

  public boolean isFirstStep() {
    return currentIndex == 0;
  }

  public boolean isLastStep() {
    return currentIndex == steps.size();
  }

  public abstract void onFinish(Player player);
}
