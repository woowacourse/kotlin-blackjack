package blackjack.model

class BlackjackEngine {
    val cardDeck: CardDeck = CardDeck()
    fun preparePlayers(names: List<String>): Players = Players(names.map { name ->
        Player(name, makeFirstCards())
    })

    fun prepareDealer(): Dealer = Dealer(makeFirstCards())

    fun playerDraw(player: Player): Unit = player.draw(cardDeck)

    fun dealerDraw(dealer: Dealer): Unit = dealer.drawUntilFinished(cardDeck)

    fun makeFirstCards(): List<Card> = List(2) { cardDeck.draw() }

}
