package com.example.enums.tags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.example.enums.Color;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ToiletAttr {
	SHOWER("ウォシュレット", Color.Blue),
	SOUND("音姫", Color.Gold),
	BARRIER_FREE("バリアフリー", Color.Gray),
	STILLAGE("荷物置き", Color.LawnGreen),
	BABY_SHEET("ベビーシート", Color.Magenta),
	;
	private final String caption;
	private final Color backgroudColor;
	
	public Node getNode(Document doc) {
		Element el = doc.createElement("tag");
		String styleVal = "background-color: " + this.backgroudColor.getRgp();
		el.setAttribute("style", styleVal);
		el.setTextContent(this.caption);
		return el;
	}
}
