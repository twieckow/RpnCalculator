package com.twieckow.githubtest1.core.exception;

public class NumberParsingException extends RuntimeException {
   public NumberParsingException(final String message) {
      super(message);
   }

   public NumberParsingException(final String message, final Throwable cause) {
      super(message, cause);
   }
}
