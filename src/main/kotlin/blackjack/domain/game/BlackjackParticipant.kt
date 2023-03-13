package blackjack.domain.game

import blackjack.domain.betting.BettingMoney
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class BlackjackParticipant private constructor(
    val dealer: Dealer,
    val players: List<Player>
) {
    companion object {
        fun of(dealer: Dealer, names: List<String>, money: (String) -> (BettingMoney)): BlackjackParticipant {
            return BlackjackParticipant(dealer, names.map { Player(it, money(it)) })
        }
    }
}
