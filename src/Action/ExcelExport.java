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
public @interface ExcelExport {

    /** �ֶ����� */
    String value();

    /** ���������Ⱥ�: ����ԽСԽ��ǰ��Ĭ�ϰ�Java���ֶ�˳�򵼳��� */
    int sort() default 0;

    /** ����ӳ�䣬��ʽ�磺0-δ֪;1-��;2-Ů */
    String kv() default "";

    /** ����ģ��ʾ��ֵ����ֵ�Ļ���ֱ��ȡ��ֵ������ӳ�䣩 */
    String example() default "";

}