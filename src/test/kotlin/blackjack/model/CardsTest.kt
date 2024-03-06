package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `기존 Cards 에 다른 card 를 추가할 수 있다`() {
        // given - 준비물
        val card = createCard()
        val cards = Cards(createCard())
        val expect =
            Cards(
                createCard(),
                createCard(),
            )
        // when
        val actual = cards + card
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `기존 Cards 에 다른 cards를 합칠 수 있다`() {
        // given - 준비물
        val cards = Cards(createCard())
        val cards2 = Cards(createCard())
        val expect =
            Cards(
                createCard(),
                createCard(),
            )
        // when
        val actual = cards + cards2
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `기존 Cards 에서 Cards 와 겹치는 부분을  뺄 수 있다`() {
        // given
        val cards = Cards(createCard())
        val card = createCard()
        val expect = Cards()
        // when
        val actual = cards - card
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
