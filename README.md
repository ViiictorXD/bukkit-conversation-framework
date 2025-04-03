# bukkit-conversation-framework
A simple conversation with others features for Bukkit

# Sample
```java
public class ConversationExample extends Conversation {

  public ConversationExample(String id) {
    super(id);

    addStep(new ConversationStepBuilder()
     .withMessages("Qual o seu nome?")
     .withType(ConversationStepType.TYPE_INPUT)
     .withHandler(((object, player) -> {
       // Use a função Conversation#storage para armazenar e obter os valores inseridos
       storage.set("name", object.toString());
       return true; // Se for true, ele vai para o próximo passo
     })).build());

    addStep(new ConversationStepBuilder()
     .withMessages("Qual a sua idade?")
     .withType(ConversationStepType.TYPE_INPUT)
     .withHandler(((object, player) -> {
       Integer age = Ints.tryParse(object.toString());

       if (age == null) {
         player.sendMessage("Insira sua idade corretamente.");
         return false; // Se for false, ele continua no mesmo passo
       }

       storage.set("age", object.toString());
       return true;
     })).build());
  }

  @Override
  public void onFinish(Player player) {
    player.sendMessage("Seu nome: " + storage.get("name"));
    player.sendMessage("Sua idade: " + storage.get("age"));
  }
}
```