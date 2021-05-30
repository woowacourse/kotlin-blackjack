package blackjackgame.model

import blackjackgame.model.card.Card
import blackjackgame.model.card.Deck
import blackjackgame.model.player.Player
import blackjackgame.model.player.Players

class BlackjackGame(private val players: Players, private val deck: Deck) {

    fun start() {
        players.drawInitCards(deck)
    }

    fun getInitStatus(): List<Pair<String, List<Card>>> {
        return players.map { Pair(it.name, it.getInitCards()) }
    }

    fun isExistHitPlayer(): Boolean {
        return players.any { it.isHit() && it.isPlaying }
    }

    fun playTurn(answer: Boolean): Pair<String, List<Card>> {
        val player = findTurnPlayer()
        if (!answer) {
            player.endTurn()
            return Pair(player.name, player.cards.getCards())
        }
        player.drawCard(deck.handOutCard())
        return Pair(player.name, player.cards.getCards())
    }

    fun findTurnPlayer(): Player {
        return players.first { it.isHit() && it.isPlaying }
    }

    fun extractResult(): List<Triple<String, List<Card>, Int>> {
        return players.map { Triple(it.name, it.cards.getCards(), it.cards.calculateFinalScore()) }
    }
}
