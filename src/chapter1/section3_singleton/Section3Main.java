package chapter1.section3_singleton;

import chapter1.section3_singleton.item1_field.Elvis;
import chapter1.section3_singleton.item2_factory.ElvisFactory;
import chapter1.section3_singleton.item3_enum.ElvisEnum;

/**
 * private 생성자나 열거 타입으로 싱글턴임을 보증하라
 *
 * 싱글턴의 예로는 무상태(stateless) 객체, 설계상 유일해야하는 시스템 컴포넌트
 *
 * 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트는 테스트가 어려워 질수 있다
 *
 * 싱글턴 인스턴스를 가짜(mock)구현으로 대체 불가
 * -> 타입을 인터페이스로 정의한 다음 -> 구현체 에서 따로 구현하여 만든 싱글턴 객체는 가능
 *
 * ----
 * 싱글턴 만드는 방식 - 필드, 정적 팩터리 방식
 * 1. 필드 방식
 * 1-1. 생성자 private
 * 1-2. public static 멤버인 INSTANCE에 유일하게 접근하도록
 * INSTANCE를 초기화 할때 한번만 생성자를 호출한다
 *
 * 2. 정적 팩터리 방식
 * 2-1. 생성자 private
 * 2-2. private static 멤버인 INSTANCE를 초기화 할때 한번만 생성자를 호출한다
 * 2-3. public static getInstance로 INSTANCE에 접근할수 있게 한다
 *
 * -------------
 * 장점
 * 1. 필드 방식
 *  - 해당 클래스가 싱글턴임이 API에 명백히 드러난다
 *  - 간결함
 *
 * 2. 정적 팩터리 방식
 *  - API를 바꾸지 않고도 싱글턴이 아니게 변경 가능 - 팩터리 메서드 부분의 return 값을 바꿔주기만 하면된다
 *  - 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다
 *  - 정적 팩터리의 메서드 참조를 공금자(supplier)로 사용 가능
 *   -> Elvis::getInstace를 Supplier<Elvis>로 사용
 *
 * -- 정리
 * 2. 정적 팩터리 메서드 의 장점이 굳이 필요하지 않다면 public 필드 방식이 좋다
 *
 * ---------------
 * 직렬화 : 자바가 객체를 바이트 스트림으로 인코딩(직렬화)하고
 * 그 바이트 스트림으로 부터 다시 객체를 재구성(역직렬화)하는 메커니즘
 *
 * 둘중 하나의 방식으로 만든 싱글턴을 직렬화하려면
 * Serializable 구현 만으로 부족
 * -> 역직렬화 때마다 새로운 인스턴스가 생긴다
 * 해결
 * -> 모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드 제공해야한다
 *
 *===============
 * 싱글턴 3번째 - 열거 타입 enum
 *
 * 장점
 * 1. 간걸하다
 * 2. 추가노력 없이 직렬화 가능
 * 3. 리플렉션 공격 불가
 *
 * -> 대부분 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법
 *
 * --문제점
 * 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면 이방법은 사용할수 없다
 * -> 열거 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다
 *
 *
 * ======
 * 정리
 * 싱글턴 객체를 만들때
 * 필드, 정적 팩터리 메서드 방식은
 * 리플렉션 공격에 대비, 직렬화 및 역직렬화에 대한 추가 구현이 필요하다
 *
 * 원소가 하나인 열거 타입(enum)방식의 싱글턴은 추가 구현 없이
 * 위의 문제가 해결된다
 *
 * -인스턴스가 JVM 내에 하나만 존재한다는 것이 100% 보장 된다
 * - enum은 외부에서 인스턴스화 할 수 있는 생성자 자체가 없다,
 * 다만 내부에서 관리되는 상수 (예제 : INSTANCE)를 생성하는 생성자는 존재 (Reflection 생성자 호출 불가)
 *
 * */
public class Section3Main {

    public static void main(String[] args) throws Exception{
        //필드방식 싱글턴
        Elvis el = Elvis.INSTANCE;
        el.leaveTheBuilding();

        Elvis el1 = Elvis.INSTANCE;

        System.out.println("el.equals(el1) = " + el.equals(el1));
        System.out.println("el1 = " + el1);
        System.out.println("el = " + el);

        ElvisFactory ef = ElvisFactory.getInstance();
        System.out.println("ef = " + ef);
        ElvisFactory ef1 = ElvisFactory.getInstance();
        System.out.println("ef1 = " + ef1);

        ElvisEnum ee = ElvisEnum.INSTACE;
        System.out.println("ee = " + ee.hashCode());
        ElvisEnum ee1 = ElvisEnum.INSTACE;
        System.out.println("ee1 = " + ee1.hashCode());
    }
}
