package com.twieckow.githubtest1.cli;

import com.twieckow.githubtest1.core.RpnCalculator;

import java.math.BigDecimal;

public class CliApp {

   public static void main(String[] args) throws Exception {
      if (args.length != 1) {
         System.out.println("One argument required. Please provide your RPN expression in quotes.");
         System.exit(1);
      }
      RpnCalculator rpnCalculator = new RpnCalculator();
      BigDecimal result = rpnCalculator.calculate(args[0]);
      System.out.println(result);
   }
}
