package view

import model.participants.Dealer
import model.participants.Hand
import model.participants.Human
import model.participants.Players
import model.result.DealerResult
import model.result.PlayersResult
import model.result.ResultType

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종승패"

    fun showGameInit(
        dealer: Dealer,
        players: Players,
    ) {
        showInitHeader(players)
        showHands(dealer, players)
    }

    private fun showInitHeader(players: Players) {
        println(HEADER_GAME_INITIAL_STATE.format(players.players.joinToString(", ") { it.humanName.name }))
    }

    private fun showHands(
        dealer: Dealer,
        players: Players,
    ) {
        showHumanHand(dealer)
        showPlayersHand(players)
        println()
    }

    fun showHumanHand(human: Human) {
        println("${human.humanName.name}: ${getCardFromHand(human.hand)}")
    }

    fun showHumanHandWithResult(human: Human) {
        println("${human.humanName.name}: ${getCardFromHand(human.hand)} - 결과: ${human.getPointIncludingAce().amount}")
    }

    fun showPlayersHandWithResult(players: Players) {
        players.players.forEach(::showHumanHandWithResult)
    }

    private fun showPlayersHand(players: Players) {
        players.players.forEach {
            showHumanHand(it)
        }
    }

    private fun getCardFromHand(hand: Hand): String = hand.cards.joinToString(", ") { it.valueType.rank + it.markType.mark }

    fun showDealerHit() = println(HEADER_DRAW_CARDS_FOR_DEALER)

    fun showResultHeader() = println(HEADER_RESULT)

    fun showDealerResult(dealerResult: DealerResult) {
        println(
            "딜러: ${dealerResult.result.map {
                it.value.toString() + it.key.word
            }.joinToString(" ")}",
        )
    }

    fun showPlayersResult(
        players: Players,
        playersResult: PlayersResult,
    ) {
        players.players.forEach { player ->
            println("${player.humanName.name}: ${playersResult.result.getOrDefault(player.humanName, ResultType.DRAW).word}")
        }
    }
}
