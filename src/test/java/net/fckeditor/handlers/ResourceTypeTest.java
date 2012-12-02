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
package net.fckeditor.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests for {@link ResourceTypeHandler}.
 * 
 * @version $Id: ResourceTypeTest.java 1585 2008-02-21 18:13:09Z th-schwarz $
 */
public class ResourceTypeTest {

	@Test
	public void getType01() throws Exception {
		assertNull(ResourceType.getResourceType("xyz"));
	}

	@Test
	public void getType02() throws Exception {
		assertEquals(ResourceType.FILE, ResourceType.getResourceType("File"));
	}

	@Test
	public void getType03() throws Exception {
		assertNotSame(ResourceType.IMAGE, ResourceType.getResourceType("ImaGe"));
	}
	
	@Test
	public void getType04() throws Exception {
		assertNull(ResourceType.getResourceType(null));
	}
	
	@Test
	public void getType05() throws Exception {
		assertNull(ResourceType.getResourceType(""));
	}
	
	@Test
	public void isValid01() throws Exception {
		assertFalse(ResourceType.isValidType("1234"));
	}

	@Test
	public void isValid02() throws Exception {
		assertFalse(ResourceType.isValidType("fLash"));
	}

	@Test
	public void isValid03() throws Exception {
		assertFalse(ResourceType.isValidType("MeDiA"));
	}

	@Test
	public void getDefaultType01() throws Exception {
		assertEquals(ResourceType.FILE, ResourceType
				.getDefaultResourceType("wrong-type"));
	}

	@Test
	public void getDefaultType02() throws Exception {
		assertNotSame(ResourceType.FLASH, ResourceType
				.getDefaultResourceType("flAsh"));
	}
	
	@Test
	public void getDefaultType03() throws Exception {
		assertEquals(ResourceType.FILE, ResourceType
				.getDefaultResourceType(null));
	}
	
	@Test
	public void getDefaultType04() throws Exception {
		assertEquals(ResourceType.FILE, ResourceType
				.getDefaultResourceType(""));
	}
	
	@Test
	public void getDefaultType05() throws Exception {
		assertNotSame(ResourceType.FILE, ResourceType
				.getDefaultResourceType("Image"));
	}
}
