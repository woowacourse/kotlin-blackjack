package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardTest {
    @Test
    fun `카드를 하나 생성할 수 있다`(){
        assertDoesNotThrow{ Card(CardRank.KING, Shape.CLUB)}
    }

    @Test
    fun `페이스 카드의 점수는 10점이다`(){
        val king = Card(CardRank.KING,Shape.CLUB).cardRank
        val queen = Card(CardRank.QUEEN, Shape.CLUB).cardRank

        assertThat(queen.score).isEqualTo(10)
        assertThat(king.score).isEqualTo(10)
    }
}
