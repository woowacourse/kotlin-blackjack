package blackjack.model

import blackjack.fixture.createCard
import blackjack.fixture.createDealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    @ParameterizedTest
    @CsvSource(
        "SIX,TEN,true",
        "SEVEN,TEN,false",
    )
    fun `손패합이 17 미만 이면, hit 할 수 있다`(
        rank: Rank,
        rank2: Rank,
        canHit: Boolean,
    ) {
        // given
        val dealer =
            Dealer(
                Hand(
                    createCard(rank = rank),
                    createCard(rank = rank2),
                ),
            )
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isEqualTo(canHit)
    }

    @Test
    fun `딜러가 hit 하면 손패가 하나 증가한다`() {
        val dealer =
            createDealer(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
            )
        val addedCard = createCard(rank = Rank.EIGHT)
        val expect =
            Hand(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
                createCard(rank = Rank.EIGHT),
            )
        // when
        dealer.hit(addedCard)
        val actual = dealer.hand
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러는 플레이어의 수익을 계산할 수 있다`() {
        TODO("Not yet implemented")
    }
}
