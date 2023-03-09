package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.result.GameResult

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    private val deck: Deck = Deck()
    val results: MutableMap<GameResult, Int> = GameResult.values().associateWith { 0 }.toMutableMap()

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    fun decidePlayersResult(participants: Participants) {
        participants.values.forEach { it.updateGameResult(it.getGameResult(cards.sum())) }
        decideDealerResult(participants)
    }

    fun decideDealerResult(participants: Participants) {
        participants.values.forEach {
            val gameResult = reversGameResult(it.gameResult)
            results[gameResult] = results[gameResult]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    fun setInitialPlayersCards(participants: Participants) {
        repeat(CARD_SETTING_COUNT) { addCard(deck.draw()) }
        participants.values.forEach {
            val initCards: List<Card> = listOf(deck.draw(), deck.draw())
            it.setInitialCards(Cards(initCards))
        }
    }

    fun drawCard(): Card = deck.draw()

    private fun reversGameResult(gameResult: GameResult): GameResult = when (gameResult) {
        GameResult.WIN -> GameResult.LOSE
        GameResult.LOSE -> GameResult.WIN
        else -> GameResult.DRAW
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
