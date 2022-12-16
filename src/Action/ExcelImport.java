package Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunnyzyq
 * @date 2021/12/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImport {

    /** �ֶ����� */
    String value();

    /** ����ӳ�䣬��ʽ�磺0-δ֪;1-��;2-Ů */
    String kv() default "";

    /** �Ƿ�Ϊ�����ֶΣ�Ĭ��Ϊ�Ǳ�� */
    boolean required() default false;

    /** ��󳤶ȣ�Ĭ��255�� */
    int maxLength() default 255;

    /** ����Ψһ����֤������ֶ���ȡ������֤�� */
    boolean unique() default false;

}
