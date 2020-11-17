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
        // 先计算多项式乘法，然后排序合并同类项
        public static List<Monomial> multiplyA(List<Monomial> list1, List<Monomial> list2) {
            List<Monomial> tmp = list1.stream()
                    .flatMap(x -> list2.stream().map(y -> Monomial.multiply(x, y)))
                    .collect(toList());
            return reduce(tmp);
        }

        private static List<Monomial> reduce(List<Monomial> monomials) {
            Element<Monomial> reduces = null;
            for (Monomial m : monomials) {
                if (reduces == null) {
                    reduces = new Element<>(m, null);
                } else {
                    insertSort(m, reduces);
                }
            }
            return elements2List(reduces);

        }
        // 计算多项式乘法的同时合并同类项
        public static List<Monomial> multiplyB(List<Monomial> list1, List<Monomial> list2) {
            Element<Monomial> reduces = new Element<>(null, null);
            list1.forEach(
                    x -> list2.forEach(y ->
                            {
                                Monomial m = Monomial.multiply(x, y);
                                if (reduces.data == null) {
                                    reduces.data = m;
                                }else {
                                    insertSort(m,reduces);
                                }
                            }
                    )
            );
            return elements2List(reduces);
        }

        private static void insertSort(Monomial m, Element<Monomial> ptr) {
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
                    ptr.data = new Monomial(m.c, m.d);
                    break;
                } else {
                    // 合并
                    ptr.data.c += m.c;
                    break;
                }
            }

        }

        private static List<Monomial> elements2List (Element<Monomial> root){
            // 数据拷贝
            List<Monomial> result = new LinkedList<>();
            Element<Monomial> ptr = root;
            while (ptr != null) {
                result.add(ptr.data);
                ptr = ptr.next;
            }
            return result;
        }


        private static class Element<T> {
            T data;
            Element<T> next;

            Element(T data, Element<T> next) {
                this.data = data;
                this.next = next;
            }
        }

    }

}
