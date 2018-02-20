package com.twieckow.githubtest1.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WorketTest {

   private Worket testObj = new Worket();

   @Test
   public void shouldDecode() {
      //given
      final String input = "someInput";

      //when
      String result = testObj.decode(input);

      //then
      assertThat(result).isEqualTo("expected");
   }
}