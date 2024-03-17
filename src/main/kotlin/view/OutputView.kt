package view

import model.Hand
import model.card.Card
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

    private fun getCardString(card: Card): String = card.denomination.rank + card.suit.mark

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

    /*
    private fun showHands(
        dealer: Dealer,
        players: Players,
    ) {
        showDealerHand(dealer)
        showPlayersHand(players)
        println()
    }

    private fun showDealerHand(dealer: Dealer) {
        println("${dealer.getName()}: ${getHand(dealer.hand)}")
    }
    fun showDealerResult(dealerResultType: Map<ResultType, Int>) {
        println(
            DEALER_HAND.format(
                dealerResultType.map {
                    it.value.toString() + it.key.word
                }.joinToString(SPACE),
            ),
        )
    }

    fun showPlayersResult(
        players: Players,
        playersResultType: Map<HumanName, ResultType>,
    ) {
        players.players.forEach { player ->
            println("${player.getName()}: ${playersResultType.getOrDefault(player.humanInfo.humanName, ResultType.DRAW).word}")
        }
    }
     */
}
