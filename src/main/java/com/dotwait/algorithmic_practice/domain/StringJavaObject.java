package com.dotwait.algorithmic_practice.domain;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class StringJavaObject extends SimpleJavaFileObject {
    /**
     * 源代码
     */
    private String content = "";

    /**
     * 遵循Java规范的类名和文件
     * @param javaFileName
     * @param content
     */
    public StringJavaObject(String javaFileName, String content) {
        super(createStringJavaObjectUri(javaFileName), Kind.SOURCE);
        this.content = content;
    }

    /**
     * 产生一个URL资源路径
     * @param javaFileName
     * @return
     */
    private static URI createStringJavaObjectUri(String javaFileName){
        return URI.create("String:///"+javaFileName+Kind.SOURCE.extension);
    }

    /**
     * 文本文件代码
     * @param ignoreEncodingErrors
     * @return
     * @throws IOException
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return content;
    }
}
