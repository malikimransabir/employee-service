package org.nisum.employeeservice.application;

import java.util.ArrayList;
import java.util.function.Consumer;

public class UserService {


    public interface Add {
        int add(int a, int b);
    }

    public interface Multiply {
        int multiply(int a, int b);
    }


    public interface Subtract<T> {
        T subtract(T a, T b);
    }


    public static void main(String[] args) {

        Add result = (int a, int b) -> a + b;
        //System.out.println(result.add(2, 3));

        Multiply mul = (a, b) -> a * b;
        //System.out.println(mul.multiply(3, 3));

        Subtract<Integer> sub = (a, b) -> a - b;
        //System.out.println(sub.subtract(3, 3));



        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        Consumer<Integer> method = (n) -> { System.out.println(n); };
        numbers.forEach( method );

        numbers.forEach( (n) -> { System.out.println(n); } );


    }


}
