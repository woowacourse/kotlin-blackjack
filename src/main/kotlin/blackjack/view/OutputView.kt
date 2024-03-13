package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.model.Result
import blackjack.model.Suit

object OutputView {
    private const val OUTPUT_MESSAGE_PARTICIPANTS_NAME = "%s와 %s에게 2장의 나누었습니다."
    private const val OUTPUT_MESSAGE_SHOW_DEALER_HAND_CARD = "%s: %s"
    private const val OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD = "%s카드: %s"
    private const val OUTPUT_MESSAGE_DEALER_RULE = "딜러는 %d이하라 한장의 카드를 더 받았습니다."
    private const val OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD = "%s카드: %s - 결과:%d"
    private const val OUTPUT_MESSAGE_BLACKJACK_RESULT = "## 최종 승패"
    private const val COMMA = ", "
    private const val SPACE = " "
    private const val SPACE_NAME = "스페이스"
    private const val CLOVER_NAME = "클로버"
    private const val HEART_NAME = "하트"
    private const val DIAMOND_NAME = "다이아몬드"
    private const val WIN_NAME = "승"
    private const val DRAW_NAME = "무"
    private const val LOSE_NAME = "패"

    fun outputParticipantsName(
        dealerName: String,
        players: List<Player>,
    ) {
        val participantNames = players.joinToString(COMMA) { it.getName() }
        println()
        println(
            OUTPUT_MESSAGE_PARTICIPANTS_NAME
                .format(
                    dealerName,
                    participantNames,
                ),
        )
    }

    fun outputDealerCurrentHandCard(
        name: String,
        firstCard: Card,
    ) {
        println(
            OUTPUT_MESSAGE_SHOW_DEALER_HAND_CARD
                .format(
                    name,
                    joinCardInfo(firstCard),
                ),
        )
    }

    fun outputPlayersCurrentHandCard(players: List<Participant>) {
        players.forEach { player ->
            outputPlayerCurrentHandCard(player)
        }
        println()
    }

    fun outputPlayerCurrentHandCard(player: Participant) {
        player.openInitCards()?.let { playerInitCards ->
            println(
                OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD
                    .format(
                        player.getName(),
                        joinCardsInfo(playerInitCards),
                    ),
            )
        }
    }

    private fun joinCardsInfo(cards: List<Card>): String {
        return cards.joinToString(COMMA) { card ->
            joinCardInfo(card = card)
        }
    }

    private fun joinCardInfo(card: Card): String {
        return "${card.denomination.score}${getCardSuitName(card.suit)}"
    }

    private fun getCardSuitName(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> SPACE_NAME
            Suit.CLOVER -> CLOVER_NAME
            Suit.HEART -> HEART_NAME
            Suit.DIAMOND -> DIAMOND_NAME
        }
    }

    fun outputDealerRule() {
        println()
        println(OUTPUT_MESSAGE_DEALER_RULE.format(Dealer.MIN_HAND_CARD_SCORE))
        println()
    }

    fun outputParticipantsHandCard(participants: List<Participant>) {
        participants.forEach { participant ->
            outputParticipantHandCard(participant)
        }
    }

    private fun outputParticipantHandCard(participant: Participant) {
        println(
            OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD
                .format(
                    participant.getName(),
                    joinCardsInfo(participant.getCards()),
                    participant.getBlackJackScore(),
                ),
        )
    }

    fun outputBlackResult() {
        println()
        println(OUTPUT_MESSAGE_BLACKJACK_RESULT)
    }

    fun outputDealerResult(
        dealerName: String,
        dealerResults: Map<Result, Int>,
    ) {
        val dealerResultNames =
            dealerResults.map { (result, count) ->
                getDealerResult(result, count)
            }
        println("$dealerName: ${dealerResultNames.joinToString(SPACE)}")
    }

    fun outputPlayersResult(playersResult: Map<Player, Result>) {
        playersResult.forEach { (player, result) ->
            outputPlayerResult(
                name = player.getName(),
                playerResult = result,
            )
        }
    }

    private fun outputPlayerResult(
        name: String,
        playerResult: Result,
    ) {
        println(
            getGameResultWithName(
                name = name,
                result = playerResult,
            ),
        )
    }

    private fun getGameResultWithName(
        name: String,
        result: Result,
    ): String {
        return "$name: ${getResultName(result)}"
    }

    private fun getDealerResult(
        result: Result,
        count: Int,
    ): String {
        return "${count}${getResultName(result)}"
    }

    private fun getResultName(result: Result): String {
        return when (result) {
            Result.WIN -> WIN_NAME
            Result.DRAW -> DRAW_NAME
            Result.LOSE -> LOSE_NAME
        }
    }
}
