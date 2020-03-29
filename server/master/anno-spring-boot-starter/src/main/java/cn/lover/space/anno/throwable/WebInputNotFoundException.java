package cn.lover.space.anno.throwable;

/**
 * date: 2020-03-29
 * time: 16:31
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class WebInputNotFoundException extends RuntimeException {

    public WebInputNotFoundException(String msg) {
        super(msg);
    }
}
