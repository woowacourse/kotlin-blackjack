# 수정 사항

## Project 구조

- [x] : ktlint hook 설정
- [x] : package 분리

## Domain

- [x] : 딜러와 플레이어에서 발생하는 중복 코드를 제거(Participant super class)
- [x] : Deck 내부 로직 뽑아서 쓰는걸로 수정
- [x] : Cards 의 합은 Participant 객체가 하도록 수정
- [x] : isBust, isBlackJack 은 Participant 가 가지는게 맞음(상태를 가지도록 해보면 어떨까..?)
- [x] : ParticipantHands 대신 Participant 가 자율성을 가지고 행동하도록 하자
- [x] : 승부를 판단하는 로직은 Participant 객체가 하자
- [x] : GameResult 에 "승", "패", "무" UI로 빼자
