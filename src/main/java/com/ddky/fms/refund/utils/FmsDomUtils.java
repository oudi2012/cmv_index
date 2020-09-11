package com.ddky.fms.refund.utils;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * @author a
 */
public class FmsDomUtils {

    private static Logger logger = LoggerFactory.getLogger(FmsDomUtils.class);

    public static Document getDocument(String xmlPath) {
        SAXReader saxReader = new SAXReader();
        try {
            return saxReader.read(xmlPath);
        } catch (DocumentException io) {
            logger.info(io.getMessage());
            return null;
        }
    }

    public static List listElement(Document document, String xpathStr) {
        Element elementRoot = document.getRootElement();
        XPath xPath = elementRoot.createXPath(xpathStr);
        return xPath.selectNodes(elementRoot, xPath);
    }
}
