package com.reddylabs;

public class TestRegularExpression {
    public static void main(String[] args) {
    String test  = "510936098888000";

    if(test.matches(".*[0-9]{3,}+.*")){
    System.out.println(test);
        }
  }
}
