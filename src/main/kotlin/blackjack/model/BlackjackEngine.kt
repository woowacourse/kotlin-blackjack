package blackjack.model

class BlackjackEngine {
    val cardDeck = CardDeck()
    fun preparePlayers(players: Players): Players {
        players.value.forEach { player ->
            player.draw(cardDeck)
        }
        return players
    }

    fun prepareDealer(): Dealer = Dealer().apply { this.draw(cardDeck) }

    fun playerDraw(player:Player) {
        player.draw(cardDeck)
    }

    fun dealerDraw(dealer: Dealer) = dealer.drawUntilFinished(cardDeck)

}
