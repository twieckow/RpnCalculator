package com.twieckow.githubtest1.core;

import com.twieckow.githubtest1.core.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RpnCalculatorTest {

   private RpnCalculator testObj = new RpnCalculator();

   @Test(expected = MalformedRpnExpression.class)
   public void shouldThrowExceptionWhenEmptyInput() {
      //given
      final String input = "  ";

      //when
      testObj.calculate(input);

      //then throw exception
   }

   @Test(expected = MalformedRpnExpression.class)
   public void shouldThrowExceptionWhenIllegalNumberOfParams() {
      //given
      final String input = "4 + 2";

      //when
      testObj.calculate(input);

      //then throw exception
   }

   @Test(expected = OperatorParsingException.class)
   public void shouldThrowExceptionWhenUnsupportedOperator() {
      //given
      final String input = "5 6 *";

      //when
      testObj.calculate(input);

      //then throw exception
   }

   @Test(expected = NumberParsingException.class)
   public void shouldThrowExceptionWhenWrongFormatOfNumber() {
      //given
      final String input = "5 6,5 *";

      //when
      testObj.calculate(input);

      //then throw exception
   }

   @Test
   public void shouldCalculateWhenJustOneNumber() {
      //given
      final String input = "23456789";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("23456789"));
   }

   @Test
   public void shouldCalculateWhenJustOneAddition() {
      //given
      final String input = "2 4 +";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("6"));
   }

   @Test
   public void shouldCalculateWhenTwoAdditions() {
      //given
      final String input = "2 4 + 100 +";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("106"));
   }

   @Test
   public void shouldCalculateWhenTwoAdditionsInDifferentOrder() {
      //given
      final String input = "2 4 100 + +";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("106"));
   }

   @Test
   public void shouldCalculateWhenOneSubtraction() {
      //given
      final String input = "2 4 -";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("-2"));
   }

   @Test
   public void shouldCalculateWhenManySubtractions() {
      //given
      final String input = "2 4 - -5 -5 - -";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("-2"));
   }

   @Test
   public void shouldCalculateWhenAdditionAndSubtraction() {
      //given
      final String input = "6 4 2 - + -3 5 - +";

      //when
      BigDecimal result = testObj.calculate(input);

      //then
      assertThat(result).isEqualTo(new BigDecimal("0"));
   }
}