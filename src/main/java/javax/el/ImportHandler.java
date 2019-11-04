package javax.el;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Feng
 * @date 2019/11/04
 */
public class ImportHandler {

    // 一个标准的包，键是 String，值是 Set
    private static final Map<String, Set<String>> standardPackage = new HashMap<>();

    static {
        Set<String> servletClassNames = new HashSet<>();

    }
}
