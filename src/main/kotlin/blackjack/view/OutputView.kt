package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.model.Revenue
import blackjack.model.Suit

object OutputView {
    private const val OUTPUT_MESSAGE_PARTICIPANTS_NAME = "%s와 %s에게 2장의 나누었습니다."
    private const val OUTPUT_MESSAGE_SHOW_DEALER_HAND_CARD = "%s: %s"
    private const val OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD = "%s카드: %s"
    private const val OUTPUT_MESSAGE_DEALER_RULE = "딜러는 %d이하라 한장의 카드를 더 받았습니다."
    private const val OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD = "%s카드: %s - 결과:%d"
    private const val OUTPUT_MESSAGE_BETTING_RESULT = "## 최종 수익"
    private const val COMMA = ", "
    private const val SPACE_NAME = "스페이스"
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

    fun outputDealerCurrentHandCard(dealer: Dealer) {
        dealer.openInitCards()
            .firstOrNull()?.let { firstCard ->
                println(
                    OUTPUT_MESSAGE_SHOW_DEALER_HAND_CARD
                        .format(
                            dealer.getName(),
                            joinCardInfo(firstCard),
                        ),
                )
            } ?: println(ERROR_CARD_INDEX_DEALER)
    }

    fun outputPlayersCurrentHandCard(players: List<Participant>) {
        players.forEach { player ->
            outputPlayerCurrentHandCard(player)
        }
        println()
    }

    fun outputPlayerCurrentHandCard(player: Participant) {
        val playerOpenCards = player.openInitCards()
        if (playerOpenCards.isNotEmpty()) {
            println(
                OUTPUT_MESSAGE_PARTICIPANTS_CURRENT_HAND_CARD
                    .format(
                        player.getName(),
                        joinCardsInfo(playerOpenCards),
                    ),
            )
        } else {
            println(ERROR_CARD_INDEX_PLAYER.format(player.getName()))
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

    fun outputParticipantsHandCard(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputDealerHandWithResult(dealer)
        players.forEach { player ->
            outputPlayerHandWithResult(player)
        }
    }

    private fun outputDealerHandWithResult(dealer: Dealer) {
        println(
            OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD
                .format(
                    "${dealer.getName()} ",
                    joinCardsInfo(dealer.getCards()),
                    dealer.getBlackJackScore(),
                ),
        )
    }

    private fun outputPlayerHandWithResult(player: Player) {
        println(
            OUTPUT_MESSAGE_PARTICIPANTS_FINISH_HAND_CARD
                .format(
                    player.getName(),
                    joinCardsInfo(player.getCards()),
                    player.getBlackJackScore(),
                ),
        )
    }

    fun outputBlackResult() {
        println()
        println(OUTPUT_MESSAGE_BETTING_RESULT)
    }

    fun outputParticipantResult(
        dealer: Dealer,
        bettingResults: List<Revenue>,
    ) {
        val dealerBettingResult = -bettingResults.sumOf { it.revenue }
        outputDealerResult(
            dealer = dealer,
            dealerBettingResult = dealerBettingResult,
        )
        outputPlayersResult(bettingResults)
    }

    private fun outputDealerResult(
        dealer: Dealer,
        dealerBettingResult: Int,
    ) {
        println("${dealer.getName()}: $dealerBettingResult")
    }

    private fun outputPlayersResult(bettingResults: List<Revenue>) {
        bettingResults.forEach { bettingResult ->
            outputPlayerResult(bettingResult)
        }
    }

    private fun outputPlayerResult(bettingResult: Revenue) {
        println("${bettingResult.name}: ${bettingResult.revenue}")
    }

    private const val ERROR_CARD_INDEX_DEALER = "딜러가 가지고 있는 카드가 없습니다."
    private const val ERROR_CARD_INDEX_PLAYER = "%s 플레이어가 가지고 있는 카드가 없습니다."
}
