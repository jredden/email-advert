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

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for {@link Command}.
 * 
 * @version $Id: CommandTest.java 4785 2009-12-21 20:10:28Z mosipov $
 */
public class CommandTest {

	@Test
	public void valueOf01() {
		assertEquals(Command.FILE_UPLOAD, Command
				.valueOf("FileUpload"));
	}

	@Test
	public void valueOf02() {
		assertEquals(Command.QUICK_UPLOAD, Command
				.valueOf("QuickUpload"));
	}

	@Test
	public void valueOf03() {
		assertEquals(Command.CREATE_FOLDER, Command
				.valueOf("CreateFolder"));
	}

	@Test
	public void valueOf04() {
		assertEquals(Command.GET_FOLDERS, Command
				.valueOf("GetFolders"));
	}

	@Test
	public void valueOf05() {
		assertEquals(Command.GET_FOLDERS_AND_FILES, Command
				.valueOf("GetFoldersAndFiles"));
	}

	@Test(expected = NullPointerException.class)
	public void valueOfStringNull() {
		Command.valueOf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valueOfInvalidCommand() {
		Command.valueOf("GetAll");
	}

	@Test
	public void getCommandNull() {
		Command command = Command.getCommand(null);
		assertNull(command);
	}

	@Test
	public void getCommandInvalid() {
		Command command = Command.getCommand("DeleteFolders");
		assertNull(command);
	}

	@Test
	public void getCommandValid() {
		Command actual = Command.getCommand("FileUpload");
		assertEquals(Command.FILE_UPLOAD, actual);
	}

	@Test
	public void equalsNot01() {
		assertFalse(Command.GET_FOLDERS
				.equals(Command.FILE_UPLOAD));
	}

	@Test
	public void equalsNot02() {
		assertFalse(Command.GET_FOLDERS.equals(new Object()));
	}

	@Test
	public void hashCode01() {
		assertEquals("GetFoldersAndFiles".hashCode(),
				Command.GET_FOLDERS_AND_FILES.hashCode());
	}

	@Test
	public void hashCode02() {
		assertNotSame(-1, Command.FILE_UPLOAD.hashCode());
	}

	@Test
	public void toString01() {
		assertEquals("GetFoldersAndFiles", Command.GET_FOLDERS_AND_FILES
				.toString());
	}

}
