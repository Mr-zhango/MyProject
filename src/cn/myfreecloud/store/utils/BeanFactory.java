package cn.myfreecloud.store.utils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {
	private static Document doc;
	//因为当dao层的方法写完之后会在配置文件里面进行修改,所以这里直接
	static{
		SAXReader saxReader = new SAXReader();
		try {
			doc = saxReader.read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//只要给我传一个接口的class 我就给你返回该接口的具体实现类对象
	@SuppressWarnings("unchecked")
	public static <T>T getInstance(Class<T> clazz){
		//获取到接口的名字
		String interfaceName = clazz.getSimpleName();
		//根据你接口的名字 去beans.xml寻找对应bean标签
		
		Element beanEle=(Element) doc.selectSingleNode("//bean[@name='"+interfaceName+"']");
		//获取到bean标签 能不能获取的该标签的 className属性
		//获取到啥了?
		//就是你配置的具体的实现类全限定名
		String className = beanEle.attribute("className").getValue();
		//能不能通过反射 创建一个具体实现类对象 返回
		try {
			Class<?> forName = Class.forName(className);
			Object newInstance = forName.newInstance();
			return (T) newInstance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
