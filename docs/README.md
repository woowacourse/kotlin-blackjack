# 블랙잭 기능 정의

### Entity
- CardType
    - type
- CardNumber
    - name
    - numberStrategy
- Card
    - type
    - number
- Cards
    - List<Card>

### Model
- Player
    - name
    - cards
    - checkMoreCard(cardChecker)
    - determineGameResult(determiner)
- CardChecker
    - UserCardChecker
    - DealerCardChecker
- GameResultDeterminer
    - UserGameResultDeterminer
    - DealerGameResultDeterminer
- CardDistributer
    - distribute(player, number)
- GameRule
    - initialDistribute()
    - checkPlayer(player)