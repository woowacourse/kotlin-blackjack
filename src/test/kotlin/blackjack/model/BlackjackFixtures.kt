package blackjack.model

fun createSinglePlayerGameInfo(): GameInfo =
    GameInfo(
        name = "케이엠",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                Card.of(Shape.HEART, CardValue.K, 6),
            ),
    )

fun createMultiplePlayersGameInfo(): List<GameInfo> =
    listOf(
        GameInfo(
            "케이엠",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.SEVEN, 0),
                Card.of(Shape.DIAMOND, CardValue.K, 7),
            ),
        ),
        GameInfo(
            "해음",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.SIX, 0),
                Card.of(Shape.SPADE, CardValue.K, 6),
            ),
        ),
        GameInfo(
            "차람",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.FIVE, 0),
                Card.of(Shape.CLOVER, CardValue.K, 5),
            ),
        ),
    )

fun createBurstDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        setOf(
            Card.of(Shape.CLOVER, CardValue.SIX, 0),
            Card.of(Shape.HEART, CardValue.K, 6),
            Card.of(Shape.DIAMOND, CardValue.K, 16),
        ),
    )

fun createHitDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        setOf(
            Card.of(Shape.CLOVER, CardValue.SIX, 0),
            Card.of(Shape.HEART, CardValue.K, 6),
        ),
    )

fun createStandDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        setOf(
            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
            Card.of(Shape.HEART, CardValue.K, 7),
        ),
    )

fun createMultiplePlayers(numberOfPlayers: Int): List<String> = List(numberOfPlayers) { "player$it" }
