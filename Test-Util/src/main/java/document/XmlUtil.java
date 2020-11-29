package document;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Iterator;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 9:25
 * @Version 1.0
 * @Description https://dom4j.github.io/
 */
@Slf4j
public class XmlUtil {
    @SneakyThrows
    public static void main(String[] args) {
        String raw = "<note>\n" +
                "  <to>Tove</to>\n" +
                "  <from>Jani</from>\n" +
                "  <heading>Reminder</heading>\n" +
                "  <body>Don't forget me this weekend!</body>\n" +
                "</note>";
        Document res = parse(raw);
        iterator(res);
        navigate(res);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    }

    @SneakyThrows
    public static Document parse(String input) {
        return DocumentHelper.parseText(input);
    }

    @SneakyThrows
    public static void iterator(Document document) {

        Element root = document.getRootElement();

        // iterate through child elements of root
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            log.info(element.getText());
        }

        // iterate through child elements of root with element name "foo"
        for (Iterator<Element> it = root.elementIterator("note"); it.hasNext(); ) {
            Element foo = it.next();
            log.info("name is {} value is {}", foo.getName(), foo.getText());
        }

        // iterate through attributes of root
        for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext(); ) {
            Attribute attribute = it.next();
            // do something
        }
    }

    public static void navigate(Document document) {
        Node node = document.selectSingleNode("//note/to");
        log.info("name is {} value is {}", node.getName(), node.getText());
    }

}
