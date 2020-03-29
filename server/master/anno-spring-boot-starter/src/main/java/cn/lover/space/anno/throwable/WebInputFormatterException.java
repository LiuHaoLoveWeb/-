package cn.lover.space.anno.throwable;

/**
 * date: 2020-03-29
 * time: 16:32
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class WebInputFormatterException extends RuntimeException {

    public WebInputFormatterException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
