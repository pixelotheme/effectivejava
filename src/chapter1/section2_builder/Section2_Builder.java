package chapter1.section2_builder;

import chapter1.section2_builder.builder.NutritionFacts;
import chapter1.section2_builder.hierachicalbuilder.Calzone;
import chapter1.section2_builder.hierachicalbuilder.NyPizza;
import chapter1.section2_builder.hierachicalbuilder.Pizza;

/**
 * 생성자에 매개변수가 많다면 builder를 사용하자
 *
 * 1. 매개변수가 많아지면 점증적 생성자 패턴을 사용
 * -> 문제점 필요없는 매개변수도 넣어줘야 한다, 어떤 값이 틀렸는지 알수없다
 *
 * 2. java beans 패턴 setter 사용
 * -> 문제점
 * -> 객체가 완전히 생성되기 전까지 일관성이 무너진다
 * Test t = new Test();
 * t.setA()
 * t.setB()
 * .....
 * -> 불변 객체로 만들기 위해 추가적인 작업을 해줘야한다
 * -> freeze 사용 -> 그러나 컴파일러가 freeze를 제대로 호출한 상태로 코드를 작성했는지 컴파일러가 보증할수 없다
 * -> 그래서 런타임 오류에 취약하다
 *
 * 3. 빌더 패턴
 * -> 필수 매개변수만 생성자(또는 정적 팩터리 메서드)로 만들어 builder 객체를 얻는다
 * -> builder 객체가 제공하는 일종의 setter 메서드로 선택 매개변수들을 설정한다
 * -> 마지막으로 build를 호출해 객체를 얻는다
 *
 * -> NutritionFacts 클래스는 불변이며 모든 매개변수의 기본값들을 한곳에 모아 뒀다
 * -> 빌더의 세터 메서드들은 빌더 자신을 반환하기 떄문에 연쇄적으로 호출할 수 있다
 * --- method chaining (메서드 체이닝)이라고 한다 ******
 *
 * --- 유효성 검사 코드를 통해 매개변수를 더 일찍 발견할수 있다
 * -> 빌더로부터 매개변수를 복사한 후 해당 객체 필드들을 검사한다
 *
 * ======
 * 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기 좋다
 * - 추상 클래스는 추상 빌더를, 구체(concrete class) 클래스는 구체 빌더를 갖게 한다
 *
 * 구체적 예제
 *hierachicalbuilder 패키지
 *
 * 각 하위클래스의 Builder(내부 클래스로 선언)클래스가 정의한
 * - build 메서드는 해당하는 구체 하위 클래스를 반환하도록 선언한다 - 각각 NyPizza 또는 Calzone
 * - > 하위 클래스의 메서드가 상우 ㅣ클래스의 메서드가 정의한 반환 타입이 아닌
 * -> 그 하위 타입을 반환하는 기능
 * => 공변 반환 타이핑(covariant return typing)******
 *
 * -> 공변 반환 타이핑을 사용하면 클라이언트가 형변환에 신경 쓰지 않고도 빌더를 사용할 수 있다
 *
 * ---
 * 호출 때 넘겨진 매개변수들을 하나의 필드로 모을 수도 있다
 * -> addTopping 메서드 구현 방법
 *
 * --> 빌더 하나로 여러 객체를 순회하면서 만들수도 있고,
 * 일련번호 같은 특정 필드는 빌더가 알아서 채우도록 할 수도 있다
 *
 *
 * */
public class Section2_Builder {

    public static void main(String[] args){
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

        //계층구조 빌더
        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .build();


    }

}
