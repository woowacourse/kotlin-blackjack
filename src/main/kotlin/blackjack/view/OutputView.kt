package blackjack.view

import blackjack.domain.Profit
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class OutputView {
    fun printCardInfo(participants: Participants) {
        val playerNames = participants.players.joinToString { it.name }
        println(MESSAGE_DEALING.format(playerNames))

        val dealerCard =
            participants.dealer.hand.cards
                .first()
        println(MESSAGE_CARD_INFO.format(participants.dealer.name, dealerCard.toText()))
        participants.players.forEach { printCards(it) }
    }

    fun printCards(participant: Participant) {
        println(cardsInfo(participant))
    }

    fun printHitOnce(participant: Participant) {
        println(MESSAGE_HIT_ONCE.format(participant.name))
    }

    fun printParticipantScore(participants: Participants) {
        println("${cardsInfo(participants.dealer)} ${MESSAGE_SCORE.format(participants.dealer.score().score)}")
        participants.players.forEach {
            println("${cardsInfo(it)} ${MESSAGE_SCORE.format(it.score().score)}")
        }
    }

    fun printParticipantsProfit(
        participants: Participants,
        dealerProfit: Profit,
        playersProfit: Map<Player, Profit>,
    ) {
        println(MESSAGE_GAME_RESULT)
        println(MESSAGE_RESULT.format(participants.dealer.name, dealerProfit.value.toString()))
        playersProfit.forEach { (player, profit) ->
            println(MESSAGE_RESULT.format(player.name, profit.value.toString()))
        }
    }

    private fun cardsInfo(participant: Participant): String {
        val joinedCards = participant.hand.cards.joinToString { it.toText() }
        return MESSAGE_CARD_INFO.format(participant.name, joinedCards)
    }

    companion object {
        private const val MESSAGE_DEALING = "\n딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_CARD_INFO = "%s 카드: %s"
        private const val MESSAGE_SCORE = "- 결과: %d"
        private const val MESSAGE_HIT_ONCE = "%s는 16이하라 한 장의 카드를 더 받았습니다."
        private const val MESSAGE_GAME_RESULT = "\n## 최종 수익"
        private const val MESSAGE_RESULT = "%s: %s"

        private fun Rank.toText(): String =
            when (this) {
                Rank.ACE -> "A"
                Rank.JACK -> "J"
                Rank.QUEEN -> "Q"
                Rank.KING -> "K"
                else -> this.number.toString()
            }

        private fun Suit.toText(): String =
            when (this) {
                Suit.DIAMOND -> "♦︎"
                Suit.CLUB -> "♣︎"
                Suit.HEART -> "♥︎"
                Suit.SPADE -> "♠︎"
            }

        private fun Card.toText(): String = "${this.rank.toText()}${this.suit.toText()}"
    }
}
