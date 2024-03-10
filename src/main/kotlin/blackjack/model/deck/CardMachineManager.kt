package blackjack.model.deck

object CardMachineManager {
    var machine: CardMachine = ShuffleCardMachine()

    fun handle(cards: List<Card>): List<Card> = machine.handle(cards)
}
