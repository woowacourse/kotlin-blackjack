package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {

    @Test
    fun `참가자가 카드를 8클로버만 가지고 있을 때, 카드를 더 받을 수 있는지 확인하면, true이다`() {

        // given
        val participant = Participant("aaa", cards = Cards(listOf(Card(CardNumber.EIGHT, CardShape.CLOVER))))

        // when
        val actual: Boolean = participant.checkProvideCardPossible()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `참가자1 스코어는 Bust 딜러 스코어는 17일때, 참가자1의 수익률을 계산하면, -1_0이다`() {

        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        val participant1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.SEVEN, CardShape.HEART),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual: Double = participant1.calculateEarningRate(dealer).rate

        // then
        assertThat(actual).isEqualTo(-1.0)
    }

    @Test
    fun `참가자1 스코어는 17 딜러 스코어는 Bust 일때, 참가자1의 수익률을 계산하면, 1_0이다`() {

        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.QUEEN, CardShape.DIAMOND),
                    Card(CardNumber.KING, CardShape.DIAMOND),
                    Card(CardNumber.THREE, CardShape.SPADE)
                )
            )
        )
        val participant1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual: Double = participant1.calculateEarningRate(dealer).rate

        // then
        assertThat(actual).isEqualTo(1.0)
    }

    @Test
    fun `참가자1 스코어는 블랙잭 딜러 스코어는 17 일때, 참가자1의 수익률을 계산하면, 1_5이다`() {

        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )
        val participant1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.ACE, CardShape.DIAMOND),
                    Card(CardNumber.TEN, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual: Double = participant1.calculateEarningRate(dealer).rate

        // then
        assertThat(actual).isEqualTo(1.5)
    }

    @Test
    fun `참가자1 스코어는 블랙잭 딜러 스코어는 블랙잭 일때, 참가자1의 수익률을 계산하면, 0_0이다`() {

        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.QUEEN, CardShape.DIAMOND)
                )
            )
        )
        val participant1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.ACE, CardShape.DIAMOND),
                    Card(CardNumber.TEN, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual: Double = participant1.calculateEarningRate(dealer).rate

        // then
        assertThat(actual).isEqualTo(0.0)
    }
}
