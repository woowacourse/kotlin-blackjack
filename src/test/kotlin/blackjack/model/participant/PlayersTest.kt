package blackjack.model.participant

import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `플레이어가 9명이면 에러가 발생한다`() {
        Players.from("모모", "치치", "뭉치", "모찌", "이든", "채넛", "밀러", "디렉")
    }
}
