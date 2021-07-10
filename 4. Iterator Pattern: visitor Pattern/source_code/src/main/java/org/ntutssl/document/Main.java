package org.ntutssl.document;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Editor editor = new Editor();
		InstructionHandler ih = new InstructionHandler(editor);
		ih.run();

	}
}
