package exercises;


import javafx.scene.input.Mnemonic;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class E13 {
    public static class Monomial {
        public int c;
        public int d;

        public Monomial(int coefficient, int degree) {
            this.c = coefficient;
            this.d = degree;
        }

        public static Monomial multiply(Monomial m1, Monomial m2) {
            return new Monomial(m1.c * m2.c, m1.d + m2.d);
        }

        @Override
        public String toString() {
            return String.format("Monomial{%sx^%s}", c, d);
        }
    }

    public static class Polynomial {
        public static List<Monomial> multiply(List<Monomial> list1, List<Monomial> list2) {
            List<Monomial> tmp = list1.stream()
                    .flatMap(x -> list2.stream()
                            .map(y -> Monomial.multiply(x, y))
                    )
                    .collect(toList());
            return reduce(tmp);
        }

        public static List<Monomial> reduce(List<Monomial> monomials) {
            Element<Monomial> reduces = null;
            for (Monomial m : monomials) {
                if (reduces == null) {
                    reduces = new Element<>(m, null);
                } else {
                    Element<Monomial> ptr = reduces;
                    // 简单的插入排序
                    while (true) {
                        if (m.d > ptr.data.d) {
                            // 下移
                            if (ptr.next != null) {
                                ptr = ptr.next;
                            } else {
                                // 尾部插入
                                ptr.next = new Element<>(new Monomial(m.c, m.d), null);
                                break;
                            }
                        } else if (m.d < ptr.data.d) {
                            // 节点前插入
                            ptr.next = new Element<>(ptr.data, ptr.next);
                            ptr.data = new Monomial(ptr.data.c, ptr.data.d);
                            break;
                        } else {
                            // 合并
                            ptr.data.c += m.c;
                            break;
                        }
                    }
                }
            }
            List<Monomial> result = new LinkedList<>();
            Element<Monomial> ptr = reduces;
            while (ptr!=null){
                result.add(ptr.data);
                ptr = ptr.next;
            }
            return result;
        }

        public static class Element<T> {
            T data;
            Element<T> next;

            Element(T data, Element<T> next) {
                this.data = data;
                this.next = next;
            }
        }
    }

}
