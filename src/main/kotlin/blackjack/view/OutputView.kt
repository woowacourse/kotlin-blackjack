package blackjack.view

import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.domain.enums.Shape
import java.lang.String.format

class OutputView {
    fun printDealerCards(
        players: List<Player>,
        dealerCard: TrumpCard,
    ) {
        println(MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS.format(players.joinToString(", ") { it.name }))
        println(format(MESSAGE_OUTPUT_DEALER_CARD, cardMessageFormat(dealerCard)))
    }

    fun printPlayerCards(players: List<Player>) {
        players.forEach { player ->
            println(
                format(
                    MESSAGE_OUTPUT_PLAYER_CARD,
                    player.name,
                    makeCardListMessage(player.cards),
                ),
            )
        }
    }

    private fun makeCardListMessage(cards: List<TrumpCard>): String =
        cards.joinToString(", ") { card ->
            cardMessageFormat(card)
        }

    private fun cardMessageFormat(card: TrumpCard): String = MESSAGE_CARD.format(card.tier.value, card.shape.toKorean())

    private fun Shape.toKorean(): String =
        when (this) {
            Shape.Heart -> "하트"
            Shape.Dia -> "다이아몬드"
            Shape.Clover -> "클로버"
            Shape.Spade -> "스페이드"
        }

    companion object {
        const val MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다."
        const val MESSAGE_OUTPUT_PLAYER_CARD = "%s카드: %s"
        const val MESSAGE_OUTPUT_DEALER_CARD = "딜러: %s"
        const val MESSAGE_CARD = "%d%s"
    }
}
