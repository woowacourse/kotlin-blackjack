package entity

import model.ManualCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `모든 플레이어에게 카드를 21이 넘을 때 까지 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.FIVE,
                CardType.CLUB to CardNumber.TEN,
                CardType.CLUB to CardNumber.TEN,
                CardType.CLUB to CardNumber.ACE,
                CardType.CLUB to CardNumber.TEN
            )
        )
        val players = Players(
            listOf(
                Player(Name("user1")),
                Player(Name("user2")),
            )
        )

        // when
        players.distribute(cardFactory, { true }, {})
        val actual = players.value.flatMap { it.cards.value }

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.FIVE),
            Card(CardType.CLUB, CardNumber.TEN),
            Card(CardType.CLUB, CardNumber.TEN),
            Card(CardType.CLUB, CardNumber.ACE),
            Card(CardType.CLUB, CardNumber.TEN),
        )

        assertThat(actual).isEqualTo(except)
    }
}
