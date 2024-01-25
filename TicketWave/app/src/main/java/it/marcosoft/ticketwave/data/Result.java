package it.marcosoft.ticketwave.data;

/**
 * A generic class that holds a result: success with data or an error with an exception.
 */
public abstract class Result<T> {
    private Result() {
    }

    /**
     * Get the value associated with this result.
     *
     * @return The result value.
     */
    public abstract T getValue();

    /**
     * Provide a string representation of the result.
     *
     * @return A string representation of the result.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[value=" + getValue().toString() + "]";
    }

    // Success sub-class
    public final static class Success<T> extends Result<T> {
        private final T data;

        /**
         * Construct a success result with data.
         *
         * @param data The success data.
         */
        public Success(T data) {
            this.data = data;
        }

        /**
         * Get the success data.
         *
         * @return The success data.
         */
        @Override
        public T getValue() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends Result<Exception> {
        private final Exception error;

        /**
         * Construct an error result with an exception.
         *
         * @param error The exception representing the error.
         */
        public Error(Exception error) {
            this.error = error;
        }

        /**
         * Get the exception representing the error.
         *
         * @return The exception representing the error.
         */
        @Override
        public Exception getValue() {
            return this.error;
        }
    }
}
