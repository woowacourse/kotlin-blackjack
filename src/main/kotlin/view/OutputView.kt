package view

import model.Game
import model.card.MarkType
import model.card.ValueType
import model.participants.Dealer
import model.participants.Hand
import model.participants.Participant
import model.participants.ParticipantName
import model.participants.Player
import model.participants.Players
import model.result.DealerResult
import model.result.PlayersResult
import model.result.Profit
import model.result.ResultType

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n%s는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종승패"

    fun showGameInit(game: Game) {
        showInitHeader(game)
        showParticipantsHandOut(game)
    }

    private fun showInitHeader(game: Game) {
        println(HEADER_GAME_INITIAL_STATE.format(game.getPlayers().players.joinToString(", ") { it.wallet.participantName.name }))
    }

    private fun showParticipantsHandOut(game: Game) {
        game.getAll().forEach { participant ->
            switchParticipantHandOut(participant)
        }
        println()
    }

    private fun switchParticipantHandOut(participant: Participant) {
        when (participant) {
            is Player -> {
                showParticipantHand(participant)
            }
            is Dealer -> {
                showParticipantHandOnlyOne(participant)
            }
        }
    }

    private fun showParticipantHandOnlyOne(participant: Participant) {
        println("${participant.wallet.participantName.name}: ${getFirstCardFromHand(participant.participantState.hand)}")
    }

    fun showParticipantHand(participant: Participant) {
        println("${participant.wallet.participantName.name}: ${getCardsFromHand(participant.participantState.hand)}")
    }

    fun showParticipantsHandWithResult(game: Game) {
        game.getAll().forEach { participant ->
            showParticipantHandWithResult(participant)
        }
    }

    private fun showParticipantHandWithResult(participant: Participant) {
        println(
            "${participant.wallet.participantName.name}: ${getCardsFromHand(
                participant.participantState.hand,
            )} - 결과: ${participant.participantState.hand.calculateOptimalPoint().amount}",
        )
    }

    private fun getCardsFromHand(hand: Hand): String =
        hand.cards.joinToString(", ") {
            getValueFromType(it.valueType) + getMarkFromType(it.markType)
        }

    private fun getFirstCardFromHand(hand: Hand): String =
        getValueFromType(hand.cards.first().valueType) + getMarkFromType(hand.cards.first().markType)

    fun showDealerHit(dealer: Dealer) = println(HEADER_DRAW_CARDS_FOR_DEALER.format(dealer.wallet.participantName.name))

    fun showResultHeader() = println(HEADER_RESULT)

    fun showDealerResult(dealerResult: DealerResult) {
        println(
            "딜러: ${dealerResult.result.map {
                it.value.toString() + getResultFromType(it.key)
            }.joinToString(" ")}",
        )
    }

    fun showProfitResult(result: MutableMap<ParticipantName, Profit>) {
        result.map {
            println("${it.key.name} : ${it.value.amount.toInt()}")
        }
    }

    fun showPlayersResult(
        players: Players,
        playersResult: PlayersResult,
    ) {
        players.players.forEach { player ->
            println(
                "${player.wallet.participantName.name}: ${getResultFromType(
                    playersResult.result.getOrDefault(player.wallet.participantName, ResultType.DRAW),
                )}",
            )
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

    private fun getResultFromType(resultType: ResultType): String {
        return when (resultType) {
            ResultType.WIN -> "승"
            ResultType.DRAW -> "무"
            ResultType.LOSE -> "패"
        }
    }
}
