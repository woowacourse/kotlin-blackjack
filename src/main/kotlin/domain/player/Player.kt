package domain.player

import domain.card.Card
import domain.card.CardNumber
import domain.card.Hand
import view.GameResult

abstract class Player(
    val name: String,
    private val hand: Hand,
) {

    val cards: List<Card> get() = hand.cards.toList()

    fun addScoreTenIfHasAce(): Int {
        if ((calculateCardValueSum() <= BLACK_JACK_LESS_TEN) and (hasAceCard())) {
            return calculateCardValueSum() + ACE_CARD_PLUS_TEN
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = hand.cards.sumOf { card ->
        card.cardNumber.number
    }

    fun addCard(card: List<Card>) {
        hand.drawOneCard(card)
    }

    fun getResult(dealer: Dealer): GameResult = calculateResult(dealer.addScoreTenIfHasAce())

    private fun calculateResult(dealerScore: Int): GameResult {
        val userScore = addScoreTenIfHasAce()
        val dealerOverMaxScore = dealerScore > GAME_MAX_SCORE
        val playerOverMaxScore = userScore > GAME_MAX_SCORE
        return when {
            isALLBlackJack(dealerScore, userScore) -> GameResult.DRAW
            (dealerOverMaxScore and playerOverMaxScore) or (dealerScore == userScore) -> GameResult.DRAW
            ((dealerScore > userScore) and !dealerOverMaxScore or playerOverMaxScore) -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }

    private fun isALLBlackJack(dealerScore: Int, userScore: Int): Boolean =
        (dealerScore == GAME_MAX_SCORE) and (userScore == GAME_MAX_SCORE)

    private fun hasAceCard(): Boolean = hand.cards.any { card ->
        card.cardNumber == CardNumber.ACE
    }

    companion object {
        private const val GAME_MAX_SCORE = 21
        private const val BLACK_JACK_LESS_TEN = 11
        private const val ACE_CARD_PLUS_TEN = 10
    }
}
