package blackjack.model.deck

class ShuffleCardMachine : CardMachine {
    override fun handle(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
