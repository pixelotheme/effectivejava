package chapter1.section2_builder.hierachicalbuilder;

import java.util.Objects;

public class Calzone extends Pizza {
    private final boolean sauceInside;

    // 부모 객체의 builder 에 있는 addTopping 사용 가능
    public static class Builder extends Pizza.Builder<Builder>{

        private Boolean sauceInside = false; //필수 값
        //생성자 - 필수값 전달
        public Builder sauceInside(){
            sauceInside = true;
            return this;
        }
        //NyPizza 생성자에 builder를 넘겨준다
        // -> 부모객체의 생성자에 값을 넘겨준다
        @Override public Calzone build(){
            return new Calzone(this);
        }
        @Override protected Builder self(){return this;}
    }

    private Calzone(Builder builder){
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
