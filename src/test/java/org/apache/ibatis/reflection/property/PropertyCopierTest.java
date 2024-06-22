package org.apache.ibatis.reflection.property;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyCopierTest {

  @Test
  void assertBasicOperations() {
    TestBean t1 = new TestBean("test", 100, Arrays.asList("ele1", Arrays.asList("ele2", "ele3"), new TestBean("100", null, null)));
    TestBean t2 = new TestBean();
    PropertyCopier.copyBeanProperties(TestBean.class, t1, t2);
    assertTrue(t2.strField.equalsIgnoreCase("test"));
    assertEquals(100, (int) t2.intField);
    assertEquals("ele1", t2.listField.get(0));
    assertEquals("ele2", ((List) t2.listField.get(1)).get(0));
    assertEquals("ele3", ((List) t2.listField.get(1)).get(1));
    assertEquals("100", ((TestBean) t2.listField.get(2)).strField);
    // 这里说明是浅拷贝
    assertEquals(t1.listField.hashCode(), t2.listField.hashCode());
  }


  static final class TestBean {
    String strField;
    Integer intField;
    List<Object> listField;

    public TestBean(String strField, Integer intField, List<Object> listField) {
      this.strField = strField;
      this.intField = intField;
      this.listField = listField;
    }

    public TestBean() {
    }
  }

}
