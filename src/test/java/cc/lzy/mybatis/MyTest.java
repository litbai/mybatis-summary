/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import org.junit.Test;

/**
 * @author taigai
 * @version MyTest.java, v 0.1 2021年01月15日 15:47 taigai Exp $
 */
public class MyTest {

    private void testVoid() {

    }

    private Integer testInteger() {
        return 1;
    }

    private int testInt() {
        return 1;
    }

    @Test
    public void testMethod() throws Exception{
        Class<?> testInt = MyTest.class.getDeclaredMethod("testInt").getReturnType();
        System.out.println(testInt.isPrimitive());

        Class<?> testInteger = MyTest.class.getDeclaredMethod("testInteger").getReturnType();
        System.out.println(testInteger);
        System.out.println(Integer.TYPE.equals(testInt));
        System.out.println(int.class.equals(testInt));
        System.out.println(Integer.class.equals(testInteger));
    }
}