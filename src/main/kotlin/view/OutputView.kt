package view

import model.Hand
import model.card.Card
import model.card.Denomination
import model.card.Suit
import model.human.Dealer
import model.human.Human
import model.human.Players

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종 수익"

    fun showGameInit(
        dealer: Dealer,
        players: Players,
    ) {
        showInitHeader(players)
        showInitHands(dealer, players)
    }

    private fun showInitHeader(players: Players) {
        println(HEADER_GAME_INITIAL_STATE.format(players.players.joinToString(", ") { it.getName() }))
    }

    private fun showInitHands(
        dealer: Dealer,
        players: Players,
    ) {
        showDealerInitHand(dealer)
        showPlayersHand(players)
        println()
    }

    private fun showDealerInitHand(dealer: Dealer) {
        println("${dealer.getName()}: ${getCardString(dealer.hand.cards[0])}")
    }

    private fun showPlayersHand(players: Players) {
        players.players.forEach {
            showPlayerHand(it)
        }
    }

    fun showPlayerHand(human: Human) {
        println("${human.getName()}: ${getHandString(human.hand)}")
    }

    private fun getHandString(hand: Hand): String = hand.cards.joinToString(", ") { getCardString(it) }

    private fun getCardString(card: Card): String = denominationToString(card.denomination) + suitToString(card.suit)

    private fun showHandWithResult(human: Human) {
        println("${human.getName()}: ${getHandString(human.hand)} - 결과: ${human.hand.getPoint().amount}")
    }

    fun showDealerHandWithResult(dealer: Dealer) {
        println()
        showHandWithResult(dealer)
    }

    fun showPlayersHandWithResult(players: Players) {
        players.players.forEach {
            showHandWithResult(it)
        }
    }

    fun drawCardForDealer() = println(HEADER_DRAW_CARDS_FOR_DEALER)

    fun showResultHeader() = println(HEADER_RESULT)

    fun showTotalResult(
        dealer: Dealer,
        players: Players,
    ) {
        showMoneyResult(dealer)
        players.players.forEach { player ->
            showMoneyResult(player)
        }
    }

    private fun showMoneyResult(human: Human) {
        println(String.format("%s: %d", human.getName(), human.getMoneyAmount()))
    }

    private fun denominationToString(denomination: Denomination): String {
        return when (denomination) {
            Denomination.ACE -> "A"
            Denomination.TWO -> "2"
            Denomination.THREE -> "3"
            Denomination.FOUR -> "4"
            Denomination.FIVE -> "5"
            Denomination.SIX -> "6"
            Denomination.SEVEN -> "7"
            Denomination.EIGHT -> "8"
            Denomination.NINE -> "9"
            Denomination.TEN -> "10"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
        }
    }

    private fun suitToString(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> "스페이드"
            Suit.CLOVER -> "클로버"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
        }
    }
}
