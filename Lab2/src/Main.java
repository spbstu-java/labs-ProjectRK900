import java.lang.annotation.*;
import java.lang.reflect.*;


public class Main {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Repeat {
        int value();
    }

    static final class AnnotationTestClass {
        @Repeat(-1)
        public void publicMethod1(String msg) {
            System.out.println("publicMethod1: " + msg);
        }

        @Repeat(0)
        public int publicMethod2(int a, int b) {
            var res = a + b;
            System.out.println("publicMethod2: " + res);
            return res;
        }

        @Repeat(3)
        protected void protectedMethod1() {
            System.out.println("protectedMethod1 has no parameters");
        }

        @Repeat(2)
        protected String protectedMethod2(String place) {
            var result = "protectedMethod2: place - " + place;
            System.out.println(result);
            return result;
        }

        @Repeat(4)
        private void privateMethod1(int x) {
            System.out.println("privateMethod1: integer number = " + x);
        }

        private void privateMethod2() {
            System.out.println("privateMethod2 has no annotation");
        }
    }

    private static Object[] getDefaultArgs(Class<?>[] paramTypes) {
        var args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i].isPrimitive())
                args[i] = 0;
            else
                args[i] = null;
        }

        return args;
    }

    public static void main(String[] args) {
        var obj = new AnnotationTestClass();

        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Repeat.class)) {
                method.setAccessible(true);
                var argsForMethod = getDefaultArgs(method.getParameterTypes());

                int times = method.getAnnotation(Repeat.class).value();
                try {
                    for (int i = 0; i < times; i++)
                        method.invoke(obj, argsForMethod);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}