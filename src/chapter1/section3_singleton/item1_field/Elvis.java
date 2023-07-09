package chapter1.section3_singleton.item1_field;

public class Elvis {
    //final 은 처음 INSTANCE를 초기화 할때 딱 한번만 들어가고 변경되지 않는다
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    public void leaveTheBuilding(){
        System.out.println("1. 나간다");
    }

    //싱글턴임을 보장해주는 readResolve 메서드
    private Object readResolve(){
        // '진짜' Elvis를 반환하고, 가짜 Elvis는 가비지 컬렉터에 맡긴다.
        return INSTANCE;
    }
}
