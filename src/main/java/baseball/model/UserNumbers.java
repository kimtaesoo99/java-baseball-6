package baseball.model;

import baseball.util.Converter;
import java.util.Collections;
import java.util.List;

public class UserNumbers {

    private static final int REQUIRED_INPUT_LENGTH = 3;
    private static final String ZERO_DIGIT = "0";
    private static final String INVALID_LENGTH_MESSAGE = "숫자는 세 가지를 고를 수 있습니다.";
    private static final String ZERO_ERROR_MESSAGE = "0이 포함되어서는 안됩니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 숫자는 입력할 수 없습니다.";

    private final List<Integer> numbers;

    private UserNumbers(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static UserNumbers createWith(final String input) {
        validate(input);
        List<Integer> numbers = Converter.convertStringToIntegerList(input);
        return new UserNumbers(numbers);
    }

    private static void validate(final String input) {
        validateLength(input);
        validateNoZeros(input);
        validateNoDuplicates(input);
    }

    private static void validateLength(final String input) {
        if (input.length() != REQUIRED_INPUT_LENGTH) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }
    }

    private static void validateNoZeros(final String input) {
        if (containsZero(input)) {
            throw new IllegalArgumentException(ZERO_ERROR_MESSAGE);
        }
    }

    private static boolean containsZero(final String input) {
        return input.contains(ZERO_DIGIT);
    }

    private static void validateNoDuplicates(final String input) {
        if (containsDuplicates(input)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private static boolean containsDuplicates(final String input) {
        return input.chars()
                .distinct()
                .count() != REQUIRED_INPUT_LENGTH;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
