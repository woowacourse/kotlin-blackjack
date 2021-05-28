package domain.card

import domain.player.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ScoreTest{

    @DisplayName("Cards가 없는 경우 0점이다.")
    @Test
    fun testScoreWithEmptyCards(){
        val score = Score(listOf())
        assertThat(score.value).isEqualTo(0)
    }

    @DisplayName("K와 3을 갖고 있으면 13점이다.")
    @Test
    fun testScoreWithNormalCards(){
        val cards = listOf(Card(NumberType.THREE, ShapeType.DIAMOND), Card(NumberType.KING, ShapeType.CLOVER))
        val score = Score(cards)
        assertThat(score.value).isEqualTo(13)
    }

    @DisplayName("A를 1로 사용하는 경우를 테스트한다.")
    @Test
    fun testScoreWithUsingAceAsOne(){
        val cards = listOf(Card(NumberType.THREE, ShapeType.DIAMOND), Card(NumberType.KING, ShapeType.CLOVER), Card(NumberType.ACE, ShapeType.CLOVER))
        val score = Score(cards)
        assertThat(score.value).isEqualTo(14)
    }

    @DisplayName("A를 11로 사용하는 경우를 테스트한다.")
    @Test
    fun testScoreWithUsingAceAsEleven(){
        val cards = listOf(Card(NumberType.THREE, ShapeType.DIAMOND), Card(NumberType.ACE, ShapeType.CLOVER))
        val score = Score(cards)
        assertThat(score.value).isEqualTo(14)
    }
}