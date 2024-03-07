package blackjack.model.deck

object CardMachineManager {
    var machine: CardMachine = ShuffleCardMachine()

    fun shuffle(cards: List<Card>): List<Card> = machine.shuffle(cards)
}
