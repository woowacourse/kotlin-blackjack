package blackjack.domain.result
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import java.lang.IllegalStateException

enum class Outcome(val rate: Double) {
    WIN_WITH_BLACKJACK(0.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    companion object {
        fun calculateGuestWin(guest: Guest, dealer: Dealer): Outcome = matchBlackJack(guest, dealer)

        private fun matchBlackJack(guest: Guest, dealer: Dealer): Outcome =
            when {
                guest.isBlackJack && guest.cards.size == 2 -> WIN_WITH_BLACKJACK
                guest.isBlackJack && dealer.isBlackJack -> DRAW
                guest.isBlackJack -> WIN
                dealer.isBlackJack -> LOSE
                else -> matchBust(guest, dealer)
            }

        private fun matchBust(guest: Guest, dealer: Dealer): Outcome =
            when {
                guest.isBust && dealer.isBust -> DRAW
                guest.isBust -> LOSE
                dealer.isBust -> WIN
                else -> matchScore(guest, dealer)
            }

        private fun matchScore(guest: Guest, dealer: Dealer): Outcome =
            when {
                guest.score > dealer.score -> WIN
                guest.score == dealer.score -> DRAW
                guest.score < dealer.score -> LOSE
                else -> throw IllegalStateException()
            }
    }
}
