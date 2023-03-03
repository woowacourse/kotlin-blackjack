package blackjack.domain

import blackjack.dto.HandsDTO

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getInitialHands(): HandsDTO = participants.getInitialHands()

    fun getPlayers(): List<Player> = participants.getPlayers()

    fun draw(player: Player) {
        player.addCard(deck.draw())
    }

    fun getGameResults(): Map<String, String> = participants.getGameResults()
}
