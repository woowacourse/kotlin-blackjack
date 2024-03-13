package view

import model.card.MarkType
import model.card.ValueType
import model.participants.*
import model.result.DealerResult
import model.result.PlayersResult
import model.result.ResultType

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종승패"

    fun showGameInit(participants: Participants) {
        showInitHeader(participants.getPlayers())
        showHands(participants.getDealer(), participants.getPlayers())
    }

    private fun showInitHeader(players: Players) {
        println(HEADER_GAME_INITIAL_STATE.format(players.players.joinToString(", ") { it.participantName.name }))
    }

    private fun showHands(
        dealer: Dealer,
        players: Players,
    ) {
        showDealerHandOnlyOne(dealer)
        showPlayersHand(players)
        println()
    }

    private fun showDealerHandOnlyOne(dealer: Dealer) {
        println("${dealer.participantName.name}: ${getFirstCardFromHand(dealer.participantState.hand)}")
    }

    fun showHumanHand(participant: Participant) {
        println("${participant.participantName.name}: ${getCardsFromHand(participant.participantState.hand)}")
    }

    fun showHumanHandWithResult(participant: Participant) {
        println(
            "${participant.participantName.name}: ${getCardsFromHand(
                participant.participantState.hand,
            )} - 결과: ${participant.getPointWithAce().amount}",
        )
    }

    fun showPlayersHandWithResult(players: Players) {
        players.players.forEach(::showHumanHandWithResult)
    }

    private fun showPlayersHand(players: Players) {
        players.players.forEach {
            showHumanHand(it)
        }
    }

    private fun getCardsFromHand(hand: Hand): String =
        hand.cards.joinToString(", ") {
            getValueFromType(it.valueType) + getMarkFromType(it.markType)
        }

    private fun getFirstCardFromHand(hand: Hand): String =
        getValueFromType(hand.cards.first().valueType) + getMarkFromType(hand.cards.first().markType)

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
            println("${player.participantName.name}: ${playersResult.result.getOrDefault(player.participantName, ResultType.DRAW).word}")
        }
    }

    fun showThrowable(t: Throwable) {
        println(t.message)
    }

    private fun getMarkFromType(markType: MarkType): String {
        return when (markType) {
            MarkType.SPADE -> "스페이드"
            MarkType.CLOVER -> "클로버"
            MarkType.HEART -> "하트"
            MarkType.DIAMOND -> "다이아몬드"
        }
    }

    private fun getValueFromType(valueType: ValueType): String {
        return when (valueType) {
            ValueType.ACE -> "A"
            ValueType.TWO -> "2"
            ValueType.THREE -> "3"
            ValueType.FOUR -> "4"
            ValueType.FIVE -> "5"
            ValueType.SIX -> "6"
            ValueType.SEVEN -> "7"
            ValueType.EIGHT -> "8"
            ValueType.NINE -> "9"
            ValueType.TEN -> "10"
            ValueType.JACK -> "J"
            ValueType.QUEEN -> "Q"
            ValueType.KING -> "K"
        }
    }
}
