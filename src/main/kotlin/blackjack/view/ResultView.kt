package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Participant.Companion.INIT_CARD_SIZE
import blackjack.domain.Player

object ResultView {
    private const val SET_UP_MESSAGE = "\n%s와 %s에게 $INIT_CARD_SIZE 장의 카드를 나누었습니다."
    private const val FACE_UP_CARDS = "%s 카드: %s"

    fun printSetUp(dealer: Dealer, players: List<Player>) {
        val names = players.joinToString(", ") { it.name }
        println(SET_UP_MESSAGE.format(dealer.name, names))
        println(dealer.faceUpOnlyOne())
        players.forEach { println(it.faceUp()) }
        println()
    }

    fun printPlayerCards(player: Player) {
        println(player.faceUp())
    }

    private fun Dealer.faceUpOnlyOne() = FACE_UP_CARDS.format(this.name, this.cards[0].name())
    private fun Participant.faceUp() = FACE_UP_CARDS.format(this.name, this.cards.joinToString(", ") { it.name() })
    private fun Card.name() = "${this.number.mark}${this.shape.mark}"
}
