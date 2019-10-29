package org.apache.catalina.startup;

import org.apache.catalina.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 * @author Feng
 */
public class BootStrap {

    /**
     * Slf4j 日志门面类，底层使用桥接器连接 Log4j2
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BootStrap.class);

    /**
     * 当前类的实例
     */
    private static final BootStrap INSTANCE = null;

    /**
     * base 类对象
     */
    private static final File CATALINA_BASE_FILE;

    /**
     * home 类对象
     */
    private static final File CATALINA_HOME_FILE;

    /**
     * 路径正则表达式
     */
    private static final Pattern PATH_PATTERN = Pattern.compile("(\".*?\")|(([^,])*)");

    /**
     * Catalina 的类实例
     */
    private Object catalinaInstance = null;

    /**
     * 公用类加载器，加载所有项目的使用 JAR 文件
     */
    private ClassLoader commonLoader = null;

    /**
     * 加载 Catalina 类的加载器，也是线程上下文的加载器
     */
    private ClassLoader catalinaLoader = null;

    /**
     *  Catalina 类实例的字段
     */
    private ClassLoader sharedLoader = null;

    static {
        String homePath = System.getProperty(Globals.CATALINA_HOME_PROP);
        String userDir = System.getProperty("user.dir");
        File f = null;
        try {
            File file = new File(userDir, homePath);
            f = file.getCanonicalFile();
        } catch (IOException e) {
            // ignore exception
        }
        CATALINA_HOME_FILE = f;
        CATALINA_BASE_FILE = f;
        if (null != CATALINA_HOME_FILE) {
            System.setProperty(Globals.CATALINA_HOME_PROP, CATALINA_HOME_FILE.getPath());
        }
        if (null != CATALINA_BASE_FILE) {
            System.setProperty(Globals.CATALINA_BASE_PROP,  CATALINA_BASE_FILE.getPath());
        }
    }

    private void initClassLoaders() {

    }

    /**
     * 创建类加载器
     * @param name 类加载器的名称
     * @param parent 父类加载器
     * @return 加载的类加载器
     *
     * 如何找到类加载器的名称？通过 CatalinaProperties 加载好了 home/conf/catalina.properties 文件
     * 里面有三个类加载器的键值对配置，键是加载器的名称，值是加载路径
     *
     * common.loader = "${catalina.base}/lib", "${catalina.base}/lib/*.jar", "${catalina.home}/lib", "${catalina/lib/*.jar}"
     * server.loader =
     * shared.loader =
     *
     * @throws Exception 异常
     */
    private ClassLoader createClassLoader(String name, ClassLoader parent) throws Exception {
        String value = CatalinaProperties.getProperty(name + ".loader");
        if (null == value || "".equals(value)) {
            return parent;
        }
        value = replace(value);
        List<ClassLoaderFactory.Repository> repositoryList = new ArrayList<>();
        String[] repositoryPaths = getPaths(value);
        for (String repository : repositoryPaths) {
            try {
                URL url = new URL(repository);
                repositoryList.add(new ClassLoaderFactory.Repository(repository, ClassLoaderFactory.RepositoryType.URL));
                continue;
            } catch (MalformedURLException e) {
                LOGGER.warn(e.toString());
            }
            if (repository.endsWith("*.jar")) {
                repository = repository.substring
                        (0, repository.length() - "*.jar".length());
                repositoryList.add(
                        new ClassLoaderFactory.Repository(repository, ClassLoaderFactory.RepositoryType.GLOB));
            } else if (repository.endsWith(".jar")) {
                repositoryList.add(
                        new ClassLoaderFactory.Repository(repository, ClassLoaderFactory.RepositoryType.JAR));
            } else {
                repositoryList.add(
                        new ClassLoaderFactory.Repository(repository, ClassLoaderFactory.RepositoryType.DIR));
            }
        }
        return null;
    }

    /**
     * 该函数将一个字符串转化为一个字符串数组
     * @param value 准备好的字符串
     * @return 字符串数组
     */
    protected static String[] getPaths(String value) {
        List<String> result = new ArrayList<>();
        Matcher matcher = PATH_PATTERN.matcher(value);
        while (matcher.find()) {
            String path = value.substring(matcher.start(), matcher.end());
            path = path.trim();
            if (path.length() == 0) {
                continue;
            }
            char first = path.charAt(0);
            char last = path.charAt(path.length() - 1);
            if (first == '"' && last == '"' && path.length() > 1) {
                path = path.substring(1, path.length() - 1);
                path = path.trim();
                if (path.length() == 0) {
                    continue;
                }
            } else if (path.contains("\"")) {
                throw new IllegalArgumentException(
                        "The double quote [\"] character only be used to quote paths. It must " +
                                "not appear in a path. This loader path is not valid: [" + value + "]");
            }
            result.add(path);
        }
        return result.toArray(new String[0]);
    }

    /**
     * 处理 home/conf/catalina.properties 中的类加载器的加载路径
     * 该方法来自 ClassLoaderLogManager.replace()
     * 但为 catalina.home 和 catalina.base 添加了特殊的处理过程
     * 该方法就是将 catalina.base 转化为 home/lib
     * @param str "${catalina.base}/lib","${catalina.base}/lib/*.jar","${catalina.home}/lib","${catalina.home}/lib/*.jar"
     * @return 加载完毕的字符串
     */
    protected String replace(String str) {
        // 地址引用
        String result = str;
        // 肯定是包含的，pos_start = 1，后面会跟着迭代
        int posStart = str.indexOf("${");
        if (posStart >= 0) {
            StringBuilder builder = new StringBuilder();
            int posEnd = -1;
            while (posStart >= 0) {
                builder.append(str, posEnd + 1, posStart);
                posEnd = str.indexOf('}', posStart + 2);
                if (posEnd < 0) {
                    posEnd = posStart - 1;
                    break;
                }
                String propName = str.substring(posStart + 2, posEnd);
                String replacement;
                if (propName.length() == 0) {
                    replacement = null;
                } else if (Globals.CATALINA_HOME_PROP.equals(propName)) {
                    replacement = getCatalinaHome();
                } else if (Globals.CATALINA_BASE_PROP.equals(propName)) {
                    replacement = getCatalinaBase();
                } else {
                    replacement = System.getProperty(propName);
                }
                if (replacement != null) {
                    builder.append(replacement);
                } else {
                    builder.append(str, posStart, posEnd + 1);
                }
                posStart = str.indexOf("${", posEnd + 1);
            }
            builder.append(str, posEnd + 1, str.length());
            result = builder.toString();
        }
        return result;
    }

    public static String getCatalinaHome() {
        return CATALINA_HOME_FILE.getPath();
    }
    
    public static String getCatalinaBase() {
        return CATALINA_BASE_FILE.getPath();
    }


    public static void main( String[] args ) throws IOException {
        String str = "\"${catalina.base}/lib\",\"${catalina.base}/lib/*.jar\",\"${catalina.home}/lib\",\"${catalina.home}/lib/*.jar\"";
        BootStrap bootStrap = new BootStrap();
        String value = bootStrap.replace(str);
        String[] array = BootStrap.getPaths(value);
        System.out.println(bootStrap.replace(str));
        for (String s1 : array) {
            System.err.println(s1);
        }
    }
}
