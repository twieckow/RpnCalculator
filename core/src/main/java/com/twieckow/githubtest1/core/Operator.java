package com.twieckow.githubtest1.core;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

enum Operator {
   PLUS("+"), MINUS("-");

   private final String stringVal;

   private static final Map<String, Operator> OPERATOR_BY_STRING_VAL = Stream.of(values())
      .collect(toMap(Operator::getStringVal, Function.identity()));


   Operator(final String stringVal) {
      this.stringVal = stringVal;
   }

   public String getStringVal() {
      return stringVal;
   }

   public static Operator getByStringVal(String stringVal) {
      return OPERATOR_BY_STRING_VAL.get(stringVal);
   }

}
