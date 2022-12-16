package Action;


import java.util.LinkedHashMap;

/**
 * @author sunnyzyq
 * @date 2021/12/17
 */
public class ExcelClassField {

    /** �ֶ����� */
    private String fieldName;

    /** ��ͷ���� */
    private String name;

    /** ӳ���ϵ */
    private LinkedHashMap<String, String> kvMap;

    /** ʾ��ֵ */
    private Object example;

    /** ���� */
    private int sort;

    /** �Ƿ�Ϊע���ֶΣ�0-��1-�� */
    private int hasAnnotation;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashMap<String, String> getKvMap() {
        return kvMap;
    }

    public void setKvMap(LinkedHashMap<String, String> kvMap) {
        this.kvMap = kvMap;
    }

    public Object getExample() {
        return example;
    }

    public void setExample(Object example) {
        this.example = example;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getHasAnnotation() {
        return hasAnnotation;
    }

    public void setHasAnnotation(int hasAnnotation) {
        this.hasAnnotation = hasAnnotation;
    }

}
