package info.solidsoft.java8.functions;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Predicates {

    public static <T> void checkElement(T elem, Predicate<T> predicate) {
        if (predicate.test(elem)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }

    public static void main(String[] args) {
        String elem = "ABC";

        Predicate<String> lengthAnonymousPredicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s != null && s.length() > 5;
            }
        };

        Predicate<String> lengthLambdaPredicate = s -> s.length() > 5;

        Predicate<String> lengthMethodPredicate = Predicates::checkLength;

//        Predicate<String> emptyStringPredicate = String::isEmpty;
//
//        checkElement(elem, lengthAnonymousPredicate);
//        checkElement(elem, lengthLambdaPredicate);

        Supplier<String> supplier = String::new;

        System.out.println("From supplier: " + supplier.get());
    }

    public static boolean checkLength(String s) {
        return s != null && s.length() > 5;
    }

    public boolean checkLengthNonStatic(String s) {
        return s != null && s.length() > 5;
    }

    public void checkNonStatic(String s) {
        checkElement(s, this::checkLengthNonStatic);
    }


}
