package reflection.tester;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    String result = "";


    public void runTests(List<String> testClassNames) {
        for (String testClassName : testClassNames){
            try {
                Class<?> aClass = Class.forName(testClassName);
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getAnnotation(MyTest.class) != null) {
                        Class<? extends Throwable> expected = method.getAnnotation(MyTest.class).expected();
                        try {
                            method.invoke(aClass.getDeclaredConstructor().newInstance());
                            if (method.invoke(aClass.getDeclaredConstructor().newInstance()) == null) {
                                if (expected.isAssignableFrom(MyTest.None.class)) {
                                    result += method.getName() + "() - OK, ";
                                } else {
                                    result += method.getName() + "() - FAILED, ";
                                }
                            }
                        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            Throwable realException = e.getCause();
                            if (expected.isAssignableFrom(realException.getClass())) {
                                result += method.getName() + "() - OK, ";
                            } else {
                                result += method.getName() + "() - FAILED, ";
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getResult() {
        return result;
    }

}
