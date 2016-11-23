package com.example.service.toilet;

import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.enums.tags.ToiletAttr;
import com.example.utils.NodeUtil;

@Component
public class ToiletServiceImpl implements ToiletService {

	@Override
	public String createContent(ToiletAttr... tags) {
		try {
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docbuilder = dbfactory.newDocumentBuilder(); // DocumentBuilderインスタンス
	        Document doc = docbuilder.newDocument();          // Documentの生成
	        Element row = doc.createElement("div");
	        Arrays.stream(tags).map(tag -> tag.getNode(doc)).forEach(el -> row.appendChild(el));
	        return NodeUtil.convert(row);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e2) {
			e2.printStackTrace();
		}
		return StringUtils.EMPTY;
	}

}
