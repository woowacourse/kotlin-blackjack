package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMarkTest {
    @Test
    fun `클로버를 반환한다`() {
        assertThat(CardMark.CLOVER.title).isEqualTo("클로버")
    }

    @Test
    fun `하트를 반환한다`() {
        assertThat(CardMark.HEART.title).isEqualTo("하트")
    }

    @Test
    fun `스페이드를 반환한다`() {
        assertThat(CardMark.SPADE.title).isEqualTo("스페이드")
    }

    @Test
    fun `다이아를 반환한다`() {
        assertThat(CardMark.DIA.title).isEqualTo("다이아")
    }
}
