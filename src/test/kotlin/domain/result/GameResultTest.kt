package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.person.PersonGenerator.makeDealer
import domain.person.PersonGenerator.makePlayer
import domain.person.Persons
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultTest {
    @Test
    fun `모든 사람의 승패를 결정한다`() {
        val players = listOf<Player>(
            makePlayer {
                name("빅스")
                addTwoCards(Card(CardShape.HEART, CardNumber.TWO), Card(CardShape.DIAMOND, CardNumber.THREE))
            }.apply {
                receiveCard(Card(CardShape.CLOVER, CardNumber.FOUR))
            },
            makePlayer {
                name("베르")
                addTwoCards(Card(CardShape.CLOVER, CardNumber.ACE), Card(CardShape.SPADE, CardNumber.KING))
            },
        )
        val dealer = makeDealer {
            addTwoCards(Card(CardShape.CLOVER, CardNumber.KING), Card(CardShape.SPADE, CardNumber.NINE))
        }
        val gameResult = GameResult(Persons(dealer, players))

        val expectedDealerResult = mapOf(OutCome.WIN to 1, OutCome.LOSE to 1)
        val expectedPlayersResult = mapOf("빅스" to OutCome.LOSE, "베르" to OutCome.WIN)

        assertAll(
            { assertThat(gameResult.getDealerResult()).isEqualTo(expectedDealerResult) },
            { assertThat(gameResult.getPlayerResult()).isEqualTo(expectedPlayersResult) },
        )
    }
}
