/*
 * Copyright (c) 2001, The RdbmsTableFromJdbcMetadataProvider Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * - Neither the name of The RdbmsTableFromJdbcMetadataProvider Group nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

/*
 * Change log
 *
 */
package org.codehaus.middlegen.util;

import junit.framework.TestCase;
import org.codehaus.middlegen.swing.BooleanNodeCheckBox;

import javax.swing.*;

/**
 * @author Aslak Helles�y
 */
public class BooleanNodeTest extends TestCase {
	public BooleanNodeTest(String name) {
		super(name);
	}

	public void testIt() {
		BooleanNode root = BooleanNode.createRoot(false);
		assertEquals(false, root.isPartiallyTrue());
		assertEquals(false, root.isCompletelyTrue());

		root = BooleanNode.createRoot(true);
		assertEquals(true, root.isPartiallyTrue());
		assertEquals(true, root.isCompletelyTrue());
		BooleanNode c1 = root.createChild(false);
		assertEquals(false, root.isPartiallyTrue());
		assertEquals(false, root.isCompletelyTrue());
		BooleanNode c2 = root.createChild(true);
		assertEquals(true, root.isPartiallyTrue());
		assertEquals(false, root.isCompletelyTrue());

		root.setValue(true);
		assertEquals(true, c1.isCompletelyTrue());

		c2.setValue(false);
		assertEquals(true, root.isPartiallyTrue());

		JFrame f = new JFrame();
		f.getContentPane().setLayout(new java.awt.FlowLayout());
		f.getContentPane().add(new BooleanNodeCheckBox("Master", root));
		f.getContentPane().add(new BooleanNodeCheckBox("Detail 1", c1));
		f.getContentPane().add(new BooleanNodeCheckBox("Detail 2", c2));
		f.pack();
		f.setVisible(true);
	}
}
