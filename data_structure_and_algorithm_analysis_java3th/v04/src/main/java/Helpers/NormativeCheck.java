package Helpers;

import java.lang.annotation.*;

/**
 * 规范性
 * 结构性+可读性+可扩展性
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface NormativeCheck {
    boolean structural();
    boolean readability();
    boolean scalability();
}
