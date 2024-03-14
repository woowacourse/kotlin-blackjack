package blackjack.base

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Name
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BaseHolderTest {
    @ParameterizedTest
    @CsvSource(value = ["1/0/1", "0/2/0", "2/2/2"], delimiter = '/')
    fun `승리, 무승부, 패배 현황이 변경돨 수 있다`(
        win: Int,
        push: Int,
        defeat: Int,
    ) {
        val winner = Player(Name("레오"))
        val loser = Player(Name("호두"))
        val dealer = Dealer()

        winner.changeResult(GameResult(win = win))
        dealer.changeResult(GameResult(push = push))
        loser.changeResult(GameResult(defeat = defeat))
        assertThat(winner.gameResult.win).isEqualTo(win)
        assertThat(dealer.gameResult.push).isEqualTo(push)
        assertThat(loser.gameResult.defeat).isEqualTo(defeat)
    }
}
