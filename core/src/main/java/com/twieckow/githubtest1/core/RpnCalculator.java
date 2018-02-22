package com.twieckow.githubtest1.core;

import com.twieckow.githubtest1.core.exception.*;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static java.lang.String.format;

public class RpnCalculator {

   private static final String SEPARATOR = " ";

   public BigDecimal calculate(final String input) {

      final Deque<BigDecimal> stack = new ArrayDeque<>();

      Stream.of(input.split(SEPARATOR))
         .map(String::trim)
         .filter(s -> !s.isEmpty())
         .forEach(item -> {
            if (isNumberCandidate(item)) {
               BigDecimal number = toNumber(item);
               stack.push(number);
            } else {
               Operator operator = toOperator(item);

               if (stack.size() < 2) {
                  throw new MalformedRpnExpression("Missing number items to use with operator=" + operator);
               }
               BigDecimal right = stack.pop();
               BigDecimal left = stack.pop();
               BigDecimal partialResult = calculate(operator, left, right);
               stack.push(partialResult);
            }
         });

      if (stack.size() != 1) {
         throw new MalformedRpnExpression(format("Malformed RPN expression! input=%s", input));
      }

      return stack.pop();
   }

   private boolean isNumberCandidate(final String item) {
      return item.matches("^-?[0-9]+.*$");
   }

   private BigDecimal toNumber(final String item) {
      BigDecimal number;
      try {
         number = new BigDecimal(item);
      } catch (Exception e) {
         throw new NumberParsingException("Error while parsing item=" + item, e);
      }
      return number;
   }

   private Operator toOperator(final String item) {
      Operator operator;
      operator = Operator.getByStringVal(item);
      if (operator == null) {
         throw new OperatorParsingException(format("Error while parsing item=%s", item));
      }
      return operator;
   }


   private BigDecimal calculate(Operator operator, BigDecimal left, BigDecimal right) {
      switch (operator) {
         case PLUS:
            return left.add(right);
         case MINUS:
            return left.subtract(right);
         default:
            throw new IllegalArgumentException(
               format("Have you added a new operation [%s] without adding support for it in code?", operator)
            );
      }
   }


}
