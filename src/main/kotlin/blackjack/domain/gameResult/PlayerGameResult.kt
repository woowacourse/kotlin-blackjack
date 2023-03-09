package blackjack.domain.gameResult

data class PlayerGameResult(
    val playerName: String,
    val profitMoney: ProfitMoney
)//TODO: playerName String으로 받지 말고 PlayerName으로 받자 부생성자가 필요할듯