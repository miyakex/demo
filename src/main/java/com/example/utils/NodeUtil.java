package com.example.utils;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NodeUtil {

	public static String convert(Node node) throws TransformerException {
		DOMSource source = new DOMSource(node);
		StringWriter swriter = new StringWriter();
		StreamResult result = new StreamResult(swriter);
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(source, result);
		return swriter.toString();
	}
}
