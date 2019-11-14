package javax.el;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Feng
 * @date 2019/11/04
 */
public class ImportHandler {

    // 一个标准的包路径和名称集合映射类，键是 String，值是 Set
    private static final Map<String, Set<String>> standardPackages = new HashMap<>();

    // 该静态模块向 Map 中添加了 javax.servlet、javax.servlet.http、java.lang 三个包下的相关的类的名称
    // 添加到集合中
    static {
        Set<String> servletClassNames = new HashSet<>();
        // 将一些关于 Servlet 规范的接口的名称添加到这个集合中
        servletClassNames.add("AsyncContext");
        servletClassNames.add("AsyncListener");
        servletClassNames.add("Filter");
        servletClassNames.add("FilterChain");
        servletClassNames.add("FilterConfig");
        servletClassNames.add("FilterRegistration");
        servletClassNames.add("FilterRegistration.Dynamic");
        servletClassNames.add("ReadListener");
        servletClassNames.add("Registration");
        servletClassNames.add("Registration.Dynamic");
        servletClassNames.add("RequestDispatcher");
        servletClassNames.add("Servlet");
        servletClassNames.add("ServletConfig");
        servletClassNames.add("ServletContainerInitializer");
        servletClassNames.add("ServletContext");
        servletClassNames.add("ServletContextAttributeListener");
        servletClassNames.add("ServletContextListener");
        servletClassNames.add("ServletRegistration");
        servletClassNames.add("ServletRegistration.Dynamic");
        servletClassNames.add("ServletRequest");
        servletClassNames.add("ServletRequestAttributeListener");
        servletClassNames.add("ServletRequestListener");
        servletClassNames.add("ServletResponse");
        servletClassNames.add("SessionCookieConfig");
        servletClassNames.add("SingleThreadModel");
        servletClassNames.add("WriteListener");

        // 将 Servlet 规范相关的类名添加到集合中
        servletClassNames.add("AsyncEvent");
        servletClassNames.add("GenericFilter");
        servletClassNames.add("GenericServlet");
        servletClassNames.add("HttpConstraintElement");
        servletClassNames.add("HttpMethodConstraintElement");
        servletClassNames.add("MultipartConfigElement");
        servletClassNames.add("ServletContextAttributeEvent");
        servletClassNames.add("ServletContextEvent");
        servletClassNames.add("ServletInputStream");
        servletClassNames.add("ServletOutputStream");
        servletClassNames.add("ServletRequestAttributeEvent");
        servletClassNames.add("ServletRequestEvent");
        servletClassNames.add("ServletRequestWrapper");
        servletClassNames.add("ServletResponseWrapper");
        servletClassNames.add("ServletSecurityElement");

        // 将 Servlet 规范相关的枚举类添加到集合中
        servletClassNames.add("DispatcherType");
        servletClassNames.add("SessionTrackingMode");

        // 异常类名
        servletClassNames.add("ServletException");
        servletClassNames.add("UnavailableException");

        // 将 javax.servlet 包下的，有关 Servlet 的接口，类，枚举，异常的名称添加到该 Map 集合中
        standardPackages.put("javax.servlet", servletClassNames);

        // Servlet 4.0
        Set<String> servletHttpClassNames = new HashSet<>();
        // Interfaces
        servletHttpClassNames.add("HttpServletMapping");
        servletHttpClassNames.add("HttpServletRequest");
        servletHttpClassNames.add("HttpServletResponse");
        servletHttpClassNames.add("HttpSession");
        servletHttpClassNames.add("HttpSessionActivationListener");
        servletHttpClassNames.add("HttpSessionAttributeListener");
        servletHttpClassNames.add("HttpSessionBindingListener");
        servletHttpClassNames.add("HttpSessionContext");
        servletHttpClassNames.add("HttpSessionIdListener");
        servletHttpClassNames.add("HttpSessionListener");
        servletHttpClassNames.add("HttpUpgradeHandler");
        servletHttpClassNames.add("Part");
        servletHttpClassNames.add("PushBuilder");
        servletHttpClassNames.add("WebConnection");
        // Classes
        servletHttpClassNames.add("Cookie");
        servletHttpClassNames.add("HttpFilter");
        servletHttpClassNames.add("HttpServlet");
        servletHttpClassNames.add("HttpServletRequestWrapper");
        servletHttpClassNames.add("HttpServletResponseWrapper");
        servletHttpClassNames.add("HttpSessionBindingEvent");
        servletHttpClassNames.add("HttpSessionEvent");
        servletHttpClassNames.add("HttpUtils");
        // Enums
        servletHttpClassNames.add("MappingMatch");

        // 将 javax.servlet.http 包下的相关类的名字添加到 Map 集合中
        standardPackages.put("javax.servlet.http", servletHttpClassNames);

        Set<String> javaLangClassNames = new HashSet<>();
        // Taken from Java 11 EA18 Javadoc
        // Interfaces
        javaLangClassNames.add("Appendable");
        javaLangClassNames.add("AutoCloseable");
        javaLangClassNames.add("CharSequence");
        javaLangClassNames.add("Cloneable");
        javaLangClassNames.add("Comparable");
        javaLangClassNames.add("Iterable");
        javaLangClassNames.add("ProcessHandle");
        javaLangClassNames.add("ProcessHandle.Info");
        javaLangClassNames.add("Readable");
        javaLangClassNames.add("Runnable");
        javaLangClassNames.add("StackWalker.StackFrame");
        javaLangClassNames.add("System.Logger");
        javaLangClassNames.add("Thread.UncaughtExceptionHandler");
        //Classes
        javaLangClassNames.add("Boolean");
        javaLangClassNames.add("Byte");
        javaLangClassNames.add("Character");
        javaLangClassNames.add("Character.Subset");
        javaLangClassNames.add("Character.UnicodeBlock");
        javaLangClassNames.add("Class");
        javaLangClassNames.add("ClassLoader");
        javaLangClassNames.add("ClassValue");
        javaLangClassNames.add("Compiler");
        javaLangClassNames.add("Double");
        javaLangClassNames.add("Enum");
        javaLangClassNames.add("Enum.EnumDesc");
        javaLangClassNames.add("Float");
        javaLangClassNames.add("InheritableThreadLocal");
        javaLangClassNames.add("Integer");
        javaLangClassNames.add("Long");
        javaLangClassNames.add("Math");
        javaLangClassNames.add("Module");
        javaLangClassNames.add("ModuleLayer");
        javaLangClassNames.add("ModuleLayer.Controller");
        javaLangClassNames.add("Number");
        javaLangClassNames.add("Object");
        javaLangClassNames.add("Package");
        javaLangClassNames.add("Process");
        javaLangClassNames.add("ProcessBuilder");
        javaLangClassNames.add("ProcessBuilder.Redirect");
        javaLangClassNames.add("Runtime");
        javaLangClassNames.add("Runtime.Version");
        javaLangClassNames.add("RuntimePermission");
        javaLangClassNames.add("SecurityManager");
        javaLangClassNames.add("Short");
        javaLangClassNames.add("StackTraceElement");
        javaLangClassNames.add("StackWalker");
        javaLangClassNames.add("StrictMath");
        javaLangClassNames.add("String");
        javaLangClassNames.add("StringBuffer");
        javaLangClassNames.add("StringBuilder");
        javaLangClassNames.add("System");
        javaLangClassNames.add("System.LoggerFinder");
        javaLangClassNames.add("Thread");
        javaLangClassNames.add("ThreadGroup");
        javaLangClassNames.add("ThreadLocal");
        javaLangClassNames.add("Throwable");
        javaLangClassNames.add("Void");
        //Enums
        javaLangClassNames.add("Character.UnicodeScript");
        javaLangClassNames.add("ProcessBuilder.Redirect.Type");
        javaLangClassNames.add("StackWalker.Option");
        javaLangClassNames.add("System.Logger.Level");
        javaLangClassNames.add("Thread.State");
        //Exceptions
        javaLangClassNames.add("ArithmeticException");
        javaLangClassNames.add("ArrayIndexOutOfBoundsException");
        javaLangClassNames.add("ArrayStoreException");
        javaLangClassNames.add("ClassCastException");
        javaLangClassNames.add("ClassNotFoundException");
        javaLangClassNames.add("CloneNotSupportedException");
        javaLangClassNames.add("EnumConstantNotPresentException");
        javaLangClassNames.add("Exception");
        javaLangClassNames.add("IllegalAccessException");
        javaLangClassNames.add("IllegalArgumentException");
        javaLangClassNames.add("IllegalCallerException");
        javaLangClassNames.add("IllegalMonitorStateException");
        javaLangClassNames.add("IllegalStateException");
        javaLangClassNames.add("IllegalThreadStateException");
        javaLangClassNames.add("IndexOutOfBoundsException");
        javaLangClassNames.add("InstantiationException");
        javaLangClassNames.add("InterruptedException");
        javaLangClassNames.add("LayerInstantiationException");
        javaLangClassNames.add("NegativeArraySizeException");
        javaLangClassNames.add("NoSuchFieldException");
        javaLangClassNames.add("NoSuchMethodException");
        javaLangClassNames.add("NullPointerException");
        javaLangClassNames.add("NumberFormatException");
        javaLangClassNames.add("ReflectiveOperationException");
        javaLangClassNames.add("RuntimeException");
        javaLangClassNames.add("SecurityException");
        javaLangClassNames.add("StringIndexOutOfBoundsException");
        javaLangClassNames.add("TypeNotPresentException");
        javaLangClassNames.add("UnsupportedOperationException");
        //Errors
        javaLangClassNames.add("AbstractMethodError");
        javaLangClassNames.add("AssertionError");
        javaLangClassNames.add("BootstrapMethodError");
        javaLangClassNames.add("ClassCircularityError");
        javaLangClassNames.add("ClassFormatError");
        javaLangClassNames.add("Error");
        javaLangClassNames.add("ExceptionInInitializerError");
        javaLangClassNames.add("IllegalAccessError");
        javaLangClassNames.add("IncompatibleClassChangeError");
        javaLangClassNames.add("InstantiationError");
        javaLangClassNames.add("InternalError");
        javaLangClassNames.add("LinkageError");
        javaLangClassNames.add("NoClassDefFoundError");
        javaLangClassNames.add("NoSuchFieldError");
        javaLangClassNames.add("NoSuchMethodError");
        javaLangClassNames.add("OutOfMemoryError");
        javaLangClassNames.add("StackOverflowError");
        javaLangClassNames.add("ThreadDeath");
        javaLangClassNames.add("UnknownError");
        javaLangClassNames.add("UnsatisfiedLinkError");
        javaLangClassNames.add("UnsupportedClassVersionError");
        javaLangClassNames.add("VerifyError");
        javaLangClassNames.add("VirtualMachineError");
        //Annotation Types
        javaLangClassNames.add("Deprecated");
        javaLangClassNames.add("FunctionalInterface");
        javaLangClassNames.add("Override");
        javaLangClassNames.add("SafeVarargs");
        javaLangClassNames.add("SuppressWarnings");

        // 将 java.lang 包下的相关类的名称添加到 Map 中，该类来自 java 11
        standardPackages.put("java.lang", javaLangClassNames);
    }

    private Map<String, Set<String>> packageNames = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> classNames = new ConcurrentHashMap<>();
    private Map<String, Class<?>> clazzes = new ConcurrentHashMap<>();
    private Map<String, Class<?>> statics = new ConcurrentHashMap<>();

    /**
     * 构造函数，先将 java.lang 下的类的集合，添加到 packageNames
     */
    public ImportHandler() {
        importPackage("java.lang");
    }

    /**
     * @word period 周期
     * @param name
     * @throws javax.el.ELException
     */
    public void importStatic(String name) throws javax.el.ELException {
        int lastPeriod = name.lastIndexOf('.');
        if (lastPeriod < 0) {
            throw new ELException();
        }
    }

    /**
     * 将 standardPackages 中的键为 name 的值的集合，添加到 packageNames 中
     * @param name 包的路径
     */
    public void importPackage(String name) {
        Set<String> preLoaded = standardPackages.get(name);
        if (null == preLoaded) {
            preLoaded = Collections.emptySet();
        }
        packageNames.put(name, preLoaded);
    }


}
