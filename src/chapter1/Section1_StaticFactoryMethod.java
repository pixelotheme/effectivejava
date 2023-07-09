package chapter1;

/**
 * 생성자 대신 정적 팩터리 메서드를 고려하자
 *
 * 1. 메서드 명으로 명확히 구분가능 - 생성자는 메서드 위치로 파악해야함
 * 2. 호출 될때마다 새로운 인스턴스를 생성하지 않을수 있다
 *  -> 인스턴스 통제 클래스 (instance-controlled)
 *    Boolean bolRef = Boolean.valueOf(bol);
 *  -> instace-controlled 를 통해 싱글톤으로 만들수 있다
 *
 * 3. 반환 타입의 하위 타입 객체를 반환할수 있다
 * -> 컬랙션 프레임워크는 핵심 인터페이스에 수정 불가, 동기화 등의 기능을
 *    Collections 에서 정적 팩터리 메서드를 얻을수 있다  - 상속받아 사용
 *   - 예시 Collections 클래스에 정의된 메서드 - 구현한 내용이 있다
 *   private static <T> T get(ListIterator<? extends T> i, int index)
 *
 *   -> List 에서 사용한다
 *   List<String> str;
 *         str.get();
 *
 *   4. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다
 *   -> 잘 이해가 안된다 다음에 다시 읽어보자
 *
 *   - 서비스 제공자 프레임워크 (service provider framework)는 아래의 내용으로 이루어져 있다 ******
 *   - 서비스 인터페이스(구현체 동작 제어), 제공자 등록 API(제공자가 구현체 등록)
 *   , 서비스 접근 API(클라이언트가 서비스 인스턴스를 얻을떄 사용)
 *   3가지가 핵심적인 기능
 *
 *   단점 ---------
 *
 *   1. 상속 하려면 public, protected 생성자가 필요해 결국 정적 팩터리 메서드만으로는 하위 클래스를 만들 수 없다
 *
 *   2. 찾기 어렵다
 *
 *   ------------
 *   명명규칙은 책에서 - p.12
 *
 *   ---
 *   정리
 *   - 정적 팩토리 메서드(SFM)와 public 생성자는 각각의 쓰임새가 있으니 구분하자
 *   - 하지만 정적 팩토리 메서드의 장점이 더 많으므로 객체의 반환은 SFM 을 사용하자
 *   -> 문제점은 매개변수가 많아짐에 따라 점점 대응하기 힘들어진다
 *
 *   -> builder를 사용하여 해결하자
 * */


public class Section1_StaticFactoryMethod {
    public static void main(String[] args){
        boolean bol = true;
        Boolean bolRef = Boolean.valueOf(bol);
//        List<String> str;
//        str.get();
     }
}
