package blackjack.base

import blackjack.model.Dealer
import blackjack.model.Nickname
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardHolderTest {
    @ParameterizedTest
    @CsvSource(value = ["1/0/1", "0/2/0", "2/2/2"], delimiter = '/')
    fun `승리, 무승부, 패배 현황이 변경돨 수 있다`(
        win: Int,
        push: Int,
        defeat: Int,
    ) {
        val winner = Player(Nickname("레오"))
        val loser = Player(Nickname("호두"))
        val dealer = Dealer(Nickname("에디"))

        winner.hand.changeResult(newWin = win)
        loser.hand.changeResult(newDefeat = defeat)
        dealer.hand.changeResult(newPush = push)
        assertThat(winner.hand.gameResult.win).isEqualTo(win)
        assertThat(loser.hand.gameResult.defeat).isEqualTo(defeat)
        assertThat(dealer.hand.gameResult.push).isEqualTo(push)
    }
}
