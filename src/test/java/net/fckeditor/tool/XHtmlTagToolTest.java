/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2004-2010 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package net.fckeditor.tool;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link XHtmlTagTool}.
 *
 * @version $Id: XHtmlTagToolTest.java 4785 2009-12-21 20:10:28Z mosipov $
 */
public class XHtmlTagToolTest {

	@Test
	public void testClosingTag01() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test");
	    assertEquals("<test />", tag.toString());
    }
	
	@Test
	public void testClosingTag02() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", "");
	    assertEquals("<test />", tag.toString());
    }

	@Test
	public void testClosingTag03() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", "val");
	    assertEquals("<test>val</test>", tag.toString());
    }
	
	@Test
	public void testClosingTag04() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", XHtmlTagTool.SPACE);
	    assertEquals("<test> </test>", tag.toString());
    }
}
