package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Denomination
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.model.Suit

object OutputView {
    private const val OUTPUT_MESSAGE_PARTICIPANTS_NAME = "%s와 %s에게 2장의 나누었습니다."
    private const val OUTPUT_MESSAGE_SHOW_DEALER_HAND_CARD = "%s: %s"
    private const val OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD = "%s카드: %s"
    private const val OUTPUT_MESSAGE_DEALER_RULE = "딜러는 %d이하라 한장의 카드를 더 받았습니다."
    private const val OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD = "%s카드: %s - 결과:%d"
    private const val OUTPUT_MESSAGE_BLACKJACK_RESULT = "## 최종 수익"
    private const val OUTPUT_MESSAGE_PROFIT_RESULT = "%s: %.0f"
    private const val COMMA = ", "
    private const val SPADE_NAME = "스페이드"
    private const val CLOVER_NAME = "클로버"
    private const val HEART_NAME = "하트"
    private const val DIAMOND_NAME = "다이아몬드"

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

    fun outputPlayersCurrentHandCard(players: List<Player>) {
        players.forEach { player ->
            outputPlayerCurrentHandCard(player)
        }
        println()
    }

    fun outputPlayerCurrentHandCard(player: Player) {
        println(
            OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD
                .format(
                    player.getName(),
                    joinCardsInfo(player.getCards()),
                ),
        )
    }

    private fun joinCardsInfo(cards: Set<Card>): String {
        return cards.joinToString(COMMA) { card ->
            joinCardInfo(card = card)
        }
    }

    private fun joinCardInfo(card: Card): String {
        return "${getCardDenomination(card.getCardDenomination())}${getCardSuitName(card.getCardSuit())}"
    }

    private fun getCardDenomination(denomination: Denomination): String {
        return when (denomination) {
            Denomination.ACE -> "A"
            Denomination.KING -> "K"
            Denomination.QUEEN -> "Q"
            Denomination.JACK -> "J"
            else -> denomination.getScore().toString()
        }
    }

    private fun getCardSuitName(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> SPADE_NAME
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

    fun outputProfitResult(profitResult: MutableMap<Participant, Double>) {
        val dealer = profitResult.keys.find { it is Dealer }
        dealer?.let {
            println(OUTPUT_MESSAGE_PROFIT_RESULT.format(it.getName(), profitResult[it]))
            profitResult.remove(it)
        }

        profitResult.forEach { player, profit ->
            println(OUTPUT_MESSAGE_PROFIT_RESULT.format(player.getName(), profit))
        }
    }
}
