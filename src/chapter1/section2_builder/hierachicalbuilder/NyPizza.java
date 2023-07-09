package chapter1.section2_builder.hierachicalbuilder;

import java.util.Objects;

public class NyPizza extends Pizza{

    public enum Size{SMALL, MEDIUM, LAGE}
    private final Size size;

    // 부모 객체의 builder 에 있는 addTopping 사용 가능

    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size; //필수 값
        //생성자 - 필수값 전달
        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }
        //NyPizza 생성자에 builder를 넘겨준다
        // -> 부모객체의 생성자에 값을 넘겨준다
        @Override public NyPizza build(){
            return new NyPizza(this);
        }
        @Override protected Builder self(){return this;}
    }
    //NyPizza builder생성자는 Pizza 의 기본 생성자를 호출한다 - topping 을 저장해야 하니 불렀다
    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
