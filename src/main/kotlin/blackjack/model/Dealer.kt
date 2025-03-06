package blackjack.model

class Dealer(
    cardDeck: CardDeck,
) : Participant(cardDeck) {
    fun drawUntilFinished(): Int {
        var count = 0

        while (hand.score() < 16 && !hand.isBusted()) {
            draw()
            count++
        }

        return count
    }
}
