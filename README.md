# Shopping Cycle
내가 가진 물건의 구매 주기 계산 및 다음 구매 예정일 예측

## 시스템의 기능(시스템 책임)
다음 구매 예정일을 계산하라.

## 다형성 적용
timo.shoppingcycle.domain.calculator 패키지
- MeasuringCalculator: 하루당 사용량 계산 추상 클래스
- AmountMeasuringCalculator: 용량(ml, l) 계산 구체 클래스
- CountMeasuringCalculator: 갯수 계산 구체 클래스

추상 클래스에 템플릿 메서드 패턴을 적용하여 공통적인 로직을 구현, 계산 유형에 따라 변경되는 포인트를 하위(구체) 클래스에게 위임<br>
이 방법을 통해 다른 계산 유형이 추가되어도 기존 로직의 변경 없이 하위 구체 클래스로 확장할 수 있다. (OCP 원칙)
