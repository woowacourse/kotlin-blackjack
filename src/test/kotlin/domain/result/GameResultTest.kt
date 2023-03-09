package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardNumber.ACE
import domain.card.CardNumber.EIGHT
import domain.card.CardNumber.KING
import domain.card.CardNumber.NINE
import domain.card.CardShape
import domain.money.Money
import domain.person.Dealer
import domain.person.Participants
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultTest {

    fun Player(cardNumbers: List<CardNumber>, name: String) =
        Player(cardNumbers.map { Card(CardShape.HEART, it) }, name)

    fun Dealer(cardNumbers: List<CardNumber>) = Dealer(cardNumbers.map { Card(CardShape.HEART, it) })

    @Test
    fun `모든 사람의 승패를 결정한다`() {
        // given
        val players = listOf<Player>(
            Player(listOf(KING, EIGHT), "빅스"),
            Player(listOf(ACE, KING), "베르"),
        )
        val dealer = Dealer(listOf(KING, NINE))
        val gameResult = GameResult(Participants(dealer, players))

        val bettingMoney = mapOf("빅스" to Money(10000), "베르" to Money(20000))

        val expectedPlayersResult = mapOf("빅스" to -10000.0, "베르" to 30000.0)

        // when
        val playerResult = gameResult.getPlayerResult(bettingMoney)
        val dealerResult = gameResult.getDealerResult(playerResult)

        assertAll(
            { assertThat(playerResult).isEqualTo(expectedPlayersResult) },
            { assertThat(dealerResult).isEqualTo(-20000.0) },
        )
    }
}
