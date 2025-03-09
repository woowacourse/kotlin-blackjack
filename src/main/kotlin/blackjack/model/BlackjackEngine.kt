package blackjack.model

class BlackjackEngine {
    val cardDeck = CardDeck()
    fun preparePlayers(names: List<String>): Players = Players(names.map { name ->
        Player(name, makeFirstCards())
    })

    fun prepareDealer(): Dealer = Dealer(makeFirstCards())

    fun playerDraw(player: Player) {
        player.draw(cardDeck)
    }

    fun dealerDraw(dealer: Dealer) = dealer.drawUntilFinished(cardDeck)

    fun makeFirstCards() = List(2) { cardDeck.draw() }

}
