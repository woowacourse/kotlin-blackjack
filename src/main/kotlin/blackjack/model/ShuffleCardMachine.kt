package blackjack.model

class ShuffleCardMachine : CardMachine {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
