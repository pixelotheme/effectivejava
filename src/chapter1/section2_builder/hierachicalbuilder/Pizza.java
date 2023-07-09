package chapter1.section2_builder.hierachicalbuilder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
// 코드 2-4 계층적으로 설계된 클래스와 잘 어울리는 빌더 패턴 (19쪽)

// 참고: 여기서 사용한 '시뮬레이트한 셀프 타입(simulated self-type)' 관용구는
// 빌더뿐 아니라 임의의 유동적인 계층구조를 허용한다.
*/


public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);// 비어있는 enum set을 만든다
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping)); //토핑 null 아닐때 추가
            // addTopping을 호출하면 하위 클래스에서 재정의한 self - 즉 하위 클래스자신을 반환
            // 다시 상속받은 하위 클래스의 chaining method 사용가능
            return self();
        }

        abstract Pizza build(); // 추상메서드
        //하위 클래스는 self 메서드를 overriding 하여 this를 반환하도록 해야 한다
        // 하위 클래스에서는 형변환 하지 않고도 메서드 연쇄가 가능하다
        // - java 에는 self 타입이 없어 이를 우회한 방법이다 - simulated self-typ(시뮬레이트한 셀프 타입 관용구)
        protected abstract T self();
    }
    //재귀적 타입 한정을 이용하는 제네릭 타입 - 아이템 50 참조
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone(); //빌더 매개변수로 들어온 값을 복사해 넣는다
    }
}
