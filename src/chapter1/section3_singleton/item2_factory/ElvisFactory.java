package chapter1.section3_singleton.item2_factory;

public class ElvisFactory {

    //필드 방식을 private으로 막는다
    private static final ElvisFactory INSTANCE = new ElvisFactory();

    private  ElvisFactory() {
    }
    //정적 팩터리 메서드
    public static ElvisFactory getInstance(){return INSTANCE; }

    public void leaveTheBuilding(){
        System.out.println("2. 나간다");
    }
}
