Android Programing
----------------------------------------------------
### 2017.11.14 32일차

#### 예제
____________________________________________________

#### 공부정리
____________________________________________________

##### __Lambda__

- 함수형 프로그래밍

  >함수형 프로그래밍은 함수의 입력만을 의존하여 출력을 만드는 구조로 외부에 상태를 변경하는 것을 지양하는 패러다임으로 부작용(Side-effect) 발생을 최소화 하는 방법론을 말한다.

  - 함수형 프로그래밍은 `순수한 함수(Pure Function)`, `익명 함수(Annonymous Function)`, `고계 함수(Higher-order Function)` 을 만족시켜야 한다.

  - 순수한 함수(Pure Function) : 함수의 실행이 외부의 상태를 변경시키지 않는 함수를 의미

  - 익명 함수(Annonymous Function) : 이름의 없는 함수를 정의

  - 고계 함수(Higher-order Function) : 함수형 언어에서는 함수도 하나의 값으로 취급하고, 함수의 인자로 함수를 전달할 수 있는 특성(일급 함수)

  - 이 함수형 프로그래밍을 람다식으로 표현한다. 하지만 JAVA 에서는 함수의 개념이 없고, 객체 개념이기 때문에 함수형 언어를 JAVA 언어 차원에서 지원을 하지 못하였다. 그래서 나온 것이 `함수형 인터페이스(단 하나의 메소드만이 선언된 인터페이스)`라는 개념을 도입하였다.

- Lambda 란?

  > 식별자 없이 실행 가능한 함수 표현식.

- Lambda 표현법

  ```java
  () -> {}                     // No parameters; result is void
  () -> 42                     // No parameters, expression body
  () -> null                   // No parameters, expression body
  () -> { return 42; }         // No parameters, block body with return
  () -> { System.gc(); }       // No parameters, void block body
  () -> {
    if (true) { return 12; }
    else { return 11; }
  }                          // Complex block body with returns
  (int x) -> x+1             // Single declared-type parameter
  (int x) -> { return x+1; } // Single declared-type parameter
  (x) -> x+1                 // Single inferred-type parameter
  x -> x+1                   // Parens optional for single inferred-type case
  (String s) -> s.length()   // Single declared-type parameter
  (Thread t) -> { t.start(); } // Single declared-type parameter
  s -> s.length()              // Single inferred-type parameter
  t -> { t.start(); }          // Single inferred-type parameter
  (int x, int y) -> x+y      // Multiple declared-type parameters
  (x,y) -> x+y               // Multiple inferred-type parameters
  (final int x) -> x+1       // Modified declared-type parameter
  (x, final y) -> x+y        // Illegal: can't modify inferred-type parameters
  (x, int y) -> x+y          // Illegal: can't mix inferred and declared types
  ```

  - 단순한 람다 구문의 경우, 람다 구분에 중괄호가 없을 수도 있다.

  - return 이 없을 수도 있다.

  - 매개변수에는 타입을 명시하지 않아도 된다. (타입 추론)

  - 람다식 문법을 컴파일러가 익명 클래스로 변환한다. 즉, 함수형 인터페이스를 컴파일러가 구현하도록 위임하는 형태라 볼 수 있다

- Lambda 사용법

  ```java
  // 람다식은 함수형 인터페이스를 표현하는 방법이다.
  // 함수형 인터페이스(FunctionalInterface) : 하나의 메소드로 구성된 인터페이스
  @FunctionalInterface
  interface One{
    public void run();
  }

  @FunctionalInterface
  interface Two{
    public void runTo(int a);
  }

  class Example {
    // 람다식 작성 ( 기본 문법 )
    One one = ()-> {System.out.println("run 작성");};
		one.run();
    // 람다식 작성
    Two two = (x)->System.out.println(x);
		two.runTo(10);
  }
  ```

- Lambda 제공 메소드

  ```java
  //////////////////////////////////////////////////////////
	// 단항 인터페이스
	//////////////////////////////////////////////////////////

	// 1. Supplier<T> : 입력 값이 없고, 반환 값이 있을 떄 사용
	Supplier<Integer> supplier = ()-> 180+20;
	System.out.println(supplier.get());

	// 2. Consumer<T> : 입력값이 있고, 반환값이 없을 때 사용
	//				  코드 블럭에서 사용 처리가 되어야 한다.
	Consumer<Integer> consumer = System.out::println;
	consumer.accept(100);

	// 3. Function<T, U> : 입력값이 있고, 반환값이 있을 때 사용
	Function<Integer, Double> function = x-> x * 0.5;
	System.out.println(function.apply(100));

	// 4. Predicate<T> : 입력값에 대한 참, 거짓을 판단. return type = boolean;
	Predicate<Integer> predicate = x -> x >100;
	System.out.println(predicate.test(50));

	// 5. UnaryOperatoer<T> : 입력과 반환 타입이 동일할 때 사용
	UnaryOperator<Integer> operator = x -> x+20;
	System.out.println(operator.apply(100));

	//////////////////////////////////////////////////////////
	// 이항 인터페이스
	//////////////////////////////////////////////////////////

	// 1. BiConsumer<T, U> : 입력값이 있고(T, U), 반환값이 없을 때 사용
	BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println(x+y);
	biConsumer.accept(10, 20);
	// 2. BiFunction<T, U, R> : 입력값이 있고(T, U), 반환값(R)이 있을 때 사용
	BiFunction<Integer, Integer, Double> biFunction = (x, y) -> x*y*0.5;
	System.out.println(biFunction.apply(10, 20));
	// 3. BiPredicate<T, U> : 입력값(T, U)에 대한 참, 거짓을 판단.
	BiPredicate<Integer, Integer> biPredicate =  (x, y) -> x==y;
	System.out.println(biPredicate.test(10, 20));
	// 4. BinaryOperatoer<T> : 입력과 반환 타입이 동일할 때 사용
	BinaryOperator<Integer> binaryOperator = (x,y)-> x+y;
	System.out.println(binaryOperator.apply(10, 20));
  ```

- 참조 : [Lambda](https://skyoo2003.github.io/post/2016/11/09/java8-lambda-expression)
