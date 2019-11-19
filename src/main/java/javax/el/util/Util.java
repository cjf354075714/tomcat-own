package javax.el.util;

/**
 * @auther Feng
 * @date 2019/11/19
 **/
public class Util {
    /**
     * 处理线程死亡和虚拟机错误的方法
     * 其他的就自动忽略
     * @param t 抛出的异常
     */
    public static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
    }
}
