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
package net.fckeditor;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * @version $Id$
 */
public class FCKeditorTest {
	
	@Test(expected = NullPointerException.class)
	public void testNullRequest() {
		new FCKeditor(null, "name");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyInstanceName() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		new FCKeditor(request, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyInstanceName02() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		new FCKeditor(request, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalInstanceName() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		new FCKeditor(request, "my instance");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalInstanceName02() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		new FCKeditor(request, "01_instance");
	}
	
	@Test
	public void testValidInstanceName() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		new FCKeditor(request, "myInstance");
	}

}