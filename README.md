Android Programing
----------------------------------------------------
### 2017.11.14 32일차

#### 예제
____________________________________________________

#### 공부정리
____________________________________________________

##### __Lambda__

- Lambda 란?

  > 식별자 없이 실행 가능한 함수 표현식.

- Lambda 표현법

  ```java
  // Lambda 식의 기본 문법
  ( parameters ) -> { expression body }
  // 하나의 실행문만 있는 Lambda 식
  ( parameters ) -> expression body
  // 매개변수가 없는 Lambda 식
  () -> { expression body }
  // 매개변수와 실행문이 하나 있는 Lambda 식
  parameter -> expression body
  ```

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

- RxJava

  
