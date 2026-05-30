package com.wintux.skibidisigmaponmi.Tools;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculadora {

    public static int prioridad(char operador) {

        switch (operador) {

            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }

        return -1;
    }

    public static Queue<String> convertirAPostfix(String infix) {

        Stack<Character> piladebateria = new Stack<>();

        Queue<String> postfix = new LinkedList<>();

        for (int i = 0; i < infix.length(); i++) {

            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {

                postfix.offer(String.valueOf(c));
            }

            else if (c == '(') {

                piladebateria.push(c);
            }

            else if (c == ')') {

                while (!piladebateria.isEmpty() &&
                        piladebateria.peek() != '(') {

                    postfix.offer(String.valueOf(piladebateria.pop()));
                }

                piladebateria.pop();
            }

            else {

                while (!piladebateria.isEmpty() &&
                        prioridad(c) <= prioridad(piladebateria.peek())) {

                    postfix.offer(String.valueOf(piladebateria.pop()));
                }

                piladebateria.push(c);
            }
        }


        while (!piladebateria.isEmpty()) {

            postfix.offer(String.valueOf(piladebateria.pop()));
        }

        return postfix;
    }

    public static double resolverExpresionPostfix(Queue<String> postfix) {

        Stack<Double> pila = new Stack<>();

        while (!postfix.isEmpty()) {

            String elemento = postfix.poll();

            char c = elemento.charAt(0);

            // Si es número
            if (Character.isDigit(c)) {

                pila.push(Double.parseDouble(elemento));
            }

            // Si es operador
            else {

                double b = pila.pop();
                double a = pila.pop();

                switch (c) {

                    case '+':
                        pila.push(a + b);
                        break;

                    case '-':
                        pila.push(a - b);
                        break;

                    case '*':
                        pila.push(a * b);
                        break;

                    case '/':
                        pila.push(a / b);
                        break;

                    case '^':
                        pila.push(Math.pow(a, b));
                        break;
                }
            }
        }

        return pila.pop();
    }

    public static void mostrarCola(Queue<String> cola) {

        for (String elemento : cola) {

            System.out.print(elemento + " ");
        }

        System.out.println();
    }
}